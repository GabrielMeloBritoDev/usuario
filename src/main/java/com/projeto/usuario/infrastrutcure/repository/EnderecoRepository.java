package com.projeto.usuario.infrastrutcure.repository;

import com.projeto.usuario.infrastrutcure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Long> {
}
