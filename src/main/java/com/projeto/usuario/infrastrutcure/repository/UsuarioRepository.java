package com.projeto.usuario.infrastrutcure.repository;


import com.projeto.usuario.infrastrutcure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

   boolean existsByEmail(String email);

   // Serve para evitar retorno de ifnormações nulas
   Optional<Usuario> findByEmail(String email);

   @Transactional
   void deleteByEmail(String email);

}
