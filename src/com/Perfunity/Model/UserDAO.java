package com.Perfunity.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




public class UserDAO {
	
	public synchronized UserBean getLogin(String email, String password)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			UserBean us = new UserBean(email,password);
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT * FROM utenti WHERE email = ?");
			ps.setString(1, email);
			
			ResultSet res = ps.executeQuery();
			String decriptpsw = null;
			if(res.next())
			{
				decriptpsw = MyCript.decrypt(res.getString("password"));
				us.setPassword(decriptpsw);
				
				if(decriptpsw.equals(password))
				{
					us.setEmail(res.getString("email"));
					us.setPassword(res.getString("password"));
					us.setNome(res.getString("nome"));
					us.setCognome(res.getString("cognome"));
					us.setGender(res.getString("gender"));
					us.setNumTel(res.getString("cellulare"));
					return us;
				}else return null;
			}
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
	
	public synchronized boolean registraAccount(UserBean ub)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			if(ub.getPassword().equals("error")) return false;
			else
			{
				conn = DriverManagerConnectionPool.getConnection();
				ps = conn.prepareStatement("INSERT INTO utenti VALUES (?,?,?,?,?,?,?) ");
				ps.setString(1, ub.getEmail());
				ps.setString(2, ub.getNome());
				ps.setString(3, ub.getCognome());
				ps.setString(4, ub.getGender());
				ps.setString(5, ub.getNumTel());
				ps.setString(6, ub.getRegistrationDate());
				ps.setString(7, ub.getPassword());
		
				ps.executeUpdate();
				return true;
			}
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
	
	public boolean insertAccount(UserBean ub)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("INSERT INTO utenti VALUES (?,?,?,?,?,?,?)");
			ps.setString(1, ub.getEmail());
			ps.setString(2, ub.getNome());
			ps.setString(3, ub.getCognome());
			ps.setString(4, ub.getGender());
			ps.setString(5, ub.getNumTel());
			ps.setString(6, ub.getRegistrationDate());
			ps.setString(7, ub.getPassword());
		
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
	
	public boolean modifyAccount(UserBean ub)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("UPDATE utenti SET email=?, nome=?, cognome=?, gender=?, cellulare=?, rData=?,password=? WHERE email=? ");
			ps.setString(1, ub.getEmail());
			ps.setString(2, ub.getNome());
			ps.setString(3, ub.getCognome());
			ps.setString(4, ub.getGender());
			ps.setString(5, ub.getNumTel());
			ps.setString(6, ub.getRegistrationDate());
			ps.setString(7, ub.getPassword());
			ps.setString(8, ub.getEmail());
		
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
	
	public boolean deleteUser(String email)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("DELETE FROM utenti WHERE email = ?");
			ps.setString(1, email);
			
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
	
	public UserBean getUtente(String email)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			UserBean us = new UserBean();
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT * FROM utenti WHERE email = ?");
			ps.setString(1, email);
			
			ResultSet res = ps.executeQuery();
			
			if(res.next())
			{
				us.setEmail(res.getString("email"));
				us.setPassword(res.getString("password"));
				us.setNome(res.getString("nome"));
				us.setCognome(res.getString("cognome"));
				us.setGender(res.getString("gender"));
				us.setNumTel(res.getString("cellulare"));
				return us;
			}
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
	
	public UserBean getUtenteTel(String numTel)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			UserBean us = new UserBean();
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT * FROM utenti WHERE cellulare = ?");
			ps.setString(1, numTel);
			
			ResultSet res = ps.executeQuery();
			
			if(res.next())
			{
				us.setEmail(res.getString("email"));
				us.setPassword(res.getString("password"));
				us.setNome(res.getString("nome"));
				us.setCognome(res.getString("cognome"));
				us.setGender(res.getString("gender"));
				us.setNumTel(res.getString("cellulare"));
				return us;
			}
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
	
	public ArrayList<UserBean> getUtenti()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<UserBean> ubarr = new ArrayList<UserBean>();
		UserBean us = null;
		
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("SELECT * FROM utenti");
			
			ResultSet res = ps.executeQuery();
			
			while(res.next())
			{
				us = new UserBean();
				us.setEmail(res.getString("email"));
				us.setNome(res.getString("nome"));
				us.setCognome(res.getString("cognome"));
				ubarr.add(us);
			}
			
			if(ubarr.isEmpty()) return null;
			else return ubarr;
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
	
	public boolean updateEmail(String newEmail, String email)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("UPDATE utenti SET email = ? WHERE email = ?");
			ps.setString(1, newEmail);
			ps.setString(2, email);

		
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
	
	public boolean updatePassword(String newPassword, String email)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			String newPassword1 = MyCript.encrypt(newPassword);
			conn = DriverManagerConnectionPool.getConnection();
			ps = conn.prepareStatement("UPDATE utenti SET password = ? WHERE email = ?");
			ps.setString(1, newPassword1);
			ps.setString(2, email);

		
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

}
