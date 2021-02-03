package com.Perfunity.Model;

public class IndirizzoBean {
	
	private int CAP;
	private String citta;
	private String via;
	private String ordineID;
	
	public IndirizzoBean() {}
	
	public String getOrdineID() {
		return ordineID;
	}
	public void setOrdineID(String ordineID) {
		this.ordineID = ordineID;
	}
	
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public int getCAP() {
		return CAP;
	}

	public void setCAP(int cAP) {
		CAP = cAP;
	}
	
	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

}
