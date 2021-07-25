package com.example.redo.chapter02.pub_sub_app;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class TemperatureSensor {
    private final ApplicationEventPublisher publisher; /* 이벤트 발행 */
    private final Random rnd = new Random(); /* 난수로 온도 생성 */
    /* 이벤트 생성 프로세스는 ScheduledExecutorService 에서 발생 */
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public TemperatureSensor(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    /**
     * 빈이 생성될때, 스프링 프레임워크에 의해 호출된다.
     */
    @PostConstruct
    public void startProcessing() {
        this.executor.schedule(this::probe, 1, SECONDS);
    }

    /**
     * 모든 로직 정의
     */
    private void probe() {
        /* 랜덤 온도 생성 */
        double temperature = 16 + rnd.nextGaussian() * 10;

        /* 이벤트 등록 */
        publisher.publishEvent(new Temperature(temperature));

        /* 반복 호출 */
        executor.schedule(this::probe, rnd.nextInt(5000), MILLISECONDS);
    }
}
