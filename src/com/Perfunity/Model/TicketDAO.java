package com.Perfunity.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class TicketDAO {
	
	public ArrayList<TicketBean> getTickets(String email)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<TicketBean> tbarr = new ArrayList<TicketBean>();
		TicketBean tb = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT t.oggetto, t.richiestaID, t.emailUtenti, t.testo, t.flag, t.testo_risposta "
									 + "FROM richieste_cliente as t, utenti as u "
									 + "WHERE t.emailUtenti = u.email AND t.emailUtenti = ?");
			
			ps.setString(1, email);
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					tb = new TicketBean();
					tb.setRichiestaID(res.getString("t.richiestaID"));
					tb.setEmailUtenti(res.getString("t.emailUtenti"));
					tb.setTesto(res.getString("t.testo"));
					tb.setFlag(res.getString("t.flag"));
					tb.setTesto_risposta(res.getString("t.testo_risposta"));
					tb.setOggetto(res.getString("t.oggetto"));
					tbarr.add(tb);	
			}
			
			if(tbarr.isEmpty()) return null;
			else return tbarr;

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
	
	public TicketBean getTicket(String ticketID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		TicketBean tb = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT t.oggetto, t.richiestaID, t.emailUtenti, t.testo, t.flag, t.testo_risposta "
									 + "FROM richieste_cliente as t "
									 + "WHERE t.richiestaID = ?");
			
			ps.setString(1, ticketID);
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					tb = new TicketBean();
					tb.setRichiestaID(res.getString("t.richiestaID"));
					tb.setEmailUtenti(res.getString("t.emailUtenti"));
					tb.setTesto(res.getString("t.testo"));
					tb.setFlag(res.getString("t.flag"));
					tb.setTesto_risposta(res.getString("t.testo_risposta"));
					tb.setOggetto(res.getString("t.oggetto"));

			}
			return tb;

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
	
	public boolean insertTicket(String emailUser, String objTicket, String testoTicket)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			Random rand = new Random();
			int id_int = rand.nextInt(1000)+1;
			String id = String.valueOf(id_int);
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("INSERT richieste_cliente VALUES(?,?,?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, emailUser);
			ps.setString(3, testoTicket);
			ps.setString(4, "false");
			ps.setString(5, null);
			ps.setString(6, objTicket);
			
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
	
	public ArrayList<TicketBean> getTicketsTrue()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<TicketBean> tbarr = new ArrayList<TicketBean>();
		TicketBean tb = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT t.oggetto, t.richiestaID, t.emailUtenti, t.testo, t.flag, t.testo_risposta "
									 + "FROM richieste_cliente as t "
									 + "WHERE t.flag = ?");
			
			ps.setString(1, "true");
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					tb = new TicketBean();
					tb.setRichiestaID(res.getString("t.richiestaID"));
					tb.setEmailUtenti(res.getString("t.emailUtenti"));
					tb.setTesto(res.getString("t.testo"));
					tb.setFlag(res.getString("t.flag"));
					tb.setTesto_risposta(res.getString("t.testo_risposta"));
					tb.setOggetto(res.getString("t.oggetto"));
					tbarr.add(tb);	
			}
			
			if(tbarr.isEmpty()) return null;
			else return tbarr;

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
	
	public ArrayList<TicketBean> getTicketsFalse()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<TicketBean> tbarr = new ArrayList<TicketBean>();
		TicketBean tb = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT t.oggetto, t.richiestaID, t.emailUtenti, t.testo, t.flag, t.testo_risposta "
									 + "FROM richieste_cliente as t "
									 + "WHERE t.flag = ?");
			
			ps.setString(1, "false");
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					tb = new TicketBean();
					tb.setRichiestaID(res.getString("t.richiestaID"));
					tb.setEmailUtenti(res.getString("t.emailUtenti"));
					tb.setTesto(res.getString("t.testo"));
					tb.setFlag(res.getString("t.flag"));
					tb.setTesto_risposta(res.getString("t.testo_risposta"));
					tb.setOggetto(res.getString("t.oggetto"));
					tbarr.add(tb);	
			}
			
			if(tbarr.isEmpty()) return null;
			else return tbarr;

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
	
	public boolean rispTicket(String richiestaID, String testo_risposta)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("UPDATE richieste_cliente SET testo_risposta=?, flag=? WHERE richiestaID=? ");
			ps.setString(1, testo_risposta);
			ps.setString(2, "true");
			ps.setString(3, richiestaID);
		
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
	
	public boolean deleteTicket(String richiestaID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("DELETE FROM richieste_cliente WHERE richiestaID = ?");
			ps.setString(1, richiestaID);
			
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
