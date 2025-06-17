package com.projeto.usuario.Controller;

import com.projeto.usuario.business.Dto.EnderecoDto;
import com.projeto.usuario.business.Dto.TelefoneDto;
import com.projeto.usuario.business.Dto.UsuarioDto;
import com.projeto.usuario.business.UsuarioSevice;
import com.projeto.usuario.infrastrutcure.entity.Usuario;
import com.projeto.usuario.infrastrutcure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class usuarioController {


    private final UsuarioSevice usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    //Cadastro de usuario feito!
    @PostMapping
    public ResponseEntity<UsuarioDto> salvaUsuario(@RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDto));
    }
    //Cadastro de login
    @PostMapping("/login")
    public String login(@RequestBody UsuarioDto usuarioDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDto.getEmail(), usuarioDto.getSenha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }
    //Buscar usuario por email
    @GetMapping
    public ResponseEntity<UsuarioDto>  buscarUsuarioPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }



    //Deletar usuario por email
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUsuarioPorEmail(@PathVariable String email){
        usuarioService.deletaUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDto> atualizarDadosUsuario(@RequestBody UsuarioDto dto,
                                                            @RequestHeader("Authorization")String token){

        return   ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token,dto));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDto> atualizaEndereco(@RequestBody EnderecoDto dto,
                                                             @RequestParam("id") Long id){
        return   ResponseEntity.ok(usuarioService.atualizaEndereco(id,dto));
    }


    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDto> atualizaTelefone(@RequestBody TelefoneDto dto,
                                                        @RequestParam("id") Long id){
        return   ResponseEntity.ok(usuarioService.atualizaTelefone(id,dto));
    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDto> cadastraEndereco(@RequestBody EnderecoDto dto,
                                                        @RequestHeader("Authorization") String token){
        return   ResponseEntity.ok(usuarioService.cadastraEndereco(token,dto));
    }
    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDto> cadastraEndereco(@RequestBody TelefoneDto dto,
                                                        @RequestHeader("Authorization") String token){
        return   ResponseEntity.ok(usuarioService.cadastraTelefone(token,dto));
    }
}
