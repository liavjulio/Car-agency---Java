package Gui;

import java.util.ArrayList;

import vehicles.vehicles;

public class Originator 
{
	private ArrayList<vehicles>carList;
	private float km;
	public void setState(ArrayList<vehicles>AcarList,float Akm)
	{
		carList = AcarList;
		km = Akm;
	}
	public Momento createNewMomento()
	{
		return new Momento(carList,km);
	}
	public void setMomento(Momento momento)
	{
		carList = momento.getArray();
		km = momento.getKm();
	}
}
