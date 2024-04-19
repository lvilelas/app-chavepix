package com.test.itau.chavepix.validation;


import com.test.itau.chavepix.dto.AccountTypeDTO;
import com.test.itau.chavepix.dto.KeyTypeDTO;
import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.helper.CPFCNPJHelper;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PixKeyRequestValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return PixKeyDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        PixKeyDTO pixKey = (PixKeyDTO) target;

        //valida numero conta
        if(!hasValidAccountNumber(pixKey.getAccountNumber())){
            errors.rejectValue("numero_conta is not valid","422");
        }

        //valida numero agencia
        if(!hasValidAgencyNumber(pixKey.getAgencyNumber())){
            errors.rejectValue("numero_agencia is not valid","422");
        }

        if(!hasValidAccountType(pixKey.getAccountTypeDTO())){
            errors.rejectValue("tipo_conta is not valid","422");
        }

        if(!hasValidPixKeyType(pixKey.getKeyTypeDTO())){
            errors.rejectValue("tipo_chave is not valid","422");
        }
        //valida chavePIX
        if(!hasValidPixKey(pixKey)){
            errors.rejectValue("valor_chave is not valid", "422");
        }

        if(!hasValidAccountHolderName(pixKey.getAccountHolderName())){
            errors.rejectValue("nome_correntista is not valid","422");
        }

    }

    private boolean hasValidAccountHolderName(String accountHolderName) {
        return !(accountHolderName==null || accountHolderName.isEmpty());
    }

    private boolean hasValidAccountType(AccountTypeDTO accountTypeDTO) {
        return !(accountTypeDTO==null);
    }

    private boolean hasValidPixKeyType(KeyTypeDTO keyTypeDTO) {
        return !(keyTypeDTO==null);
    }


    private boolean hasValidAccountNumber(String numeroConta){
        if(numeroConta==null || numeroConta.isEmpty()){
            return false;
        }
        String accountRegex = "[0-9]{1,8}";
        return hasValidStringFormat(accountRegex,numeroConta);
    }

    private boolean hasValidAgencyNumber(String numeroAgencia){
        if(numeroAgencia==null || numeroAgencia.isEmpty()){
            return false;
        }
        String agencyRegex = "[0-9]{1,4}";
        return hasValidStringFormat(agencyRegex,numeroAgencia);
    }

        private boolean hasValidPixKey(PixKeyDTO pixKey){
        if(pixKey.getKeyValue() == null){
            return false;
        }
        switch (pixKey.getKeyTypeDTO())  {
            case CELULAR : {
                String regex = "\\+[0-9]{1,2}[0-9]{2,3}[0-9]{9}";
                if(!hasValidStringFormat(regex,pixKey.getKeyValue())){
                    return false;
                }
                break;
            }
            case EMAIL:{
                if(!pixKey.getKeyValue().contains("@") || pixKey.getKeyValue().length()>77){
                    return false;
                }
                break;
            }
            case CPF:{
                if(!CPFCNPJHelper.isCPF(pixKey.getKeyValue())){
                    return false;
                }
                break;
            }
            case CNPJ:{
                if(!CPFCNPJHelper.isCNPJ(pixKey.getKeyValue())){
                    return false;
                }
                break;
            } case ALEATORIO:{
                if(pixKey.getKeyValue().length()>36){
                    return false;
                }
                break;
            } default:
                return false;

        }
        return true;
    }

    private boolean hasValidStringFormat(String regex, String message){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        return matcher.matches();
    }


}
