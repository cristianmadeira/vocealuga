package br.cefetrj.mg.bsi.vocealuga.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import java.util.List;
import javax.persistence.OneToMany;
@Entity
@SQLDelete(sql = "UPDATE agencia SET deleted_at = current_timestamp()  WHERE id = ? ")
@Where(clause = "deleted_at is null")
public class Agencia{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@Column(length = 100, nullable = false)
    @Size(min = 5,max = 100, message = "O nome deve ter entre {min} e {max} caracteres.")
    private String nome;
    
	@Column(length = 14, nullable = false)
    @Size(min=14, max = 14, message = "o CNPJ deve conter {max} caracteres.")
    private String cnpj;
    
    @Column(length = 8)
    @Size(min = 8, max = 8, message = "O CEP deve conter {max} caracteres.")
    private String cep;
    
	@Column(length = 150)
    @Size(min = 10, max = 150, message = "O logradouro deve conter entre {min} e {max} caracteres.")
    private String logradouro;
    
    @Column
    private String numero;
    
	@Column(nullable = false, length = 100)
    @Size(min = 5, max = 100, message = "O bairro deve conter entre {min} e {max} caracteres.")
    private String bairro;

	@Column(nullable = false, length = 100)
    @Size(min = 5, max = 100, message = "O município deve conter entre {min} e {max} caracteres.")
    private String municipio;
    
    @Column(nullable = false, length = 2)
    @Size(min = 2, max = 2, message = "A UF conter {max} caracteres.")
    private String uf;

	@OneToMany(mappedBy ="agencia")
	private List<Veiculo> veiculos;
	
	@Column(updatable = false)
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

    public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}
    
    
}
