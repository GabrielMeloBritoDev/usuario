package com.projeto.usuario.infrastrutcure.repository;


import com.projeto.usuario.infrastrutcure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone,Long> {
}
