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
                .id(endereco.getId())
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
                .id(telefone.getId())
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

    // Metodo de atualizar somente o usuario, sem  endereco e telefone
    public Usuario atualizarDto (UsuarioDto usuarioDto, Usuario entity){
        return  Usuario.builder()
                .nome(usuarioDto.getNome() != null ? usuarioDto.getNome() : entity.getNome())
                .id(entity.getId())
                .senha(usuarioDto.getSenha() !=  null ? usuarioDto.getSenha() : entity.getSenha())
                .email(usuarioDto.getEmail() != null ? usuarioDto.getEmail() : entity.getEmail())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }

    public Endereco atualizarEndereco (EnderecoDto enderecoDto, Endereco endereco){
        return  Endereco.builder()
                .id(endereco.getId())
                .rua(enderecoDto.getRua() != null ? enderecoDto.getRua() : endereco.getRua())
                .numero(enderecoDto.getNumero() != null ? enderecoDto.getNumero() : endereco.getNumero())
                .cidade(enderecoDto.getCidade() != null ? enderecoDto.getCidade() : endereco.getCidade())
                .cep(enderecoDto.getCep() != null ? enderecoDto.getCep() : endereco.getCep())
                .complemento(enderecoDto.getComplemento() != null ? enderecoDto.getComplemento() : endereco.getComplemento())
                .estado(enderecoDto.getEstado() != null ? enderecoDto.getEstado() : endereco.getEstado())
                .build();
    }


    public Telefone atualizaTelefone(TelefoneDto telefoneDto, Telefone telefone) {
        return Telefone.builder()
                .id(telefone.getId())
                .numero(telefoneDto.getNumero() != null ? telefoneDto.getNumero() : telefone.getNumero())
                .ddd(telefoneDto.getDdd() != null ? telefoneDto.getDdd() : telefone.getDdd() )
                .build();
    }
}
