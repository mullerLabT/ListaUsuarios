package com.example.listaUsuarios.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDto {

    @JsonProperty(value = "nomeCompleto")
    private String nomeCompleto;
    @JsonProperty(value = "nomeSocial")
    private String nomeSocial;
    @JsonProperty(value = "dataDeNascimento")
    private String dataDeNascimento;
    @JsonProperty(value = "codigo")
    private long codigo;
    @JsonProperty(value = "sexo")
    private String sexo;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "estado")
    private String estado;
    @JsonProperty(value = "municipio")
    private String municipio;
    @JsonProperty(value = "numeroDeAcessos")
    private long numeroDeAcessos;
    @JsonProperty(value = "situacao")
    private String situacao;
    @JsonProperty(value = "dataDeVinculo")
    private String dataDeVinculo;
}
