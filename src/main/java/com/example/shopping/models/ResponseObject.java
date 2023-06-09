package com.example.shopping.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseObject {
    private String status;
    private String message;
    private Object data;
}
