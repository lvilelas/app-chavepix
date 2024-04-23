package com.test.itau.chavepix.controller;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;
import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.dto.*;

import com.test.itau.chavepix.exceptions.InvalidFieldException;
import com.test.itau.chavepix.mapper.PixKeyMapper;
import com.test.itau.chavepix.service.PixKeysService;
import com.test.itau.chavepix.validation.PixKeyValidation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pix_key")
public class PixKeyController {

    private final PixKeyValidation pixKeyValidation;
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

//    @PutMapping
//    public PixKeyOutDTO searchPixKey(@RequestBody PixKeyDTO pixKeyDTO){
//        return pixKeysService.updatePixKey(pixKeyDTO);
//    }
//
//    @GetMapping
//    public List<PixQueryOutDTO> searchPixKey(@RequestParam(value="id",required = false) UUID id,
//                                             @RequestParam(value="tipo_chave",required = false) String keyTYpe,
//                                             @RequestParam(value="numero_agencia",required = false) String agencyNumber,
//                                             @RequestParam(value="numero_conta",required = false) String accountNumber,
//                                             @RequestParam(value="nome_correntista",required = false)String accountHolderName, HttpServletResponse response){
//
//
//        return pixKeysService.searchPixKey(new PixKeyQueryDTO(id,keyTYpe,agencyNumber,accountNumber,accountHolderName),response) ;
//    }
//
//    @DeleteMapping
//    public PixKeyDeleteOutDTO deletePixKey(@PathVariable UUID id){
//
//        return pixKeysService.deletePixKey(id);
//    }


}




