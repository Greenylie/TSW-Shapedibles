package model;

import java.io.Serializable;

public class AdressBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String id;
	String utente;
	String paese;
	String strada;
	String città;
	int numero;
	String codice_postale;
	
	String getId()
	{
		return id;
	}
	
	String getUtente()
	{
		return utente;
	}
	
	String getPaese()
	{
		return paese;
	}
	
	String getStrada()
	{
		return strada;
	}
	
	String getCittà()
	{
		return città;
	}
	
	int getNumero()
	{
		return numero;
	}
	
	String getCodicePostale()
	{
		return codice_postale;
	}
	
	void setId(String id)
	{
		this.id=id;
	}
	
	void setUtente(String utente)
	{
		this.utente=utente;
	}
	
	void setPaese(String paese)
	{
		this.paese=paese;
	}
	
	void setStrada(String strada)
	{
		this.strada=strada;
	}
	
	void setCittà(String città)
	{
		this.città=città;
	}
	
	void setNumero(int numero)
	{
		this.numero=numero;
	}
	
	void setCodicePostale(String codice_postale)
	{
		this.codice_postale=codice_postale;
	}
	
	@Override
	public String toString() {
		return id + " " + utente + " " + paese + " " + strada + " "+ città + " " + numero + " " + codice_postale;
	}
	
}

