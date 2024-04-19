package com.test.itau.chavepix.model;

import com.test.itau.chavepix.dto.PersonTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder

public class AccountPixKeysModel {

    String accountNumber;
    String agencyNumber;
    PersonTypeModel personType;
    @Builder.Default
    List<PixKeyModel> pixKeys = new ArrayList<>();


    public boolean hasKeysLimitBeenReached() {
        if(personType== PersonTypeModel.valueOf(PersonTypeDTO.FISICA.name())){
            return pixKeys.size()>=5;
        }
        return pixKeys.size()>=20;
    }

    public boolean isValidPersonType(PersonTypeDTO pixKey) {
        return personType==PersonTypeModel.valueOf((pixKey.name()));
    }
}
