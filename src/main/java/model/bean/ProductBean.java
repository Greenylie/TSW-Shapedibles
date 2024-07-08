package model.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("ALL")
public class ProductBean implements Serializable
{
	@Serial
	private static final long serialVersionUID = 1L;
	int codice;
	String nome;
	double costo;
	String descrizione;
	int disponibilità;
	String tipologia;
	
	public ProductBean()
	{
	 codice= -1;
     nome= " ";
	 costo= 0.0;
	 descrizione= " ";
	 disponibilità= 1;
	 tipologia= " ";
	}
	
	public int getCodice()
	{
		return codice;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public double getCosto()
	{
		return costo;
	}
	
	public String getDescrizione()
	{
		return descrizione;
	}
	
	public int getDisponibilità()
	{
		return disponibilità;
	}
	
	public String getTipologia()
	{
		return tipologia;
	}
	
	public void setCodice(int codice)
	{
		this.codice=codice;
	}
	
	public void setNome (String nome)
	{
		this.nome=nome;
	}
	
	public void setCosto (double costo)
	{
		this.costo=costo;
	}
	
	public void setDescrizione (String descrizione)
	{
		this.descrizione=descrizione;
	}
	
	public void setDisponibilità (int disponibilità)
	{
		this.disponibilità=disponibilità;
	}
	
	public void setTipologia (String tipologia)
	{
		this.tipologia=tipologia;
	}
	 
	@Override
	public String toString() {
		return nome + " (" + codice + "), " + costo + " " + tipologia + ""+ disponibilità + ". " + descrizione;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProductBean that = (ProductBean) o;
		return Objects.equals(codice, that.codice);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codice);
	}
	
}
