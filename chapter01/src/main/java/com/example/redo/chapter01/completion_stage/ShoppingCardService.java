package com.example.redo.chapter01.completion_stage;

import com.example.redo.chapter01.commons.Input;
import com.example.redo.chapter01.commons.Output;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

public interface ShoppingCardService {
    CompletionStage<Output> calculate(Input value);
}
