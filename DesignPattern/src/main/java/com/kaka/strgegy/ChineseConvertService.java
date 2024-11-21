package com.kaka.javaAdvancedDemo.design.strgegy;

import org.springframework.stereotype.Service;

@Service("Chinses")
public class ChineseConvertService implements ConvertService {
    @Override
    public String convert(String value) {
        return "中文转换结果" + value;
    }
}
