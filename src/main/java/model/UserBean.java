package model;

import java.io.Serializable;

public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String username;
	String email;
	String pass;
	String nome_cognome;
	String sesso;
	String paese;
	String data_nascita;
	boolean user_admin;
	
	public UserBean()
	{
		username= " ";
		email= " ";
		pass= " ";
		nome_cognome= " ";
		sesso= " ";
		paese= " ";
		data_nascita= " ";
		user_admin= false;
	}
	
	public void setUsername(String username) 
	{
		this.username=username;
	}
	public void setEmail(String email) 
	{
		this.email=email;
	}
	public void setPass(String pass) 
	{
		this.pass=pass;
	}
	public void setNomeCOgnome(String nome_cognome) 
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
	public void setDataNascita(String paese) 
	{
		this.paese=paese;
	}
	public void setUserAdmmin(boolean user_admin) 
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
	public String setNomeCognome()
	{
		return nome_cognome;
	}
	public String setSesso()
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
	public boolean getUserAdmin()
	{
		return user_admin;
	}
	
	
	@Override
	public String toString() {
		return username + user_admin;
	}

}
