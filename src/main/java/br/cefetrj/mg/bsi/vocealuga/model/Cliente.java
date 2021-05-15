package br.cefetrj.mg.bsi.vocealuga.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql =  "UPDATE agencia SET deleted_at = current_timestamp() where id = ?")
@Where(clause = "deleted_at is null")
public class Cliente {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Integer id;

    @Column(nullable = false, length = 60)
    @NotBlank(message = "O Nome não pode estar em branco")
    @Size(min = 5, max = 60, message = "O Nome deve conter entre {min} e {max} caracteres")
    private String nome;

    @Column(nullable = false, length = 11, unique = true)
    @NotBlank(message = "O CPF não pode estar em branco")
    @Size(min = 11, max = 11, message = "O CPF deve conter {max} caracteres")
    private String cpf;

    @Column(nullable = false, length = 10)
    @NotBlank(message = "A Data de nascimento não pode estar em branco")
    @Size(min = 8, max = 10, message = "A Data de nascimento deve conter entre {min} e {max} caracteres")
    private LocalDate dtNascimento;

    @Column(nullable = false)
    @NotBlank(message = "Deve ser especificado se está ou não na Lista Negra")
    private Boolean listaNegra;

    @Column(nullable = false, length = 16)
    @NotBlank(message = "O Cartão de crédito não pode estar em branco")
    @Size(min = 16, max = 16, message = "O Cartão de crédito deve conter {max} caracteres")
    private String cartaoCredito;

    @Column(length = 11, unique = true)
    @Size(min = 11, max = 11, message = "A CNH deve conter {max} caracteres")
    private String cnh;

    @Column(nullable = false)
    @NotBlank(message = "Deve ser especificado se há ou não uma Apólice de seguro.")
    private Boolean apolice;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "clientes_pedidos",
        joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "pedidos_id", referencedColumnName = "id")

    )
    private List<Pedido> pedidos;

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

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDtNascimento() {
        return this.dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Boolean getListaNegra() {
        return this.listaNegra;
    }

    public void setListaNegra(Boolean listaNegra) {
        this.listaNegra = listaNegra;
    }

    public String getCartaoCredito() {
        return this.cartaoCredito;
    }

    public void setCartaoCredito(String cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public String getCnh() {
        return this.cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Boolean getApolice() {
        return this.apolice;
    }

    public void setApolice(Boolean apolice) {
        this.apolice = apolice;
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

}
