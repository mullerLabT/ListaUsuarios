package com.example.listaUsuarios.repository;

import com.example.listaUsuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNomeCompletoContainingIgnoreCaseOrNomeSocialContainingIgnoreCaseOrEmailContainingIgnoreCase(String nomeCompleto, String nomeSocial, String email);

}
