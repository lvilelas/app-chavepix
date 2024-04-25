package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.domain.KeyType;
import com.test.itau.chavepix.domain.PersonType;
import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.exceptions.BusinessRulesException;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BusinessValidationTest extends PixKeyDTOMocks {


    @Test
    public void testValidatePixKeyDocument() {
        PixKeyRepository pixKeyRepository = mock(PixKeyRepository.class);
        when(pixKeyRepository.existsByKeyValue(any())).thenReturn(false);
        BusinessValidation businessValidation = new BusinessValidation(pixKeyRepository);

        PixKey pixKey = getValidPixKey();
        pixKey.setPersonType(PersonType.JURIDICA);

        assertThrows(BusinessRulesException.class, () -> businessValidation.validatePixKey(pixKey));
    }

    @Test
    public void testValidatePixKey() {
        PixKeyRepository pixKeyRepository = mock(PixKeyRepository.class);
        when(pixKeyRepository.existsByKeyValue(any())).thenReturn(true);
        BusinessValidation businessValidation = new BusinessValidation(pixKeyRepository);

        PixKey pixKey = getValidPixKey();


        assertThrows(BusinessRulesException.class, () -> businessValidation.validatePixKey(pixKey));
    }

    @Test
    public void testValidatePixKeyNonUnique() {
        PixKeyRepository pixKeyRepository = mock(PixKeyRepository.class);
        when(pixKeyRepository.existsByKeyValue(any())).thenReturn(true);
        BusinessValidation businessValidation = new BusinessValidation(pixKeyRepository);

        PixKey pixKey = getValidPixKey();


        assertThrows(BusinessRulesException.class, () -> businessValidation.validatePixKeyNonUnique(pixKey));
    }

    @Test
    public void testValidateIfDocumentAlredyExists() {
        BusinessValidation businessValidation = new BusinessValidation(null);
        List<PixKey> pixKeys = new ArrayList<>();
        pixKeys.add(getValidPixKey());
        pixKeys.get(0).setKeyType(KeyType.CPF);
        PixKey pixKey = getValidPixKey();
        pixKey.setKeyType(KeyType.CPF);

        assertThrows(BusinessRulesException.class, () -> businessValidation.validateIfDocumentAlredyExists(pixKeys, "CPF"));
    }

    @Test
    public void testValidatePixKeyAccount() {
        PixKeyRepository pixKeyRepository = mock(PixKeyRepository.class);
        BusinessValidation businessValidation = new BusinessValidation(pixKeyRepository);

        List<PixKey> pixKeyList = new ArrayList<>();
        PixKey pixKey =getValidPixKey();
        pixKey.setKeyType(KeyType.CPF);
        pixKey.setPersonType(PersonType.FISICA);
        pixKeyList.add(pixKey);
        assertThrows(BusinessRulesException.class, () -> businessValidation.validatePixKeyAccount(pixKeyList, pixKey));
    }

    @Test
    public void testValidatePixKeyNonEmptyAccount() {
        PixKeyRepository pixKeyRepository = mock(PixKeyRepository.class);
        BusinessValidation businessValidation = new BusinessValidation(pixKeyRepository);

        List<PixKey> pixKeyList = new ArrayList<>();
        pixKeyList.add(getValidPixKeyEmail());
        PixKey pixKey =getValidPixKey();
        pixKey.setKeyType(KeyType.CPF);
        pixKey.setPersonType(PersonType.FISICA);
        assertDoesNotThrow(() -> businessValidation.validatePixKeyAccount(pixKeyList, pixKey));
    }

    @Test
    public void testValidateIfKeysLimitBeenReached() {
        BusinessValidation businessValidation = new BusinessValidation(null);
        List<PixKey> pixKeys = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pixKeys.add(getValidPixKey());
            pixKeys.get(i).setPersonType(PersonType.FISICA);
        }

        assertThrows(BusinessRulesException.class, () -> businessValidation.validateIfKeysLimitBeenReached(pixKeys));
    }

    @Test
    public void testValidateIfPersonTypeIrCorrect() {
        BusinessValidation businessValidation = new BusinessValidation(null);
        List<PixKey> pixKeyList = new ArrayList<>();
        PixKey pixKey =getValidPixKey();
        pixKey.setPersonType(PersonType.JURIDICA);
        pixKeyList.add(pixKey);

        assertThrows(BusinessRulesException.class, () -> businessValidation.validateIfPersonTypeIrCorrect(pixKeyList, PersonType.FISICA.name()));
    }

    @Test
    public void testValidateIfPixKeyDocumentIsCorrect() {
        BusinessValidation businessValidation = new BusinessValidation(null);
        PixKey pixKey =getValidPixKey();
        pixKey.setKeyType(KeyType.CPF);
        pixKey.setPersonType(PersonType.JURIDICA);

        assertThrows(BusinessRulesException.class, () -> businessValidation.validateIfPixKeyDocumentIsCorrect(pixKey));
    }
}
