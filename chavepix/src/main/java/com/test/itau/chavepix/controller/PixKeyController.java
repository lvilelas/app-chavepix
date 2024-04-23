package com.test.itau.chavepix.controller;

import br.com.fluentvalidator.context.ValidationResult;
import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.dto.*;
import com.test.itau.chavepix.mapper.PixKeyMapper;
import com.test.itau.chavepix.service.PixKeysService;
import com.test.itau.chavepix.validation.PixKeyUpdateValidation;
import com.test.itau.chavepix.validation.PixKeyValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pix_key")
public class PixKeyController {

    private final PixKeyValidation pixKeyValidation;
    private final PixKeyUpdateValidation pixKeyUpdateValidation;
    private final PixKeysService pixKeysService;

    @PostMapping
    public PixKeyOutDTO createPixKey(@RequestBody PixKeyDTO pixKeyDTO){
        ValidationResult validationResult = pixKeyValidation.validate(pixKeyDTO);
        if(!validationResult.isValid()){
            throw new RuntimeException("teste");
        }
        PixKey pixKey = PixKeyMapper.INSTANCE.toPixKey(pixKeyDTO);

        return PixKeyMapper.INSTANCE.toPixKeyOutDTO(pixKeysService.createPixKey(pixKey));
    }

    @PutMapping
    public PixKeyOutDTO updatePixKey(@RequestBody PixKeyUpdateDTO pixKeyUpdateDTO){
        ValidationResult validationResult = pixKeyUpdateValidation.validate(pixKeyUpdateDTO);
        if(!validationResult.isValid()){
            throw new RuntimeException("teste");
        }
        PixKey pixKey = PixKeyMapper.INSTANCE.toPixKey(pixKeyUpdateDTO);
        return PixKeyMapper.INSTANCE.toPixKeyOutDTO(pixKeysService.updatePixKey(pixKey));
    }



//
//    @GetMapping
//    public PixQueryOutDTO searchPixKeyById(@RequestParam(value="id",required = false) UUID id)String accountHolderName, HttpServletResponse response){
//
//        return pixKeysService.searchPixKey(new PixKeyQueryDTO(id,keyTYpe,agencyNumber,accountNumber,accountHolderName),response) ;
//    }

//    @GetMapping
//    public List<PixQueryOutDTO> searchPixKey(@RequestParam(value="tipo_chave",required = false) String keyTYpe,
//                                             @RequestParam(value="numero_agencia",required = false) BigDecimal agencyNumber,
//                                             @RequestParam(value="numero_conta",required = false) BigDecimal accountNumber,
//                                             @RequestParam(value="nome_correntista",required = false)String accountHolderName){
//
//        PixKeyQueryDTO pixKeyQueryDTO = new PixKeyQueryDTO(keyTYpe,agencyNumber,accountNumber,accountHolderName);
//        PixKey pixKey = PixKeyMapper.INSTANCE.toPixKey(pixKeyQueryDTO);
//        return pixKeysService.searchPixKey(pixKey) ;
//    }
//
//    @DeleteMapping
//    public PixKeyDeleteOutDTO deletePixKey(@PathVariable UUID id){
//
//        return pixKeysService.deletePixKey(id);
//    }


}




