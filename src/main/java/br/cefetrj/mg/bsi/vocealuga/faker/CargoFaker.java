package br.cefetrj.mg.bsi.vocealuga.faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.cefetrj.mg.bsi.vocealuga.model.Cargo;

public class CargoFaker extends BaseFaker<Cargo> {

    @Override
    public Cargo create() {
        Cargo c = new Cargo();
        c.setNome(faker.company().name());
        c.setCreatedAt(LocalDateTime.now());
        return c;
    }

    @Override
    public List<Cargo> create(int amount) {
        List<Cargo> cargos = new ArrayList<>();
        for(int i = 0 ; i < amount ; i++){
            cargos.add(create());
        }
        return cargos;
    }

    @Override
    public String customFaker(int amount) {
        return UUID.randomUUID().toString().substring(0,amount);
    }
    
}
