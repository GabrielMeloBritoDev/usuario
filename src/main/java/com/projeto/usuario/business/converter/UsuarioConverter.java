package com.projeto.usuario.business.converter;

import com.projeto.usuario.business.Dto.EnderecoDto;
import com.projeto.usuario.business.Dto.TelefoneDto;
import com.projeto.usuario.business.Dto.UsuarioDto;
import com.projeto.usuario.infrastrutcure.entity.Endereco;
import com.projeto.usuario.infrastrutcure.entity.Telefone;
import com.projeto.usuario.infrastrutcure.entity.Usuario;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDto usuarioDto){
         return   Usuario.builder()
                 .nome(usuarioDto.getNome())
                 .email(usuarioDto.getEmail())
                 .senha(usuarioDto.getSenha())
                 .enderecos(paralistaEndereco(usuarioDto.getEnderecos()))
                 .telefones(paralistaTelefone(usuarioDto.getTelefones()))
                 .build();

    }
    //Este método transforma uma lista de EnderecoDto em uma lista de Endereco usando Java Streams
    public List<Endereco> paralistaEndereco(List<EnderecoDto> enderecoDtos){
        return enderecoDtos.stream().map((this::paraEndereco)).toList();
    }

    //Esse método transforma um único EnderecoDto em Endereco também usando o padrão Builder
    public Endereco paraEndereco(EnderecoDto enderecoDto){
        return  Endereco.builder()
                .rua(enderecoDto.getRua())
                .numero(enderecoDto.getNumero())
                .cidade(enderecoDto.getCidade())
                .complemento(enderecoDto.getComplemento())
                .cep(enderecoDto.getCep())
                .estado(enderecoDto.getEstado())
                .build();
    }
    public List<Telefone> paralistaTelefone(List<TelefoneDto> telefoneDtos){
        if(telefoneDtos == null || telefoneDtos.isEmpty()){
            return Collections.emptyList();
        }
        return telefoneDtos.stream().map((this::paraTelefone)).toList();
    }

    public Telefone paraTelefone(TelefoneDto telefoneDto){
        return Telefone.builder()
                .numero(telefoneDto.getNumero())
                .ddd(telefoneDto.getDdd())
                .build();
    }






    public UsuarioDto paraUsuarioDto(Usuario usuario){
        return   UsuarioDto.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paralistaEnderecoDto(usuario.getEnderecos()))
                .telefones(paralistaTelefoneDto(usuario.getTelefones()))
                .build();

    }

    public List<EnderecoDto> paralistaEnderecoDto(List<Endereco> enderecos){
        return enderecos.stream().map((this::paraEnderecoDto)).toList();
    }

    public EnderecoDto paraEnderecoDto(Endereco endereco){
        return  EnderecoDto.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }

    public List<TelefoneDto> paralistaTelefoneDto(List<Telefone> telefones){
        return telefones.stream().map((this::paraTelefoneDto)).toList();
    }

    public TelefoneDto paraTelefoneDto(Telefone telefone){
        return TelefoneDto.builder()
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

}
