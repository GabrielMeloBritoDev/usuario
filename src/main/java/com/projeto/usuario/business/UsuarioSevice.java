package com.projeto.usuario.business;

import com.projeto.usuario.UsuarioApplication;
import com.projeto.usuario.business.Dto.EnderecoDto;
import com.projeto.usuario.business.Dto.TelefoneDto;
import com.projeto.usuario.business.Dto.UsuarioDto;
import com.projeto.usuario.business.converter.UsuarioConverter;
import com.projeto.usuario.infrastrutcure.entity.Endereco;
import com.projeto.usuario.infrastrutcure.entity.Telefone;
import com.projeto.usuario.infrastrutcure.entity.Usuario;
import com.projeto.usuario.infrastrutcure.exceptions.ConflictException;
import com.projeto.usuario.infrastrutcure.exceptions.ResourceNotFoundException;
import com.projeto.usuario.infrastrutcure.repository.EnderecoRepository;
import com.projeto.usuario.infrastrutcure.repository.TelefoneRepository;
import com.projeto.usuario.infrastrutcure.repository.UsuarioRepository;
import com.projeto.usuario.infrastrutcure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioSevice {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;

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



    public UsuarioDto buscarUsuarioPorEmail(String email){
        try {
            return usuarioConverter.paraUsuarioDto(usuarioRepository.findByEmail(email).orElseThrow(
                    ()-> new ResourceNotFoundException("Email não encontrado"+ email)));

        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Email não encotrado "+email);
        }
    }






    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDto atualizarDadosUsuario(String token ,UsuarioDto dto){
        //Aqui busquei tirar a obriatoriedade do email
      String email =  jwtUtil.extrairEmailDoToken(token.substring(7));
        //crip de senha
      dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);
      //Busquei os dado no BD
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("Email não localizado"));

        Usuario usuario = usuarioConverter.atualizarDto(dto, usuarioEntity);
        return  usuarioConverter.paraUsuarioDto(usuarioRepository.save(usuario));
    }

    public EnderecoDto atualizaEndereco (Long id, EnderecoDto enderecoDto){
        Endereco end = enderecoRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Id não encontrado "+ id));


        Endereco endereco = usuarioConverter.atualizarEndereco(enderecoDto, end );
        return usuarioConverter.paraEnderecoDto(enderecoRepository.save(endereco));

    }


    public TelefoneDto atualizaTelefone (Long id, TelefoneDto telefoneDto){
        Telefone tel = telefoneRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Id não encontrado "+ id));


        Telefone telefone = usuarioConverter.atualizaTelefone(telefoneDto, tel );
        return usuarioConverter.paraTelefoneDto(telefoneRepository.save(telefone));

    }

    public EnderecoDto cadastraEndereco(String token, EnderecoDto enderecoDto){
        String email = jwtUtil.extrairEmailDoToken(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("Email não localizado "+email));
        Endereco endereco = usuarioConverter.paraEnderecoEntity(enderecoDto, usuario.getId());
        return usuarioConverter.paraEnderecoDto(enderecoRepository.save(endereco));
    }


    public TelefoneDto cadastraTelefone(String token, TelefoneDto  telefoneDto){
        String email = jwtUtil.extrairEmailDoToken(token.substring(7));
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("Email não localizado "+email));
        Telefone telefone = usuarioConverter.paraTelefoneEntity(telefoneDto, usuario.getId());

        return usuarioConverter.paraTelefoneDto(telefoneRepository.save(telefone));
    }
}
