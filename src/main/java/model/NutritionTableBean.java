package model;

import java.io.Serializable;

public class NutritionTableBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String codice_prodotto;
	int energia;
	double grassi;
	double grassi_saturi;
	double carboedrati;
	double zucherri;
	double fibre;
	double proteine;
	double sale;
	
	public NutritionTableBean()
	{
	  codice_prodotto=" ";
	  energia=0;
	  grassi=0.0;
	  grassi_saturi=0.0;
	  carboedrati=0.0;
	  zucherri=0.0;
	  fibre=0.0;
	  proteine=0.0;
	  sale=0.0;
	}
	
	String getCodiceProdotto()
	{
		return codice_prodotto;
	}
	
	int getEnergia()
	{
		return energia;
	}
	
	double getGrassi()
	{
		return grassi;
	}
	
	double getGrassiSaturi()
	{
		return grassi_saturi;
	}
	
	double getCarboedati()
	{
		return carboedrati;
	}
	
	double getZucherri()
	{
		return zucherri;
	}
	
	double getFibre()
	{
		return fibre;
	}
	
	double getProteine()
	{
		return proteine;
	}
	
	double getSale()
	{
		return sale;
	}
	
	void setCodiceProdotto (String codice_prodotto)
	{
	  this.codice_prodotto=codice_prodotto;
	}
	
	void setEnergia(int energia)
	{
		  this.energia=energia;
	}
	void setGrassi(double grassi)
	{
		  this.grassi=grassi;
	}
	
	void setGrassiSaturi(double grassi_saturi)
	{
		  this.grassi_saturi=grassi_saturi;
	}
	
	void setCarboedrati(double carboedrati)
	{
		  this.carboedrati=carboedrati;
	}
	
	void setZucherri(double zucherri)
	{
		  this.zucherri=zucherri;
	}
	
	void setFibre(double fibre)
	{
		  this.fibre=fibre;
	}
	
	void setProteine(double proteine)
	{
		  this.proteine=proteine;
	}
	
	void ssetSale(double sale)
	{
		  this.sale=sale;
	}
	
	@Override
	public String toString() {
		return codice_prodotto + " " + energia + " " + grassi + " " + grassi_saturi + " " + carboedrati + " "+ zucherri + " " + fibre + " " + proteine + " " + sale;
	}
}

