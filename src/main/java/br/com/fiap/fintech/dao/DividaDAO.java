package br.com.fiap.fintech.dao;

import java.util.List;
import br.com.fiap.fintech.bean.Divida;
import br.com.fiap.fintech.exception.DBException;

public interface DividaDAO {

	void cadastrar(Divida divida) throws DBException;
	void atualizar(Divida divida) throws DBException;
	void remover(int codigo) throws DBException;
	Divida buscar(int id);
	List<Divida> listar(String idUsuario);
}
