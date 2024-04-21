package com.test.itau.chavepix.service;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.dto.PixKeyQueryDTO;
import com.test.itau.chavepix.dto.PixQueryOutDTO;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.model.KeyTypeModel;
import com.test.itau.chavepix.model.PixKeyModel;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import com.test.itau.chavepix.validation.handler.PixKeyQueryValidationHandler;
import com.test.itau.chavepix.validation.handler.PixKeyRequestValidatorHandler;
import com.test.itau.chavepix.validation.handler.PixKeyValidationHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PixKeysServiceTest extends PixKeyDTOMocks {

    @Mock
    private PixKeyRepository pixKeyRepository;

    @Mock
    private PixKeyValidationHandler pixKeyValidationHandler;

    @Mock
    private PixKeyRequestValidatorHandler pixKeyRequestValidatorHandler;

    @Mock
    private PixKeyQueryValidationHandler pixKeyQueryValidationHandler;

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
        pixKeyEntity.setDateTimeCreation(LocalDateTime.now());
        List<PixKeyEntity> list = Collections.nCopies(5, new PixKeyEntity(getValidCPFPixKeyMock()));

        // Mocking repository behavior
        when(pixKeyRepository.save(any(PixKeyEntity.class))).thenReturn(pixKeyEntity);
        when(pixKeyRepository.findByAgencyNumberAndAccountNumber(anyString(), anyString())).thenReturn(list);
        // Calling the service method
        pixKeysService.createPixKey(pixKeyDTO);

         //Verifying interactions
        verify(pixKeyRequestValidatorHandler, times(1)).validateRequest(any(PixKeyDTO.class));
        verify(pixKeyValidationHandler, times(1)).validatePixKey(any(AccountPixKeysModel.class), any(PixKeyDTO.class));
    }

    @Test
    void testSearchPixKey() {
        // Mocking PixKeyQueryDTO

        PixKeyEntity pixKeyEntity = new PixKeyEntity(getValidCPFPixKeyMock());
        PixKeyQueryDTO pixKeyQueryDTO = new PixKeyQueryDTO(pixKeyEntity.getId(),
                pixKeyEntity.getKeyTypeEntity().name(),
                pixKeyEntity.getAgencyNumber(),
                pixKeyEntity.getAccountNumber(),
                pixKeyEntity.getAccountHolderName());

        // Mocking PixKeyEntity list
        List<PixKeyEntity> pixKeyEntities = new ArrayList<>();
        pixKeyEntities.add(pixKeyEntity);

        // Mocking repository behavior
        when(pixKeyRepository.findCustom(eq(pixKeyQueryDTO.getId()), any(), any(), any(), any())).thenReturn(pixKeyEntities);

        // Mocking PixQueryOutDTO list (expected output)
        List<PixQueryOutDTO> expectedOutput = new ArrayList<>();
        expectedOutput.add(new PixQueryOutDTO(pixKeyEntity));

        // Performing the test
        List<PixQueryOutDTO> result = pixKeysService.searchPixKey(pixKeyQueryDTO);

        // Verifying the result
        assertEquals(expectedOutput.size(), result.size());
        assertEquals(expectedOutput.get(0).getKeyValue(), result.get(0).getKeyValue());

        verify(pixKeyQueryValidationHandler, times(1)).validatePixKeyQuery(anyMap());
    }
}
