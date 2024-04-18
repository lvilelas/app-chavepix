package com.test.itau.chavepix.validation;


import com.test.itau.chavepix.dto.ChavePixDTO;
import com.test.itau.chavepix.helper.CPFCNPJHelper;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("before")
public class ChavePixValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return ChavePixDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ChavePixDTO chavePix = (ChavePixDTO) target;

        //valida numero conta
        if(!validaNumeroConta(chavePix.getNumeroConta())){
            errors.rejectValue("numeroConta", null, "Numero Conta incorrecto");
        }

        //valida numero agencia
        if(!validaNumeroAgencia(chavePix.getNumeroAgencia())){
            errors.rejectValue("numeroAgencia", null, "Numero Agencia incorrecto");
        }

        //valida chavePIX
        if(!validaChavePix(chavePix)){
            errors.rejectValue("valorChave", null, "Valor chave incorrecto");
        }
    }


    private boolean validaNumeroConta(String numeroConta){
        String regexConta = "[0-9]{1,8}";
        return validaRegex(regexConta,numeroConta);
    }

    private boolean validaNumeroAgencia(String numeroAgencia){
        String regexConta = "[0-9]{1,4}";
        return validaRegex(regexConta,numeroAgencia);
    }

    private boolean validaChavePix(ChavePixDTO chavePix){
        switch (chavePix.getTipoChave())  {
            case CELULAR : {
                String regex = "\\+[0-9]{1,2}[0-9]{2,3}[0-9]{9}";
                if(!validaRegex(regex,chavePix.getValorChave())){
                    return false;
                }
                break;
            }
            case EMAIL:{
                if(!chavePix.getValorChave().contains("@") || chavePix.getValorChave().length()>77){
                    return false;
                }
                break;
            }
            case CPF:{
                if(!CPFCNPJHelper.isCPF(chavePix.getValorChave())){
                    return false;
                }
                break;
            }
            case CNPJ:{
                if(!CPFCNPJHelper.isCNPJ(chavePix.getValorChave())){
                    return false;
                }
                break;
            }
        }
        return true;
    }

    private boolean validaRegex(String regex, String message){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        return matcher.matches();
    }


}
