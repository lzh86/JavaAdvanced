package com.kaka.tempoary;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

public class BidiMapDemo {
    public static void main(String[] args) {
        //extracted();
        test();
    }

    private static void test() {
        String s1 = "hello";
        String s2 = new String("hello");
        System.out.println(s1 == s2);           // false：s1指向常量池，s2指向堆
        System.out.println(s2.intern() == s1);  // true：s2.intern()返回常量池中的引用
    }

    private static void extracted() {
        BidiMap<String, String> bidi = new TreeBidiMap<>();
        bidi.put("One", "1");
        bidi.put("Two", "2");
        bidi.put("Three", "3");
        System.out.println(bidi.get("One"));
        System.out.println(bidi.getKey("1"));
        System.out.println("Original Map: " + bidi);
        bidi.removeValue("1");
        System.out.println("Modified Map: " + bidi);
        BidiMap<String, String> inversedMap = bidi.inverseBidiMap();
        System.out.println("Inversed Map: " + inversedMap);
    }
}
