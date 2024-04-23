package com.test.itau.chavepix.mapper;

import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.persistence.entity.AccountTypeEntity;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PixKeyUpdateMapper {

    PixKeyUpdateMapper INSTANCE = Mappers.getMapper(PixKeyUpdateMapper.class);

    @Mapping(target = "personTypeEntity", ignore = true)
    @Mapping(target = "keyTypeEntity", ignore = true)
    @Mapping(target = "accountTypeEntity", ignore = true)
    @Mapping(target = "dateTimeCreation", ignore = true)
    @Mapping(target = "keyValue", ignore = true)
    @Mapping(target = "id", source="id")
    @Mapping(target = "agencyNumber", source = "agencyNumber")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "accountHolderName", source = "accountHolderName")
    @Mapping(target = "accountHolderLastName", source = "accountHolderLastName")
    PixKeyEntity updatePixKeyEntityFromPixKey(@MappingTarget PixKeyEntity pixKeyEntity, PixKey pixKey);

    @AfterMapping
    default void afterMapping(@MappingTarget PixKeyEntity pixKeyEntity, PixKey pixKey) {
        pixKeyEntity.setAccountTypeEntity(AccountTypeEntity.valueOf(pixKey.getAccountType().name()));
    }
}
