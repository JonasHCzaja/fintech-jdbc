package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.exception.DBException;

public interface UsuarioDAO {
	
	void cadastrar(Usuario usuario) throws DBException;
	void atualizarDados(Usuario usuario) throws DBException;
	void excluirPerfil (String email, String senha) throws DBException;
	Usuario buscar(String perfil);
	boolean validarUsuario(Usuario usuario);
}
