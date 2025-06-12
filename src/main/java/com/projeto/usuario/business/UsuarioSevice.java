package com.projeto.usuario.business;

import com.projeto.usuario.business.Dto.UsuarioDto;
import com.projeto.usuario.business.converter.UsuarioConverter;
import com.projeto.usuario.infrastrutcure.entity.Usuario;
import com.projeto.usuario.infrastrutcure.exceptions.ConflictException;
import com.projeto.usuario.infrastrutcure.exceptions.ResourceNotFoundException;
import com.projeto.usuario.infrastrutcure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioSevice {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;


    public UsuarioDto salvaUsuario(UsuarioDto usuarioDto){

        emailExiste(usuarioDto.getEmail());
        usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDto);
        return usuarioConverter.paraUsuarioDto(usuarioRepository.save(usuario));
    }

    public void emailExiste(String email){
        try {
            boolean existe =   verificaEmailexistente(email);
            if(existe){
                throw new ConflictException("O email já cadastrado");
            }
        } catch (ConflictException e) {
            throw new RuntimeException("Emai já cadastrado "+ e.getMessage());
        }
    }
    public boolean verificaEmailexistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }



    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Email não encontrado"+ email));
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }
}
