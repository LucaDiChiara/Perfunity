package com.Perfunity.Model;

import java.util.ArrayList;

public class CarrelloBean {

	private ArrayList<ProdottoBean> prodotti = null;
	private ProdottoBean pb=null;
	private ArrayList<Integer> qty = null;
	private ArrayList<Float> totale = null;
	
	public int getQty(String prodottoID) {
		for(int i=0;i<prodotti.size();i++)
		{
			if(prodotti.get(i).getProdottoID().equals(prodottoID)) 	return qty.get(i);
		}
		return 0;
	}
	
	public ArrayList<Integer> getAllQty()
	{
		return qty;
	}

	public void setQty(int qty, String prodottoID) {
		int i=0;
		while(i<prodotti.size())
		{
			if(prodotti.get(i).getProdottoID().equals(prodottoID)) 
			{
				this.qty.add(i,qty);
			}
			i++;
		}
	}

	public CarrelloBean() {
		prodotti = new ArrayList<>();
		qty = new ArrayList<>();
		totale = new ArrayList<>();
		pb = new ProdottoBean();
	}
	
	public ProdottoBean getPb() {
		return pb;
	}
	
	public void addP(ProdottoBean p) {
		
		prodotti.add(p);
	}
	
	
	public void removeP(ProdottoBean p) 
	{
		int i = 0;
		while(i < prodotti.size()) 
		{
			if(p.getProdottoID().equals(prodotti.get(i).getProdottoID())) 
			{
				prodotti.remove(i);
				break;
			}
			i++;
		}
	}
	
	
	public void clean() {
		prodotti.clear();
		qty.clear();
		totale.clear();
	}

	public ArrayList<ProdottoBean> getProdotti() {
		return prodotti;
	}
	
	public boolean getProdottoB(String prodottoID)
	{
		for(int i=0;i<prodotti.size();i++)
		{
			if(prodotti.get(i).getProdottoID().equals(prodottoID)) 	return true;
		}
		return false;
	}
	
	public ProdottoBean getProdotto(String prodottoID)
	{
		for(int i=0;i<prodotti.size();i++)
		{
			if(prodotti.get(i).getProdottoID().equals(prodottoID)) 	return prodotti.get(i);
		}
		return null;
	}

	public void setProdotti(ArrayList<ProdottoBean> prodotti) {
		this.prodotti = prodotti;
	}
	
	public ArrayList<Float> getTotaleParziale(ArrayList<ProdottoBean> prodotti, ArrayList<Integer> qty)
	{
		totale.clear();
		int i = 0;
		float totale_prod = 0;
		while(i<prodotti.size())
		{
			totale_prod = prodotti.get(i).getPrezzo() * qty.get(i);
			totale.add(i,totale_prod);
			i++;
		}
		return totale;
	}
	
	public float getTotaleCart()
	{
		int i = 0;
		float totaleCart = 0;
		while(i<this.totale.size())
		{
			totaleCart += this.totale.get(i);
			i++;
		}
		return totaleCart;
	}
	
	
}