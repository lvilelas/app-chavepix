package com.test.itau.chavepix.model;

import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.dto.PersonTypeDTO;
import com.test.itau.chavepix.dto.PixKeyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder

public class AccountPixKeysModel {

    BigInteger accountNumber;
    BigInteger agencyNumber;
    PersonTypeModel personType;
    @Builder.Default
    List<PixKeyModel> pixKeys = new ArrayList<>();






}
