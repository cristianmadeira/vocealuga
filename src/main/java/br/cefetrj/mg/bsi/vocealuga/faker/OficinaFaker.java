package br.cefetrj.mg.bsi.vocealuga.faker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.cefetrj.mg.bsi.vocealuga.model.Oficina;

public class OficinaFaker extends BaseFaker<Oficina> {

    @Override
    public Oficina create() {
        Oficina o = new Oficina();
        o.setEmail(faker.internet().emailAddress());
        o.setNome(faker.name().fullName());
        o.setTelefone(customFaker(10));
        return o;
    }

    @Override
    public List<Oficina> create(int amount) {
        List<Oficina> oficinas = new ArrayList<>();
        for(int i = 0; i< amount; i++)
            oficinas.add(create());
        return oficinas;
    }

    @Override
    public String customFaker(int amount) {
        return UUID.randomUUID().toString().substring(0,amount);
    }
    
}
