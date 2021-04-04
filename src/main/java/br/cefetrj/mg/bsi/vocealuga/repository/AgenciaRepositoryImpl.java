package br.cefetrj.mg.bsi.vocealuga.repository;

import java.util.ArrayList;
import java.util.List;

import br.cefetrj.mg.bsi.vocealuga.dao.AgenciaDAOImpl;
import br.cefetrj.mg.bsi.vocealuga.dao.IDAO;
import br.cefetrj.mg.bsi.vocealuga.model.Agencia;

public class AgenciaRepositoryImpl implements IAgenciaRepository{

    private IDAO<Agencia> dao = null;
    public AgenciaRepositoryImpl(){
        this.dao = new AgenciaDAOImpl();
        
    }
    @Override
    public Agencia save(Agencia o) throws Exception {
        return this.dao.save(o);
    }

    @Override
    public Agencia update(Agencia o) throws Exception {
        return this.dao.update(o);
    }

    @Override
    public Agencia delete(int id) throws Exception {
        return this.dao.delete(id);
    }

    @Override
    public Agencia findById(int id) throws Exception {
        Agencia a = this.dao.find(id);
        if ( a.getDeletedAt() != null)
            throw new Exception();
        return a;
            
    }

    @Override
    public List<Agencia> findAll() throws Exception {
        List<Agencia> agencias = this.dao.findAll();
        List<Agencia> agenciasNaoExcluidas = new ArrayList<>();
        for (int i =0; i< agencias.size(); i++){
            Agencia a = agencias.get(i);
            if (a.getDeletedAt() == null)
                agenciasNaoExcluidas.add(a);
        }
        return agenciasNaoExcluidas;
        
    }
    
}
