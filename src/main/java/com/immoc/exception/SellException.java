package com.immoc.exception;

import com.immoc.enums.ExceptionEnum;
import lombok.Data;

/**
 * Created by Administrator on 2017/9/10 0010.
 */
@Data
public class SellException extends RuntimeException{

    private Integer code;

    private String msg;

    public SellException(ExceptionEnum exceptionEnum){
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }

//    public ProductException(Integer code, String msg) {
//        this.code = code;
//        this.msg = msg;
//    }
}
