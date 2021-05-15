package br.cefetrj.mg.bsi.vocealuga.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@SQLDelete(sql = "UPDATE oficina SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Oficina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(min = 5, max = 50, message = "O nome deve ter entre {min} e {max} caracteres.")
    private String nome;
    
    @Email(message = "O E-mail deve ser válido.")
    @NotBlank(message = "O E-mail não pode ser em branco.")
    private String email;

    @NotBlank(message = "O telefone não pode ser em branco.")
    @Size(min = 10, max = 11, message = "O telefone deve ter entre {min} e {max} caracteres.")
    private String telefone;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    

    
    
    
    
    

    
}
