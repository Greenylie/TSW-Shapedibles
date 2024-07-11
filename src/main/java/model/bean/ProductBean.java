package model.bean;

import java.io.Serializable;

public class ProductBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int codice;
	private int infoCorrenti;
	String nome;
	
	public ProductBean()
	{
	  codice= -1;
      infoCorrenti=-1;
      nome= " ";
	}
	
	public int getCodice()
	{
		return codice;
	}
	
	public void setCodice(int codice)
	{
		this.codice=codice;
	}
	
	public int getInfoCorrenti()
	{
		return infoCorrenti;
	}
	
	public void setInfoCorrenti(int infoCorrenti)
	{
		this.infoCorrenti=infoCorrenti;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public void setNome (String nome)
	{
		this.nome=nome;
	}
}
