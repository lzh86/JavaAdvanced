package com.kaka.url;

import java.util.HashMap;
import java.util.Map;

public class ShortURLGenerate {
    private Map<String, String> urlMap = new HashMap<>();
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int counter = 1;

    public String encode(String longUrl) {
        StringBuilder shortUrl = new StringBuilder();
        int id = counter++;
        while (id > 0) {
            shortUrl.append(BASE62.charAt(id % 62));
            id /= 62;
        }
        shortUrl.reverse();
        String shortUrlStr = shortUrl.toString();
        urlMap.put(shortUrlStr, longUrl);
        return shortUrlStr;
    }

    public String decode(String shortUrl) {
        return urlMap.get(shortUrl);
    }

    public static void main(String[] args) {
        ShortURLGenerate shortener = new ShortURLGenerate();
        String longUrl = "https://www.example.com/very-long-url";
        String shortUrl = shortener.encode(longUrl);
        System.out.println("Short URL: " + shortUrl);
        System.out.println("Decoded URL: " + shortener.decode(shortUrl));
    }
}
