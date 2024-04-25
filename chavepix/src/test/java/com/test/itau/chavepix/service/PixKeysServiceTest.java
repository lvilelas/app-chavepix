package com.test.itau.chavepix.service;

import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.domain.PixKeyQuery;
import com.test.itau.chavepix.dto.PixKeyDeleteOutDTO;
import com.test.itau.chavepix.dto.PixQueryOutDTO;
import com.test.itau.chavepix.exceptions.PixKeyNotFoundException;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import com.test.itau.chavepix.validation.BusinessValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PixKeysServiceTest extends PixKeyDTOMocks {

    @Mock
    private PixKeyRepository pixKeyRepository;

    @Mock
    private BusinessValidation businessValidation;

    @InjectMocks
    private PixKeysService pixKeysService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePixKey() {
        PixKey pixKey = getValidPixKey();
        PixKeyEntity pixKeyEntity = getPixKeyEntity();
        when(pixKeyRepository.save(any())).thenReturn(pixKeyEntity);

        PixKey createdPixKey = pixKeysService.createPixKey(pixKey);

        assertNotNull(createdPixKey);
        verify(businessValidation, times(1)).validatePixKey(pixKey);
        verify(businessValidation, times(1)).validatePixKeyAccount(Collections.emptyList(), pixKey);
        verify(pixKeyRepository, times(1)).save(any());
    }

    @Test
    public void testSearchPixKey() {
        PixKeyQuery pixKeyQuery = getPixKeyQuery();
        pixKeyQuery.setKeyType("CPF");
        when(pixKeyRepository.findCustom(any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(Collections.singletonList( getPixKeyEntity()));

        List<PixQueryOutDTO> pixKeys = pixKeysService.searchPixKey(pixKeyQuery);

        assertNotNull(pixKeys);
        assertFalse(pixKeys.isEmpty());
        verify(pixKeyRepository, times(1)).findCustom(any(), any(), any(), any(), any(), any(), any(), any(), any());
    }

    @Test
    public void testUpdatePixKey() {
        PixKey newPixKey = getValidPixKey();

        PixKeyEntity pixKeyEntity = getPixKeyEntity();
        when(pixKeyRepository.findByIdAndDateTimeDeleteIsNull(any())).thenReturn(pixKeyEntity);
        when(pixKeyRepository.save(any())).thenReturn(pixKeyEntity);

        PixKey updatedPixKey = pixKeysService.updatePixKey(newPixKey);
        List<PixKey> list = new ArrayList<>();
        list.add(newPixKey);

        assertNotNull(updatedPixKey);

        verify(businessValidation, times(1)).validatePixKeyAccount(any(), any());
        verify(pixKeyRepository, times(1)).findByIdAndDateTimeDeleteIsNull(any());
        verify(pixKeyRepository, times(1)).save(any());
    }

    @Test
    public void testDeletePixKey() {
        UUID id = UUID.randomUUID();
        PixKeyEntity pixKeyEntity = getPixKeyEntity();
        when(pixKeyRepository.findByIdAndDateTimeDeleteIsNull(any())).thenReturn(pixKeyEntity);
        when(pixKeyRepository.save(any())).thenReturn(pixKeyEntity);

        PixKeyDeleteOutDTO deletedPixKey = pixKeysService.deletePixKey(id);

        assertNotNull(deletedPixKey);
        verify(pixKeyRepository, times(1)).findByIdAndDateTimeDeleteIsNull(any());
        verify(pixKeyRepository, times(1)).save(any());
    }

    @Test
    public void testDeletePixKey_PixKeyNotFoundException() {
        UUID id = UUID.randomUUID();
        when(pixKeyRepository.findByIdAndDateTimeDeleteIsNull(any())).thenReturn(null);

        assertThrows(PixKeyNotFoundException.class, () -> pixKeysService.deletePixKey(id));
        verify(pixKeyRepository, times(1)).findByIdAndDateTimeDeleteIsNull(any());
        verify(pixKeyRepository, never()).save(any());
    }
}
