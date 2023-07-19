package Gui;

import java.util.ArrayList;

import vehicles.vehicles;

public class Momento 
{
	private ArrayList<vehicles>carList;
	private float km;
	public Momento(ArrayList<vehicles>AcarList,float Akm)
	{
		carList = AcarList;
		km = Akm;
	}
	public ArrayList<vehicles> getArray()
	{
		return carList;
	}
	public float getKm()
	{
		return km;
	}
}
