package com.example.redo.chapter02.pub_sub_app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static java.lang.String.format;

@RestController
@Slf4j
public class TemperatureController {
    static final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L;
    /**
     * SseEmitter : 스프링 웹 MVC 에서는 SSE 이벤트를 보내는 목적으로 사용
     * complete() 메서드 호출되거나 오류 발생/시간 초과가 발생할때까지 실제 요청 처리가 계속 진행된다.
     */
    private final Set<SseEmitter> clients = new CopyOnWriteArraySet<>();

    @RequestMapping(value = "/temperature-stream", method = RequestMethod.GET)
    public SseEmitter events(HttpServletRequest request) {
        log.info("SSE stream opened for client: " + request.getRemoteAddr());
        SseEmitter emitter = new SseEmitter(SSE_SESSION_TIMEOUT);
        clients.add(emitter);

        // Remove SseEmitter from active clients on error or client disconnect
        // 처리가 끝나거나 timeout 에 도달하면
        emitter.onTimeout(() -> clients.remove(emitter));
        emitter.onCompletion(() -> clients.remove(emitter));

        return emitter;
    }

    @Async
    @EventListener
    public void handleMessage(Temperature temperature) {
        log.info(format("Temperature: %4.2f C, active subscribers: %d",
                temperature.getValue(), clients.size()));

        List<SseEmitter> deadEmitters = new ArrayList<>();
        clients.forEach(emitter -> {
            try {
                Instant start = Instant.now();
                emitter.send(temperature, MediaType.APPLICATION_JSON);
                log.info("Sent to client, took: {}", Duration.between(start, Instant.now()));
            } catch (Exception ignore) {
                deadEmitters.add(emitter);
            }
        });
        clients.removeAll(deadEmitters);
    }
}
