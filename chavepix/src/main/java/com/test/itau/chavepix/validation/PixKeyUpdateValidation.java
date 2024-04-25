package com.test.itau.chavepix.validation;

import br.com.fluentvalidator.AbstractValidator;
import com.test.itau.chavepix.domain.AccountType;
import com.test.itau.chavepix.dto.PixKeyUpdateDTO;
import com.test.itau.chavepix.exceptions.PixKeyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

@Component
public class PixKeyUpdateValidation extends AbstractValidator<PixKeyUpdateDTO> {
    @Override
    public void rules() {

        validateID();
        validateAccountNumber();
        validateAgencyNumber();
        validateAccountType();
        validateAccountHolderName();
        validateAccountHolderLastName();

    }

    private void validateAccountHolderLastName() {
        ruleFor(PixKeyUpdateDTO::getAccountHolderLastName).must(getAccountHolderLastName -> !StringUtils.isBlank(getAccountHolderLastName) && getAccountHolderLastName.length()<=45)
                .when(Objects::nonNull)
                .withMessage("Account holder Last name Value invalid")
                .withFieldName("accountHolderLastName")
                .critical();
    }

    private void validateAccountHolderName() {
        ruleFor(PixKeyUpdateDTO::getAccountHolderName).must(Objects::nonNull)
                .withMessage("Account holder name Value invalid")
                .withFieldName("accountHolderName")
                .critical();
        ruleFor(PixKeyUpdateDTO::getAccountHolderName).must(accountHolderName -> !StringUtils.isBlank(accountHolderName)&& accountHolderName.length()<=30)
                .when(Objects::nonNull)
                .withMessage("Account holder name Value invalid")
                .withFieldName("accountHolderName")
                .critical();
    }

    private void validateAccountType() {
        ruleFor(PixKeyUpdateDTO::getAccountType)
                .must(Objects::nonNull)
                .withMessage("Account type must not be null")
                .withFieldName("accountType")
                .critical(PixKeyException.class);

        ruleFor(PixKeyUpdateDTO::getAccountType)
                .must(accountTypeDTO -> Arrays.stream(AccountType.values())
                        .map(Enum::name)
                        .anyMatch(accountTypeDTO::equalsIgnoreCase))
                .when(Objects::nonNull)
                .withMessage("Invalid account type")
                .withFieldName("accountType")
                .critical();

    }

    private void validateAgencyNumber() {
        ruleFor(PixKeyUpdateDTO::getAgencyNumber)
                .must(Objects::nonNull)
                .withMessage("Agency number must not be null")
                .withFieldName("agencyNumber")
                .critical(PixKeyException.class);

        ruleFor(PixKeyUpdateDTO::getAgencyNumber)
                .must(agencyNumber -> agencyNumber.compareTo(BigInteger.valueOf(9999)) <= 0 && agencyNumber.compareTo(BigInteger.valueOf(1)) >= 0)
                .withMessage("agency number must be between 1 and 9999")
                .withFieldName("agencyNumber")
                .critical();
    }

    private void validateAccountNumber() {
        ruleFor(PixKeyUpdateDTO::getAccountNumber)
                .must(Objects::nonNull)
                .withMessage("Account Number must not be null")
                .withFieldName("accountNumber")
                .critical(PixKeyException.class);

        ruleFor(PixKeyUpdateDTO::getAccountNumber)
                .must(accountNumber -> accountNumber.compareTo(BigInteger.valueOf(99999999)) <= 0 && accountNumber.compareTo(BigInteger.valueOf(1)) >= 0)
                .withMessage("Account Number must be between 1 and 99999999")
                .withFieldName("accountNumber")
                .critical();

    }

    private void validateID() {
        ruleFor(PixKeyUpdateDTO::getId)
                .must(Objects::nonNull)
                .withMessage("ID must not be null")
                .withFieldName("id")
                .critical();

    }
}
