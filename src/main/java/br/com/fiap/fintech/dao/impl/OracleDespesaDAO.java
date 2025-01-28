package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.Despesa;
import br.com.fiap.fintech.dao.DespesaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

/**
 * Implementação da interface InvestimentoDAO para realizar operações de CRUD
 * (Create, Read, Update, Delete) na entidade Despesa no banco de dados Oracle.
 */

public class OracleDespesaDAO implements DespesaDAO {

	private Connection conexao;
	
	
	@Override
	public void cadastrar(Despesa despesa) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_FNT_DESPESA (CD_DESPESA, DS_TIPO_DESPESA, DS_DESPESA, DS_CATEGORIA, VL_DESPESA, DT_DESPESA, DS_CARTAO_CREDITO, DS_EMAIL) VALUES (SQ_FNT_CODIGO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, despesa.getTipoDespesa());
			stmt.setString(2, despesa.getDescricao());
			stmt.setString(3, despesa.getCategoria());
			stmt.setDouble(4, despesa.getValor());
			java.sql.Date data = new java.sql.Date(despesa.getDtDespesa().getTimeInMillis());
			stmt.setDate(5, data);
			stmt.setString(6, despesa.getDsCartao());
			stmt.setString(7, despesa.getIdUsuario());
			
			
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
	public void atualizar(Despesa despesa) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_FNT_DESPESA SET DS_TIPO_DESPESA = ?, DS_DESPESA = ?, DS_CATEGORIA = ?, VL_DESPESA = ?, DT_DESPESA = ?, DS_CARTAO_CREDITO = ? WHERE CD_DESPESA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, despesa.getTipoDespesa());
			stmt.setString(2, despesa.getDescricao());
			stmt.setString(3, despesa.getCategoria());
			stmt.setDouble(4, despesa.getValor());
			java.sql.Date data = new java.sql.Date(despesa.getDtDespesa().getTimeInMillis());
			stmt.setDate(5, data);
			stmt.setString(6, despesa.getDsCartao());
			stmt.setInt(7, despesa.getCdDespesa());
			
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
			String sql = "DELETE FROM T_FNT_DESPESA WHERE CD_DESPESA = ?";
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
	public Despesa buscar(int id) {
		Despesa despesa = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_FNT_DESPESA WHERE CD_DESPESA = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int cdDespesa = rs.getInt("CD_DESPESA");
				String tipoDespesa = rs.getString("DS_TIPO_DESPESA");
				String descricao = rs.getString("DS_DESPESA");
				String categoria = rs.getString("DS_CATEGORIA");
				double valor = rs.getDouble("VL_DESPESA");
				java.sql.Date data = rs.getDate("DT_DESPESA");
				Calendar dtDespesa = Calendar.getInstance();
				dtDespesa.setTimeInMillis(data.getTime());
				String cartao = rs.getString("DS_CARTAO_CREDITO");	
				String idUsuario = rs.getString("DS_EMAIL");
				
				despesa = new Despesa(cdDespesa, tipoDespesa, descricao, categoria, valor, dtDespesa, cartao, idUsuario);
				
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
		return despesa;
	}

	@Override
	public List<Despesa> listar(String idUsuario) {
		List<Despesa> lista = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_FNT_DESPESA WHERE DS_EMAIL = ?");
			stmt.setString(1, idUsuario);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int cdDespesa = rs.getInt("CD_DESPESA");
				String tipoDespesa = rs.getString("DS_TIPO_DESPESA");
				String descricao = rs.getString("DS_DESPESA");
				String categoria = rs.getString("DS_CATEGORIA");
				double valor = rs.getDouble("VL_DESPESA");
				java.sql.Date data = rs.getDate("DT_DESPESA");
				Calendar dtDespesa = Calendar.getInstance();
				dtDespesa.setTimeInMillis(data.getTime());
				String dsCartao = rs.getString("DS_CARTAO_CREDITO");
				
				Despesa despesa = new Despesa(cdDespesa, tipoDespesa, descricao, categoria, valor, dtDespesa, dsCartao, idUsuario);
			
				lista.add(despesa);
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
