package br.cefetrj.mg.bsi.vocealuga.model;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;



@Entity
@SQLDelete(sql = "UPDATE grupo set  deleted_at = current_timestamp() WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Grupo {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@Size(min = 5, max = 50, message =  "O nome deve ter entre {min} e {max} caracteres.")
	private String nome;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "grupo")
	private List<Veiculo> veiculos;

	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(insertable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	private LocalDateTime deletedAt;

	
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

	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}
}
