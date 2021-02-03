package com.Perfunity.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class RecensioneDAO {
	
	public ArrayList<RecensioneBean> getRecensioniUtente(String email)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<RecensioneBean> rbarr = new ArrayList<RecensioneBean>();
		RecensioneBean rb = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT r.status, r.nome, r.prodottoID, r.testo, r.valutazione FROM recensioni as r WHERE r.emailUser = ?");
			ps.setString(1,email);
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					rb = new RecensioneBean();
					//rb.setProdottoID(res.getString("r.prodottoID"));
					rb.setTesto(res.getString("r.testo"));
					rb.setValutazione(res.getInt("r.valutazione"));
					//rb.setEmailUser(res.getString("r.emailUser"));
					rb.setNome(res.getString("r.nome"));
					rb.setStatus(res.getString("r.status"));
					rbarr.add(rb);	
			}
			
			if(rbarr.isEmpty()) return null;
			else return rbarr;

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
	
	public ArrayList<RecensioneBean> getRecensioni(String prodottoID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<RecensioneBean> rbarr = new ArrayList<RecensioneBean>();
		RecensioneBean rb = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT r.status, r.nome, r.prodottoID, r.testo, r.valutazione FROM recensioni as r WHERE prodottoID = ? AND status = ?");
			ps.setString(1,prodottoID);
			ps.setString(2,"true");
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					rb = new RecensioneBean();
					//rb.setProdottoID(res.getString("r.prodottoID"));
					rb.setTesto(res.getString("r.testo"));
					rb.setValutazione(res.getInt("r.valutazione"));
					//rb.setEmailUser(res.getString("r.emailUser"));
					rb.setNome(res.getString("r.nome"));
					rb.setStatus(res.getString("r.status"));
					rbarr.add(rb);	
			}
			
			if(rbarr.isEmpty()) return null;
			else return rbarr;

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
	
	public boolean insertRecensione(String prodottoID, String testo, int valutazione, String emailUser, String nome)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			Random rand = new Random();
			int id_int = rand.nextInt(1000)+1;
			String id = String.valueOf(id_int);
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("INSERT recensioni VALUES(?,?,?,?,?,?,?)");
			ps.setString(1,id);
			ps.setString(2, prodottoID);
			ps.setInt(3, valutazione);
			ps.setString(4, testo);
			ps.setString(5, emailUser);
			ps.setString(6, nome);
			ps.setString(7, "false");
			
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
	
	public ArrayList<RecensioneBean> getRecensioniTrue()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<RecensioneBean> rbarr = new ArrayList<RecensioneBean>();
		RecensioneBean rb = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT r.recensioniID, r.emailUser, r.status, r.nome, r.prodottoID, r.testo, r.valutazione FROM recensioni as r WHERE status = ?");
			ps.setString(1,"true");
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					rb = new RecensioneBean();
					rb.setRecensioneID(res.getString("r.recensioniID"));
					rb.setProdottoID(res.getString("r.prodottoID"));
					rb.setTesto(res.getString("r.testo"));
					rb.setValutazione(res.getInt("r.valutazione"));
					rb.setEmailUser(res.getString("r.emailUser"));
					rb.setNome(res.getString("r.nome"));
					rb.setStatus(res.getString("r.status"));
					rbarr.add(rb);	
			}
			
			if(rbarr.isEmpty()) return null;
			else return rbarr;

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
	
	public ArrayList<RecensioneBean> getRecensioniFalse()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<RecensioneBean> rbarr = new ArrayList<RecensioneBean>();
		RecensioneBean rb = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT r.recensioniID,r.emailUser, r.status, r.nome, r.prodottoID, r.testo, r.valutazione FROM recensioni as r WHERE status = ?");
			ps.setString(1,"false");
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					rb = new RecensioneBean();
					rb.setRecensioneID(res.getString("r.recensioniID"));
					rb.setProdottoID(res.getString("r.prodottoID"));
					rb.setTesto(res.getString("r.testo"));
					rb.setValutazione(res.getInt("r.valutazione"));
					rb.setEmailUser(res.getString("r.emailUser"));
					rb.setNome(res.getString("r.nome"));
					rb.setStatus(res.getString("r.status"));
					rbarr.add(rb);	
			}
			
			if(rbarr.isEmpty()) return null;
			else return rbarr;

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
	
	public boolean updateRecensioniTrue(String recensioneID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("UPDATE recensioni SET status=? WHERE recensioniID = ?");
			ps.setString(1, "true");
			ps.setString(2, recensioneID);
			
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
	
	public boolean deleteRecensioni(String recensioneID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("DELETE FROM recensioni WHERE recensioniID = ?");
			ps.setString(1, recensioneID);
			
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
