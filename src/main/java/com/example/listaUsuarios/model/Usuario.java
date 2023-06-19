package com.example.listaUsuarios.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Usuarios")
public class Usuario {

    private String nomeCompleto;
    private String nomeSocial;
    private String dataDeNascimento;
    @Id
    private long codigo;
    private String sexo;
    private String email;
    private String estado;
    private String municipio;
    private long numeroDeAcessos;
    private String situacao;
    private String dataDeVinculo;


}
