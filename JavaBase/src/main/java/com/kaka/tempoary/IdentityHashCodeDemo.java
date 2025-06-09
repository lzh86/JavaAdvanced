package com.kaka.tempoary;

import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IdentityHashCodeDemo {
    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(20);
        student.setName("John");
        List<Student> list = new ArrayList<>();
        list.add(student);

        int identityHashCode = System.identityHashCode(list);
        System.out.println("before list identityHashCode:" + identityHashCode);
        System.out.println("before list hashCode:" + list.hashCode());

        List<Student> list1  = list.stream().filter(z -> z.getName().equals("John"))
                .peek(x -> x.setAge(80)).collect(Collectors.toList());

        System.out.println("after list hashCode:" + list1.hashCode());
        int identityHashCode1 = System.identityHashCode(list1);
        System.out.println("after list identityHashCode:" + identityHashCode1);


        Student student1 = new Student();
        student1.setAge(20);
        student1.setName("John");
        Set<Student> set = new HashSet<>();
        set.add(student1);

        int setIdentityHashCode = System.identityHashCode(set);
        System.out.println("before set setIdentityHashCode:" + setIdentityHashCode);
        System.out.println("before set hashCode:" + set.hashCode());

        Set<Student> set1  = set.stream().filter(z -> z.getName().equals("John"))
                .peek(x -> x.setAge(80)).collect(Collectors.toSet());

        System.out.println("after set hashCode:" + set1.hashCode());
        int setIdentityHashCode1 = System.identityHashCode(set1);
        System.out.println("after set identityHashCode:" + setIdentityHashCode1);


        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        int hashSetIdentityHashCode = System.identityHashCode(hashSet);
        System.out.println("before set identityHashCode:" + hashSetIdentityHashCode);
        System.out.println("before set hashCode:" + hashSet.hashCode());
        hashSet.add(5);
        System.out.println(JSON.toJSONString(hashSet));
        System.out.println("after set hashCode:" + hashSet.hashCode());
        System.out.println("after set identityHashCode:" + System.identityHashCode(hashSet));
    }


    public static class Student {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
