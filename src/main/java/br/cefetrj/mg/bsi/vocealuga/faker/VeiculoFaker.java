package br.cefetrj.mg.bsi.vocealuga.faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.cefetrj.mg.bsi.vocealuga.model.Veiculo;

public class VeiculoFaker extends BaseFaker<Veiculo> {

    @Override
    public Veiculo create() {
        Veiculo v  = new  Veiculo();
        v.setAgencia(new AgenciaFaker().create());
        v.setCor(this.faker.color().name());
        v.setCreatedAt(LocalDateTime.now());
        v.setDisponivel(true);
        v.setEstaRevisado(true);
        v.setGrupo(new GrupoFaker().create());
        v.setKmRodado(String.valueOf(this.faker.number().numberBetween(0, 10000)));
        v.setMarca("Gurgel");
        v.setPlaca(this.customFaker(7));
        return v;
    }

    @Override
    public List<Veiculo> create(int amount) {
        List<Veiculo> veiculos = new ArrayList<>();
        for(int i = 0; i< amount; i++)
            veiculos.add(create());
        return veiculos;
    }

    @Override
    public String customFaker(int amount) {
        return UUID.randomUUID().toString().substring(0,amount);
    }
    
}
