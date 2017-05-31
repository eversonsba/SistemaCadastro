/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author Killer
 */
public class DAOPessoa {
    public List<Pessoa> getLista(){
    return Dados.listaPessoas;
}
    public boolean salvar(Pessoa obj){
        if (obj.getCodigo()==null) {
            Integer codigo = Dados.listaPessoas.size()+1;
            obj.setCodigo(codigo);
            Dados.listaPessoas.add(obj);
        }
        return true;
    }
    public boolean remover(Pessoa obj){
        Dados.listaPessoas.remove(obj);
        return true;
    }
    
}
