package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.Border;
import vehicles.Ampivi_Vehicle;
import vehicles.observer;
import vehicles.sea_vehcles;
import vehicles.statusandcolor_Decorator;
import vehicles.vehicle_Decorator;
import vehicles.vehicles;
import javax.swing.JTextField;

public class mainMenu extends JFrame implements observer
{
	private static final long serialVersionUID = 1L;
	private JFrame vehicleFrame;
	private float generalKm;
	private JTextField txtGeneralkm;
	private Originator originator = new Originator();
	private Caretaker caretaker = new Caretaker();
	public mainMenu() 
	{
		tryCar.setTryCarObserver(this);
		displayFrame();
		vehicleFrame.addWindowFocusListener(new WindowFocusListener() 
		{
	        @Override
	        public void windowGainedFocus(WindowEvent e) 
	        {
	            refreshMenu(); // Refresh the menu when the frame gains focus
	        }
	        @Override
	        public void windowLostFocus(WindowEvent e) 
	        {
	            // Do nothing
	        }
	    });
	}
    public void refreshMenu()
    {
    	SwingUtilities.invokeLater(() -> 
    	{
        // Display the "Updating database" message immediately after starting the background task
    	vehicleFrame.getContentPane().removeAll(); // Remove all components from the content pane
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        
        if (Gui_Car_Agency.carList.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "No cars");
            vehicleFrame.setVisible(false); // Hide the frame if there are no cars
            return;
        } 
        else 
        {
            // Loop through all the vehicles and add their image icons to the panel
            for (int i = 0; i < Gui_Car_Agency.carList.size(); i++) 
            {
                ImageIcon originalIcon = Gui_Car_Agency.carList.get(i).getImage();
                ImageIcon scaledIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
                JLabel imageLabel = new JLabel(scaledIcon);
                Border border = BorderFactory.createLineBorder(((statusandcolor_Decorator) Gui_Car_Agency.carList.get(i)).getColor(), 3); // Customize the border thickness as needed
                imageLabel.setBorder(border);
                imageLabel.setToolTipText(Gui_Car_Agency.carList.get(i).toString());
                final int index = i;
                imageLabel.addMouseListener(new MouseAdapter() 
                {
                    @Override
                    public void mouseClicked(MouseEvent e) 
                    {
                        vehicledetails detail = new vehicledetails(index);
                    }
                });

                panel.add(imageLabel);
            }
        }

        JButton exitButton = new JButton("Exit");
        JButton inputButton = new JButton("Input car");
        JButton infoButton = new JButton("Report info");
        JButton resetKmButton = new JButton("Reset KM");
        JButton changeFlagButton = new JButton("Change Flag");
        infoButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                infoMenu info = new infoMenu();
                
            }
        });
        inputButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                input_menu input_menu = new input_menu();
            }
        });
        // Add action listeners to the buttons
        exitButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                boolean exit = false;
                for (int i = 0; i < Gui_Car_Agency.carList.size(); i++) 
                {
                    if (((statusandcolor_Decorator)Gui_Car_Agency.carList.get(i)).getStatus() == "On try" || ((statusandcolor_Decorator)Gui_Car_Agency.carList.get(i)).getStatus() == "On buy") 
                    {
                        exit = true;
                    }
                }
                if (exit) 
                {
                    JOptionPane.showMessageDialog(null, "Cannot exit. Please finish all ongoing operations.");
                } 
                else 
                {
                    System.exit(0); // Close the entire program
                }
            }
        });
        
        resetKmButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	vehicles currentCar;
           	 	for (int i = 0 ; i <Gui_Car_Agency.carList.size();i++) 
	            {
           		 currentCar = ((statusandcolor_Decorator) Gui_Car_Agency.carList.get(i)).getVehicle();	
           		 currentCar.resetKm();
  	            }
            	JDialog dialog = new JDialog();
             	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
             	dialog.setSize(200, 100);
             	dialog.setLocationRelativeTo(null);
             	JLabel label = new JLabel("Updating km database...");
             	dialog.add(label);
             	dialog.setVisible(true);

             	// Set the duration for the dialog to be displayed (in milliseconds)
             	int duration = 3000 + new Random().nextInt(5000);

             	// Schedule a timer to close the dialog after the specified duration
             	Timer timer = new Timer(duration, new ActionListener() {
             	    @Override
             	    public void actionPerformed(ActionEvent e) 
             	    {
             	        dialog.dispose(); // Close the dialog
             	        // Display the "Car added successfully" message
             	       JOptionPane.showMessageDialog(null, "All kilometers reset");
             	    }
             	});
             	timer.setRepeats(false); // Ensure the timer fires only once
             	timer.start();
                 
            }
        });
        changeFlagButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	String[] flagNames = { "Israel", "Italy", "USA", "Germany", "Greece", "Somalia", "Seathives" };
                String[] flagImagePaths = { "flag_of_Israel.png", "flag_of_Italy.png", "flag_of_usa.png",
                    "flag_of_Germany.png", "flag_of_Greece.png", "flag_of_Sumalia.png", "flag_of_seathives.png" };
                JFrame flagFrame = new JFrame();
                flagFrame.setTitle("Flag Chooser");
                flagFrame.setSize(400, 400);
                flagFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	    flagFrame.setLocationRelativeTo(null); // Center the frame on the screen
                // Create the panel for the buttons
                JPanel buttonPanel = new JPanel(new GridLayout(0, 2));

                // Load the flag images and create the buttons
                JButton[] flagButtons = new JButton[flagNames.length];
                for (int i = 0; i < flagNames.length; i++) {
                    ImageIcon flagIcon = new ImageIcon(flagImagePaths[i]);
                    flagButtons[i] = new JButton(flagIcon);
                    buttonPanel.add(flagButtons[i]);
                    String flagName = flagNames[i];
                    flagButtons[i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) 
                        {
                            changeFlag(flagName);
                            flagFrame.dispose(); // Close the flag chooser window after a flag button is pressed
                        }
                    });
                    flagButtons[i].setToolTipText(flagNames[i]);
                }

                // Add the button panel to the window
                flagFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);

                // Show the window
                
                flagFrame.setVisible(true);
            }
        });
        JButton saveButton = new JButton("Save state");
        saveButton.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		saveState();
        	}
        });
        buttonPanel.add(saveButton);
        
        JButton loadButton = new JButton("Load state");
        loadButton.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		loadState();
        	}
        });
        buttonPanel.add(loadButton);
        buttonPanel.add(inputButton);
        buttonPanel.add(resetKmButton);
        buttonPanel.add(changeFlagButton);
        buttonPanel.add(infoButton);
        buttonPanel.add(exitButton);
        // Set the panel as the content pane of the frame
        vehicleFrame.getContentPane().add(panel, BorderLayout.CENTER);
        vehicleFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        txtGeneralkm = new JTextField("total km " +String.valueOf(generalKm));
        buttonPanel.add(txtGeneralkm);
        txtGeneralkm.setColumns(10);
        vehicleFrame.revalidate(); // Revalidate the frame to update its components
        vehicleFrame.repaint(); // Repaint the frame to reflect the changes
    	});
    }
	public void displayFrame()
	{
			
		    vehicleFrame = new JFrame("Vehicle Images");
		    vehicleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    vehicleFrame.setSize(600, 570);
		    vehicleFrame.setLocationRelativeTo(null);
		 	JPanel panel = new JPanel();
		    panel.setLayout(new FlowLayout());
		    JPanel buttonPanel = new JPanel();
		    buttonPanel.setLayout(new FlowLayout());
	        if (Gui_Car_Agency.carList.isEmpty()) 
	        {
                JOptionPane.showMessageDialog(null,"no cars");
                return;
	        } 
	        else 
	        {
	            // Loop through all the vehicles and add their image icons to the panel
	            for (int i = 0; i < Gui_Car_Agency.carList.size(); i++) 
	            {
	                ImageIcon originalIcon = Gui_Car_Agency.carList.get(i).getImage();
	                ImageIcon scaledIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
	                JLabel imageLabel = new JLabel(scaledIcon);
	                imageLabel.setToolTipText(Gui_Car_Agency.carList.get(i).toString());
	                final int index = i;
	                imageLabel.addMouseListener(new MouseAdapter() 
	                {
	                    @Override
	                    public void mouseClicked(MouseEvent e) 
	                    {
	                    	vehicledetails detail = new vehicledetails(index);
	                    }
	                });
	                panel.add(imageLabel);
	            }
	        }

	        JButton exitButton = new JButton("Exit");
	        JButton inputButton = new JButton("Input car");
	        JButton infoButton = new JButton("Report info");
	        JButton resetKmButton = new JButton("Reset KM");
	        JButton changeFlagButton = new JButton("Change Flag");

	        inputButton.addActionListener(new ActionListener() 
	        {
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	                input_menu input_menu = new input_menu();
	            }
	        });

	        infoButton.addActionListener(new ActionListener() 
	        {
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	                infoMenu info = new infoMenu();
	            }
	        });
	        exitButton.addActionListener(new ActionListener() 
	        {
	        	@Override
	            public void actionPerformed(ActionEvent e) 
	            {
	                boolean exit = false;
	                for (int i = 0; i < Gui_Car_Agency.carList.size(); i++) 
	                {
	                    if (((statusandcolor_Decorator)Gui_Car_Agency.carList.get(i)).getStatus() == "On try" || ((statusandcolor_Decorator)Gui_Car_Agency.carList.get(i)).getStatus() == "On buy") 
	                    {
	                        exit = true;
	                    }
	                }
	                if (exit) 
	                {
	                    JOptionPane.showMessageDialog(null, "Cannot exit. Please finish all ongoing operations.");
	                } 
	                else 
	                {
	                    System.exit(0); // Close the entire program
	                }
	            }
	        });
	        
	        resetKmButton.addActionListener(new ActionListener() 
	        {
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	            	vehicles currentCar;
	            	 for (int i = 0 ; i <Gui_Car_Agency.carList.size();i++) 
	 	            {
	            		 currentCar = ((statusandcolor_Decorator) Gui_Car_Agency.carList.get(i)).getVehicle();	
	            		 currentCar.resetKm();
	   	            }
	            	 JDialog dialog = new JDialog();
	              	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	              	dialog.setSize(200, 100);
	              	dialog.setLocationRelativeTo(null);
	              	JLabel label = new JLabel("Updating km database...");
	              	dialog.getContentPane().add(label);
	              	dialog.setVisible(true);

	              	// Set the duration for the dialog to be displayed (in milliseconds)
	              	int duration = 3000 + new Random().nextInt(5000);

	              	// Schedule a timer to close the dialog after the specified duration
	              	Timer timer = new Timer(duration, new ActionListener() 
	              	{
	              	    @Override
	              	    public void actionPerformed(ActionEvent e) 
	              	    {
	              	        dialog.dispose(); // Close the dialog
	              	        // Display the "Car added successfully" message
	              	       JOptionPane.showMessageDialog(null, "All cars km reset");
	              	    }
	              	});
	              	timer.setRepeats(false); // Ensure the timer fires only once
	              	timer.start();
	            }
	        });
	        changeFlagButton.addActionListener(new ActionListener() 
	        {
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	            	String[] flagNames = { "Israel", "Italy", "USA", "Germany", "Greece", "Somalia", "Seathives" };
	                String[] flagImagePaths = { "flag_of_Israel.png", "flag_of_Italy.png", "flag_of_usa.png",
	                    "flag_of_Germany.png", "flag_of_Greece.png", "flag_of_Sumalia.png", "flag_of_seathives.png" };
	                JFrame flagFrame = new JFrame();
	                flagFrame.setTitle("Flag Chooser");
	                flagFrame.setSize(400, 400);
	                flagFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        	    flagFrame.setLocationRelativeTo(null); // Center the frame on the screen
	                // Create the panel for the buttons
	                JPanel buttonPanel = new JPanel(new GridLayout(0, 2));

	                // Load the flag images and create the buttons
	                JButton[] flagButtons = new JButton[flagNames.length];
	                for (int i = 0; i < flagNames.length; i++) {
	                    ImageIcon flagIcon = new ImageIcon(flagImagePaths[i]);
	                    flagButtons[i] = new JButton(flagIcon);
	                    buttonPanel.add(flagButtons[i]);
	                    String flagName = flagNames[i];
	                    flagButtons[i].addActionListener(new ActionListener() {
	                        public void actionPerformed(ActionEvent e) 
	                        {
	                            changeFlag(flagName);
	                            flagFrame.dispose(); // Close the flag chooser window after a flag button is pressed
	                        }
	                    });
	                    flagButtons[i].setToolTipText(flagNames[i]);
	                }

	                // Add the button panel to the window
	                flagFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);

	                // Show the window
	                
	                flagFrame.setVisible(true);
	            }
	        });
	        
	        JButton saveButton = new JButton("Save state");
	        saveButton.addActionListener(new ActionListener() 
	        {
	        	public void actionPerformed(ActionEvent e) 
	        	{
	        		saveState();
	        	}
	        });
	        buttonPanel.add(saveButton);
	        
	        JButton loadButton = new JButton("Load state");
	        loadButton.addActionListener(new ActionListener() 
	        {
	        	public void actionPerformed(ActionEvent e) 
	        	{
	        		loadState();
	        	}
	        });
	        buttonPanel.add(loadButton);
	        //changeFlagButton.addActionListener(e -> change_flag());
	        buttonPanel.add(inputButton);
	        buttonPanel.add(resetKmButton);
	        buttonPanel.add(changeFlagButton);
	        buttonPanel.add(infoButton);
	        buttonPanel.add(exitButton);

	        // Set the panel as the content pane of the frame
	        vehicleFrame.getContentPane().add(panel,BorderLayout.CENTER);
	        vehicleFrame.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
	        
	        txtGeneralkm = new JTextField("total km" + String.valueOf(generalKm));
	        buttonPanel.add(txtGeneralkm);
	        txtGeneralkm.setColumns(10);
	        // Set the size and visibility of the frame
	        vehicleFrame.setSize(600, 400);
	        vehicleFrame.setVisible(true);
	}
	public void changeFlag(String flag)
	{
		int count = 0;
		for (int i = 0; i < Gui_Car_Agency.carList.size(); i++) 
		{
			vehicles currentCar = ((statusandcolor_Decorator)Gui_Car_Agency.carList.get(i)).getVehicle();
			if (currentCar instanceof sea_vehcles )	
			{
				  sea_vehcles seaVehicle = (sea_vehcles)currentCar;
				if (seaVehicle.getflag() != null) 
				{
					seaVehicle.setflag(flag);
					count ++;
				}
			}
			else if (currentCar instanceof Ampivi_Vehicle) 
			{
				Ampivi_Vehicle ampivi_Vehicle = (Ampivi_Vehicle)Gui_Car_Agency.carList.get(i);
				if (ampivi_Vehicle.getflag() != null) 
				{
					ampivi_Vehicle.setflag(flag);
					count ++;
				}
			}
		}
		if (count == 0) 
		{
			JOptionPane.showMessageDialog(null,"No sea cars in agency");
		}
		else
		{
			JDialog dialog = new JDialog();
         	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         	dialog.setSize(200, 100);
         	dialog.setLocationRelativeTo(null);
         	JLabel label = new JLabel("Updating database...");
         	dialog.getContentPane().add(label);
         	dialog.setVisible(true);

         	// Set the duration for the dialog to be displayed (in milliseconds)
         	int duration = 3000 + new Random().nextInt(5000);

         	// Schedule a timer to close the dialog after the specified duration
         	Timer timer = new Timer(duration, new ActionListener() {
         	    @Override
         	    public void actionPerformed(ActionEvent e) 
         	    {
         	        dialog.dispose(); // Close the dialog
         	        // Display the "Car added successfully" message
         	       JOptionPane.showMessageDialog(null, "All sea cars flag changed");
         	    }
         	});
         	timer.setRepeats(false); // Ensure the timer fires only once
         	timer.start();
		}
	}
	
	public void saveState()
	{
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to save the current state ?","confirmation",JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) 
		{
			originator.setState(new ArrayList<>(Gui_Car_Agency.carList),generalKm);
			Momento momento = originator.createNewMomento();
			caretaker.addMomento(momento);
			JOptionPane.showMessageDialog(null,"the state has been saved");
		}
		else 
		{
			JOptionPane.showMessageDialog(null,"no changes were made");
		}
	}
	public void loadState()
	{
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to load last state ?","confirmation",JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) 
		{
			Momento momento = caretaker.getMomento();
			if (momento == null) 
			{
				JOptionPane.showMessageDialog(null,"no data has been found");
			}
			else 
			{
				//Caretaker.addMomento(momento);
				originator.setMomento(momento);
				Gui_Car_Agency.carList.clear();
				Gui_Car_Agency.carList.addAll(new ArrayList<>(momento.getArray()));
				generalKm = momento.getKm();
			}
		}
		else 
		{
			JOptionPane.showMessageDialog(null,"no changes were made");
		}
	}
	@Override
	public void update(float km) 
	{
		// TODO Auto-generated method stub
		generalKm += km;
	}
}
