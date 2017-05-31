/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Killer
 */
public class DAOCidade {

    public List<Cidade> getLista() {
        String sql = "Select * from cidades";
        List<Cidade> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cidade obj = new Cidade();
                obj.setCodigo(rs.getInt("codigo"));
                obj.setNome(rs.getString("nome"));
                obj.setUf(rs.getString("uf"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }
        return lista;
    }

    public boolean salvar(Cidade obj) {
        if (obj.getCodigo() == null) {
            Integer codigo = Dados.listaCidades.size() + 1;
            obj.setCodigo(codigo);
            Dados.listaCidades.add(obj);
        }
        return true;
    }

    public boolean remover(Cidade obj) {
        Dados.listaCidades.remove(obj);
        return true;
    }
}
