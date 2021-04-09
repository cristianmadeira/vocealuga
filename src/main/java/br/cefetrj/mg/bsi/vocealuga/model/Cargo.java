package br.cefetrj.mg.bsi.vocealuga.model;

import javax.persistence.Entity;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cargo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Size(min = 5, max = 50, message = "O nome deve ter entre {min} e {max} caracteres. ")
    private String nome;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

}
