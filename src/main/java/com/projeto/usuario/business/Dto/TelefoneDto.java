package com.projeto.usuario.business.Dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class TelefoneDto {

    private long id;

    private String numero;

    private String ddd;
}

