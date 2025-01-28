package br.com.fiap.fintech.bean;





import java.util.Calendar;


public class Despesa {
	private int cdDespesa;
	private String tipoDespesa;
	private String descricao;
	private String categoria;
	private double valor;
	private Calendar dtDespesa;
	private boolean cartaoCredito = false;
	private String dsCartao;
	private String idUsuario;
	
	public Despesa() {
		
	}

	

	public Despesa(int cdDespesa, String tipoDespesa, String descricao, String categoria, double valor, Calendar dtDespesa,
			String dsCartao, String idUsuario) {
		super();
		this.cdDespesa = cdDespesa;
		this.tipoDespesa = tipoDespesa;
		this.descricao = descricao;
		this.categoria = categoria;
		this.valor = valor;
		this.dtDespesa = dtDespesa;
		this.dsCartao = dsCartao;
		this.idUsuario = idUsuario;
	}
	
	public int getCdDespesa() {
		return cdDespesa;
	}

	public void setCdDespesa(int cdDespesa) {
		this.cdDespesa = cdDespesa;
	}

	public String getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(String tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
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

	public Calendar getDtDespesa() {
		return dtDespesa;
	}

	public void setDtDespesa(Calendar dtDespesa) {
		this.dtDespesa = dtDespesa;
	}

	
	public boolean isCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(boolean cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public String getDsCartao() {
		return dsCartao;
	}

	public void setDsCartao(String dsCartao) {
		this.dsCartao = dsCartao;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
}
