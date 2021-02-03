package com.Perfunity.Model;

public class RecensioneBean {
	
	private String recensioneID;
	private String prodottoID;
	private String testo;
	private int valutazione;
	private String emailUser;
	private String nome;
	private String status;
	
	public String getRecensioneID() {
		return recensioneID;
	}
	public void setRecensioneID(String recensioneID) {
		this.recensioneID = recensioneID;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmailUser() {
		return emailUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public String getProdottoID() {
		return prodottoID;
	}
	public void setProdottoID(String prodottoID) {
		this.prodottoID = prodottoID;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public int getValutazione() {
		return valutazione;
	}
	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}
	
	

}
