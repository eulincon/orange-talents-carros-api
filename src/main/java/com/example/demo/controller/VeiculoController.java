package com.example.demo.controller;

import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.Veiculo;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class VeiculoController {

  @Autowired
  private VeiculoRepository veiculoRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;

  @PostMapping("/veiculos")
  public void save(@RequestBody Veiculo veiculo){
    Usuario dono = usuarioRepository.findById(veiculo.getDono().getId())
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR));

    veiculoRepository.save(veiculo);
    List<Veiculo> veiculos = dono.getVeiculos();
    veiculos.add(veiculo);
    usuarioRepository.save(dono);
  }
}
