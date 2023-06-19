
package com.example.listaUsuarios.service;

import com.example.listaUsuarios.model.Usuario;
import com.example.listaUsuarios.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public UsuarioService(UsuarioRepositorio usuarioRepositorio){
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public void processarArquivoCsv(String filePath){
        List<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            String line;
            boolean primeiraLinha = true;

            while((line = reader.readLine()) != null){
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                Usuario usuario = criarUsuarioAPartirDaLinhaCsv(line);
                usuarios.add(usuario);
            }
            usuarioRepositorio.saveAll(usuarios);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private Usuario criarUsuarioAPartirDaLinhaCsv(String line){
        String[] dados = line.split(",");

        Usuario usuario = new Usuario();

        usuario.setNomeCompleto(dados[0]);
        usuario.setNomeSocial(dados[1]);
        usuario.setDataDeNascimento(dados[2]);
        usuario.setCodigo(Long.parseLong(dados[3]));
        usuario.setSexo(dados[4]);
        usuario.setEmail(dados[5]);
        usuario.setEstado(dados[6]);
        usuario.setMunicipio(dados[7]);
        usuario.setNumeroDeAcessos(Long.parseLong((dados[8])));
        usuario.setSituacao(dados[9]);
        usuario.setDataDeVinculo(dados[10]);

        return usuario;
    }
}
