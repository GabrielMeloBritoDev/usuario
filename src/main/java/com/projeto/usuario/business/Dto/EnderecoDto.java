package com.projeto.usuario.business.Dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDto {

    private String rua;

    private Long numero;

    private String complemento;

    private String cidade;

    private String estado;

    private String cep;
}
