package model;

import java.io.Serializable;

public class ContainBean implements Serializable{

	private static final long serialVersionUID = 1L;

	String utente;
	String codice_ordine;
	int codice_prodotto;
	
	public ContainBean()
	{
		utente=" ";
		codice_ordine=" ";
		codice_prodotto=-1;
	}
	
	String getUtente()
	{
		return utente;
	}
	
	String getCodiceOrdine()
	{
		return codice_ordine;
	}
	
	int getCodiceProdotto()
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
	
	void setCodiceProdotto(int codice_prodotto)
	{
		this.codice_prodotto=codice_prodotto;
	}
	
	@Override
	public String toString() {
		return utente + " " + codice_ordine + " " + codice_prodotto;
	}
}
