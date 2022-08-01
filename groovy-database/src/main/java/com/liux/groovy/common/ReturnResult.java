package com.liux.groovy.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo-i18n
 * @date :Create in 2022/7/24 10:05
 * @description :返回结果包装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnResult {
    private int code;
    private String message;
    private Object data;

    public ReturnResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.code == HttpStatus.OK.value();
    }

    public static ReturnResult success(Object data) {
        return new ReturnResult(HttpStatus.OK.value(), HttpStatus.OK.name(), data);
    }

    public static ReturnResult success() {
        return new ReturnResult(HttpStatus.OK.value(), HttpStatus.OK.name());
    }

    public static ReturnResult fail(int code, String message) {
        return new ReturnResult(code, message);
    }
}
