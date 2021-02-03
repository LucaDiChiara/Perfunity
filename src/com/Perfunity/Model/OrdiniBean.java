package com.Perfunity.Model;

public class OrdiniBean {
	
	private String ordineID;
	private String data;
	private String status;
	private String numTel;
	private float totale;
	private float totale_prodotto;
	private int qty;
	private String fkProdottoID;
	
	

	public String getFkProdottoID() {
		return fkProdottoID;
	}

	public void setFkProdottoID(String fkProdottoID) {
		this.fkProdottoID = fkProdottoID;
	}

	public float getTotale_prodotto() {
		return totale_prodotto;
	}

	public void setTotale_prodotto(float totale_prodotto) {
		this.totale_prodotto = totale_prodotto;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public OrdiniBean() {}
	
	public float getTotale() {
		return totale;
	}

	public void setTotale(float totale) {
		this.totale = totale;
	}
	
	public String getOrdineID() {
		return ordineID;
	}
	public void setOrdineID(String ordineID) {
		this.ordineID = ordineID;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
}
