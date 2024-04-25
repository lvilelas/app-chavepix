package com.test.itau.chavepix.validation;

import br.com.fluentvalidator.AbstractValidator;
import com.test.itau.chavepix.domain.AccountType;
import com.test.itau.chavepix.domain.KeyType;
import com.test.itau.chavepix.domain.PersonType;
import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.dto.KeyTypeDTO;
import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.exceptions.PixKeyException;
import com.test.itau.chavepix.helper.CPFCNPJHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

@Component
public class PixKeyValidation extends AbstractValidator<PixKeyDTO> {
    @Override
    public void rules() {

        validateAccountType();
        validatePersonType();
        validateAgencyNumber();
        validateAccountNumber();
        validateAccountHolderName();
        validateAccountHolderLastName();
        validateKeyType();
        validatePixKey();
    }

    private void validateAccountHolderLastName() {

        ruleFor(PixKeyDTO::getAccountHolderLastName).must(getAccountHolderLastName -> !StringUtils.isBlank(getAccountHolderLastName) && getAccountHolderLastName.length()<=45)
                .when(Objects::nonNull)
                .withMessage("Account holder Last name Value invalid")
                .withFieldName("accountHolderLastName")
                .critical();
    }

    private void validateAccountHolderName() {
        ruleFor(PixKeyDTO::getAccountHolderName).must(Objects::nonNull)
                .withMessage("Account holder name Value invalid")
                .withFieldName("accountHolderName")
                .critical();
        ruleFor(PixKeyDTO::getAccountHolderName).must(accountHolderName -> !StringUtils.isBlank(accountHolderName)&& accountHolderName.length()<=30)
                .when(Objects::nonNull)
                .withMessage("Account holder name Value invalid")
                .withFieldName("accountHolderName")
                .critical();
    }

    private void validatePixKey() {
        ruleFor(PixKeyDTO::getKeyValue).must(Objects::nonNull)
                .withMessage("Pix Value invalid")
                .withFieldName("keyValue")
                .critical();
        ruleFor(Function.identity()).must(pixKey -> pixKey.getKeyValue().matches("\\+[0-9]{1,2}[0-9]{2,3}[0-9]{9}"))
                .when(pixKey -> Objects.nonNull(pixKey.getKeyValue()) && KeyType.getByDescription(pixKey.getKeyType()).equals(KeyType.CELULAR))
                .withMessage("Pix Value invalid")
                .withFieldName("keyValue")
                .critical();
        ruleFor(Function.identity()).must(pixKey -> CPFCNPJHelper.isCPF(pixKey.getKeyValue()))
                .when(pixKey -> Objects.nonNull(pixKey.getKeyValue()) &&  KeyType.getByDescription(pixKey.getKeyType()).equals(KeyType.CPF))
                .withMessage("Pix Value invalid")
                .withFieldName("keyValue")
                .critical();
        ruleFor(Function.identity()).must(pixKey -> CPFCNPJHelper.isCNPJ(pixKey.getKeyValue()))
                .when(pixKey -> Objects.nonNull(pixKey.getKeyValue()) &&  KeyType.getByDescription(pixKey.getKeyType()).equals(KeyType.CNPJ))
                .withMessage("Pix Value invalid")
                .withFieldName("keyValue")
                .critical();
        ruleFor(Function.identity()).must(pixKey -> pixKey.getKeyValue().contains("@") && pixKey.getKeyValue().length()<=77)
                .when(pixKey -> Objects.nonNull(pixKey.getKeyValue()) &&  KeyType.getByDescription(pixKey.getKeyType()).equals(KeyType.EMAIL))
                .withMessage("Pix Value invalid")
                .withFieldName("keyValue")
                .critical();
        ruleFor(Function.identity()).must(pixKey -> pixKey.getKeyValue().length()<=36)
                .when(pixKey -> Objects.nonNull(pixKey.getKeyValue()) && KeyType.getByDescription(pixKey.getKeyType()).equals(KeyType.ALEATORIO))
                .withMessage("Pix Value invalid")
                .withFieldName("keyValue")
                .critical();
    }

    private void validateKeyType(){
        ruleFor(PixKeyDTO::getKeyType)
                .must(Objects::nonNull)
                .withMessage("Key type must not be null")
                .withFieldName("keyType")
                .critical(PixKeyException.class);

        ruleFor(PixKeyDTO::getKeyType)
                .must(keyType -> Objects.nonNull(KeyTypeDTO.getByDescription(keyType)))
                .when(Objects::nonNull)
                .withMessage("Invalid Key Type")
                .withFieldName("keyType")
                .critical(PixKeyException.class);
    }

    private void validatePersonType(){
        ruleFor(PixKeyDTO::getPersonType)
                .must(Objects::nonNull)
                .withMessage("Person type must not be null")
                .withFieldName("personType")
                .critical();

        ruleFor(PixKeyDTO::getPersonType)
                .must(personType -> Arrays.stream(PersonType.values())
                        .map(Enum::name)
                        .anyMatch(personType::equalsIgnoreCase))
                .when(Objects::nonNull)
                .withMessage("Person type must not be null")
                .withFieldName("personType")
                .critical();
    }

    private void validateAccountType() {
        ruleFor(PixKeyDTO::getAccountType)
                .must(Objects::nonNull)
                .withMessage("Account type must not be null")
                .withFieldName("accountType")
                .critical();

        ruleFor(PixKeyDTO::getAccountType)
                .must(accountTypeDTO -> Arrays.stream(AccountType.values())
                    .map(Enum::name)
                        .anyMatch(accountTypeDTO::equalsIgnoreCase))
                .when(Objects::nonNull)
                .withMessage("Invalid account type")
                .withFieldName("accountType")
                .critical();

    }


    private void validateAgencyNumber() {
        ruleFor(PixKeyDTO::getAgencyNumber)
                .must(Objects::nonNull)
                .withMessage("Agency number must not be null")
                .withFieldName("agencyNumber")
                .critical(PixKeyException.class);

        ruleFor(PixKeyDTO::getAgencyNumber)
                .must(agencyNumber -> agencyNumber.compareTo(BigInteger.valueOf(9999)) <= 0 && agencyNumber.compareTo(BigInteger.valueOf(1)) >= 0)
                .withMessage("agency number must be between 1 and 9999")
                .withFieldName("agencyNumber")
                .critical();
    }

    private void validateAccountNumber() {
        ruleFor(PixKeyDTO::getAccountNumber)
                .must(Objects::nonNull)
                .withMessage("Account Number must not be null")
                .withFieldName("accountNumber")
                .critical(PixKeyException.class);

        ruleFor(PixKeyDTO::getAccountNumber)
                .must(accountNumber -> accountNumber.compareTo(BigInteger.valueOf(99999999)) <= 0 && accountNumber.compareTo(BigInteger.valueOf(1)) >= 0)
                .withMessage("Account Number must be between 1 and 99999999")
                .withFieldName("accountNumber")
                .critical();

    }
}
