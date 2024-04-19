package com.test.itau.chavepix.service;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import com.test.itau.chavepix.validation.handler.PixKeyValidationHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PixKeysServiceTest extends PixKeyDTOMocks {

    @Mock
    private PixKeyRepository pixKeyRepository;

    @Mock
    private PixKeyValidationHandler pixKeyValidationHandler;

    @InjectMocks
    private PixKeysService pixKeysService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePixKey() {

        // Mocking expected output data
        PixKeyDTO pixKeyDTO = getValidCPFPixKeyMock();
        PixKeyEntity pixKeyEntity = new PixKeyEntity(pixKeyDTO);
        List<PixKeyEntity> list = Collections.nCopies(5, new PixKeyEntity(getValidCPFPixKeyMock()));

        // Mocking repository behavior
        when(pixKeyRepository.save(any(PixKeyEntity.class))).thenReturn(pixKeyEntity);
        when(pixKeyRepository.findByAgencyNumberAndAccountNumber(anyString(), anyString())).thenReturn(list);
        // Calling the service method
        pixKeysService.createPixKey(pixKeyDTO);

         //Verifying interactions
        verify(pixKeyValidationHandler, times(1)).validatePixKey(any(AccountPixKeysModel.class), any(PixKeyDTO.class));
    }
}
