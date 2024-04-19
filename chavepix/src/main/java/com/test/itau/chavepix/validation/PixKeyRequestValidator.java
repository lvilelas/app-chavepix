package com.test.itau.chavepix.validation;


import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.helper.CPFCNPJHelper;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("before")
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
            errors.rejectValue("numeroConta", null, "Numero Conta incorrecto");
        }

        //valida numero agencia
        if(!hasValidAgencyNumber(pixKey.getAgencyNumber())){
            errors.rejectValue("numeroAgencia", null, "Numero Agencia incorrecto");
        }

        //valida chavePIX
        if(!hasValidPixKey(pixKey)){
            errors.rejectValue("valorChave", null, "Valor chave incorrecto");
        }
    }


    private boolean hasValidAccountNumber(String numeroConta){
        String accountRegex = "[0-9]{1,8}";
        return hasValidStringFormat(accountRegex,numeroConta);
    }

    private boolean hasValidAgencyNumber(String numeroAgencia){
        String agencyRegex = "[0-9]{1,4}";
        return hasValidStringFormat(agencyRegex,numeroAgencia);
    }

        private boolean hasValidPixKey(PixKeyDTO pixKey){
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
                if(!CPFCNPJHelper.isCPF(pixKey.getKeyValue()) || !pixKey.getPersonTypeDTO().name().equals("FISICA")){
                    return false;
                }
                break;
            }
            case CNPJ:{
                if(!CPFCNPJHelper.isCNPJ(pixKey.getKeyValue()) || !pixKey.getPersonTypeDTO().name().equals("JURIDICA")){
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
