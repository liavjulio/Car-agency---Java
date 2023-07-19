package Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import vehicles.vehicles;

public class infoMenu extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame infoFrame;
	public infoMenu() 
	{
		createInfo();
		infoFrame.addWindowFocusListener(new WindowFocusListener() 
		{
	        @Override
	        public void windowGainedFocus(WindowEvent e) 
	        {
	            refreshInfo(); // Refresh the menu when the frame gains focus
	        }
	        @Override
	        public void windowLostFocus(WindowEvent e) 
	        {
	            // Do nothing
	        }
	    });
	}
	public void createInfo()
	{
		System.out.println("enter");
		infoFrame = new JFrame();
    	infoFrame.setTitle("Enter Kilometers");
    	infoFrame.setSize(300, 150);
    	infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	infoFrame.setLayout(new FlowLayout());
    	infoFrame.setLocationRelativeTo(null);
	 	JPanel panel = new JPanel();
	    panel.setLayout(new FlowLayout());
        if (Gui_Car_Agency.carList.size() == 0) 
        {
            JOptionPane.showInternalMessageDialog(null, "No cars");
        } 
        else 
        {
            // Loop through all the vehicles and add their image icons to the panel
            for (int i = 0; i <Gui_Car_Agency.carList.size(); i++) {
                ImageIcon originalIcon = Gui_Car_Agency.carList.get(i).getImage();
                ImageIcon scaledIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
                JLabel imageLabel = new JLabel(scaledIcon);
                imageLabel.setToolTipText(Gui_Car_Agency.carList.get(i).toString());
                panel.add(imageLabel);
            }
        }

        // Set the panel as the content pane of the frame
        infoFrame.getContentPane().add(panel);
        // Set the size and visibility of the frame
        infoFrame.setSize(600, 400);
        infoFrame.setVisible(true);
	}
	public void refreshInfo() 
	{
	    SwingUtilities.invokeLater(() -> {
	        infoFrame.getContentPane().removeAll(); // Remove all components from the content pane
	        JPanel panel = new JPanel();
	        panel.setLayout(new FlowLayout());

	        if (Gui_Car_Agency.carList.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "No cars");
	            infoFrame.setVisible(false); // Hide the frame if there are no cars
	            return;
	        } else {
	            // Loop through all the vehicles and add their image icons to the panel
	            for (int i = 0; i <Gui_Car_Agency.carList.size(); i++) {
	                ImageIcon originalIcon = Gui_Car_Agency.carList.get(i).getImage();
	                ImageIcon scaledIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
	                JLabel imageLabel = new JLabel(scaledIcon);
	                imageLabel.setToolTipText(Gui_Car_Agency.carList.get(i).toString());
	                panel.add(imageLabel);
	            }
	        }

	        // Set the panel as the content pane of the frame
	        infoFrame.setContentPane(panel);
	        infoFrame.pack(); // Pack the frame to fit the components
	        infoFrame.setVisible(true);
	    });
	}
}
