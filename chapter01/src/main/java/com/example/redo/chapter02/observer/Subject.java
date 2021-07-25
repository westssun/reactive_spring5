package com.example.redo.chapter02.observer;

/**
 * 주체 (구독 관리 메서드 포함)
 * @param <T>
 */
public interface Subject<T> {
    /* 등록 */
    void registerObserver(Observer<T> observer);

    /* 삭제 */
    void unregisterObserver(Observer<T> observer);

    /* 알림 */
    void notifyObservers(T event);
}
