package com.example.redo.chapter02.observer;

/**
 * Observer 구현체 A
 */
public class ConcreteObserverA implements Observer<String> {
    @Override
    public void observe(String event) {
        System.out.println("Observer A: " + event);
    }
}
