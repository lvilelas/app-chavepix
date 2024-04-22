package com.test.itau.chavepix.service;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.dto.PixKeyDeleteOutDTO;
import com.test.itau.chavepix.dto.PixKeyQueryDTO;
import com.test.itau.chavepix.dto.PixQueryOutDTO;
import com.test.itau.chavepix.exceptions.InvalidFieldException;
import com.test.itau.chavepix.exceptions.PixKeyNotFoundException;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import com.test.itau.chavepix.model.AccountPixKeysModel;
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
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PixKeysServiceTest extends PixKeyDTOMocks {

    @Mock
    private PixKeyRepository pixKeyRepository;

    @Mock
    private PixKeyValidationHandler pixKeyValidationHandler;

    @Mock
    @Qualifier("createRequestChain")
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
        pixKeyEntity.setId(UUID.randomUUID());
        List<PixKeyEntity> list = Collections.nCopies(5, new PixKeyEntity(getValidCPFPixKeyMock()));

        // Mocking repository behavior

        when(pixKeyRepository.findByAgencyNumberAndAccountNumber(anyString(), anyString())).thenReturn(list);
        when(pixKeyRepository.save(any())).thenReturn(pixKeyEntity);

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
        List<PixQueryOutDTO> result = pixKeysService.searchPixKey(pixKeyQueryDTO,null);

        // Verifying the result
        assertEquals(expectedOutput.size(), result.size());
        assertEquals(expectedOutput.get(0).getKeyValue(), result.get(0).getKeyValue());

        verify(pixKeyQueryValidationHandler, times(1)).validatePixKeyQuery(anyMap());
    }

    @Test
    void testDeletePixKey() {
        // Mocking PixKeyEntity
        UUID id = UUID.randomUUID();
        PixKeyEntity pixKeyEntity = new PixKeyEntity(getValidCPFPixKeyMock());
        pixKeyEntity.setId(id);
        pixKeyEntity.setDateTimeCreation(LocalDateTime.now());
        pixKeyEntity.setDateTimeDelete(null);

        // Mocking repository behavior
        when(pixKeyRepository.findById(id)).thenReturn(Optional.of(pixKeyEntity));
        when(pixKeyRepository.save(any(PixKeyEntity.class))).thenReturn(pixKeyEntity);

        // Performing the test
        PixKeyDeleteOutDTO result = pixKeysService.deletePixKey(id);

        // Verifying the result
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertNotNull(result.getDateTimeDelete());

        // Verifying repository interactions
        verify(pixKeyRepository, times(1)).findById(id);
        verify(pixKeyRepository, times(1)).save(any(PixKeyEntity.class));
    }

    @Test
    void testDeletePixKeyNotFound() {
        // Mocking repository behavior
        when(pixKeyRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // Performing the test and verifying the exception
        assertThrows(PixKeyNotFoundException.class, () -> pixKeysService.deletePixKey(UUID.randomUUID()));

        // Verifying repository interactions
        verify(pixKeyRepository, times(1)).findById(any(UUID.class));
        verify(pixKeyRepository, never()).save(any(PixKeyEntity.class));
    }

    @Test
    void testDeletePixKeyAlreadyDeleted() {
        // Mocking PixKeyEntity
        UUID id = UUID.randomUUID();
        PixKeyEntity pixKeyEntity = new PixKeyEntity();
        pixKeyEntity.setId(id);
        pixKeyEntity.setDateTimeDelete(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))); // Chave Pix já foi excluída

        // Mocking repository behavior
        when(pixKeyRepository.findById(id)).thenReturn(Optional.of(pixKeyEntity));

        // Performing the test and verifying the exception
        assertThrows(RuntimeException.class, () -> pixKeysService.deletePixKey(id));

        // Verifying repository interactions
        verify(pixKeyRepository, times(1)).findById(id);
        verify(pixKeyRepository, never()).save(any(PixKeyEntity.class));
    }
}
