package com.test.itau.chavepix.mocks;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.model.KeyTypeModel;
import com.test.itau.chavepix.model.PersonTypeModel;
import com.test.itau.chavepix.model.PixKeyModel;

import java.util.ArrayList;
import java.util.List;

public class AccountPixKeysModelMocks extends PixKeyDTOMocks{

    public AccountPixKeysModel getValidAccountCPFPixKeysModelMock(){
        PixKeyDTO pixKeyDTO = getValidCPFPixKeyMock();
        PixKeyModel pixKeyModel = new PixKeyModel(pixKeyDTO.getKeyValue(), KeyTypeModel.valueOf(pixKeyDTO.getKeyTypeDTO().name()));
        List<PixKeyModel> list = new ArrayList<>();
        list.add(pixKeyModel);
        AccountPixKeysModel mock = new AccountPixKeysModel(
                pixKeyDTO.getAccountNumber(),
                pixKeyDTO.getAgencyNumber(),
                PersonTypeModel.valueOf(pixKeyDTO.getPersonTypeDTO().name()),
                list);
        return mock;
    }

    public AccountPixKeysModel getValidAccountCNPJPixKeysModelMock(){
        PixKeyDTO pixKeyDTO = getValidCNPJPixKeyMock();
        PixKeyModel pixKeyModel = new PixKeyModel(pixKeyDTO.getKeyValue(), KeyTypeModel.valueOf(pixKeyDTO.getKeyTypeDTO().name()));
        List<PixKeyModel> list = new ArrayList<>();
        list.add(pixKeyModel);
        AccountPixKeysModel mock = new AccountPixKeysModel(
                pixKeyDTO.getAccountNumber(),
                pixKeyDTO.getAgencyNumber(),
                PersonTypeModel.valueOf(pixKeyDTO.getPersonTypeDTO().name()),
                list);
        return mock;
    }

    public AccountPixKeysModel getInvalidAccountCPFPixKeysModelMock(){
        PixKeyDTO pixKeyDTO = getValidCPFPixKeyMock();
        PixKeyModel pixKeyModel = new PixKeyModel(pixKeyDTO.getKeyValue(), KeyTypeModel.CNPJ);
        List<PixKeyModel> list = new ArrayList<>();
        list.add(pixKeyModel);
        AccountPixKeysModel mock = new AccountPixKeysModel(
                pixKeyDTO.getAccountNumber(),
                pixKeyDTO.getAgencyNumber(),
                PersonTypeModel.valueOf(pixKeyDTO.getPersonTypeDTO().name()),
                list);
        return mock;
    }

    public AccountPixKeysModel getInvalidAccountCNPJPixKeysModelMock(){
        PixKeyDTO pixKeyDTO = getValidCPFPixKeyMock();
        PixKeyModel pixKeyModel = new PixKeyModel(pixKeyDTO.getKeyValue(), KeyTypeModel.CPF);
        List<PixKeyModel> list = new ArrayList<>();
        list.add(pixKeyModel);
        AccountPixKeysModel mock = new AccountPixKeysModel(
                pixKeyDTO.getAccountNumber(),
                pixKeyDTO.getAgencyNumber(),
                PersonTypeModel.valueOf(pixKeyDTO.getPersonTypeDTO().name()),
                list);
        return mock;
    }
}
