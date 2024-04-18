package com.test.itau.chavepix.controller;

import com.test.itau.chavepix.dto.ChavePixDTO;
import com.test.itau.chavepix.dto.TipoChave;
import com.test.itau.chavepix.dto.TipoPessoa;
import com.test.itau.chavepix.entity.ChavePixEntity;
import com.test.itau.chavepix.repository.ChavePixReposotiry;

import com.test.itau.chavepix.validation.ChavePixValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chaves_pix")
public class ChavePixController {

    @Autowired
    private ChavePixReposotiry chavePixReposotiry;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new ChavePixValidator());
    }

    @RequestMapping("/cadastraChavePix")
    public void cadastrarChavePix( @RequestBody @Valid ChavePixDTO chavePixDTO){

        ChavePixEntity novaChave = new ChavePixEntity(chavePixDTO);

        if(chavePixReposotiry.existsByValorChave(novaChave.getValorChave())){
            System.out.println("Erro ao cadastrar chave pix");
        }else{

            long chaves = chavePixReposotiry.countByNumeroAgenciaAndNumeroConta(novaChave.getNumeroAgencia(),novaChave.getNumeroConta());
            if((chavePixDTO.getTipoPessoa().equals(TipoPessoa.FISICA) && chaves<5) || chavePixDTO.getTipoPessoa().equals(TipoPessoa.JURIDICA) && chaves<19) {
                    chavePixReposotiry.save(novaChave);
            }else{
                System.out.println("Erro ao cadastrar chave pix");
            }
        }
    }



}
