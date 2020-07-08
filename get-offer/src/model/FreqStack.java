package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author xiakai
 * @create 2020-07-07 15:21
 */
public class FreqStack {

    Map<Integer, Integer> cache;
    Map<Integer, Stack<Integer>> freqStackMap;
    int maxFreq;

    public FreqStack() {
        cache = new HashMap<>();
        freqStackMap = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int x) {
        int freq = cache.getOrDefault(x, 0) + 1;
        cache.put(x, freq);
        if (maxFreq < freq) {
            maxFreq = freq;
        }
        Stack<Integer> freqStack = freqStackMap.get(freq);
        if (freqStack == null) {
            freqStack = new Stack<>();
            freqStackMap.put(freq, freqStack);
        }
        freqStack.push(x);
    }

    public int pop() {
        Stack<Integer> stack = freqStackMap.get(maxFreq);
        int x = stack.pop();
        if (stack.isEmpty()) {
            maxFreq--;
        }
        cache.put(x, cache.get(x) - 1);
        return x;
    }

}
