package br.cefetrj.mg.bsi.vocealuga.faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.cefetrj.mg.bsi.vocealuga.model.Grupo;

public class GrupoFaker extends BaseFaker<Grupo> {

    @Override
    public Grupo getObjectFaker() {
        Grupo g = new Grupo();
        g.setNome(faker.company().name());
        g.setCreatedAt(LocalDateTime.now());
        return g;
    }

    @Override
    public List<Grupo> generate(int amount) {
        List<Grupo> grupos = new ArrayList<>();
        for(int i = 0 ; i < amount ; i++){
            grupos.add(getObjectFaker());
        }
        return grupos;
    }

    @Override
    public String customFaker(int amount) {
        return UUID.randomUUID().toString().substring(0,amount);
    }
    
}
