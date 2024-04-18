package com.test.itau.chavepix.entity;

import com.test.itau.chavepix.dto.ChavePixDTO;
import com.test.itau.chavepix.dto.TipoChave;
import com.test.itau.chavepix.dto.TipoConta;
import com.test.itau.chavepix.dto.TipoPessoa;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "chaves_pix")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class ChavePixEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private TipoChave tipoChave;
    private String valorChave;
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;
    private String numeroAgencia;
    private String numeroConta;
    private String nomeCorrentista;
    private String sobrenomeCorrentista;


    public ChavePixEntity(ChavePixDTO chavePixDTO) {
        this.tipoChave = chavePixDTO.getTipoChave();
        this.valorChave  = chavePixDTO.getValorChave();
        this.tipoConta = chavePixDTO.getTipoConta();
        this.tipoPessoa = chavePixDTO.getTipoPessoa();
        this.numeroAgencia = chavePixDTO.getNumeroAgencia();
        this.numeroConta = chavePixDTO.getNumeroConta();
        this.nomeCorrentista = chavePixDTO.getNomeCorrentista();
        this.sobrenomeCorrentista = chavePixDTO.getSobrenomeCorrentista();
    }
}
