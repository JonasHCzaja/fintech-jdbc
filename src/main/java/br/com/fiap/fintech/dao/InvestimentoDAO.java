package br.com.fiap.fintech.dao;

import java.util.List;

import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.exception.DBException;

public interface InvestimentoDAO {

	void cadastrar(Investimento investimento) throws DBException;
	void atualizar(Investimento investimento) throws DBException;
	void remover(int codigo) throws DBException;
	Investimento buscar(int id);
	List<Investimento> listar(String idUsuario);
}
