package com.test.itau.chavepix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO {

    String cause;

    String message;

}
