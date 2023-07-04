package com.example.listaUsuarios.controller;

import com.example.listaUsuarios.business.UsuarioConverter;
import com.example.listaUsuarios.dto.UsuarioDto;
import com.example.listaUsuarios.model.Usuario;
import com.example.listaUsuarios.repository.UsuarioRepositorio;
import com.example.listaUsuarios.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ApiOperation(value = "Consulta usuario pelo ID")
    public ResponseEntity<UsuarioDto> consultaUsuario(@PathVariable Long codigo){

        Optional<Usuario> optionalUsuario = usuarioRepositorio.findById(codigo);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            UsuarioDto dto = UsuarioConverter.toDto(usuario);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @ApiOperation(value="Lista usuários por filtro")
    public ResponseEntity<List<Usuario>> listaFiltroUsuario(@RequestParam("filtro") String filtro){
        List<Usuario> usuarios = usuarioRepositorio.findByNomeCompletoContainingIgnoreCaseOrNomeSocialContainingIgnoreCaseOrEmailContainingIgnoreCase(filtro, filtro, filtro);

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarios);
        }
    }

    @PostMapping
    @ApiOperation(value="Cria usuário")
    public ResponseEntity<Usuario> incluirUsuario(@RequestBody Usuario usuario){
        try {
            Usuario novoUsuario = usuarioRepositorio.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    @ApiOperation(value="Altera um usuário")
    public ResponseEntity<Usuario> alterarUsuario(@RequestBody Usuario usuario){
        //verifica usuario
        Optional<Usuario> usuarioConsulta = usuarioRepositorio.findById(usuario.getCodigo());

        if (usuarioConsulta.isPresent()) {
            try {
                Usuario usuarioAtualizado = usuarioRepositorio.save(usuario);
                return ResponseEntity.ok(usuarioAtualizado);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    @ApiOperation(value="Deleta usuário")
    public ResponseEntity<?> excluiUsuario(@PathVariable Long codigo){
        try {
            usuarioRepositorio.deleteById(codigo);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) { //para não encontrado 404
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/carregaCSV")
    @ApiOperation(value="Carrega o arquivo CSV no Postgre")
    public ResponseEntity<?> processCsvFile() {
        String filePath = "usuarios.csv";
        try {
            ClassPathResource resource = new ClassPathResource(filePath);
            File file = resource.getFile();
            usuarioService.processarArquivoCsv(file.getAbsolutePath());
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
