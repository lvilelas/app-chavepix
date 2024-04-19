package com.test.itau.chavepix.controller;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.entity.PixKeyEntity;

import com.test.itau.chavepix.service.PixKeysService;
import com.test.itau.chavepix.validation.PixKeyValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pix_key")
public class PixKeyController {


    @Autowired
    PixKeysService pixKeysService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new PixKeyValidator());
    }

    @PostMapping("/create_pixKey")
    public PixKeyEntity createPixKey(@RequestBody @Valid PixKeyDTO pixKeyDTO){
        return pixKeysService.createPixKey(pixKeyDTO);

    }

}
