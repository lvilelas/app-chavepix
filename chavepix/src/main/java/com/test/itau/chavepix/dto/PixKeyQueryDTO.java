package com.test.itau.chavepix.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class PixKeyQueryDTO {

    private UUID id;
    private Map<String,String> parameters;

}


