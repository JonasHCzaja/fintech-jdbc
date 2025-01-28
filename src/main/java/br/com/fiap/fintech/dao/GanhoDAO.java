package br.com.fiap.fintech.dao;

import java.util.List;

import br.com.fiap.fintech.bean.Ganho;
import br.com.fiap.fintech.exception.DBException;

public interface GanhoDAO {
	
	void cadastrar(Ganho ganho) throws DBException;
	void atualizar(Ganho ganho) throws DBException;
	void remover(int codigo) throws DBException;
	Ganho buscar(int id);
	List<Ganho> listar(String idUsuario);
}
