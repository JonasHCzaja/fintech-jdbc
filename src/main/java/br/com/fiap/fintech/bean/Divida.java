package br.com.fiap.fintech.bean;


import java.util.Calendar;

public class Divida {
	private int cdDivida;
	private double valor;
	private String descricao;
	private Calendar prazo;
	private String idUsuario;
	
	
	public Divida() {
		
	}

	public Divida(int cdDivida, double valor, String descricao, Calendar prazo, String idUsuario) {
		super();
		this.cdDivida = cdDivida;
		this.valor = valor;
		this.descricao = descricao;
		this.prazo = prazo;
		this.idUsuario = idUsuario;
	}

	public int getCdDivida() {
		return cdDivida;
	}

	public void setCdDivida(int cdDivida) {
		this.cdDivida = cdDivida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getPrazo() {
		return prazo;
	}

	public void setPrazo(Calendar prazo) {
		this.prazo = prazo;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
}
