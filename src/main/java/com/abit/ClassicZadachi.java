package com.abit;

import java.util.HashMap;
import java.util.Map;

public class ClassicZadachi {

    public int fib2(int n) {
        if (n < 2) {
            return n;
        }
            /*System.out.println(fib2(n - 1) + fib2(n - 2));//3 2,1
                System.out.println(fib2(n - 1) + fib2(n - 2));//2 1,0 | 1 0,-1*/
        return fib2(n - 1) + fib2(n - 2);
    }

    static Map<Integer, Integer> memo = new HashMap<>(Map.of(0, 0, 1, 1));

    public int fib3(int n) {
        if (!memo.containsKey(n)) {
            // шаг мемоизации
            memo.put(n, fib3(n - 1) + fib3(n - 2));
        }
        return memo.get(n);
    }
}