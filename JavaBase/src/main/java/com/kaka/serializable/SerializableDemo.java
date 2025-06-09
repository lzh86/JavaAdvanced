package com.kaka.serializable;


import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SerializableDemo extends ParentDemo {

    private String name;

    private User user;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class User {
        private Integer age;
    }

    public static void main(String[] args) {
//        User user1 = new User();
//        user1.setAge(999);
//        SerializableDemo ceshi = SerializableDemo.builder().name("ceshi").user(user1).build();
//        String jsonString = JSON.toJSONString(ceshi);
//        String jsonString1 = JSON.toJSONString(ceshi, SerializerFeature.WriteClassName);
//        System.out.println(jsonString);
//        System.out.println(jsonString1);
//
//        SerializableDemo serializableDemo =  JSON.parseObject(jsonString,SerializableDemo.class);
//        System.out.println(serializableDemo);

    }

}
