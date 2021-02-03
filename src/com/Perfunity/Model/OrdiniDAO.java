package com.Perfunity.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdiniDAO {
	
	public boolean effettuaOrdine(String email, String ordineID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("INSERT effettua VALUES(?,?)");
			ps.setString(1, email);
			ps.setString(2, ordineID);
			
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
	
	public boolean updateStatus(String status, String ordineID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("UPDATE ordini SET status = ? WHERE ordineID = ?");
			ps.setString(1, status);
			ps.setString(2, ordineID);
			
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
	
	public boolean insertQtyItems(String fkProdottoID, String fkOrdineID, int qty)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ProdottoDAO pdao = new ProdottoDAO();
		ProdottoBean pb = new ProdottoBean();
		try
		{
			pb = pdao.getProdotto(fkProdottoID);
			float price = pb.getPrezzo();
			float totale_prodotto = price * qty;
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("INSERT order_items VALUES(?,?,?,?)");
			ps.setString(1, fkProdottoID);
			ps.setString(2, fkOrdineID);
			ps.setInt(3, qty);
			ps.setFloat(4,totale_prodotto);
			
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
	
	public boolean insertOrder(String id, String data, String numTel, float totale)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("INSERT ordini VALUES(?,?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, data);
			ps.setString(3, "In consegna");
			ps.setString(4, numTel);
			ps.setFloat(5, totale);
			
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
	
	public ArrayList<OrdiniBean> getOrdini(String numTel)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<OrdiniBean> obarr = new ArrayList<OrdiniBean>();
		OrdiniBean ob = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT o.totale, o.ordineID, o.data, o.status, o.numTel "
									 + "FROM ordini as o, utenti as u "
									 + "WHERE o.numTel = u.cellulare AND o.numTel = ?");
			
			ps.setString(1, numTel);
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					ob = new OrdiniBean();
					ob.setOrdineID(res.getString("o.ordineID"));
					ob.setData(res.getString("o.data"));
					ob.setStatus(res.getString("o.status"));
					ob.setNumTel(res.getString("o.numTel"));
					ob.setTotale(res.getFloat("o.totale"));
					obarr.add(ob);	
			}
			
			if(obarr.isEmpty()) return null;
			else return obarr;

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
	
	public ArrayList<OrdiniBean> getOrdiniTotali()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<OrdiniBean> obarr = new ArrayList<OrdiniBean>();
		OrdiniBean ob = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT o.totale, o.ordineID, o.data, o.status, o.numTel "
									 + "FROM ordini as o");
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					ob = new OrdiniBean();
					ob.setOrdineID(res.getString("o.ordineID"));
					ob.setData(res.getString("o.data"));
					ob.setStatus(res.getString("o.status"));
					ob.setNumTel(res.getString("o.numTel"));
					ob.setTotale(res.getFloat("o.totale"));
					obarr.add(ob);	
			}
			
			if(obarr.isEmpty()) return null;
			else return obarr;

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
	
	public OrdiniBean getOrdine(String ordineID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		OrdiniBean ob = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT o.totale, o.ordineID, o.data, o.status, o.numTel "
									 + "FROM ordini as o, utenti as u "
									 + "WHERE o.ordineID = ?");
			
			ps.setString(1, ordineID);
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					ob = new OrdiniBean();
					ob.setOrdineID(res.getString("o.ordineID"));
					ob.setData(res.getString("o.data"));
					ob.setStatus(res.getString("o.status"));
					ob.setNumTel(res.getString("o.numTel"));
					ob.setTotale(res.getFloat("o.totale"));	
			}
			
			return ob;

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
	
	public ArrayList<OrdiniBean> getQtyTotale(String ordineID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		OrdiniBean ob = null;
		ArrayList<OrdiniBean> obarr = new ArrayList<OrdiniBean>();
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT qty, totale_prodotto, fkProdottoID "
									 + "FROM order_items "
									 + "WHERE fkOrdineID = ?");
			
			ps.setString(1, ordineID);
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					ob = new OrdiniBean();
					ob.setQty(res.getInt("qty"));
					ob.setTotale_prodotto(res.getFloat("totale_prodotto"));
					ob.setFkProdottoID(res.getString("fkProdottoID"));
					obarr.add(ob);
			}
			
			if(obarr.isEmpty()) return null;
			else return obarr;

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
	
	public boolean deleteOrdine(String ordineID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("DELETE FROM ordini WHERE ordineID = ?");
			ps.setString(1, ordineID);
			
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

}
