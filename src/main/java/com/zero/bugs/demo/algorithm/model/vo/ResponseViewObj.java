package com.zero.bugs.demo.algorithm.model.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseViewObj {
    String msg;
    int errorCode;
    String data;
}
