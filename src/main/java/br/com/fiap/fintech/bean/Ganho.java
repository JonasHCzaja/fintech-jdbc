package br.com.fiap.fintech.bean;

import java.util.Calendar;


public class Ganho {
	private int cdGanho;
	private String descricao;
	private String categoria;
	private double valor;
	private Calendar dtRecebimento;
	private String idUsuario;
	
	
	public Ganho() {
		
	}

	public Ganho(int cdGanho, String descricao, String categoria, double valor, Calendar dtRecebimento, String idUsuario) {
		super();
		this.cdGanho = cdGanho;
		this.descricao = descricao;
		this.categoria = categoria;
		this.valor = valor;
		this.dtRecebimento = dtRecebimento;
		this.idUsuario = idUsuario;
	}

	
	public int getCdGanho() {
		return cdGanho;
	}

	public void setCdGanho(int cdGanho) {
		this.cdGanho = cdGanho;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Calendar getDtRecebimento() {
		return dtRecebimento;
	}

	public void setDtRecebimento(Calendar dtRecebimento) {
		this.dtRecebimento = dtRecebimento;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
	
}
