package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.DespesaDAO;
import br.com.fiap.fintech.dao.DividaDAO;
import br.com.fiap.fintech.dao.GanhoDAO;
import br.com.fiap.fintech.dao.InvestimentoDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.dao.impl.OracleDespesaDAO;
import br.com.fiap.fintech.dao.impl.OracleDividaDAO;
import br.com.fiap.fintech.dao.impl.OracleGanhoDAO;
import br.com.fiap.fintech.dao.impl.OracleInvestimentoDAO;
import br.com.fiap.fintech.dao.impl.OracleUsuarioDAO;

public class DAOFactory {

	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}
	
	public static DespesaDAO getDespesaDAO() {
		return new OracleDespesaDAO();
	}
	
	public static DividaDAO getDividaDAO() {
		return new OracleDividaDAO();
	}
	
	public static GanhoDAO getGanhoDAO() {
		return new OracleGanhoDAO();
	}
	
	public static InvestimentoDAO getInvestimentoDAO() {
		return new OracleInvestimentoDAO();
	}
}
