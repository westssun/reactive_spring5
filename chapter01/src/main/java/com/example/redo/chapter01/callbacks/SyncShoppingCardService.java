package com.example.redo.chapter01.callbacks;

import com.example.redo.chapter01.commons.Input;
import com.example.redo.chapter01.commons.Output;

import java.util.function.Consumer;

public class SyncShoppingCardService implements ShoppingCardService {

    /**
     * Consumser (매개변수 O, 반환값 X)
     * 매개변수를 받아서 소비하는 일을 구현하는 역할
     * @param value
     * @param c
     */
    @Override
    public void calculate(Input value, Consumer<Output> c) {
        /* Input : Input.class, c : SyncShoppingCardService */
        c.accept(new Output());
    }
}
