package com.Perfunity.Model;

public class TicketBean {
	
	private String richiestaID;
	private String emailUtenti;
	private String testo;
	private String flag;
	private String testo_risposta;
	private String oggetto;
	
	public String getOggetto() {
		return oggetto;
	}
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	public String getRichiestaID() {
		return richiestaID;
	}
	public void setRichiestaID(String richiestaID) {
		this.richiestaID = richiestaID;
	}
	public String getEmailUtenti() {
		return emailUtenti;
	}
	public void setEmailUtenti(String emailUtenti) {
		this.emailUtenti = emailUtenti;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getTesto_risposta() {
		return testo_risposta;
	}
	public void setTesto_risposta(String testo_risposta) {
		this.testo_risposta = testo_risposta;
	}

}
