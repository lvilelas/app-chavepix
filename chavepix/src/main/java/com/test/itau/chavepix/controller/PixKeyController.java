package com.test.itau.chavepix.controller;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.dto.PixKeyQueryDTO;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;

import com.test.itau.chavepix.service.PixKeysService;
import com.test.itau.chavepix.validation.pixkey.PixKeyRequestValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pix_key")
public class PixKeyController {


    @Autowired
    PixKeysService pixKeysService;

    @InitBinder("pixKeyDTO")
    public void initBinder(final WebDataBinder webDataBinder){
        webDataBinder.addValidators(new PixKeyRequestValidator());
    }

    @PostMapping("/create_pix_key")
    public PixKeyEntity createPixKey(@RequestBody @Valid PixKeyDTO pixKeyDTO){
        return pixKeysService.createPixKey(pixKeyDTO);
    }

    @GetMapping("/search_pix_key")
    public List<PixKeyEntity> updatePixKey(@RequestParam(value="id",required = false) UUID id,
                                           @RequestParam(value="tipo_chave",required = false) String keyTYpe,
                                           @RequestParam(value="numero_agencia",required = false) String agencyNumber,
                                           @RequestParam(value="numero_conta",required = false) String accountNumber,
                                           @RequestParam(value="nome_correntista",required = false)String accountHolderName){

        return pixKeysService.searchPixKey(new PixKeyQueryDTO(id,keyTYpe,agencyNumber,accountNumber,accountHolderName));
    }
}




