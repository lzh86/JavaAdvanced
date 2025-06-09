package com.kaka.tempoary;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class UnaryOperatorDemo {
    public static void main(String[] args) {
        UnaryOperator<String> identity = UnaryOperator.identity();
        String result = identity.apply("test"); // 返回 "test"

        UnaryOperator<Integer> multiplyByTwo = n -> n * 2;
        UnaryOperator<Integer> addThree = n -> n + 3;

        Function<Integer, Integer> integerIntegerFunction = multiplyByTwo.andThen(addThree);
        System.out.println(integerIntegerFunction.apply(5)); // 输出 13 (5*2=10 → 10+3=13)




    }
}
