package com.kaka.javaAdvancedDemo.design.strgegy;

import org.springframework.stereotype.Service;

@Service("Japanese")
public class JapaneseConvertService implements ConvertService {
    @Override
    public String convert(String value) {
        return "日文转换结果" + value;
    }
}
