package com.kaka.strgegy;

import org.springframework.stereotype.Service;

@Service("English")
public class EnglishConvertService implements ConvertService {
    @Override
    public String convert(String value) {
        return "英文转换结果" + value;
    }
}
