package br.cefetrj.mg.bsi.vocealuga.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Agencia{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	@Column(length = 100)
    @NotBlank
    @Size(min = 5,max = 100, message = "O nome deve ter entre {min} e {max} caracteres.")
    private String nome;
    
	@Column(length = 14)
    @Size(min=14, max = 14, message = "o CNPJ deve conter {max} caracteres.")
    @NotBlank
    private String cnpj;
    
    @Column(length = 8)
    @NotBlank
	@Size(min = 8, max = 8, message = "O CEP deve conter {max} caracteres.")
    private String cep;
    
	@Column(length = 150)
    @NotBlank(message = "O logradouro não pode ser em branco")
    private String logradouro;
    
    @Column
    private String numero;
    
    @NotBlank(message = "O bairro não pode ser em branco")
    private String bairro;

    @NotBlank(message = "O município não pode ser em branco")
    private String municipio;
    
    @Column(length=2)
    @NotBlank(message = "A UF não pode ser em branco")
    private String uf;

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



    
    
}
