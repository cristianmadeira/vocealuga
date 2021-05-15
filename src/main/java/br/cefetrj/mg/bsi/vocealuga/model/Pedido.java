package br.cefetrj.mg.bsi.vocealuga.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;



@Entity
@SQLDelete(sql = "UPDATE pedido SET deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer id;
    @Column
    private LocalDateTime dtLocacao;
    @Column
    private LocalDateTime dtDevolucao;
    @Column
    private LocalDateTime dtReserva;
    
    @Column(length = 20)
    @NotBlank
    @Size(min = 5, max = 20, message = "O tipo deve conter entre {min} e {max} caracteres.")
    private String tipo;
    
    @Column
    @NotBlank
    @Size(message = "As diárias devem ser válidas.")
    private int diarias;
    
    @Column
    private float impostos;
    
    @Column
    private float taxas;

    @Column
    private float desconto;
    
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @Column(insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @Column
    private LocalDateTime deletedAt;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDtLocacao() {
        return this.dtLocacao;
    }

    public void setDtLocacao(LocalDateTime dtLocacao) {
        this.dtLocacao = dtLocacao;
    }

    public LocalDateTime getDtDevolucao() {
        return this.dtDevolucao;
    }

    public void setDtDevolucao(LocalDateTime dtDevolucao) {
        this.dtDevolucao = dtDevolucao;
    }

    public LocalDateTime getDtReserva() {
        return this.dtReserva;
    }

    public void setDtReserva(LocalDateTime dtReserva) {
        this.dtReserva = dtReserva;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDiarias() {
        return this.diarias;
    }

    public void setDiarias(int diarias) {
        this.diarias = diarias;
    }

    public float getImpostos() {
        return this.impostos;
    }

    public void setImpostos(float impostos) {
        this.impostos = impostos;
    }

    public float getTaxas() {
        return this.taxas;
    }

    public void setTaxas(float taxas) {
        this.taxas = taxas;
    }

    public float getDesconto() {
        return this.desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
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
