package br.cefetrj.mg.bsi.vocealuga.faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.cefetrj.mg.bsi.vocealuga.model.Agencia;

public class AgenciaFaker extends BaseFaker<Agencia>{

    
    @Override
    public List<Agencia> generate(int amount) {
        List<Agencia> agencias = new ArrayList<Agencia>();
        for(int i =0; i< amount; i++){
            agencias.add(getObjectFaker());
        }
        return agencias;
        
    }

    @Override
    public Agencia getObjectFaker() {
        Agencia a = new Agencia();
        a.setNome(faker.name().name());
        a.setBairro(customFaker(20));
        a.setCep(customFaker(8));
        a.setCnpj(customFaker(14));
        a.setCreatedAt(LocalDateTime.now());
        a.setLogradouro(faker.address().streetAddress());
        a.setMunicipio(customFaker(20));
        a.setNome(faker.name().fullName());
        a.setNumero(faker.address().streetAddressNumber());
        a.setUf("RJ");
        return a;
    }

    @Override
    public String customFaker(int amount) {
        return UUID.randomUUID().toString().substring(0,amount);
    }

    
    
}
