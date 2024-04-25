package com.test.itau.chavepix.controller;

import br.com.fluentvalidator.context.ValidationResult;
import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.dto.*;
import com.test.itau.chavepix.exceptions.PixKeyException;
import com.test.itau.chavepix.mapper.PixKeyMapper;
import com.test.itau.chavepix.service.PixKeysService;
import com.test.itau.chavepix.validation.PixKeySearchValidation;
import com.test.itau.chavepix.validation.PixKeyUpdateValidation;
import com.test.itau.chavepix.validation.PixKeyValidation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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

    private static final Logger log = LoggerFactory.getLogger(PixKeyController.class);

    @PostMapping
    public PixKeyOutDTO createPixKey(@RequestBody PixKeyDTO pixKeyDTO){
        log.info("Create pix key request : {}", pixKeyDTO.toString());
        ValidationResult validationResult = pixKeyValidation.validate(pixKeyDTO);

        validationResult.isInvalidThrow(PixKeyException.class);

        log.info("pix key fields validation success");
        PixKey pixKey = PixKeyMapper.INSTANCE.toPixKey(pixKeyDTO);

        return PixKeyMapper.INSTANCE.toPixKeyOutDTO(pixKeysService.createPixKey(pixKey));
    }


    @PatchMapping
    public PixKeyOutDTO updatePixKey(@RequestBody PixKeyUpdateDTO pixKeyUpdateDTO){
        log.info("Update pix key request : {}", pixKeyUpdateDTO.toString());
        ValidationResult validationResult = pixKeyUpdateValidation.validate(pixKeyUpdateDTO);

        validationResult.isInvalidThrow(PixKeyException.class);
        log.info("pix key fields validation success");
        PixKey pixKey = PixKeyMapper.INSTANCE.toPixKey(pixKeyUpdateDTO);
        return PixKeyMapper.INSTANCE.toPixKeyOutDTO(pixKeysService.updatePixKey(pixKey));
    }



    @GetMapping(value = {"/","/{id}"})
    public List<PixQueryOutDTO> searchPixKey(@PathVariable(required = false) UUID id, @RequestParam Map<String, String> parameters){
        log.info("Create pix key request : {} {}", id, parameters.toString());
        PixKeyQueryDTO pixKeyQueryDTO = new PixKeyQueryDTO(id,parameters);

        ValidationResult validationResult = pixKeySearchValidation.validate(pixKeyQueryDTO);

        validationResult.isInvalidThrow(PixKeyException.class);
        log.info("pix key fields validation success");
        return pixKeysService.searchPixKey(PixKeyMapper.INSTANCE.toPixKeyQuery(pixKeyQueryDTO));
    }


    @DeleteMapping("/{id}")
    public PixKeyDeleteOutDTO deletePixKey(@PathVariable UUID id){
        log.info("Delete pix key request : {}",id.toString());
        return pixKeysService.deletePixKey(id);
    }

}




