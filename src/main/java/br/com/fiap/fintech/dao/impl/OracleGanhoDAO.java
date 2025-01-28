package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import br.com.fiap.fintech.bean.Ganho;
import br.com.fiap.fintech.dao.GanhoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

/**
 * Implementação da interface InvestimentoDAO para realizar operações de CRUD
 * (Create, Read, Update, Delete) na entidade Ganhho no banco de dados Oracle.
 */

public class OracleGanhoDAO implements GanhoDAO {
	
	private Connection conexao;
	
	@Override
	public void cadastrar(Ganho ganho) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_FNT_GANHO (CD_GANHO, DS_GANHO, DS_CATEGORIA, VL_GANHO, DT_RECEBIMENTO, DS_EMAIL) VALUES (SQ_FNT_CODIGO.NEXTVAL, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, ganho.getDescricao());
			stmt.setString(2, ganho.getCategoria());
			stmt.setDouble(3, ganho.getValor());
			java.sql.Date data = new java.sql.Date(ganho.getDtRecebimento().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setString(5, ganho.getIdUsuario());
			
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
	public void atualizar(Ganho ganho) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_FNT_GANHO SET DS_GANHO = ?, DS_CATEGORIA = ?, VL_GANHO = ?, DT_RECEBIMENTO = ? WHERE CD_GANHO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, ganho.getDescricao());
			stmt.setString(2, ganho.getCategoria());
			stmt.setDouble(3, ganho.getValor());
			java.sql.Date data = new java.sql.Date(ganho.getDtRecebimento().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setInt(5, ganho.getCdGanho());			
			
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
			String sql = "DELETE FROM T_FNT_GANHO WHERE CD_GANHO = ?";
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
	public Ganho buscar(int id) {
		Ganho ganho = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_FNT_GANHO WHERE CD_GANHO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int cdGanho = rs.getInt("CD_GANHO");
				String descricao = rs.getString("DS_GANHO");
				String categoria = rs.getString("DS_CATEGORIA");
				double valor = rs.getDouble("VL_GANHO");
				java.sql.Date data = rs.getDate("DT_RECEBIMENTO");
				Calendar dtRecebimento = Calendar.getInstance();
				dtRecebimento.setTimeInMillis(data.getTime());
				String idUsuario = rs.getString("DS_EMAIL");
				
				ganho = new Ganho(cdGanho, descricao, categoria, valor, dtRecebimento, idUsuario);
				
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
		return ganho;
	}

	@Override
	public List<Ganho> listar(String idUsuario) {
		List<Ganho> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_FNT_GANHO WHERE DS_EMAIL = ?");
			stmt.setString(1, idUsuario);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int cdGanho = rs.getInt("CD_GANHO");
				String descricao = rs.getString("DS_GANHO");
				String categoria = rs.getString("DS_CATEGORIA");
				double valor = rs.getDouble("VL_GANHO");
				java.sql.Date data = rs.getDate("DT_RECEBIMENTO");
				Calendar dtRecebimento = Calendar.getInstance();
				dtRecebimento.setTimeInMillis(data.getTime());
				
				Ganho ganho = new Ganho(cdGanho, descricao, categoria, valor, dtRecebimento, idUsuario);
				
				lista.add(ganho);
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
