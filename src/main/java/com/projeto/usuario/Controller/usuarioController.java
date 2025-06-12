package com.projeto.usuario.Controller;

import com.projeto.usuario.business.Dto.UsuarioDto;
import com.projeto.usuario.business.UsuarioSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class usuarioController {


    private final UsuarioSevice usuarioSevice;
    @PostMapping
    public ResponseEntity<UsuarioDto> salvaUsuario(@RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(usuarioSevice.salvaUsuario(usuarioDto));
    }
}
