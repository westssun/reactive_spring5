package com.example.redo.chapter02.observer;

/**
 * Observer 구현체 B
 */
public class ConcreteObserverB implements Observer<String> {
    @Override
    public void observe(String event) {
        System.out.println("Observer B: " + event);
    }
}
