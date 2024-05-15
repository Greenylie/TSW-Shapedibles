package model;

import java.io.Serializable;

public class ContainBeans implements Serializable{

	private static final long serialVersionUID = 1L;

	String utente;
	String codice_ordine;
	String codice_prodotto;
	
	public ContainBeans()
	{
		utente=" ";
		codice_ordine=" ";
		codice_prodotto=" ";
	}
	
	String getUtente()
	{
		return utente;
	}
	
	String getCodiceOrdine()
	{
		return codice_ordine;
	}
	
	String getCodiceProdotto()
	{
		return codice_prodotto;
	}
	
	void setUtente(String utente)
	{
		this.utente=utente;
	}
	
	void setCodiceUtente(String codice_ordine)
	{
		this.codice_ordine=codice_ordine;
	}
	
	void setCodiceProdotto(String codice_prodotto)
	{
		this.codice_prodotto=codice_prodotto;
	}
	
	@Override
	public String toString() {
		return utente + " " + codice_ordine + " " + codice_prodotto;
	}
}
