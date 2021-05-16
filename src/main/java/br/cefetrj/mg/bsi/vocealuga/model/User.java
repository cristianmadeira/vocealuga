package br.cefetrj.mg.bsi.vocealuga.model;


import java.util.Collection;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements  UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(nullable = false, unique = true)
    @Email
    @NotBlank
    private String username;
    
    @Column(nullable = false)
    @NotBlank
    private String password;

    @Column
    private boolean enabled;

    @Column(nullable = false, length = 40)
    @Size(min= 5, max = 40, message = "O nome deve ter entre {min} e {max} caracteres.")
    @NotBlank
    private String name;

    @Column(nullable = false, length = 11)
    @Size(min= 11, max = 11, message = "O CPF deve ter entre {min} e {max} caracteres.")
    @NotBlank
    private String cpf;

    @Column(nullable = false, length = 40)
    @NotBlank
    private String type;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
        name =  "users_roles",
        joinColumns = @JoinColumn(
            name = "user_id", referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "role_id",
            referencedColumnName = "id"
        )
    
    )
    private List<Role> roles;

    
    public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return (Collection<? extends GrantedAuthority>)this.roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String getPassword() {
       return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String getUsername() {
       return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
