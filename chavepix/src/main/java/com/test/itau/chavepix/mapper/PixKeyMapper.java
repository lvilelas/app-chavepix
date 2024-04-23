package com.test.itau.chavepix.mapper;

import com.test.itau.chavepix.domain.AccountType;
import com.test.itau.chavepix.domain.KeyType;
import com.test.itau.chavepix.domain.PersonType;
import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.dto.*;
import com.test.itau.chavepix.persistence.entity.AccountTypeEntity;
import com.test.itau.chavepix.persistence.entity.KeyTypeEntity;
import com.test.itau.chavepix.persistence.entity.PersonTypeEntity;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PixKeyMapper {

    PixKeyMapper INSTANCE = Mappers.getMapper(PixKeyMapper.class);

    @Mapping(target = "dateTimeDelete",ignore = true)
    @Mapping(target = "dateTimeCreation",ignore = true)
    @Mapping(target = "id", source="id")
    @Mapping(target = "keyValue", source = "keyValue")
    @Mapping(target = "keyType", ignore = true)
    @Mapping(target = "accountType", ignore = true)
    @Mapping(target = "personType", ignore = true)
    @Mapping(target = "agencyNumber", source = "agencyNumber")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "accountHolderName", source = "accountHolderName")
    @Mapping(target = "accountHolderLastName", source = "accountHolderLastName")
    PixKey toPixKey(PixKeyDTO pixKeyDTO);

    @AfterMapping
    default void afterMapping(@MappingTarget PixKey pixKey, PixKeyDTO pixKeyDTO) {
        pixKey.setAccountType(AccountType.getByDescription(pixKeyDTO.getAccountType()));
        pixKey.setKeyType(KeyType.getByDescription(pixKeyDTO.getKeyType()));
        pixKey.setPersonType(PersonType.getPersonType(pixKeyDTO.getPersonType()));
    }

    @Mapping(target = "dateTimeCreation", source="dateTimeCreation")
    @Mapping(target = "id", source="id")
    @Mapping(target = "keyValue", source = "keyValue")
    @Mapping(target = "keyTypeDTO", ignore = true)
    @Mapping(target = "accountTypeDTO", ignore = true)
    @Mapping(target = "personTypeDTO", ignore = true)
    @Mapping(target = "agencyNumber", source = "agencyNumber")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "accountHolderName", source = "accountHolderName")
    @Mapping(target = "accountHolderLastName", source = "accountHolderLastName")
    PixKeyOutDTO toPixKeyOutDTO(PixKey pixKey);

    @AfterMapping
    default void afterMapping(@MappingTarget PixKeyOutDTO pixKeyOutDTO, PixKey pixKey) {
        pixKeyOutDTO.setAccountTypeDTO(AccountTypeDTO.valueOf(pixKey.getAccountType().name()));
        pixKeyOutDTO.setKeyTypeDTO(KeyTypeDTO.valueOf(pixKey.getKeyType().name()));
        pixKeyOutDTO.setPersonTypeDTO(PersonTypeDTO.valueOf(pixKey.getPersonType().name()));
    }


    @Mapping(target = "dateTimeDelete", ignore = true)
    @Mapping(target = "dateTimeCreation", ignore = true)
    @Mapping(target = "id", source="id")
    @Mapping(target = "keyValue", source = "keyValue")
    @Mapping(target = "keyTypeEntity", ignore = true)
    @Mapping(target = "accountTypeEntity", ignore = true)
    @Mapping(target = "personTypeEntity", ignore = true)
    @Mapping(target = "agencyNumber", source = "agencyNumber")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "accountHolderName", source = "accountHolderName")
    @Mapping(target = "accountHolderLastName", source = "accountHolderLastName")
    PixKeyEntity toPixKeyEntity(PixKey pixKey);

    @AfterMapping
    default void afterMapping(@MappingTarget PixKeyEntity pixKeyEntity,PixKey pixKey) {
        pixKeyEntity.setAccountTypeEntity(AccountTypeEntity.valueOf(pixKey.getAccountType().name()));
        pixKeyEntity.setKeyTypeEntity(KeyTypeEntity.valueOf(pixKey.getKeyType().name()));
        pixKeyEntity.setPersonTypeEntity(PersonTypeEntity.valueOf(pixKey.getPersonType().name()));
    }


    @Mapping(target = "dateTimeDelete", ignore = true)
    @Mapping(target = "dateTimeCreation", source = "dateTimeCreation")
    @Mapping(target = "id", source="id")
    @Mapping(target = "keyValue", source = "keyValue")
    @Mapping(target = "keyType", ignore = true)
    @Mapping(target = "accountType", ignore = true)
    @Mapping(target = "personType", ignore = true)
    @Mapping(target = "agencyNumber", source = "agencyNumber")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "accountHolderName", source = "accountHolderName")
    @Mapping(target = "accountHolderLastName", source = "accountHolderLastName")
    PixKey toPixKey(PixKeyEntity pixKey);

    @AfterMapping
    default void afterMapping(@MappingTarget PixKey pixKey,PixKeyEntity pixKeyEntity) {
        pixKey.setAccountType(AccountType.valueOf(pixKeyEntity.getAccountTypeEntity().name()));
        pixKey.setKeyType(KeyType.valueOf(pixKeyEntity.getKeyTypeEntity().name()));
        pixKey.setPersonType(PersonType.valueOf(pixKeyEntity.getPersonTypeEntity().name()));
    }




}
