package br.com.fiap.fintech.bean;



public class Investimento {

	private int cdAtivo;
	private String nomeAtivo;
	private String categoriaAtivo;
	private double precoMedio;
	private double quantidade;
	private String idUsuario;
	
	
	public Investimento() {
		
	}


	public Investimento(int cdAtivo, String nomeAtivo, String categoriaAtivo, 
			double precoMedio, double quantidade, String idUsuario) {
		super();
		this.cdAtivo = cdAtivo;
		this.nomeAtivo = nomeAtivo;
		this.categoriaAtivo = categoriaAtivo;
		this.precoMedio = precoMedio;
		this.quantidade = quantidade;
		this.idUsuario = idUsuario;
	}

	
	public int getCdAtivo() {
		return cdAtivo;
	}
	
	public void setCdAtivo(int cdAtivo) {
		this.cdAtivo = cdAtivo;
	}
	
	public String getNomeAtivo() {
		return nomeAtivo;
	}
	
	
	public void setNomeAtivo(String nomeAtivo) {
		this.nomeAtivo = nomeAtivo;
	}


	public String getCategoriaAtivo() {
		return categoriaAtivo;
	}


	public void setCategoriaAtivo(String categoriaAtivo) {
		this.categoriaAtivo = categoriaAtivo;
	}



	public double getPrecoMedio() {
		return precoMedio;
	}


	public void setPrecoMedio(double precoMedio) {
		this.precoMedio = precoMedio;
	}


	public double getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}


	public String getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
	
}
