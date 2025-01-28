package br.com.fiap.fintech.bean;

import br.com.fiap.fintech.util.CriptografiaUtils;

public class Usuario {
	private String email;
	private String nome;
	private String senha;
	
	
	public Usuario() {
		
	}
	
	public Usuario(String email, String nome, String senha) {
		this.email = email;
		this.nome = nome;
		setSenha(senha);
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		try {
			this.senha = CriptografiaUtils.criptografar(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
