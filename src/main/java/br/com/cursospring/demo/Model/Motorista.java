package br.com.cursospring.demo.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Motorista {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private int idade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Motorista)) return false;
        Motorista motorista = (Motorista) o;
        return getIdade() == motorista.getIdade() &&
                getId().equals(motorista.getId()) &&
                getNome().equals(motorista.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getIdade());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }



}
