package com.Perfunity.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IndirizzoDAO {
	
	public boolean insertIndirizzo(String ordineID,String via, String citta, int cap)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("INSERT indirizzo VALUES(?,?,?,?)");
			ps.setString(1, ordineID);
			ps.setString(2, via);
			ps.setString(3, citta);
			ps.setInt(4,cap);
			
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
	
	public IndirizzoBean getIndirizzo(String ordineID)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		IndirizzoBean ib = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT * "
									 + "FROM indirizzo "
									 + "WHERE ordineID = ?");
			
			ps.setString(1, ordineID);
			
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
					ib = new IndirizzoBean();
					ib.setOrdineID(res.getString("ordineID"));
					ib.setVia(res.getString("via"));
					ib.setCitta(res.getString("citta"));
					ib.setCAP(res.getInt("cap"));
			}
			
			return ib;
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
