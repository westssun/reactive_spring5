package com.example.redo.chapter01.callbacks;

import com.example.redo.chapter01.commons.Input;
import com.example.redo.chapter01.commons.Output;

import java.util.function.Consumer;

public interface ShoppingCardService {
    void calculate(Input value, Consumer<Output> c);
}
