package Gui;

import java.util.ArrayList;

public class Caretaker 
{
	private static final ArrayList<Momento> stateList = new ArrayList<>(3);
	public void addMomento(Momento momento)
	{
		if (stateList.size() < 3) 
		{
			stateList.add(momento);
		}
		else 
		{
			stateList.remove(0);
			stateList.add(momento);
		}
	}
	public Momento getMomento() 
	{
        if (stateList.size() > 0) 
        {
            return stateList.remove(stateList.size() - 1);
        }
        return null;
    }
	
}

