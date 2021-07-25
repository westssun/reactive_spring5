package com.example.redo.chapter01.callbacks;

import com.example.redo.chapter01.commons.Input;
import com.example.redo.chapter01.commons.Output;

import java.util.function.Consumer;

public class AsyncShoppingCardService implements ShoppingCardService {
    @Override
    public void calculate(Input value, Consumer<Output> c) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /* 콜백함수 호출 */
            c.accept(new Output());
        }).start();
    }
}
