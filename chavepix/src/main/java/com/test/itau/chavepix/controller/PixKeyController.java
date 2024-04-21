package com.test.itau.chavepix.controller;

import com.test.itau.chavepix.dto.*;

import com.test.itau.chavepix.service.PixKeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pix_key")
public class PixKeyController {


    @Autowired
    PixKeysService pixKeysService;

    @PostMapping("/create_pix_key")
    public PixKeyOutDTO createPixKey(@RequestBody PixKeyDTO pixKeyDTO){
        return pixKeysService.createPixKey(pixKeyDTO);
    }

    @GetMapping("/search_pix_key")
    public List<PixQueryOutDTO> updatePixKey(@RequestParam(value="id",required = false) UUID id,
                                             @RequestParam(value="tipo_chave",required = false) String keyTYpe,
                                             @RequestParam(value="numero_agencia",required = false) String agencyNumber,
                                             @RequestParam(value="numero_conta",required = false) String accountNumber,
                                             @RequestParam(value="nome_correntista",required = false)String accountHolderName){

        return pixKeysService.searchPixKey(new PixKeyQueryDTO(id,keyTYpe,agencyNumber,accountNumber,accountHolderName));
    }

    @DeleteMapping("/delete_pix_key/{id}")
    public PixKeyDeleteOutDTO deletePixKey(@PathVariable UUID id){

        return pixKeysService.deletePixKey(id);
    }


}




