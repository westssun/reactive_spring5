package com.example.redo.chapter01.futures;

import com.example.redo.chapter01.commons.Input;
import com.example.redo.chapter01.commons.Output;

import java.util.concurrent.Future;

public interface ShoppingCardService {
    Future<Output> calculate(Input value);
}
