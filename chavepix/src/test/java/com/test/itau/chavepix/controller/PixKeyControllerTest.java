package com.test.itau.chavepix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.itau.chavepix.dto.PixKeyDeleteOutDTO;
import com.test.itau.chavepix.dto.PixKeyQueryDTO;
import com.test.itau.chavepix.dto.PixQueryOutDTO;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import com.test.itau.chavepix.service.PixKeysService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PixKeyController.class)
public class PixKeyControllerTest  extends PixKeyDTOMocks {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    PixKeysService pixKeysService;

    @InjectMocks
    private PixKeyController pixKeyController;

    @Test
    public void shouldCreatePixKey() throws Exception {
        mockMvc.perform(post("/pix_key/create_pix_key").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getValidCPFPixKeyMock())))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdatePixKey() throws Exception {
        mockMvc.perform(put("/pix_key/update_pix_key").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getValidCPFPixKeyMock())))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetErrorInvalidField() throws Exception {
               mockMvc.perform(post("/pix_key/create_pixKey").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getInvalidPixKeyValueCPF())))
                .andExpect(status().is4xxClientError());
    }
//
//    @Test
//    public void shouldSearchPixKeys() throws Exception {
//        List<PixQueryOutDTO> list = new ArrayList<>();
//        when(pixKeysService.searchPixKey(new PixKeyQueryDTO(null,"cpf","1234",null,null),null)).thenReturn(list);
//        mockMvc.perform(get("/pix_key/search_pix_key").contentType(MediaType.APPLICATION_JSON)
//                        .param("id","0685ccec-e7a3-450e-8be0-e30ebddbc7ef"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }


//    @Test
//    public void shouldDeletePixKey() throws Exception {
//
//        UUID id = UUID.randomUUID();
//        PixKeyEntity pixKeyEntity = new PixKeyEntity(getValidCPFPixKeyMock());
//        pixKeyEntity.setDateTimeCreation(LocalDateTime.now());
//        pixKeyEntity.setDateTimeDelete(LocalDateTime.now());
//        PixKeyDeleteOutDTO expectedOutput = new PixKeyDeleteOutDTO(pixKeyEntity);
//
//        expectedOutput.setId(id);
//        when(pixKeysService.deletePixKey(id)).thenReturn(expectedOutput);
//        mockMvc.perform(delete("/pix_key/delete_pix_key/{id}","0685ccec-e7a3-450e-8be0-e30ebddbc7ef").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
}
