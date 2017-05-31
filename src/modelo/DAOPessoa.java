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
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Killer
 */
public class DAOPessoa {
    DAOCidade daoCidade = new DAOCidade();
    public List<Pessoa> getLista(){
         String sql = "Select * from pessoas";
        List<Pessoa> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Pessoa obj = new Pessoa();
                obj.setCodigo(rs.getInt("codigo"));
                obj.setNome(rs.getString("nome"));
                obj.setSalario(rs.getDouble("salario"));
                java.sql.Date dt = rs.getDate("nascimento");
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                obj.setNascimento(c);
                obj.setCidade(daoCidade.localizar(rs.getInt("cidade")));
                lista.add(obj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
        }
        return lista; 
    }


   public boolean salvar(Pessoa obj) {
        if (obj.getCodigo() == null) {
            return incluir(obj);
        }else{
            return alterar(obj);
   
        }
    }
    public boolean incluir(Pessoa obj){
        String sql = "insert into pessoas (nome,salario,nascimento,cidade) values(?,?,?,?)";
         try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setDouble(2, obj.getSalario());
            pst.setDate(3,new java.sql.Date(obj.getNascimento().getTimeInMillis()) );
            pst.setInt(4, obj.getCidade().getCodigo());
            if(pst.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"Pessoa incluida com sucesso");
                return true;
            }else{
                        JOptionPane.showMessageDialog(null,"Pessoa não incluida com sucesso");
                         return false;
                }        
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    public boolean alterar(Pessoa obj){
        String sql = "update  pessoas set nome = ?,salario = ?,nascimento = ?,cidade = ? where codigo = ?";
         try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setDouble(2, obj.getSalario());
            pst.setDate(3,new java.sql.Date(obj.getNascimento().getTimeInMillis()) );
            pst.setInt(4, obj.getCidade().getCodigo());
            pst.setInt(5, obj.getCodigo());
            if(pst.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"Pessoa atualizada com sucesso");
                return true;
            }else{
                        JOptionPane.showMessageDialog(null,"Pessoa não atualizada com sucesso");
                         return false;
                }        
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    public boolean remover(Pessoa obj){
        String sql = "delete from pessoas where codigo = ?";
         try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getCodigo());
            if(pst.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null,"Pessoa excluida com sucesso");
                return true;
            }else{
                        JOptionPane.showMessageDialog(null,"Pessoa não excluida com sucesso");
                         return false;
                }        
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage());
            return false;
        }
    } 
}
