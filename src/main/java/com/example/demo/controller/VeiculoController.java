package com.example.demo.controller;

import com.example.demo.modelo.Usuario;
import com.example.demo.modelo.Veiculo;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.List;

@RestController
public class VeiculoController {

  @Autowired
  private VeiculoRepository veiculoRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;

  @PostMapping("/veiculos")
  @ResponseStatus(HttpStatus.CREATED)
  public void save(@RequestBody Veiculo veiculo) {
    Usuario dono = usuarioRepository.findById(veiculo.getDono().getId())
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.BAD_REQUEST));

    String anoVeiculo = veiculo.getAno();

    if (anoVeiculo.endsWith("0") || anoVeiculo.endsWith("1")) {
      veiculo.setDiaDoRodizio(Calendar.MONDAY);
    } else if (anoVeiculo.endsWith("2") || anoVeiculo.endsWith("3")) {
      veiculo.setDiaDoRodizio(Calendar.TUESDAY);
    } else if (anoVeiculo.endsWith("4") || anoVeiculo.endsWith("5")) {
      veiculo.setDiaDoRodizio(Calendar.WEDNESDAY);
    } else if (anoVeiculo.endsWith("6") || anoVeiculo.endsWith("7")) {
      veiculo.setDiaDoRodizio(Calendar.THURSDAY);
    } else if (anoVeiculo.endsWith("8") || anoVeiculo.endsWith("9")) {
      veiculo.setDiaDoRodizio(Calendar.FRIDAY);
    }

//    ValorDoCarro valorDoCarro = valorCarroClient.getValorDoCarro();

//    veiculo.setValor(valorDoCarro.getValor());

    veiculoRepository.save(veiculo);
    List<Veiculo> veiculos = dono.getVeiculos();
    veiculos.add(veiculo);
    usuarioRepository.save(dono);
  }

  @GetMapping("/veiculos/{idVeiculo}/diarodizio")
  public boolean eDiaRodizio(@PathVariable Long idVeiculo) {
    Veiculo veiculo = veiculoRepository.findById(idVeiculo)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    int diaDeHoje = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    if (veiculo.getDiaDoRodizio() == diaDeHoje) {
      return true;
    }
    return false;
  }
}
