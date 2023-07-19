package vehicles;

import javax.swing.ImageIcon;

public interface abstractFactory 
{
	public vehicles create(int num , ImageIcon image ,String... args);
}
