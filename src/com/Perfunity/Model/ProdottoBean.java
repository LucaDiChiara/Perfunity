package com.Perfunity.Model;

public class ProdottoBean {
	
	private String prodottoID;
	private String nome;
	private String standard;
	private String linkImmagine;
	private float prezzo;
	private String descrizione;
	
	public String getProdottoID() {
		return prodottoID;
	}
	public void setProdottoID(String prodottoID) {
		this.prodottoID = prodottoID;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getLinkImmagine() {
		return linkImmagine;
	}
	public void setLinkImmagine(String linkImmagine) {
		this.linkImmagine = linkImmagine;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdottoBean other = (ProdottoBean) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (linkImmagine == null) {
			if (other.linkImmagine != null)
				return false;
		} else if (!linkImmagine.equals(other.linkImmagine))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Float.floatToIntBits(prezzo) != Float.floatToIntBits(other.prezzo))
			return false;
		if (prodottoID == null) {
			if (other.prodottoID != null)
				return false;
		} else if (!prodottoID.equals(other.prodottoID))
			return false;
		if (standard == null) {
			if (other.standard != null)
				return false;
		} else if (!standard.equals(other.standard))
			return false;
		return true;
	}

}
