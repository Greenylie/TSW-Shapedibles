package model;

import java.io.Serializable;

public class OrderBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String utente;
	String codice;
	String indirizzo;
	String stato;
	String data_ordine;
	double saldo_totale;
	
	public OrderBean()
	{
	  utente=" ";
	  codice=" ";
	  indirizzo=" ";
	  stato=" ";
	  data_ordine=" ";
	  saldo_totale=0.0;
	}
	
	String getUtente()
	{ 
		return utente;
	}
	
	String getCodice()
	{ 
		return codice;
	}
	
	String getIndirizzo()
	{ 
		return indirizzo;
	}
	
	String getStato()
	{ 
		return stato;
	}
	
	String getDataOrdine()
	{ 
		return data_ordine;
	}
	
	double getSaldoTotale()
	{ 
		return saldo_totale;
	}
	
	void setUtente(String utente) 
	{
	  this.utente=utente;	
	}
	
	void setCodice(String codice) 
	{
	  this.codice=codice;
	}
	
	void setIndirizzo(String indirizzo) 
	{
	  this.indirizzo=indirizzo;
	}
	
	void setStato(String stato) 
	{
	  this.stato=stato;
	}
	
	void setDataOrdine(String data_ordine)
	{
	  this.data_ordine=data_ordine;	
	}
	
	void setSaldoTotale(double saldo_totale) 
	{
	  this.saldo_totale=saldo_totale;
	}
	
	@Override
	public String toString() {
		return utente + " (" + codice + "), " + indirizzo + " " + stato + " "+ data_ordine + " " + saldo_totale;
	}
	
}

