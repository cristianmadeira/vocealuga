package br.cefetrj.mg.bsi.vocealuga.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;


@Entity
@SQLDelete(sql = "UPDATE veiculo SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Veiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 7)
    @NotBlank(message = "A placa não pode estar em branco.")
    @Size(min = 7, max = 7, message = "A placa deve conter {max} caracteres.")
    private String placa;
    
    @Column(nullable = false, length = 50)
    @NotBlank(message = "A cor não pode estar em branco.")
    @Size(min = 3, max = 50, message = "A cor deve conter entre {min} e {max} caracteres.")
    private String cor;
    
    @Column(nullable = false, length = 6)
    @NotBlank(message = "A quilometeragem não pode estar em branco.")
    private String kmRodado;

    @Column(nullable = false, length = 25)
    @NotBlank(message = "A marca não pode estar em branco.")
    @Size(min = 2, max = 25, message = "A marca deve conter entre {min} e {max} caracteres.")
    private String marca;
    
    @Column(nullable = false, length = 2)
    @NotNull(message = "A revisão não pode estar em branco.")
    private boolean estaRevisado;

    @Column(nullable = false, length = 2)

    @NotNull(message = "A revisão não pode estar em branco.")
    private boolean disponivel;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn()
    private Agencia agencia;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn()
    private Grupo grupo;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    private LocalDateTime deletedAt;


    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return this.placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return this.cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getKmRodado() {
        return this.kmRodado;
    }
    public void setKmRodado(String kmRodado) {
        this.kmRodado = kmRodado;
    }

    public String getMarca() {
        return this.marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isEstaRevisado() {
        return this.estaRevisado;
    }

    public void setEstaRevisado(boolean estaRevisado) {
        this.estaRevisado = estaRevisado;
    }

    public boolean isDisponivel() {
        return this.disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Agencia getAgencia() {
        return this.agencia;
    }
    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Grupo getGrupo() {
        return this.grupo;
    }
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return this.deletedAt;
    }
    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
