package com.test.itau.chavepix.mapper;

import com.test.itau.chavepix.domain.*;
import com.test.itau.chavepix.dto.*;
import com.test.itau.chavepix.helper.DateTimeFormatterHelper;
import com.test.itau.chavepix.persistence.entity.AccountTypeEntity;
import com.test.itau.chavepix.persistence.entity.KeyTypeEntity;
import com.test.itau.chavepix.persistence.entity.PersonTypeEntity;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import io.micrometer.common.util.StringUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Mapper
public interface PixKeyMapper {

    PixKeyMapper INSTANCE = Mappers.getMapper(PixKeyMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateTimeDelete",ignore = true)
    @Mapping(target = "dateTimeCreation",ignore = true)
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
    default void toPixKey(@MappingTarget PixKey pixKey, PixKeyDTO pixKeyDTO) {
        pixKey.setAccountType(AccountType.getByDescription(pixKeyDTO.getAccountType()));
        pixKey.setKeyType(KeyType.getByDescription(pixKeyDTO.getKeyType()));
        pixKey.setPersonType(PersonType.getPersonType(pixKeyDTO.getPersonType()));
    }

    @Mapping(target = "dateTimeCreation", ignore = true)
    @Mapping(target = "id", source="id")
    @Mapping(target = "keyValue", source = "keyValue")
    @Mapping(target = "keyTypeDTO", ignore = true)
    @Mapping(target = "accountTypeDTO", ignore = true)
    @Mapping(target = "personTypeDTO", ignore = true)
    @Mapping(target = "agencyNumber", source = "agencyNumber")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "accountHolderName", source = "accountHolderName")
    @Mapping(target = "accountHolderLastName", ignore = true)
    PixKeyOutDTO toPixKeyOutDTO(PixKey pixKey);

    @AfterMapping
    default void toPixKeyOutDTO(@MappingTarget PixKeyOutDTO pixKeyOutDTO, PixKey pixKey) {
        pixKeyOutDTO.setAccountTypeDTO(AccountTypeDTO.valueOf(pixKey.getAccountType().name()));
        pixKeyOutDTO.setKeyTypeDTO(KeyTypeDTO.valueOf(pixKey.getKeyType().name()));
        pixKeyOutDTO.setPersonTypeDTO(PersonTypeDTO.valueOf(pixKey.getPersonType().name()));
        pixKeyOutDTO.setDateTimeCreation(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(pixKey.getDateTimeCreation()));
        pixKeyOutDTO.setAccountHolderLastName(Objects.isNull(pixKey.getAccountHolderLastName()) ? "" : pixKey.getAccountHolderLastName());
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
    default void toPixKeyEntity(@MappingTarget PixKeyEntity pixKeyEntity,PixKey pixKey) {
        pixKeyEntity.setAccountTypeEntity(AccountTypeEntity.valueOf(pixKey.getAccountType().name()));
        pixKeyEntity.setKeyTypeEntity(KeyTypeEntity.valueOf(pixKey.getKeyType().name()));
        pixKeyEntity.setPersonTypeEntity(PersonTypeEntity.valueOf(pixKey.getPersonType().name()));
    }

    @InheritInverseConfiguration
    PixKey toPixKey(PixKeyEntity pixKey);

    @AfterMapping
    default void toPixKey(@MappingTarget PixKey pixKey,PixKeyEntity pixKeyEntity) {
        pixKey.setAccountType(AccountType.valueOf(pixKeyEntity.getAccountTypeEntity().name()));
        pixKey.setKeyType(KeyType.valueOf(pixKeyEntity.getKeyTypeEntity().name()));
        pixKey.setPersonType(PersonType.valueOf(pixKeyEntity.getPersonTypeEntity().name()));
    }

    @Mapping(target = "personType", ignore = true)
    @Mapping(target = "keyValue", ignore = true)
    @Mapping(target = "keyType", ignore = true)
    @Mapping(target = "dateTimeDelete", ignore = true)
    @Mapping(target = "dateTimeCreation", ignore = true)
    @Mapping(target = "id", source="id")
    @Mapping(target = "accountType", ignore = true)
    @Mapping(target = "agencyNumber", source = "agencyNumber")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "accountHolderName", source = "accountHolderName")
    @Mapping(target = "accountHolderLastName", source = "accountHolderLastName")
    PixKey toPixKey(PixKeyUpdateDTO pixKey);

    @AfterMapping
    default void toPixKey(@MappingTarget PixKey pixKeyUpdate,PixKeyUpdateDTO pixKeyUpdateDTO) {
        pixKeyUpdate.setAccountType(AccountType.getByDescription(pixKeyUpdateDTO.getAccountType()));
    }

    @Mapping(target = "accountTypeDTO", ignore = true)
    @Mapping(target = "personTypeDTO", ignore = true)
    @Mapping(target = "keyTypeDTO", ignore = true)
    @Mapping(target = "keyValue", source = "keyValue")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "dateTimeCreation", ignore= true)
    @Mapping(target = "dateTimeDelete", ignore= true)
    @Mapping(target = "agencyNumber", source = "agencyNumber")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "accountHolderName", source = "accountHolderName")
    @Mapping(target = "accountHolderLastName", ignore = true)
    PixKeyDeleteOutDTO toPixKeyDeleteOutDTO(PixKeyEntity pixKeyEntity);

    @AfterMapping
    default void toPixKeyDeleteOutDTO(@MappingTarget PixKeyDeleteOutDTO pixKeyDeleteOutDTO,PixKeyEntity pixKeyEntity) {
        pixKeyDeleteOutDTO.setAccountTypeDTO(AccountTypeDTO.valueOf(pixKeyEntity.getAccountTypeEntity().name()));
        pixKeyDeleteOutDTO.setKeyTypeDTO(KeyTypeDTO.valueOf(pixKeyEntity.getKeyTypeEntity().name()));
        pixKeyDeleteOutDTO.setPersonTypeDTO(PersonTypeDTO.valueOf(pixKeyEntity.getPersonTypeEntity().name()));
        pixKeyDeleteOutDTO.setDateTimeDelete(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(pixKeyEntity.getDateTimeDelete()));
        pixKeyDeleteOutDTO.setDateTimeCreation(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(pixKeyEntity.getDateTimeCreation()));
        pixKeyDeleteOutDTO.setAccountHolderLastName(Objects.isNull(pixKeyEntity.getAccountHolderLastName()) ? "" : pixKeyEntity.getAccountHolderLastName());
    }

    @Mapping(target = "accountTypeDTO", ignore = true)
    @Mapping(target = "personTypeDTO", ignore = true)
    @Mapping(target = "keyTypeDTO", ignore = true)
    @Mapping(target = "dateTimeDelete", ignore = true)
    @Mapping(target = "dateTimeCreation", ignore = true)
    @Mapping(target = "keyValue", source = "keyValue")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "agencyNumber", source = "agencyNumber")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "accountHolderName", source="accountHolderName")
    @Mapping(target = "accountHolderLastName", ignore = true)
    PixQueryOutDTO toPixQueryOutDTO(PixKeyEntity pixKeyEntity);

    @AfterMapping
    default void toPixQueryOutDTO(@MappingTarget PixQueryOutDTO pixQueryOutDTO,PixKeyEntity pixKeyEntity) {
        pixQueryOutDTO.setAccountTypeDTO(AccountTypeDTO.valueOf(pixKeyEntity.getAccountTypeEntity().name()));
        pixQueryOutDTO.setKeyTypeDTO(KeyTypeDTO.valueOf(pixKeyEntity.getKeyTypeEntity().name()));
        pixQueryOutDTO.setPersonTypeDTO(PersonTypeDTO.valueOf(pixKeyEntity.getPersonTypeEntity().name()));
        pixQueryOutDTO.setDateTimeCreation(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(pixKeyEntity.getDateTimeCreation()));
        pixQueryOutDTO.setDateTimeDelete(Objects.nonNull(pixKeyEntity.getDateTimeDelete()) ?
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(pixKeyEntity.getDateTimeDelete()) :
                "");
        pixQueryOutDTO.setAccountHolderLastName(Objects.isNull(pixKeyEntity.getAccountHolderLastName()) ? "" : pixKeyEntity.getAccountHolderLastName());
    }

    @Mapping(target = "keyType", source = "parameters.tipo_chave")
    @Mapping(target = "dateDeleteStart", ignore = true)
    @Mapping(target = "dateDeleteEnd", ignore = true)
    @Mapping(target = "dateCreateStart", ignore = true)
    @Mapping(target = "dateCreateEnd", ignore = true)
    @Mapping(source = "parameters.nome_correntista", target = "accountHolderName")
    @Mapping(source = "parameters.numero_conta", target = "accountNumber")
    @Mapping(source = "parameters.numero_agencia", target = "agencyNumber")
    @Mapping(source = "id", target = "id")
    PixKeyQuery toPixKeyQuery(PixKeyQueryDTO pixKeyQueryDTO);

    @AfterMapping
    default void toPixKeyQuery(@MappingTarget PixKeyQuery pixKey, PixKeyQueryDTO pixKeyQueryDTO){
        String dateCreate = pixKeyQueryDTO.getParameters().get("data_inclusao");
        if(StringUtils.isNotBlank(dateCreate)){
            pixKey.setDateCreateStart(DateTimeFormatterHelper
                    .toLocalDateTime(dateCreate,DateTimeFormatterHelper
                            .formatterWithDateOnly()));
            pixKey.setDateCreateEnd(pixKey.getDateCreateStart().plusMinutes(1439));
        }

        String dateDelete = pixKeyQueryDTO.getParameters().get("data_exclusao");
        if(StringUtils.isNotBlank((dateDelete))){
            pixKey.setDateDeleteStart(DateTimeFormatterHelper
                    .toLocalDateTime(dateDelete,DateTimeFormatterHelper
                            .formatterWithDateOnly()));
            pixKey.setDateDeleteEnd(pixKey.getDateDeleteStart().plusMinutes(1439));
        }

    }
}
