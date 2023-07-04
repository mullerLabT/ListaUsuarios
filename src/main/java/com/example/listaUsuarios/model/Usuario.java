package com.example.listaUsuarios.model;


import jakarta.persistence.*;
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
