package model;

import java.io.Serializable;

public class CartBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String utente;
	int codice_prodotto;
	
	public CartBean()
	{
		utente=" ";
		codice_prodotto=-1;
	}
	
	public String getUtente()
	{
	  return utente;	
	}
	
	public int getCodiceProdotto()
	{
       return codice_prodotto;
	}
	
	public void setUtente(String utente)
	{
	  	this.utente=utente;
	}
	
	public void setCodiceProdotto(int codice_prodotto) 
	{
		this.codice_prodotto=codice_prodotto;
	}
	
	@Override
	public String toString() {
		return utente + " " + codice_prodotto;
	}

}
