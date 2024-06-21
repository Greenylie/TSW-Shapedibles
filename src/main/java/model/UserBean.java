package model;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String username;
	String email;
	String pass;
	String nome_cognome;
	String sesso;
	String paese;
	String data_nascita;
	int user_admin;
	
	public UserBean()
	{
		username= " ";
		email= " ";
		pass= " ";
		nome_cognome= " ";
		sesso= " ";
		paese= " ";
		data_nascita= " ";
		user_admin= -1;
	}
	
	public void setUsername(String username) 
	{
		this.username=username;
	}
	public void setEmail(String email) 
	{
		this.email=email;
	}
	public void setPass(String pass) throws NoSuchAlgorithmException 
	{
		this.pass=hashPassword(pass);
	}
	public void setNomeCognome(String nome_cognome) 
	{
		this.nome_cognome=nome_cognome;
	}
	public void setSesso(String sesso) 
	{
		this.sesso=sesso;
	}
	public void setPaese(String paese) 
	{
		this.paese=paese;
	}
	public void setDataNascita(String data_nascita) 
	{
		this.data_nascita=data_nascita;
	}
	public void setUserAdmin(int user_admin) 
	{
		this.user_admin=user_admin;
	}
	
	public String getUsername()
	{
		return username;
	}
	public String getEmail()
	{
		return email;
	}
	public String getPass()
	{
		return pass;
	}
	public String getNomeCognome()
	{
		return nome_cognome;
	}
	public String getSesso()
	{
		return sesso;
	}
	public String getPaese()
	{
		return paese;
	}
	public String getDataNascita()
	{
		return data_nascita;
	}
	public int getUserAdmin()
	{
		return user_admin;
	}
	
	
	@Override
	public String toString() {
		return username + user_admin;
	}
	
	 private String hashPassword(String password) throws NoSuchAlgorithmException {
		    MessageDigest md = MessageDigest.getInstance("SHA-512");
		    byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
		    StringBuilder sb = new StringBuilder();
		    for (byte b : hashedBytes) {
		        sb.append(String.format("%02x", b));
		    }
		    return sb.toString();
		   }

}
