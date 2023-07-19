package vehicles;

import java.awt.Color;

import javax.swing.ImageIcon;

public interface Vehicle_Interface 
{
	Color getColor();
	String getStatus();
	void setStatus(String status);
	ImageIcon getImage();
	String getName();
}
