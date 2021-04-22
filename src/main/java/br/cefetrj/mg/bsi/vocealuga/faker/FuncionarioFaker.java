package br.cefetrj.mg.bsi.vocealuga.faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.cefetrj.mg.bsi.vocealuga.model.Funcionario;

public class FuncionarioFaker extends BaseFaker<Funcionario> {

    @Override
    public Funcionario create() {
        Funcionario f = new Funcionario();
        f.setCpf(customFaker(11));
        f.setEmail(faker.internet().emailAddress());
        f.setNome(faker.name().fullName());
        f.setCreatedAt(LocalDateTime.now());
        return f;
    }

    @Override
    public List<Funcionario> create(int amount) {
        List<Funcionario> funcionarios = new ArrayList<>();
        for(int i = 0 ; i < amount ; i++){
            funcionarios.add(create());
        }
        return funcionarios;
    }

    @Override
    public String customFaker(int amount) {
        return UUID.randomUUID().toString().substring(0,amount);
    }
    
}
