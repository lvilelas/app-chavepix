package com.test.itau.chavepix.controller;

import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.domain.PixKeyQuery;
import com.test.itau.chavepix.dto.*;
import com.test.itau.chavepix.exceptions.PixKeyException;
import com.test.itau.chavepix.mapper.PixKeyMapper;
import com.test.itau.chavepix.mapper.PixKeyMapperImpl;
import com.test.itau.chavepix.mapper.PixKeyUpdateMapper;
import com.test.itau.chavepix.mapper.PixKeyUpdateMapperImpl;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import com.test.itau.chavepix.service.PixKeysService;
import com.test.itau.chavepix.validation.PixKeySearchValidation;
import com.test.itau.chavepix.validation.PixKeyUpdateValidation;
import com.test.itau.chavepix.validation.PixKeyValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;



public class PixKeyControllerTest extends PixKeyDTOMocks {

    @Mock
    private PixKeyValidation pixKeyValidation;

    @Mock
    private PixKeyUpdateValidation pixKeyUpdateValidation;

    @Mock
    private PixKeySearchValidation pixKeySearchValidation;

    @Mock
    private PixKeysService pixKeysService;

    @Spy
    private PixKeyMapper pixKeyMapperImpl =  new PixKeyMapperImpl();

    @Spy
    private PixKeyUpdateMapper pixKeyUpdateMapperImpl =  new PixKeyUpdateMapperImpl();

    @InjectMocks
    private PixKeyController pixKeyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePixKey() {


        // Mocking the request body
        PixKeyDTO pixKeyDTO = getValidCPFPixKeyMock();

        // Mocking validation result
        ValidationResult validationResult = ValidationResult.ok();
        when(pixKeyValidation.validate(any(PixKeyDTO.class))).thenReturn(validationResult);
        // Mocking service behavior
        when(pixKeysService.createPixKey(any(PixKey.class))).thenReturn(getValidPixKey());

        // Calling the method under test
        PixKeyOutDTO result = pixKeyController.createPixKey(pixKeyDTO);

        // Verifying if the service method was called
        verify(pixKeysService, times(1)).createPixKey(any(PixKey.class));
    }

    @Test
    public void testCreatePixKeyError() {

        // Mocking the request body
        PixKeyDTO pixKeyDTO = getValidCPFPixKeyMock();

        Collection<Error> collections = new ArrayList<Error>();

        Error error =  Error.create("teste","teste","teste",getInvalidAccountNumberMock());

        collections.add(error);

        ValidationResult validationResult = ValidationResult.fail(collections);
        when(pixKeyValidation.validate(any(PixKeyDTO.class))).thenReturn(validationResult);

        assertThrows(PixKeyException.class,() -> pixKeyController.createPixKey(pixKeyDTO));
    }

    @Test
    public void testUpdatePixKey() {
        // Mocking the request body
        PixKeyUpdateDTO pixKeyUpdateDTO = getPixUpdated();

        // Mocking validation result
        when(pixKeyUpdateValidation.validate(pixKeyUpdateDTO)).thenReturn(ValidationResult.ok());

        // Mocking service behavior
        when(pixKeysService.updatePixKey(any(PixKey.class))).thenReturn(getValidPixKey());

        // Calling the method under test
        PixKeyOutDTO result = pixKeyController.updatePixKey(pixKeyUpdateDTO);

        // Verifying if the service method was called
        verify(pixKeysService, times(1)).updatePixKey(any(PixKey.class));
    }

    @Test
    public void testSearchPixKey() {
        // Mocking path variable and request parameter
        UUID id = UUID.randomUUID();
        Map<String, String> parameters = new HashMap<>();

        // Mocking validation result
        when(pixKeySearchValidation.validate(any(PixKeyQueryDTO.class))).thenReturn(ValidationResult.ok());

        // Mocking service behavior
        when(pixKeysService.searchPixKey(any(PixKeyQuery.class))).thenReturn(Collections.singletonList(null));

        // Calling the method under test
        List<PixQueryOutDTO> result = pixKeyController.searchPixKey(id, parameters);

        // Verifying if the service method was called
        verify(pixKeysService, times(1)).searchPixKey(any(PixKeyQuery.class));
    }

    @Test
    public void testDeletePixKey() {
        // Mocking path variable
        UUID id = UUID.randomUUID();

        // Mocking service behavior
        when(pixKeysService.deletePixKey(id)).thenReturn(getPixKeyDeleteOutDTO());

        // Calling the method under test
        PixKeyDeleteOutDTO result = pixKeyController.deletePixKey(id);

        // Verifying if the service method was called
        verify(pixKeysService, times(1)).deletePixKey(id);
    }
}
