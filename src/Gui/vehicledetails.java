package Gui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import vehicles.*;

public class vehicledetails extends JFrame 
{
    private static final long serialVersionUID = 1L;
    private JFrame vehicleDetailsFrame;
    private vehicles currentcar;
    private int index;
    static Semaphore trialSemaphore;

    public vehicledetails(int index) 
    {
        this.currentcar = Gui_Car_Agency.carList.get(index);
        this.index = index;
        this.trialSemaphore = new Semaphore(7);
        createFrame();
        vehicleDetailsFrame.addWindowFocusListener(new WindowFocusListener() 
        {
            @Override
            public void windowGainedFocus(WindowEvent e) 
            {
            	if (currentcar == null || ((statusandcolor_Decorator)currentcar).getStatus()== "On buy") 
                {
                    JOptionPane.showMessageDialog(null, "This car is no longer available.");
					vehicleDetailsFrame.dispose();
				}
            	else 
            	{
					refreshInfo();
				}
            }

            @Override
            public void windowLostFocus(WindowEvent e) 
            {
            }
        });
    }

    public void refreshInfo() {
        SwingUtilities.invokeLater(() -> {
            vehicleDetailsFrame.getContentPane().removeAll(); // Remove all components from the content pane

            // Check if the index is still valid
            if (index >= 0 && index < Gui_Car_Agency.carList.size()) 
            {
                // The index is valid, retrieve the currentcar
                currentcar = Gui_Car_Agency.carList.get(index);

                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                JLabel label = new JLabel(currentcar.toString());
                JPanel buttonpanel = new JPanel();
                panel.add(label);
                JButton tryButton = new JButton("Try Car!");
                JButton buyButton = new JButton("Buy Car!");
                buttonpanel.add(tryButton, BorderLayout.SOUTH);
                JButton exitButton = new JButton("Exit");
                exitButton.addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        vehicleDetailsFrame.dispose();
                    }
                });
                buttonpanel.add(exitButton);
                buttonpanel.add(buyButton, BorderLayout.SOUTH);

                tryButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        tryCar tryCar = new tryCar(index);
                        vehicleDetailsFrame.dispose();
                    }
                });

                buyButton.addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                            // The index is valid, proceed with buying the car
                        buyCar buyCar = new buyCar(index);
                        vehicleDetailsFrame.dispose();
                        
                    }
                });

                vehicleDetailsFrame.getContentPane().add(buttonpanel, BorderLayout.SOUTH);
                vehicleDetailsFrame.getContentPane().add(panel, BorderLayout.CENTER);
            } 

            vehicleDetailsFrame.setVisible(true);
            vehicleDetailsFrame.setLocationRelativeTo(null);
        });
    }

    public void createFrame() {
        vehicleDetailsFrame = new JFrame("Vehicle Details");
        vehicleDetailsFrame.setSize(new Dimension(250, 250));
        vehicleDetailsFrame.setMinimumSize(new Dimension(50, 50));
        JLabel label = new JLabel(currentcar.toString());
        JPanel panel = new JPanel();
        JPanel buttonpanel = new JPanel();
        panel.add(label);
        JButton tryButton = new JButton("Try Car!");
        JButton buyButton = new JButton("Buy Car!");
        buttonpanel.add(tryButton, BorderLayout.SOUTH);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vehicleDetailsFrame.dispose();
            }
        });
        buttonpanel.add(exitButton);
        buttonpanel.add(buyButton, BorderLayout.SOUTH);

        tryButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                tryCar tryCar = new tryCar(index);
                vehicleDetailsFrame.dispose();

            }
        });

        buyButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                    buyCar buyCar = new buyCar(index);
                    vehicleDetailsFrame.dispose();

                
            }
        });

        vehicleDetailsFrame.getContentPane().add(buttonpanel, BorderLayout.SOUTH);
        vehicleDetailsFrame.getContentPane().add(panel, BorderLayout.CENTER);
        vehicleDetailsFrame.setVisible(true);
        vehicleDetailsFrame.setLocationRelativeTo(null);
    }

    private boolean isCarOnTrial() 
    {
        return ((statusandcolor_Decorator) currentcar).getStatus().equals("On try");
    }
}
