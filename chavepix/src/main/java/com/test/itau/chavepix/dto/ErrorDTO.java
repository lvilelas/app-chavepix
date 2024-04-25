package com.test.itau.chavepix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO {

    String fieldName;
    String error;
}
