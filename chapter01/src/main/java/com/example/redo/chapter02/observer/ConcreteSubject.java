package com.example.redo.chapter02.observer;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Subject 구현체
 */
public class ConcreteSubject implements Subject<String> {
    /**
     * CopyOnWriteArraySet : 스레드 안정성을 윶ㅣ하며, 업데이트 작업이 발생할 때마다 새 복사본을 생성하는 Set 구현체
     * 주의) 업데이트 하는데에 비용이 많이 들기 때문에, 자주 변경되지 않은 경우 사용 권장
     */
    private final Set<Observer<String>> observers = new CopyOnWriteArraySet<>();


    /**
     * observers 구독
     * @param observer
     */
    @Override
    public void registerObserver(Observer<String> observer) {
        observers.add(observer);
    }

    /**
     * observers 구독 취소
     * @param observer
     */
    @Override
    public void unregisterObserver(Observer<String> observer) {
        observers.remove(observer);
    }

    /**
     * 각 observer 에 대해 반복적으로 observe() 메서드를 호출
     * @param event
     */
    @Override
    public void notifyObservers(String event) {
        observers.forEach(observer -> observer.observe(event));
    }
}
