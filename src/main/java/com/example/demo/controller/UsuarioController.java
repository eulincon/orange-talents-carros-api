package com.example.demo.controller;

import com.example.demo.modelo.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UsuarioController {

  @Autowired
  private UsuarioRepository repository;

  @PostMapping("/usuarios")
  public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {

    repository.save(usuario);

    return ResponseEntity.ok().build();
  }

  @GetMapping("/usuarios")
  public ResponseEntity<List<Usuario>> findAll() {
    List<Usuario> usuarios = repository.findAll();

    return ResponseEntity.ok(usuarios);
  }

  @GetMapping("/usuarios/{id}")
  public Usuario findById(@PathVariable Long id) {
    Usuario usuario =
        repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

    return usuario;
  }

}
