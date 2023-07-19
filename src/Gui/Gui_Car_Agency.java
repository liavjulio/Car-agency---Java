package Gui;
import javax.swing.*;
import vehicles.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Gui_Car_Agency extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton Inputbutton; 
	private JButton menubutton;
	// Button
	static ArrayList<vehicles> carList;
	private JFrame frmMainMenu;
	private static Gui_Car_Agency instance;
	public static Gui_Car_Agency getInstance() 
	{
        // Step 4: Check if instance variable is null, create new instance if necessary
        if (instance == null) 
        {
            instance = new Gui_Car_Agency();
        }
        return instance;
    }
	public Gui_Car_Agency() 
	{
		carList = new ArrayList<vehicles>();
		frmMainMenu = new JFrame();
		// Set the title of the window
		frmMainMenu.setTitle("car agency");
		// Create the main panel and set its layout manager to BorderLayout
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.gray);
		// Create the panel for the label and add it to the center of the main panel
		JPanel labelPanel = new JPanel();
		labelPanel.setBackground(Color.DARK_GRAY);
		JLabel label = new JLabel("Welcome to Car Agency");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelPanel.add(label);
		mainPanel.add(labelPanel, BorderLayout.CENTER);		// Create the panel for the buttons and add it to the bottom of the main panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.DARK_GRAY);
		Inputbutton = new JButton("Input cars!");
		Inputbutton.setFont(new Font("Tahoma", Font.BOLD, 12));
		Inputbutton.setPreferredSize(new Dimension(150, 50)); // set preferred size
		menubutton = new JButton("All vehicles!");
		menubutton.setFont(new Font("Tahoma", Font.BOLD, 12));
		menubutton.setPreferredSize(new Dimension(150, 50)); // set preferred size
		buttonPanel.add(Inputbutton);
		buttonPanel.add(menubutton);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		Inputbutton.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		        SwingWorker<Void, Void> inputWorker = new SwingWorker<Void, Void>() 
		        {
		            @Override
		            
		            protected Void doInBackground() throws Exception 
		            {
		                // Perform any necessary background tasks here
		                return null;
		            }
		            @Override
		            protected void done() 
		            {
		                // This method is executed on the Event Dispatch Thread after doInBackground() completes
		                input_menu input = new input_menu(); // Call the input_menu() method on the Event Dispatch Thread
		            }
		        };
		        inputWorker.execute();
		    }
		});
		menubutton.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) {
		        SwingWorker<Void, Void> menuWorker = new SwingWorker<Void, Void>() 
		        {
		            @Override
		            protected Void doInBackground() throws Exception 
		            {
		            	mainMenu menu = new mainMenu();
		                return null;
		            }
		            protected void done() 
		            {
		            	try 
		            	{
		            		// Handle any exceptions that might have occurred during doInBackground()
                            get();
                        } catch (InterruptedException | ExecutionException ex) 
		            	{
                            ex.printStackTrace();
                            // Handle the exception gracefully
                            JOptionPane.showMessageDialog(frmMainMenu, "Error occurred in menu: " + ((Throwable) ex).getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
		            }
		        };
		        menuWorker.execute();
		    }
		});
		// Add the main panel to the window
		frmMainMenu.getContentPane().add(mainPanel);
		// Set the size of the window and make it visible
		frmMainMenu.setSize(420, 156);
		frmMainMenu.setVisible(true);
		frmMainMenu.setLocationRelativeTo(null); // Center the frame on the screen

	}	
	public static void main(String[] args) 
    {
		SwingUtilities.invokeLater(() -> 
		{
	        Gui_Car_Agency gui = Gui_Car_Agency.getInstance();
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}