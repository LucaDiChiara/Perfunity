package com.Perfunity.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdottoDAO {
	
	public boolean modifyProdotto(ProdottoBean pb)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("UPDATE prodotti SET prodottoID=?, nome=?, standard=?, linkImmagine=?, prezzo=?, descrizione=? WHERE prodottoID=? ");
			ps.setString(1, pb.getProdottoID());
			ps.setString(2, pb.getNome());
			ps.setString(3, pb.getStandard());
			ps.setString(4, pb.getLinkImmagine());
			ps.setFloat(5, pb.getPrezzo());
			ps.setString(6, pb.getDescrizione());
			ps.setString(7, pb.getProdottoID());
			
		
			ps.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
			
		}
		finally
		{
			try
			{
				DriverManagerConnectionPool.releaseConnection(conn);
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
			
	}
	
	public boolean deleteProdotto(String prodottoID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("DELETE FROM prodotti WHERE prodottoID = ?");
			ps.setString(1, prodottoID);
			
			ps.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				DriverManagerConnectionPool.releaseConnection(conn);
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		return false;
	}
	
	public boolean insertProdotto(ProdottoBean pb)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("INSERT prodotti VALUES(?,?,?,?,?,?)");
			ps.setString(1,pb.getProdottoID());
			ps.setString(2, pb.getNome());
			ps.setString(3, pb.getStandard());
			ps.setString(4, pb.getLinkImmagine());
			ps.setFloat(5, pb.getPrezzo());
			ps.setString(6, pb.getDescrizione());
			
			ps.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				DriverManagerConnectionPool.releaseConnection(conn);
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		return false;
	}
	
	public ArrayList<ProdottoBean> ricercaProdotto(String param,ProdottoBean pb){
		if(param != "") {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ArrayList<ProdottoBean> listaprod = new ArrayList<ProdottoBean>();
		
		try
		{
		
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT * FROM prodotti p WHERE p.nome LIKE ? LIMIT 8");
			ps.setString(1, "%"+param+"%");
			
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				pb = new ProdottoBean();
				pb.setProdottoID(res.getString("p.prodottoID"));
				pb.setNome(res.getString("p.nome"));
				pb.setStandard(res.getString("p.standard"));
				pb.setLinkImmagine(res.getString("p.linkImmagine"));
				pb.setPrezzo(res.getFloat("p.prezzo"));
				pb.setDescrizione(res.getString("p.descrizione"));
				listaprod.add(pb);
			}
			if(listaprod.isEmpty()) {
				ps2 = conn.prepareStatement("SELECT * FROM prodotti p WHERE p.nome LIKE ?");
				ps2.setString(1, "%"+param+"%");
				
				ResultSet res2 = ps2.executeQuery();
				while(res2.next()) {
					pb = new ProdottoBean();
					pb.setProdottoID(res.getString("p.prodottoID"));
					pb.setNome(res.getString("p.nome"));
					pb.setStandard(res.getString("p.standard"));
					pb.setLinkImmagine(res.getString("p.linkImmagine"));
					pb.setPrezzo(res.getFloat("p.prezzo"));
					pb.setDescrizione(res.getString("p.descrizione"));
					listaprod.add(pb);
				}
				if(listaprod.isEmpty()) {
					return null;
				}
				else { 
					return listaprod;
				}
			}
			else { 
				return listaprod;
				}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			try 
			{
				DriverManagerConnectionPool.releaseConnection(conn);
				ps.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
		else return null;
	}
	
	public ArrayList<ProdottoBean> getProdotti()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<ProdottoBean> pbarr = new ArrayList<ProdottoBean>();
		ProdottoBean pb = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT prodottoID, nome, standard, linkImmagine, prezzo, descrizione FROM prodotti LIMIT 8");
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					pb = new ProdottoBean();
					pb.setProdottoID(res.getString("prodottoID"));
					pb.setNome(res.getString("nome"));
					pb.setStandard(res.getString("standard"));
					pb.setLinkImmagine(res.getString("linkImmagine"));
					pb.setPrezzo(res.getFloat("prezzo"));
					pb.setDescrizione(res.getString("descrizione"));
					pbarr.add(pb);	
			}
			
			if(pbarr.isEmpty()) return null;
			else return pbarr;

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				DriverManagerConnectionPool.releaseConnection(conn);
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	public ProdottoBean getProdotto(String prodottoID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ProdottoBean pb = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT prodottoID, nome, standard, linkImmagine, prezzo, descrizione FROM prodotti WHERE prodottoID = ?");
			ps.setString(1,prodottoID);
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					pb = new ProdottoBean();
					pb.setProdottoID(res.getString("prodottoID"));
					pb.setNome(res.getString("nome"));
					pb.setStandard(res.getString("standard"));
					pb.setLinkImmagine(res.getString("linkImmagine"));
					pb.setPrezzo(res.getFloat("prezzo"));
					pb.setDescrizione(res.getString("descrizione"));
			}
			
			return pb;

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				DriverManagerConnectionPool.releaseConnection(conn);
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	public ArrayList<ProdottoBean> getProdottiStandard(String standard)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<ProdottoBean> pbarr = new ArrayList<ProdottoBean>();
		ProdottoBean pb = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT prodottoID, nome, standard, linkImmagine, prezzo, descrizione FROM prodotti WHERE standard = ?");
			ps.setString(1,standard);
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					pb = new ProdottoBean();
					pb.setProdottoID(res.getString("prodottoID"));
					pb.setNome(res.getString("nome"));
					pb.setStandard(res.getString("standard"));
					pb.setLinkImmagine(res.getString("linkImmagine"));
					pb.setPrezzo(res.getFloat("prezzo"));
					pb.setDescrizione(res.getString("descrizione"));
					pbarr.add(pb);	
			}
			
			if(pbarr.isEmpty()) return null;
			else return pbarr;

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				DriverManagerConnectionPool.releaseConnection(conn);
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	public ArrayList<ProdottoBean> getProdottiVenduti()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<ProdottoBean> pbarr = new ArrayList<ProdottoBean>();
		ProdottoBean pb = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT p.prodottoID, p.nome, p.standard, p.linkImmagine, p.prezzo, p.descrizione " + 
					"FROM prodotti as p, order_items as o " + 
					"WHERE p.prodottoID = o.fkProdottoID LIMIT 8");
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					pb = new ProdottoBean();
					pb.setProdottoID(res.getString("prodottoID"));
					pb.setNome(res.getString("nome"));
					pb.setStandard(res.getString("standard"));
					pb.setLinkImmagine(res.getString("linkImmagine"));
					pb.setPrezzo(res.getFloat("prezzo"));
					pb.setDescrizione(res.getString("descrizione"));
					pbarr.add(pb);	
			}
			
			if(pbarr.isEmpty()) return null;
			else return pbarr;

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				DriverManagerConnectionPool.releaseConnection(conn);
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		return null;
	}

}
