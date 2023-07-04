package com.example.listaUsuarios.business;

import com.example.listaUsuarios.dto.UsuarioDto;
import com.example.listaUsuarios.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {
    public static UsuarioDto toDto(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.setNomeCompleto(usuario.getNomeCompleto());
        dto.setNomeSocial(usuario.getNomeSocial());
        dto.setDataDeNascimento(usuario.getDataDeNascimento());
        dto.setCodigo(usuario.getCodigo());
        dto.setSexo(usuario.getSexo());
        dto.setEmail(usuario.getEmail());
        dto.setEstado(usuario.getEstado());
        dto.setMunicipio(usuario.getMunicipio());
        dto.setNumeroDeAcessos(usuario.getNumeroDeAcessos());
        dto.setSituacao(usuario.getSituacao());
        dto.setDataDeVinculo(usuario.getDataDeVinculo());
        return dto;
    }

    public static Usuario toModel(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(dto.getNomeCompleto());
        usuario.setNomeSocial(dto.getNomeSocial());
        usuario.setDataDeNascimento(dto.getDataDeNascimento());
        usuario.setCodigo(dto.getCodigo());
        usuario.setSexo(dto.getSexo());
        usuario.setEmail(dto.getEmail());
        usuario.setEstado(dto.getEstado());
        usuario.setMunicipio(dto.getMunicipio());
        usuario.setNumeroDeAcessos(dto.getNumeroDeAcessos());
        usuario.setSituacao(dto.getSituacao());
        usuario.setDataDeVinculo(dto.getDataDeVinculo());
        return usuario;
    }
}
