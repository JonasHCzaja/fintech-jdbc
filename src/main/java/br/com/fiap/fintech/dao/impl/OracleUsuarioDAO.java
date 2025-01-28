package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

/**
 * Implementação da interface UsuarioDAO para manipulação de dados
 * da entidade Usuario no banco de dados Oracle.
 */

public class OracleUsuarioDAO implements UsuarioDAO {


	
	 @Override
	    public void cadastrar(Usuario usuario) throws DBException {
	        String sql = "INSERT INTO T_FNT_USUARIO (DS_EMAIL, NM_NOME, DS_SENHA) VALUES (?, ?, ?)";
	        try (Connection conexao = ConnectionManager.getInstance().getConnection();
	             PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setString(1, usuario.getEmail());
	            stmt.setString(2, usuario.getNome());
	            stmt.setString(3, usuario.getSenha());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new DBException("Erro ao cadastrar.", e);
	        }
	    }

	    @Override
	    public void atualizarDados(Usuario usuario) throws DBException {
	        String sql = "UPDATE T_FNT_USUARIO SET NM_NOME = ?, DS_SENHA = ? WHERE DS_EMAIL = ?";
	        try (Connection conexao = ConnectionManager.getInstance().getConnection();
	             PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setString(1, usuario.getNome());
	            stmt.setString(2, usuario.getSenha());
	            stmt.setString(3, usuario.getEmail());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new DBException("Erro ao atualizar dados.", e);
	        }
	    }

	    @Override
	    public void excluirPerfil(String email, String senha) throws DBException {
	        String sql = "DELETE FROM T_FNT_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?";
	        try (Connection conexao = ConnectionManager.getInstance().getConnection();
	             PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setString(1, email);
	            stmt.setString(2, senha);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new DBException("Erro ao excluir perfil.", e);
	        }
	    }

	    @Override
	    public Usuario buscar(String perfil) {
	        String sql = "SELECT * FROM T_FNT_USUARIO WHERE DS_EMAIL = ?";
	        Usuario usuario = null;
	        try (Connection conexao = ConnectionManager.getInstance().getConnection();
	             PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setString(1, perfil);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    String email = rs.getString("DS_EMAIL");
	                    String nome = rs.getString("NM_NOME");
	                    String senha = rs.getString("DS_SENHA");
	                    usuario = new Usuario(email, nome, senha);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return usuario;
	    }

	    @Override
	    public boolean validarUsuario(Usuario usuario) {
	        String sql = "SELECT * FROM T_FNT_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?";
	        try (Connection conexao = ConnectionManager.getInstance().getConnection();
	             PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setString(1, usuario.getEmail());
	            stmt.setString(2, usuario.getSenha());
	            try (ResultSet rs = stmt.executeQuery()) {
	                return rs.next();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	
	
	
}
