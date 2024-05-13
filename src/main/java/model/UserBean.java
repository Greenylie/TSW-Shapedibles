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
	
	void setUsername(String username) 
	{
		this.username=username;
	}
	void setEmail(String email) 
	{
		this.email=email;
	}
	void setPass(String pass) 
	{
		this.pass=pass;
	}
	void setNomeCOgnome(String nome_cognome) 
	{
		this.nome_cognome=nome_cognome;
	}
	void setSesso(String sesso) 
	{
		this.sesso=sesso;
	}
	void setPaese(String paese) 
	{
		this.paese=paese;
	}
	void setDataNascita(String paese) 
	{
		this.paese=paese;
	}
	void setUserAdmmin(boolean user_admin) 
	{
		this.user_admin=user_admin;
	}
	
	String getUsername()
	{
		return username;
	}
	String getEmail()
	{
		return email;
	}
	String getPass()
	{
		return pass;
	}
	String setNomeCognome()
	{
		return nome_cognome;
	}
	String setSesso()
	{
		return sesso;
	}
	String getPaese()
	{
		return paese;
	}
	String getDataNascita()
	{
		return data_nascita;
	}
	boolean getUserAdmin()
	{
		return user_admin;
	}
	
	
	@Override
	public String toString() {
		return username + user_admin;
	}

}
