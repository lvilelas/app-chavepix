package com.test.itau.chavepix.controller;

import br.com.fluentvalidator.context.ValidationResult;
import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.dto.*;
import com.test.itau.chavepix.mapper.PixKeyMapper;
import com.test.itau.chavepix.service.PixKeysService;
import com.test.itau.chavepix.validation.PixKeySearchValidation;
import com.test.itau.chavepix.validation.PixKeyUpdateValidation;
import com.test.itau.chavepix.validation.PixKeyValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pix_key")
public class PixKeyController {

    private final PixKeyValidation pixKeyValidation;
    private final PixKeyUpdateValidation pixKeyUpdateValidation;
    private final PixKeySearchValidation pixKeySearchValidation;
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



    @GetMapping(value = {"/","/{id}"})
    public List<PixQueryOutDTO> searchPixKey(@PathVariable(required = false) UUID id, @RequestParam Map<String, String> parameters){
        PixKeyQueryDTO pixKeyQueryDTO = new PixKeyQueryDTO(id,parameters);

        ValidationResult validationResult = pixKeySearchValidation.validate(pixKeyQueryDTO);
        if(!validationResult.isValid()){
            throw new RuntimeException("teste");
        }

        return pixKeysService.searchPixKey(pixKeyQueryDTO) ;
    }

    @DeleteMapping("/{id}")
    public PixKeyDeleteOutDTO deletePixKey(@PathVariable UUID id){

        return pixKeysService.deletePixKey(id);
    }


}




