package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.Divida;
import br.com.fiap.fintech.dao.DividaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

/**
 * Implementação da interface InvestimentoDAO para realizar operações de CRUD
 * (Create, Read, Update, Delete) na entidade Divida no banco de dados Oracle.
 */

public class OracleDividaDAO implements DividaDAO {

	private Connection conexao;
	
	@Override
	public void cadastrar(Divida divida) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_FNT_DIVIDA (CD_DIVIDA, VL_VALOR, DS_DIVIDA, DT_PRAZO, DS_EMAIL) VALUES (SQ_FNT_CODIGO.NEXTVAL, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, divida.getValor());
			stmt.setString(2, divida.getDescricao());
			java.sql.Date data = new java.sql.Date(divida.getPrazo().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setString(4, divida.getIdUsuario());
			
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
	public void atualizar(Divida divida) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_FNT_DIVIDA SET VL_VALOR = ?, DS_DIVIDA = ?, DT_PRAZO = ? WHERE CD_DIVIDA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, divida.getValor());
			stmt.setString(2, divida.getDescricao());
			java.sql.Date data = new java.sql.Date(divida.getPrazo().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setInt(4, divida.getCdDivida());
			
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
			String sql = "DELETE FROM T_FNT_DIVIDA WHERE CD_DIVIDA = ?";
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
	public Divida buscar(int id) {
		Divida divida = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_FNT_DIVIDA WHERE CD_DIVIDA = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int cdDivida = rs.getInt("CD_DIVIDA");
				double valor = rs.getDouble("VL_VALOR");
				String descricao = rs.getString("DS_DIVIDA");
				java.sql.Date data = rs.getDate("DT_PRAZO");
				Calendar prazo = Calendar.getInstance();
				prazo.setTimeInMillis(data.getTime());
				String idUsuario = rs.getString("DS_EMAIL");
				
				divida = new Divida(cdDivida, valor, descricao, prazo, idUsuario);
				
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
		return divida;
	}

	@Override
	public List<Divida> listar(String idUsuario) {
		List<Divida> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_FNT_DIVIDA WHERE DS_EMAIL = ?");
			stmt.setString(1, idUsuario);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int cdDivida = rs.getInt("CD_DIVIDA");
				double valor = rs.getDouble("VL_VALOR");
				String descricao = rs.getString("DS_DIVIDA");
				java.sql.Date data = rs.getDate("DT_PRAZO");
				Calendar prazo = Calendar.getInstance();
				prazo.setTimeInMillis(data.getTime());
				
				Divida divida = new Divida(cdDivida, valor, descricao, prazo, idUsuario);
				
				lista.add(divida);
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
