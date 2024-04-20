package com.test.itau.chavepix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import com.test.itau.chavepix.service.PixKeysService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PixKeyController.class)
public class PixKeyControllerTest  extends PixKeyDTOMocks {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    PixKeysService pixKeysService;

    @Test
    public void shouldCreatePixKey() throws Exception {
        mockMvc.perform(post("/pix_key/create_pix_key").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getValidCPFPixKeyMock())))
                .andExpect(status().isOk());
    }

    @Test
    public void shoudGetErrorInvalidField() throws Exception {
               mockMvc.perform(post("/pix_key/create_pixKey").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getInvalidPixKeyValueCPF())))
                .andExpect(status().is4xxClientError());
    }



}
