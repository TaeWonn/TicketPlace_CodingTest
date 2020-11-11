package com.example.demo.conf.exceptoins;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EnumConvertServiceException extends RuntimeException{

    public EnumConvertServiceException(String msg){ super(msg); }

}
