package com.test.itau.chavepix.controller;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;

import com.test.itau.chavepix.service.PixKeysService;
import com.test.itau.chavepix.validation.PixKeyRequestValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pix_key")
public class PixKeyController {


    @Autowired
    PixKeysService pixKeysService;

    @InitBinder("pixKeyDTO")
    public void initBinder(final WebDataBinder webDataBinder){
        webDataBinder.addValidators(new PixKeyRequestValidator());
    }

    @PostMapping("/create_pixKey")
    public PixKeyEntity createPixKey(@RequestBody @Valid PixKeyDTO pixKeyDTO){
        return pixKeysService.createPixKey(pixKeyDTO);

    }

}
