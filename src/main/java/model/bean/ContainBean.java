package model.bean;

import java.io.Serializable;

public class ContainBean implements Serializable{

	private static final long serialVersionUID = 1L;

	String utente;
	int codice_ordine;
	int codice_prodotto;
	
	public ContainBean()
	{
		utente=" ";
		codice_ordine=-1;
		codice_prodotto=-1;
	}
	
	public String getUtente()
	{
		return utente;
	}
	
	public int getCodiceOrdine()
	{
		return codice_ordine;
	}
	
	public int getCodiceProdotto()
	{
		return codice_prodotto;
	}
	
	public void setUtente(String utente)
	{
		this.utente=utente;
	}
	
	public void setCodiceOrdine(int codice_ordine)
	{
		this.codice_ordine=codice_ordine;
	}
	
	public void setCodiceProdotto(int codice_prodotto)
	{
		this.codice_prodotto=codice_prodotto;
	}
	
	@Override
	public String toString() {
		return utente + " " + codice_ordine + " " + codice_prodotto;
	}
}
