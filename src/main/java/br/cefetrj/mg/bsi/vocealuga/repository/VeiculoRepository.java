package br.cefetrj.mg.bsi.vocealuga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.cefetrj.mg.bsi.vocealuga.model.Veiculo;

public interface VeiculoRepository extends CrudRepository<Veiculo, Integer> {
    
    @Query("SELECT v FROM Veiculo v WHERE v.disponivel = 1 AND v.deletedAt is null")
    public List<Veiculo> findByVeiculoDisponivel();
}
