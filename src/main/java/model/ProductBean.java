package model;

import java.io.Serializable;

public class ProductBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	String codice;
	String nome;
	double costo;
	String descrizione;
	int disponibilità;
	String tipologia;
	
	public ProductBean()
	{
	 codice= " ";
     nome= " ";
	 costo= 0.0;
	 descrizione= " ";
	 disponibilità= 1;
	 tipologia= " ";
	}
	
	String getCodice()
	{
		return codice;
	}
	
	String getNome()
	{
		return nome;
	}
	
	double getCosto()
	{
		return costo;
	}
	
	String getDescrizione()
	{
		return descrizione;
	}
	
	int getDisponibilità()
	{
		return disponibilità;
	}
	
	String getTipologia()
	{
		return tipologia;
	}
	
	void setCodice(String codice)
	{
		this.codice=codice;
	}
	
	void setNome (String nome)
	{
		this.nome=nome;
	}
	
	void setCosto (double costo)
	{
		this.costo=costo;
	}
	
	void setDescrizione (String descrizione)
	{
		this.descrizione=descrizione;
	}
	
	void setDisponibilità (int disponibilità)
	{
		this.disponibilità=disponibilità;
	}
	
	void setTipologia (String tipologia)
	{
		this.tipologia=tipologia;
	}
	 
	@Override
	public String toString() {
		return nome + " (" + codice + "), " + costo + " " + tipologia + ""+ disponibilità + ". " + descrizione;
	}
	
}
