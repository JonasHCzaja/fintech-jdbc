package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.dao.InvestimentoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

/**
 * Implementação da interface InvestimentoDAO para realizar operações de CRUD
 * (Create, Read, Update, Delete) na entidade Investimento no banco de dados Oracle.
 */

public class OracleInvestimentoDAO implements InvestimentoDAO {

	private Connection conexao;
	
	
	@Override
	public void cadastrar(Investimento investimento) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_FNT_INVESTIMENTO (CD_ATIVO, NM_ATIVO, DS_CATEGORIA, VL_PRECO_MEDIO, NR_QUANTIDADE, DS_EMAIL) VALUES (SQ_FNT_CODIGO.NEXTVAL, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, investimento.getNomeAtivo());
			stmt.setString(2, investimento.getCategoriaAtivo());
			stmt.setDouble(3, investimento.getPrecoMedio());
			stmt.setDouble(4, investimento.getQuantidade());
			stmt.setString(5, investimento.getIdUsuario());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void atualizar(Investimento investimento) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_FNT_INVESTIMENTO SET NM_ATIVO = ?, DS_CATEGORIA = ?, VL_PRECO_MEDIO = ?, NR_QUANTIDADE = ? WHERE CD_ATIVO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, investimento.getNomeAtivo());
			stmt.setString(2, investimento.getCategoriaAtivo());
			stmt.setDouble(3, investimento.getPrecoMedio());
			stmt.setDouble(4, investimento.getQuantidade());
			stmt.setInt(5, investimento.getCdAtivo());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void remover(int codigo) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_FNT_INVESTIMENTO WHERE CD_ATIVO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao remover.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Investimento buscar(int id) {
		Investimento investimento = null;	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_FNT_INVESTIMENTO WHERE CD_ATIVO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int cdAtivo = rs.getInt("CD_ATIVO");
				String nomeAtivo = rs.getString("NM_ATIVO");
				String categoriaAtivo = rs.getString("DS_CATEGORIA");
				double precoMedio = rs.getDouble("VL_PRECO_MEDIO");
				double quantidade = rs.getDouble("NR_QUANTIDADE");
				String idUsuario = rs.getString("DS_EMAIL");
				
				investimento = new Investimento(cdAtivo, nomeAtivo, categoriaAtivo, precoMedio, quantidade, idUsuario);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return investimento;
	}

	@Override
	public List<Investimento> listar(String idUsuario) {
		List<Investimento> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_FNT_INVESTIMENTO WHERE DS_EMAIL = ?");
			stmt.setString(1, idUsuario);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int cdAtivo = rs.getInt("CD_ATIVO");
				String nomeAtivo = rs.getString("NM_ATIVO");
				String categoriaAtivo = rs.getString("DS_CATEGORIA");
				double precoMedio = rs.getDouble("VL_PRECO_MEDIO");
				double quantidade = rs.getDouble("NR_QUANTIDADE");
				
				Investimento investimento = new Investimento(cdAtivo, nomeAtivo, categoriaAtivo, precoMedio, quantidade, idUsuario);
				
				lista.add(investimento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

}
