package model;

import java.io.Serializable;

public class CartBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String utente;
	String codice_prodotto;
	
	public CartBean()
	{
		utente=" ";
		codice_prodotto=" ";
	}
	
	String getUtente()
	{
	  return utente;	
	}
	
	String getCodiceProdotto()
	{
       return codice_prodotto;
	}
	
	void setUtente(String utente)
	{
	  	this.utente=utente;
	}
	
	void setCodiceProdotto(String codice_prodotto) 
	{
		this.codice_prodotto=codice_prodotto;
	}
	
	@Override
	public String toString() {
		return utente + " " + codice_prodotto;
	}

}
