package com.example.demo.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Veiculo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String marca;
  private String modelo;
  private String ano;
  private BigDecimal valor;
  private int diaDoRodizio;

  @OneToOne
  @JsonIgnore
  private Usuario dono;

  @JsonIgnore
  public Usuario getDono() {
    return dono;
  }

  @JsonProperty
  public void setDono(Usuario dono) {
    this.dono = dono;
  }

  public int getDiaDoRodizio() {
    return diaDoRodizio;
  }

  public void setDiaDoRodizio(int diaDoRodizio) {
    this.diaDoRodizio = diaDoRodizio;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public String getAno() {
    return ano;
  }

  public void setAno(String ano) {
    this.ano = ano;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }
}
