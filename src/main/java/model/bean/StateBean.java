package model.bean;

import java.io.Serializable;

public class StateBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int numState;
	private int codiceProdotto;
	private String img;
	private String nome;
	private double costo;
	
	public StateBean() 
	{
		numState= 0;
		codiceProdotto= 0;
		img=" ";
		nome=" ";
		costo= 0.0;
	}
	
	public void setNumState(int numState)
	{
		this.numState=numState;
	}
	
	public void setImg(String img)
	{
		this.img=img;
	}
	
	public void setCodiceProdotto(int codiceProdotto)
	{
		this.codiceProdotto=codiceProdotto;
	}
	
	public void setNome (String nome)
	{
		this.nome=nome;
	}
	
	public void setCosto (double costo)
	{
		this.costo=costo;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public double getCosto()
	{
		return costo;
	}
	
	public int getNumState()
	{
		return numState;
	}
	
	public String getImg()
	{
		return img;
	}
	
	public int getCodiceProdotto()
	{
		return codiceProdotto;
	}
	

	
	@Override
	public String toString() {
		return numState + " " + codiceProdotto + " " + nome +" " + img + " " + costo;
	}
}
