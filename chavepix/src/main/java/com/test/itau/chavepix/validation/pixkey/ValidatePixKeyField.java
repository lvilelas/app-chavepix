package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.helper.CPFCNPJHelper;
import org.springframework.beans.NotReadablePropertyException;

public class ValidatePixKeyField extends AbstractPixKeyRequestValidationHandler{
    @Override
    public void validate(PixKeyDTO pixKey) {
        if(!hasValidPixKey(pixKey)){
            throw new NotReadablePropertyException(PixKeyDTO.class,"pix key is not valid");
        }
    }

    private boolean hasValidPixKey(PixKeyDTO pixKey){
        if(pixKey.getKeyValue() == null || pixKey.getKeyValue().isEmpty()){
            return false;
        }
        switch (pixKey.getKeyTypeDTO())  {
            case CELULAR : {
                if(!pixKey.getKeyValue().matches("\\+[0-9]{1,2}[0-9]{2,3}[0-9]{9}")){
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
}
