package com.example.listaUsuarios.controller;

import com.example.listaUsuarios.model.Usuario;
import com.example.listaUsuarios.repository.UsuarioRepositorio;
import com.example.listaUsuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioRepositorio usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepositorio = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @GetMapping("{codigo}")
    public Optional<Usuario> consultaUsuario(@PathVariable Long codigo){
        return usuarioRepositorio.findById(codigo);
    }

    @GetMapping
    public List<Usuario> listaFiltroUsuario(@RequestParam("filtro") String filtro){
        return usuarioRepositorio.findByNomeCompletoContainingIgnoreCaseOrNomeSocialContainingIgnoreCaseOrEmailContainingIgnoreCase(filtro, filtro, filtro);
    }

    @PostMapping
    public void incluirUsuario(@RequestBody Usuario usuario){
        usuarioRepositorio.save(usuario);
    }

    @PutMapping
    public void alterarUsuario(@RequestBody Usuario usuario){
        usuarioRepositorio.save(usuario);
    }

    @DeleteMapping("/{codigo}")
    public void excluiUsuario(@PathVariable Long codigo){
        usuarioRepositorio.deleteById(codigo);
    }

    @PostMapping("/carregaCSV")
    public void processCsvFile() {
        String filePath = "usuarios.csv";
        ClassPathResource resource = new ClassPathResource(filePath);
        File file;
        try {
            file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        usuarioService.processarArquivoCsv(file.getAbsolutePath());

    }

}
