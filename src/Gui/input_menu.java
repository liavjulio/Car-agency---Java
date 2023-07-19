package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import vehicles.*;

public class input_menu extends JFrame
{
	private JFrame inputFrame;
	public input_menu()
	{
		create_input();
	}
	public void create_input()
	{
		inputFrame = new JFrame("Input Menu");
	    // Create a panel
	    JPanel panel = new JPanel();
	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding to the panel
	    // Create a label
	    JLabel label = new JLabel("Select a choice:");
	    label.setFont(new Font("Tahoma", Font.BOLD, 13));

	    // Create a list of choices
	    String[] choices = {"Jeep", "Bicycle", "Frigate","Cruise" , "Amphivi" , "SpyGlider" , "GamingGlider","HybridPlane","electricBicycle"};
	    JComboBox<String> list = new JComboBox<>(choices);
	    list.setFont(new Font("Tahoma", Font.BOLD, 12));
	    list.setPreferredSize(new Dimension(200, 30)); // Set the preferred size of the combo box
	    // Create a button
	    JButton button = new JButton("Select");
	    button.setFont(new Font("Tahoma", Font.BOLD, 14));
	    button.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            button.setEnabled(false); // Disable the button to prevent multiple clicks

	            // Display a progress bar or loading message to indicate the task is in progress
	            JProgressBar progressBar = new JProgressBar();
	            progressBar.setIndeterminate(true);
	            panel.add(progressBar);
	            panel.revalidate(); // Revalidate the panel to update its components

	            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	                @Override
	                protected Void doInBackground() throws Exception 
	                {
	                	panel.add(progressBar);
	    	            panel.revalidate(); // Revalidate the panel to update its components
	    	            Thread.sleep(1000);
	                    String choice = (String) list.getSelectedItem();
	                    Car_input(choice);
	                    return null;
	                }

	                protected void done() 
	                {
	                    // Remove the progress bar or loading message
	                    panel.remove(progressBar);
	                    panel.revalidate(); // Revalidate the panel to update its components
	                    button.setEnabled(true); // Enable the button
	                    // Perform any cleanup or update the UI after the task is finished
	                    inputFrame.dispose();
	                }

	                protected void process(List<Void> chunks) 
	                {
	                    // This method can be used to process intermediate results if needed
	                }
	            };

	            worker.execute();
	        }
	    });
	    // Add the components to the panel
	    panel.add(label);
	    panel.add(list);
	    panel.add(button);
	    // Add the panel to the frame
	    inputFrame.getContentPane().add(panel);
	    // Set the size of the frame and make it visible
	    inputFrame.setSize(407, 127);
	    inputFrame.setLocationRelativeTo(null); // Center the frame on the screen
	    inputFrame.setVisible(true);
	}
	class ColorChooserPanel extends JPanel 
	{
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JColorChooser colorChooser;

	    public ColorChooserPanel() 
	    {
	        colorChooser = new JColorChooser();
	        add(colorChooser);
	    }

	    public Color getColor() 
	    {
	        return colorChooser.getColor();
	    }
	}

	public void Car_input(String choice)
	{
		if (choice.equals("Jeep")) {
		    // Create a new frame for Jeep input
		    JFrame jeepFrame = new JFrame("Jeep Input");
		    // Create a panel
		    JPanel panel = new JPanel();
		    panel.setLayout(new GridLayout(7, 2, 10, 10));

		    // Create input fields and labels for Jeep variables
		    JLabel nameLabel = new JLabel("Name:");
		    JTextField nameField = new JTextField();
		    JLabel speedLabel = new JLabel("Maximum Speed:");
		    JTextField speedField = new JTextField();
		    JLabel avgFuelLabel = new JLabel("Average Fuel Consumption:");
		    JTextField avgFuelField = new JTextField();
		    JLabel engineLifeLabel = new JLabel("Engine Life:");
		    JTextField engineLifeField = new JTextField();

		    // Create color and status panels
		    ColorChooserPanel colorPanel = new ColorChooserPanel();

		    // Create a button to submit the input
		    JButton submitButton = new JButton("Submit");
		    submitButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            String name = nameField.getText();
		            String speedStr = speedField.getText();
		            String avgFuelStr = avgFuelField.getText();
		            String engineLifeStr = engineLifeField.getText();
		            String[] args = {name, speedStr, avgFuelStr, engineLifeStr};
		            try {
		                // Validate and parse the input into the appropriate data types
		                if (name.isEmpty() || speedStr.isEmpty() || avgFuelStr.isEmpty() || engineLifeStr.isEmpty()) {
		                    throw new IllegalArgumentException("Please fill in all the fields!");
		                }

		                // Create an array of image file paths
		                String[] jeepPaths = {"jeep1.jpg", "jeep2.jpg", "jeep3.jpg", "Upload.png"};

		                // Create an array of ImageIcons to hold the images
		                ImageIcon[] jeepIcons = new ImageIcon[jeepPaths.length];

		                // Load the images and create ImageIcons
		                for (int i = 0; i < jeepPaths.length; i++) {
		                    if (jeepPaths[i].equals("Upload.png")) {
		                        jeepIcons[i] = new ImageIcon(jeepPaths[i], "upload");
		                    } else {
		                        jeepIcons[i] = new ImageIcon(jeepPaths[i]);
		                    }
		                }

		                // Show a dialog with the images and let the user choose one
		                int imageChoice = JOptionPane.showOptionDialog(null, "Choose a Jeep image", "Jeep Image", JOptionPane.DEFAULT_OPTION,
		                        JOptionPane.PLAIN_MESSAGE, null, jeepIcons, jeepIcons[0]);

		                ImageIcon chosenImagePath; // Declare a new variable

		                if (jeepIcons[imageChoice] == jeepIcons[3]) {
		                    JFileChooser fileChooser = new JFileChooser();
		                    File selectedFile;
		                    while (true) {
		                        int result = fileChooser.showOpenDialog(null);
		                        if (result == JFileChooser.APPROVE_OPTION) {
		                            selectedFile = fileChooser.getSelectedFile();
		                            String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);
		                            if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
		                                chosenImagePath = new ImageIcon(selectedFile.getAbsolutePath()); // Assign to the new variable
		                                break;
		                            } else {
		                                JOptionPane.showMessageDialog(null, "Selected file is not an image. Please select a valid image file.", "Error", JOptionPane.ERROR_MESSAGE);
		                            }
		                        } else {
		                            // If the user cancelled the file chooser dialog, exit the method without creating a new Jeep object
		                            return;
		                        }
		                    }
		                } else {
		                    chosenImagePath = jeepIcons[imageChoice]; // Assign to the new variable
		                }
		                abstractFactory vehicleFactory = factory.getFact(1);
		                vehicles jeep = vehicleFactory.create(0, chosenImagePath, args);
		                // TODO: Perform operations specific to Jeep
		                Color color = colorPanel.getColor();		                
		                jeep = new statusandcolor_Decorator(jeep,color);
		                addVehicle(jeep);
		                // Display a message dialog to confirm that the Jeep was added successfully

		                // Close the Jeep input frame
		                jeepFrame.dispose();
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Invalid input format. Please enter numeric values for speed, average fuel consumption, and engine life.", "Error", JOptionPane.ERROR_MESSAGE);
		            } catch (IllegalArgumentException ex) {
		                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            }

		        }
		    });

		    // Add the components to the panel
		    panel.add(nameLabel);
		    panel.add(nameField);
		    panel.add(speedLabel);
		    panel.add(speedField);
		    panel.add(avgFuelLabel);
		    panel.add(avgFuelField);
		    panel.add(engineLifeLabel);
		    panel.add(engineLifeField);
		    panel.add(colorPanel);
		    panel.add(submitButton);

		    // Set empty border for padding
		    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		    // Add the panel to the frame
		    jeepFrame.getContentPane().add(panel);

		    // Set the size of the frame and make it visible
		    jeepFrame.setSize(400, 300);
		    jeepFrame.setLocationRelativeTo(null); // Center the frame on the screen
		    jeepFrame.setVisible(true);
		}

		else if (choice.equals("Cruise")) {
		    // Create a new frame for Cruise Ship input
		    JFrame cruiseFrame = new JFrame("Cruise Ship Input");
		    // Create a panel
		    JPanel panel = new JPanel();
		    panel.setLayout(new GridLayout(7, 2));

		    // Create input fields and labels for Cruise Ship variables
		    JLabel nameLabel = new JLabel("Name:");
		    JTextField nameField = new JTextField();
		    JLabel speedLabel = new JLabel("Maximum Speed:");
		    JTextField speedField = new JTextField();
		    JLabel passengersLabel = new JLabel("Passengers:");
		    JTextField passengersField = new JTextField();
		    JLabel flagLabel = new JLabel("Flag:");
		    JTextField flagField = new JTextField();
		    JLabel engineLifeLabel = new JLabel("Engine Life:");
		    JTextField engineLifeField = new JTextField();
		    JLabel averageFuelLabel = new JLabel("Average Fuel:");
		    JTextField averageFuelField = new JTextField();

		    // Add the components to the panel
		    panel.add(nameLabel);
		    panel.add(nameField);
		    panel.add(speedLabel);
		    panel.add(speedField);
		    panel.add(passengersLabel);
		    panel.add(passengersField);
		    panel.add(flagLabel);
		    panel.add(flagField);
		    panel.add(engineLifeLabel);
		    panel.add(engineLifeField);
		    panel.add(averageFuelLabel);
		    panel.add(averageFuelField);
		    
		    ColorChooserPanel colorPanel = new ColorChooserPanel();
		    
		    // Create a button to submit the input
		    JButton submitButton = new JButton("Submit");
		    submitButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            String name = nameField.getText();
		            String speedStr = speedField.getText();
		            String passengersStr = passengersField.getText();
		            String flag = flagField.getText();
		            String engineLifeStr = engineLifeField.getText();
		            String averageFuelStr = averageFuelField.getText();
		            String[] args = {name,speedStr , passengersStr,flag , engineLifeStr , averageFuelStr };
		            try {
		                // Validate and parse the input into the appropriate data types
		                if (name.isEmpty() || speedStr.isEmpty() || passengersStr.isEmpty() || flag.isEmpty() || engineLifeStr.isEmpty() || averageFuelStr.isEmpty()) {
		                    throw new IllegalArgumentException("Please fill in all the fields!");
		                }           
		                // Create an array of image file paths
		                String[] cruisePaths = { "Cruise1.jpg", "Cruise2.jpg", "Cruise3.jpg", "upload.png" };

		                // Create an array of ImageIcons to hold the images
		                ImageIcon[] cruiseIcons = new ImageIcon[cruisePaths.length];

		                // Load the images and create ImageIcons
		                for (int i = 0; i < cruisePaths.length; i++) {
		                    if (cruisePaths[i].equals("upload.png")) {
		                        cruiseIcons[i] = new ImageIcon(cruisePaths[i], "upload");
		                    } else {
		                        cruiseIcons[i] = new ImageIcon(cruisePaths[i]);
		                    }
		                }

		                // Show a dialog with the images and let the user choose one
		                int imageChoice = JOptionPane.showOptionDialog(null, "Choose a Cruise image", "Cruise Image", JOptionPane.DEFAULT_OPTION,
		                        JOptionPane.PLAIN_MESSAGE, null, cruiseIcons, cruiseIcons[0]);

		                final ImageIcon chosenImagePath; // Declare a new final variable

		                if (cruiseIcons[imageChoice] == cruiseIcons[3]) {
		                    JFileChooser fileChooser = new JFileChooser();
		                    File selectedFile;
		                    while (true) {
		                        int result = fileChooser.showOpenDialog(null);
		                        if (result == JFileChooser.APPROVE_OPTION) {
		                            selectedFile = fileChooser.getSelectedFile();
		                            String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);
		                            if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
		                                chosenImagePath = new ImageIcon(selectedFile.getAbsolutePath());
		                                break;
		                            } else {
		                                JOptionPane.showMessageDialog(null, "Selected file is not an image. Please select a valid image file.", "Error", JOptionPane.ERROR_MESSAGE);
		                            }
		                        } else {
		                            return; // If the user cancelled the file chooser dialog, exit the method without creating a new Cruise Ship object
		                        }
		                    }
		                } else {
		                    chosenImagePath = cruiseIcons[imageChoice]; // Assign to the new final variable
		                }

		                abstractFactory vehicleFactory = factory.getFact(2);
	                    vehicles cruiseShip = vehicleFactory.create(1,chosenImagePath,args);
	                    Color color = colorPanel.getColor();		                
		                cruiseShip = new statusandcolor_Decorator(cruiseShip,color);
		                addVehicle(cruiseShip);
		                // Display a message dialog to confirm that the Cruise Ship was added successfully

		                // Close the Cruise Ship input frame
		                cruiseFrame.dispose();
		            } 
		            catch (NumberFormatException ex) 
		            {
		                JOptionPane.showMessageDialog(null, "Invalid input format. Please enter numeric values for speed, passengers, engine life, and average fuel.", "Error", JOptionPane.ERROR_MESSAGE);
		            } 
		            catch (IllegalArgumentException ex) 
		            {
		                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		        
		    });
		    panel.add(colorPanel);
		    // Add the submit button to the panel
		    panel.add(submitButton);

		    // Add the panel to the frame
		    cruiseFrame.getContentPane().add(panel);

		    // Set the size of the frame and make it visible
		    cruiseFrame.setSize(400, 250);
		    cruiseFrame.setLocationRelativeTo(null); // Center the frame on the screen
		    cruiseFrame.setVisible(true);
		}
		else if (choice.equals("electricBicycle")) {
		    // Create a new frame for electricBicycle input
		    JFrame electricBicycleFrame = new JFrame("Electric Bicycle Input");
		    // Create a panel
		    JPanel panel = new JPanel();
		    panel.setLayout(new GridLayout(4, 2));

		    // Create input fields and labels for electricBicycle variables
		    JLabel nameLabel = new JLabel("Name:");
		    JTextField nameField = new JTextField();
		    JLabel engineLifeLabel = new JLabel("Engine Life:");
		    JTextField engineLifeField = new JTextField();
		    JLabel speedLabel = new JLabel("Speed:");
		    JTextField speedField = new JTextField();

		    // Add the components to the panel
		    panel.add(nameLabel);
		    panel.add(nameField);
		    panel.add(engineLifeLabel);
		    panel.add(engineLifeField);
		    panel.add(speedLabel);
		    panel.add(speedField);
		    
		    ColorChooserPanel colorPanel = new ColorChooserPanel();
		    
		    // Create a button to submit the input
		    JButton submitButton = new JButton("Submit");
		    submitButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            String name = nameField.getText();
		            String engineLifeStr = engineLifeField.getText();
		            String speedStr = speedField.getText();
		            String[] args = {name , engineLifeStr , speedStr};
		            try {
		                // Validate and parse the input into the appropriate data types
		                if (name.isEmpty() || engineLifeStr.isEmpty() || speedStr.isEmpty()) {
		                    throw new IllegalArgumentException("Please fill in all the fields!");
		                }

		                // Create an array of image file paths
		                String[] electricBicyclePaths = { "electricBicycle1.jpg", "electricBicycle2.jpg", "electricBicycle3.jpg", "upload.png" };

		                // Create an array of ImageIcons to hold the images
		                ImageIcon[] electricBicycleIcons = new ImageIcon[electricBicyclePaths.length];

		                // Load the images and create ImageIcons
		                for (int i = 0; i < electricBicyclePaths.length; i++) {
		                    if (electricBicyclePaths[i].equals("upload.png")) {
		                        electricBicycleIcons[i] = new ImageIcon(electricBicyclePaths[i], "upload");
		                    } else {
		                        electricBicycleIcons[i] = new ImageIcon(electricBicyclePaths[i]);
		                    }
		                }

		                // Show a dialog with the images and let the user choose one
		                int imageChoice = JOptionPane.showOptionDialog(null, "Choose an Electric Bicycle image", "Electric Bicycle Image", JOptionPane.DEFAULT_OPTION,
		                        JOptionPane.PLAIN_MESSAGE, null, electricBicycleIcons, electricBicycleIcons[0]);

		                final ImageIcon chosenImagePath; // Declare a new final variable

		                if (electricBicycleIcons[imageChoice] == electricBicycleIcons[3]) {
		                    JFileChooser fileChooser = new JFileChooser();
		                    File selectedFile;
		                    while (true) {
		                        int result = fileChooser.showOpenDialog(null);
		                        if (result == JFileChooser.APPROVE_OPTION) {
		                            selectedFile = fileChooser.getSelectedFile();
		                            String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);
		                            if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
		                                chosenImagePath = new ImageIcon(selectedFile.getAbsolutePath());
		                                break;
		                            } else {
		                                JOptionPane.showMessageDialog(null, "Selected file is not an image. Please select a valid image file.", "Error", JOptionPane.ERROR_MESSAGE);
		                            }
		                        } else {
		                            return; // If the user cancelled the file chooser dialog, exit the method without creating a new Electric Bicycle object
		                        }
		                    }
		                } else {
		                    chosenImagePath = electricBicycleIcons[imageChoice]; // Assign to the new final variable
		                }

		                abstractFactory vehicleFactory = factory.getFact(1);
	                    vehicles electricBike = vehicleFactory.create(1,chosenImagePath,args);
		                // TODO: Perform operations specific to Electric Bicycle
	                    Color color = colorPanel.getColor();		                
		                electricBike = new statusandcolor_Decorator(electricBike,color);
		                addVehicle(electricBike);
		                // Display a message dialog to confirm that the Electric Bicycle was added successfully

		                // Close the Electric Bicycle input frame
		                electricBicycleFrame.dispose();
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Invalid input format. Please enter numeric values for speed and engine life.", "Error", JOptionPane.ERROR_MESSAGE);
		            } catch (IllegalArgumentException ex) {
		                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        
		        }
		    });
		    panel.add(colorPanel);
		    // Add the submit button to the panel
		    panel.add(submitButton);

		    // Add the panel to the frame
		    electricBicycleFrame.getContentPane().add(panel);

		    // Set the size of the frame and make it visible
		    electricBicycleFrame.setSize(400, 150);
		    electricBicycleFrame.setLocationRelativeTo(null); // Center the frame on the screen
		    electricBicycleFrame.setVisible(true);
		}
		else if (choice.equals("Bicycle")) 
		{
		    // Create a new frame for Bicycle input
		    JFrame bicycleFrame = new JFrame("Bicycle Input");
		    // Create a panel
		    JPanel panel = new JPanel();
		    panel.setLayout(new GridLayout(3, 2));

		    // Create input fields and labels for Bicycle variables
		    JLabel nameLabel = new JLabel("Name:");
		    JTextField nameField = new JTextField();
		    JLabel speedLabel = new JLabel("Speed:");
		    JTextField speedField = new JTextField();

		    // Add the components to the panel
		    panel.add(nameLabel);
		    panel.add(nameField);
		    panel.add(speedLabel);
		    panel.add(speedField);
		    
		    ColorChooserPanel colorPanel = new ColorChooserPanel();
		    
		    // Create a button to submit the input
		    JButton submitButton = new JButton("Submit");
		    submitButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            String name = nameField.getText();
		            String speedStr = speedField.getText();
		            String[] args = {name , speedStr};
		            try {
		                // Validate and parse the input into the appropriate data types
		                if (name.isEmpty() || speedStr.isEmpty()) {
		                    throw new IllegalArgumentException("Please fill in all the fields!");
		                }

		                

		                // Create an array of image file paths
		                String[] bicyclePaths = { "bicycle1.jpg", "bicycle2.jpg", "bicycle3.jpg", "upload.png" };

		                // Create an array of ImageIcons to hold the images
		                ImageIcon[] bicycleIcons = new ImageIcon[bicyclePaths.length];

		                // Load the images and create ImageIcons
		                for (int i = 0; i < bicyclePaths.length; i++) {
		                    if (bicyclePaths[i].equals("upload.png")) {
		                        bicycleIcons[i] = new ImageIcon(bicyclePaths[i], "upload");
		                    } else {
		                        bicycleIcons[i] = new ImageIcon(bicyclePaths[i]);
		                    }
		                }

		                // Show a dialog with the images and let the user choose one
		                int imageChoice = JOptionPane.showOptionDialog(null, "Choose a Bicycle image", "Bicycle Image", JOptionPane.DEFAULT_OPTION,
		                        JOptionPane.PLAIN_MESSAGE, null, bicycleIcons, bicycleIcons[0]);

		                final ImageIcon chosenImagePath; // Declare a new final variable

		                if (bicycleIcons[imageChoice] == bicycleIcons[3]) {
		                    JFileChooser fileChooser = new JFileChooser();
		                    File selectedFile;
		                    while (true) {
		                        int result = fileChooser.showOpenDialog(null);
		                        if (result == JFileChooser.APPROVE_OPTION) {
		                            selectedFile = fileChooser.getSelectedFile();
		                            String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);
		                            if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
		                                chosenImagePath = new ImageIcon(selectedFile.getAbsolutePath());
		                                break;
		                            } else {
		                                JOptionPane.showMessageDialog(null, "Selected file is not an image. Please select a valid image file.", "Error", JOptionPane.ERROR_MESSAGE);
		                            }
		                        } else {
		                            return; // If the user cancelled the file chooser dialog, exit the method without creating a new Bicycle object
		                        }
		                    }
		                } else {
		                    chosenImagePath = bicycleIcons[imageChoice]; // Assign to the new final variable
		                }
		                abstractFactory vehicleFactory = factory.getFact(1);
	                    vehicles bicycle = vehicleFactory.create(2,chosenImagePath,args);
		                // TODO: Perform operations specific to Bicycle
	                    Color color = colorPanel.getColor();		                
		                bicycle = new statusandcolor_Decorator(bicycle,color);
		                addVehicle(bicycle);

		                // Close the Bicycle input frame
		                bicycleFrame.dispose();

		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Invalid input format. Please enter a numeric value for speed.", "Error", JOptionPane.ERROR_MESSAGE);
		            } catch (IllegalArgumentException ex) {
		                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    });
		    panel.add(colorPanel);
		    // Add the submit button to the panel
		    panel.add(submitButton);

		    // Add the panel to the frame
		    bicycleFrame.getContentPane().add(panel);

		    // Set the size of the frame and make it visible
		    bicycleFrame.setSize(400, 150);
		    bicycleFrame.setLocationRelativeTo(null); // Center the frame on the screen
		    bicycleFrame.setVisible(true);
		}
		else if (choice.equals("Frigate")) {
		    // Create a new JFrame for Frigate input
		    JFrame frigateFrame = new JFrame("Frigate Input");
		    // Create a panel
		    JPanel panel = new JPanel();
		    panel.setLayout(new GridLayout(5, 2));
		    
		    // Create input fields and labels for Frigate variables
		    JLabel nameLabel = new JLabel("Name:");
		    JTextField nameField = new JTextField();
		    JLabel speedLabel = new JLabel("Maximum Speed:");
		    JTextField speedField = new JTextField();
		    JLabel passengersLabel = new JLabel("Passengers:");
		    JTextField passengersField = new JTextField();
		    JLabel sailingLabel = new JLabel("Is sailing with the wind?");
		    JCheckBox sailingCheckBox = new JCheckBox();

		    // Add the components to the panel
		    panel.add(nameLabel);
		    panel.add(nameField);
		    panel.add(speedLabel);
		    panel.add(speedField);
		    panel.add(passengersLabel);
		    panel.add(passengersField);
		    panel.add(sailingLabel);
		    panel.add(sailingCheckBox);
		    panel.add(new JLabel());
		   
		    
		    ColorChooserPanel colorPanel = new ColorChooserPanel();
		    
		    // Create a button to submit the input
		    JButton submitButton = new JButton("Submit");
		    submitButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) 
		        {
		        	boolean isSailingWithWind = sailingCheckBox.isSelected();
		            String name = nameField.getText();
		            String speedStr = speedField.getText();
		            String passengersStr = passengersField.getText();
		            String isSailingWithWindStr = Boolean.toString(isSailingWithWind);
		            String[] args = {name,speedStr , passengersStr, isSailingWithWindStr};
		            try {
		                // Validate and parse the input into the appropriate data types
		                if (name.isEmpty() || speedStr.isEmpty() || passengersStr.isEmpty()) {
		                    throw new IllegalArgumentException("Please fill in all the fields!");
		                }

		                
		                // Create an array of image file paths
		                String[] frigatePaths = { "frigate1.jpg", "frigate2.jpg", "frigate3.jpg", "upload.png" };

		                // Create an array of ImageIcons to hold the images
		                ImageIcon[] frigateIcons = new ImageIcon[frigatePaths.length];
		                // Load the images and create ImageIcons
		                for (int i = 0; i < frigatePaths.length; i++) {
		                    if (frigatePaths[i].equals("upload.png")) {
		                        frigateIcons[i] = new ImageIcon(frigatePaths[i], "upload");
		                    } else {
		                        frigateIcons[i] = new ImageIcon(frigatePaths[i]);
		                    }
		                }

		                // Show a dialog with the images and let the user choose one
		                int choice = JOptionPane.showOptionDialog(null, "Choose a Frigate image", "Frigate Image", JOptionPane.DEFAULT_OPTION,
		                        JOptionPane.PLAIN_MESSAGE, null, frigateIcons, frigateIcons[0]);

		                final ImageIcon finalChosenImagePath; // Declare a new final variable
		                if (choice < frigateIcons.length && frigateIcons[choice] == frigateIcons[3]) 
		                {
		                    JFileChooser fileChooser = new JFileChooser();
		                    File selectedFile;
		                    while (true) {
		                        int result = fileChooser.showOpenDialog(null);
		                        if (result == JFileChooser.APPROVE_OPTION) {
		                            selectedFile = fileChooser.getSelectedFile();
		                            String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);
		                            if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
		                                finalChosenImagePath = new ImageIcon(selectedFile.getAbsolutePath()); // Assign to the new final variable
		                                break;
		                            } else {
		                                JOptionPane.showMessageDialog(null, "Selected file is not an image. Please select a valid image file.", "Error", JOptionPane.ERROR_MESSAGE);
		                            }
		                        } else {
		                            // If the user cancelled the file chooser dialog, exit the method without creating a new Frigate object
		                            return;
		                        }
		                    }
		                } else {
		                    finalChosenImagePath = frigateIcons[choice]; // Assign to the new final variable
		                }

		                abstractFactory vehicleFactory = factory.getFact(2);
	                    vehicles frigate = vehicleFactory.create(2,finalChosenImagePath,args);
	                    Color color = colorPanel.getColor();		                
		                frigate = new statusandcolor_Decorator(frigate,color);
		                addVehicle(frigate);
		                // Display a message dialog to confirm that the Frigate was added successfully

		                // Close the Frigate input frame
		                frigateFrame.dispose();
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Invalid input for speed or passengers. Please enter numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
		            } catch (IllegalArgumentException ex) {
		                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    });
		    panel.add(colorPanel);
		    // Add the submit button to the panel
		    panel.add(submitButton);

		    // Add the panel to the frame
		    frigateFrame.getContentPane().add(panel);

		    // Set the size of the frame and make it visible
		    frigateFrame.setSize(400, 200);
		    frigateFrame.setLocationRelativeTo(null); // Center the frame on the screen
		    frigateFrame.setVisible(true);
		}
		
		else if (choice.equals("SpyGlider")) 
		{
		    // Create a new JFrame for SpyGlider input
		    JFrame spyGliderFrame = new JFrame("SpyGlider Input");
		    // Create a panel
		    JPanel panel = new JPanel();
		    panel.setLayout(new GridLayout(2, 2));

		    // Create input fields and labels for SpyGlider variables
		    JLabel nameLabel = new JLabel("Name:");
		    JTextField nameField = new JTextField();

		    // Add the components to the panel
		    panel.add(nameLabel);
		    panel.add(nameField);
		    
		    ColorChooserPanel colorPanel = new ColorChooserPanel();
		    
		    
		    // Create a button to submit the input
		    JButton submitButton = new JButton("Submit");
		    submitButton.addActionListener(new ActionListener() 
		    {
		        public void actionPerformed(ActionEvent e) {
		            String name = nameField.getText();
		            String[] args = {name};
		            try {
		                // Validate the input
		                if (name.isEmpty()) {
		                    throw new IllegalArgumentException("Please enter a name!");
		                }

		                // Create an array of image file paths
		                String[] spyPaths = { "spy1.jpg", "spy2.jpg", "spy3.jpg", "upload.png" };

		                // Create an array of ImageIcons to hold the images
		                ImageIcon[] spyIcons = new ImageIcon[spyPaths.length];
		                // Load the images and create ImageIcons
		                for (int i = 0; i < spyPaths.length; i++) {
		                    if (spyPaths[i].equals("upload.png")) {
		                        spyIcons[i] = new ImageIcon(spyPaths[i], "upload");
		                    } else {
		                        spyIcons[i] = new ImageIcon(spyPaths[i]);
		                    }
		                }

		                // Show a dialog with the images and let the user choose one
		                int choice = JOptionPane.showOptionDialog(null, "Choose a SpyGlider image", "SpyGlider Image", JOptionPane.DEFAULT_OPTION,
		                        JOptionPane.PLAIN_MESSAGE, null, spyIcons, spyIcons[0]);

		                final ImageIcon finalChosenImagePath; // Declare a new final variable
		                if (spyIcons[choice] == spyIcons[3]) {
		                    JFileChooser fileChooser = new JFileChooser();
		                    File selectedFile;
		                    while (true) {
		                        int result = fileChooser.showOpenDialog(null);
		                        if (result == JFileChooser.APPROVE_OPTION) {
		                            selectedFile = fileChooser.getSelectedFile();
		                            String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);
		                            if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
		                                finalChosenImagePath = new ImageIcon(selectedFile.getAbsolutePath()); // Assign to the new final variable
		                                break;
		                            } else {
		                                JOptionPane.showMessageDialog(null, "Selected file is not an image. Please select a valid image file.", "Error", JOptionPane.ERROR_MESSAGE);
		                            }
		                        } else {
		                            // If the user cancelled the file chooser dialog, exit the method without creating a new SpyGlider object
		                            return;
		                        }
		                    }
		                } else {
		                    finalChosenImagePath = spyIcons[choice]; // Assign to the new final variable
		                }

		                abstractFactory vehicleFactory = factory.getFact(0);
	                    vehicles spyGlider = vehicleFactory.create(2,finalChosenImagePath,args);
	                    Color color = colorPanel.getColor();		                
		                spyGlider = new statusandcolor_Decorator(spyGlider,color);
		                addVehicle(spyGlider);
		                // Display a message dialog to confirm that the SpyGlider was added successfully

		                // Close the SpyGlider input frame
		                spyGliderFrame.dispose();
		            } catch (IllegalArgumentException ex) {
		                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    });
		    panel.add(colorPanel);
		    // Add the submit button to the panel
		    panel.add(submitButton);

		    // Add the panel to the frame
		    spyGliderFrame.getContentPane().add(panel);

		    // Set the size of the frame and make it visible
		    spyGliderFrame.setSize(400, 200);
		    spyGliderFrame.setLocationRelativeTo(null); // Center the frame on the screen
		    spyGliderFrame.setVisible(true);
		}
		else if (choice.equals("GamingGlider")) {
		    // Create a new JFrame for GamingGlider input
		    JFrame gamingGliderFrame = new JFrame("GamingGlider Input");
		    // Create a panel
		    JPanel panel = new JPanel();
		    panel.setLayout(new GridLayout(2, 1));

		    try {
		        // Create an array of image file paths
		        String[] gamingPaths = { "game1.jpg", "game2.jpg", "game3.jpg", "upload.png" };

		        // Create an array of ImageIcons to hold the images
		        ImageIcon[] gamingIcons = new ImageIcon[gamingPaths.length];
		        // Load the images and create ImageIcons
		        for (int i = 0; i < gamingPaths.length; i++) {
		            if (gamingPaths[i].equals("upload.png")) {
		                gamingIcons[i] = new ImageIcon(gamingPaths[i], "upload");
		            } else {
		                gamingIcons[i] = new ImageIcon(gamingPaths[i]);
		            }
		        }

		        // Show a dialog with the images and let the user choose one
		        int choice1 = JOptionPane.showOptionDialog(null, "Choose a GamingGlider image", "GamingGlider Image", JOptionPane.DEFAULT_OPTION,
		                JOptionPane.PLAIN_MESSAGE, null, gamingIcons, gamingIcons[0]);

		        final ImageIcon finalChosenImagePath; // Declare a new final variable
		        if (gamingIcons[choice1] == gamingIcons[3]) {
		            JFileChooser fileChooser = new JFileChooser();
		            File selectedFile;
		            while (true) {
		                int result = fileChooser.showOpenDialog(null);
		                if (result == JFileChooser.APPROVE_OPTION) {
		                    selectedFile = fileChooser.getSelectedFile();
		                    String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);
		                    if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
		                        finalChosenImagePath = new ImageIcon(selectedFile.getAbsolutePath()); // Assign to the new final variable
		                        break;
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Selected file is not an image. Please select a valid image file.", "Error", JOptionPane.ERROR_MESSAGE);
		                    }
		                } else {
		                    // If the user cancelled the file chooser dialog, exit the method without creating a new GamingGlider object
		                    return;
		                }
		            }
		        } else {
		            finalChosenImagePath = gamingIcons[choice1]; // Assign to the new final variable
		        }
		        ColorChooserPanel colorPanel = new ColorChooserPanel();
		        // Create a button to submit the input
		        JButton submitButton = new JButton("Submit");
		        submitButton.addActionListener(new ActionListener() 
		        {
		            public void actionPerformed(ActionEvent e) 
		            {
		            	abstractFactory vehicleFactory = factory.getFact(0);
				        vehicles gamingGlider = vehicleFactory.create(0, finalChosenImagePath);
		                Color color = colorPanel.getColor();
		                gamingGlider = new statusandcolor_Decorator(gamingGlider, color);
		                addVehicle(gamingGlider);
		                // Display a message dialog to confirm that the GamingGlider was added successfully

		                // Close the GamingGlider input frame
		                gamingGliderFrame.dispose();
		            }
		        });
		        panel.add(colorPanel);
		        panel.add(submitButton);

		        // Add the panel to the frame
		        gamingGliderFrame.getContentPane().add(panel);

		        // Set the size of the frame and make it visible
		        gamingGliderFrame.setSize(400, 200);
		        gamingGliderFrame.setLocationRelativeTo(null); // Center the frame on the screen
		        gamingGliderFrame.setVisible(true);
		    } catch (IllegalArgumentException ex) {
		        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}

		else if (choice.equals("Amphivi") || choice.equals("HybridPlane")) {
		    // Create a new JFrame for Amphivi/HybridPlane input
		    JFrame vehicleFrame = new JFrame(choice + " Input");
		    vehicleFrame.setSize(400, 600);
		    vehicleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    vehicleFrame.setLayout(new BorderLayout());
	        ColorChooserPanel colorPanel = new ColorChooserPanel();

		    // Create a panel for the form inputs
		    JPanel inputPanel = new JPanel();
		    inputPanel.setLayout(new GridLayout(11, 2));

		    // Create labels and components for input
		    JLabel nameLabel = new JLabel("Name:");
		    JTextField nameField = new JTextField();
		    JLabel speedLabel = new JLabel("Maximum Speed:");
		    JTextField speedField = new JTextField();
		    JLabel passengersLabel = new JLabel("Passengers:");
		    JTextField passengersField = new JTextField();
		    JLabel sailingLabel = new JLabel("Is sailing with the wind?");
		    JCheckBox sailingCheckBox = new JCheckBox();
		    JLabel avgFuelLabel = new JLabel("Average Fuel Consumption:");
		    JTextField avgFuelField = new JTextField();
		    JLabel wheelsLabel = new JLabel("Wheels:");
		    JTextField wheelsField = new JTextField();
		    JLabel engineLifeLabel = new JLabel("Engine Life:");
		    JTextField engineLifeField = new JTextField();
		    JLabel flagLabel = new JLabel("Flag:");
		    JTextField flagField = new JTextField();

		    // Add labels and components to the input panel
		    inputPanel.add(nameLabel);
		    inputPanel.add(nameField);
		    inputPanel.add(speedLabel);
		    inputPanel.add(speedField);
		    inputPanel.add(passengersLabel);
		    inputPanel.add(passengersField);
		    inputPanel.add(sailingLabel);
		    inputPanel.add(sailingCheckBox);
		    inputPanel.add(avgFuelLabel);
		    inputPanel.add(avgFuelField);
		    inputPanel.add(wheelsLabel);
		    inputPanel.add(wheelsField);
		    inputPanel.add(engineLifeLabel);
		    inputPanel.add(engineLifeField);
		    inputPanel.add(flagLabel);
		    inputPanel.add(flagField);
		    inputPanel.add(colorPanel);
		    		    // Create a button for submitting the form
		    JButton submitButton = new JButton("Submit");
		    submitButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            String name = nameField.getText();
		            String speedStr = speedField.getText();
		            String passengersStr = passengersField.getText();
		            boolean isSailingWithWind = sailingCheckBox.isSelected();
		            String avgFuelStr = avgFuelField.getText();
		            String wheelsStr = wheelsField.getText();
		            String engineLifeStr = engineLifeField.getText();
		            String flag = flagField.getText();
		            String isSailingWithWindStr = Boolean.toString(isSailingWithWind);
		            String[] args = {name, passengersStr, speedStr, wheelsStr, engineLifeStr, avgFuelStr, isSailingWithWindStr, flag}; 
		            if (!name.isEmpty() && !speedStr.isEmpty() && !passengersStr.isEmpty() && !avgFuelStr.isEmpty() && !wheelsStr.isEmpty() && !engineLifeStr.isEmpty()) {
		                try 
		                {
		                    if (choice.equals("Amphivi")) 
		                    {
		                        // Perform Amphivi specific logic here
		                        String[] amphiviPaths = { "Amp1.jpg", "Amp2.jpg", "Amp3.jpg", "Upload.png" };
		                        // Create an array of ImageIcons to hold the images
		                        ImageIcon[] amphiviIcons = new ImageIcon[amphiviPaths.length];
		                        // Load the images and create ImageIcons
		                        for (int i = 0; i < amphiviPaths.length; i++) {
		                            if(amphiviPaths[i].equals("Upload.png")) {
		                                amphiviIcons[i] = new ImageIcon(amphiviPaths[i], "Upload");
		                            } else {
		                                amphiviIcons[i] = new ImageIcon(amphiviPaths[i]);
		                            }
		                        }
		                        // Show a dialog with the images and let the user choose one
		                        int imageChoice = JOptionPane.showOptionDialog(vehicleFrame, "Choose an Amphivi image", "Amphivi Image", JOptionPane.DEFAULT_OPTION,
		                                JOptionPane.PLAIN_MESSAGE, null, amphiviIcons, amphiviIcons[0]);

		                        ImageIcon chosenImage;
		                        // If the user selected "Upload Image", show a file chooser dialog and create an ImageIcon from the selected file
		                        if (amphiviIcons[imageChoice] == amphiviIcons[3]) {
		                            JFileChooser fileChooser = new JFileChooser();
		                            int result = fileChooser.showOpenDialog(vehicleFrame);
		                            if (result == JFileChooser.APPROVE_OPTION) {
		                                File selectedFile = fileChooser.getSelectedFile();
		                                String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);
		                                if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
		                                    chosenImage = new ImageIcon(selectedFile.getAbsolutePath());
		                                } else {
		                                    JOptionPane.showMessageDialog(vehicleFrame, "Selected file is not an image. Please select a valid image file.", "Error", JOptionPane.ERROR_MESSAGE);
		                                    return;
		                                }
		                            } else {
		                                // If the user cancelled the file chooser dialog, exit the method without creating a new Amphivi object
		                                return;
		                            }
		                        } 
		                        else {
		                            chosenImage = amphiviIcons[imageChoice];
		                        }

		                        abstractFactory vehicleFactory = factory.getFact(2);
		                        vehicles amphivi = vehicleFactory.create(0,chosenImage,args);
		                        Color color = colorPanel.getColor();
				                amphivi = new statusandcolor_Decorator(amphivi, color);
		                        addVehicle(amphivi);
		                        vehicleFrame.dispose();
		                    } 
		                    else if (choice.equals("HybridPlane")) 
		                    {
		                        // Perform HybridPlane specific logic here
		                        String[] planePaths = { "Pln1.jpg", "Pln2.jpg", "Pln3.jpg", "Upload.png" };
		                        // Create an array of ImageIcons to hold the images
		                        ImageIcon[] planeIcons = new ImageIcon[planePaths.length];
		                        // Load the images and create ImageIcons
		                        for (int i = 0; i < planePaths.length; i++) {
		                            if(planePaths[i].equals("Upload.png")) {
		                                planeIcons[i] = new ImageIcon(planePaths[i], "Upload");
		                            } else {
		                                planeIcons[i] = new ImageIcon(planePaths[i]);
		                            }
		                        }
		                        // Show a dialog with the images and let the user choose one
		                        int imageChoice = JOptionPane.showOptionDialog(vehicleFrame, "Choose a Plane image", "Plane Image", JOptionPane.DEFAULT_OPTION,
		                                JOptionPane.PLAIN_MESSAGE, null, planeIcons, planeIcons[0]);

		                        ImageIcon chosenImage;
		                        // If the user selected "Upload Image", show a file chooser dialog and create an ImageIcon from the selected file
		                        if (planeIcons[imageChoice] == planeIcons[3]) {
		                            JFileChooser fileChooser = new JFileChooser();
		                            int result = fileChooser.showOpenDialog(vehicleFrame);
		                            if (result == JFileChooser.APPROVE_OPTION) {
		                                File selectedFile = fileChooser.getSelectedFile();
		                                String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);
		                                if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
		                                    chosenImage = new ImageIcon(selectedFile.getAbsolutePath());
		                                } 
		                                else 
		                                {
		                                    JOptionPane.showMessageDialog(vehicleFrame, "Selected file is not an image. Please select a valid image file.", "Error", JOptionPane.ERROR_MESSAGE);
		                                    return;
		                                }
		                            } 
		                            else 
		                            {
		                                // If the user cancelled the file chooser dialog, exit the method without creating a new HybridPlane object
		                                return;
		                            }
		                        } 
		                        else 
		                        {
		                            chosenImage = planeIcons[imageChoice];
		                        }

		                        abstractFactory vehicleFactory = factory.getFact(0);
		                        vehicles hybridPlane = vehicleFactory.create(1,chosenImage,args);
		                        Color color = colorPanel.getColor();
				                hybridPlane = new statusandcolor_Decorator(hybridPlane, color);
		                        addVehicle(hybridPlane);
		                        vehicleFrame.dispose();
		                    }
		                } 
		                catch (NumberFormatException ex) 
		                {
		                    JOptionPane.showMessageDialog(vehicleFrame, "Invalid input. Please enter valid numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		                JOptionPane.showMessageDialog(vehicleFrame, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    });

		    // Add the input panel and submit button to the vehicle frame
		    vehicleFrame.add(inputPanel, BorderLayout.CENTER);
		    vehicleFrame.add(submitButton, BorderLayout.SOUTH);
		    vehicleFrame.setVisible(true);
		}
		
	}
	public void addVehicle(vehicles v) 
	{
		JDialog dialog = new JDialog();
    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	dialog.setSize(200, 100);
    	dialog.setLocationRelativeTo(null);
    	JLabel label = new JLabel("Updating cars database...");
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
    	        JOptionPane.showMessageDialog(null, "Car added successfully!");
    	        Gui_Car_Agency.carList.add(v);
    	    }
    	});
    	timer.setRepeats(false); // Ensure the timer fires only once
    	timer.start();
	}
}
