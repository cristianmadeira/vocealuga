package br.cefetrj.mg.bsi.vocealuga.model;

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

    @Column(nullable = false, lenght = 2)
    @NotBlank(message = "Deve ser especificado se está ou não na Lista Negra")
    private Boolean listaNegra;

    @Column(nullable = false, lenght = 16)
    @NotBlank(message = "O Cartão de crédito não pode estar em branco")
    @Size(min = 16, max = 16, message = "O Cartão de crédito deve conter {max} caracteres")
    // Confirmar se cartões de créditos podem contar menos ou mais números.
    private String cartaoCredito;

    @Size(min = 11, max = 11, message = "A CNH deve conter {max} caracteres")
    // Confirmar se CNH podem conter menos ou mais números, e se possui o hífen do dígito final.
    private String cnh;

    @Column(nullable = false, lenght = 2)
    @NotBlank(message = "Deve ser especificado se há ou não uma Apólice de seguro.")
    private Boolean apolice;

    private LocalDateTime createdAt;
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



}
