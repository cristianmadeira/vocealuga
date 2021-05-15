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
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "UPDATE oficina_veiculo SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Oficina_Veiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 10)
    @NotBlank(message = "O tipo de reparo não pode estar em branco.")    
    @Size(min = 7, max = 10, message = "O tipo deve conter de {min} a {max} caracteres.")
    private String tipo;

    @Column(nullable = false, length = 8)
    @NotBlank(message = "A data do reparo nao pode estar em branco.")
    @Size(min = 8, max = 8, message = "A data deve conter {max} caracteres")
    private LocalDateTime dtReparo;

    @Column(nullable = false, length = 8)
    @NotBlank(message = "A data do devolução nao pode estar em branco.")
    @Size(min = 8, max = 8, message = "A data deve conter {max} caracteres")
    private String dtDevolucao;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn()
    private Oficina oficina;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn()
    private Veiculo veiculo;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createAt;
    
    @Column(insertable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;

    private LocalDateTime deletedAt;

    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
   
    public LocalDateTime getDtReparo() {
        return this.dtReparo;
    }

    public void setDtReparo(LocalDateTime dtReparo) {
        this.dtReparo = dtReparo;
    }
    
    public String getDtDevolucao() {
        return this.dtDevolucao;
    }

    public void setDtDevolucao(String dtDevolucao) {
        this.dtDevolucao = dtDevolucao;
    }
    
    public Oficina getOficina() {
        return this.oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }
    
    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    public LocalDateTime getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
    
    public LocalDateTime getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
    
    public LocalDateTime getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
    
}
