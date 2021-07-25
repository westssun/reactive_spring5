package com.example.redo.chapter02.observer;

/**
 * 관찰자
 * @param <T>
 */
public interface Observer<T> {
    void observe(T event); /* 이벤트 처리 */
}
