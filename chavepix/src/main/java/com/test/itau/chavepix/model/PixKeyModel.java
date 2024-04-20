package com.test.itau.chavepix.model;

import com.test.itau.chavepix.dto.KeyTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PixKeyModel {
    String keyValue;
    KeyTypeModel keyType;
}
