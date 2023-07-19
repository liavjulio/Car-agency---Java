package Gui;

import vehicles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class buyCar extends JFrame 
{
    private static final long serialVersionUID = 1L;
    private JLabel promptLabel;
    private JButton confirmButton;
    private vehicles currentCar;
    private int index;
    private JFrame buyFrame;
    private static Map<Integer, Lock> carLocks = new HashMap<>();

    public buyCar(int index) 
    {
        this.index = index;
        this.currentCar = Gui_Car_Agency.carList.get(index);
        createBuy();
    }

    public void createBuy() 
    {
        buyFrame = new JFrame();
        buyFrame.setTitle("Buy Car");
        buyFrame.setSize(300, 150);
        buyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buyFrame.setLayout(new FlowLayout());
        buyFrame.setLocationRelativeTo(null);
        promptLabel = new JLabel("Are you sure you want to buy this car?");
        confirmButton = new JButton("Confirm");

        if (((statusandcolor_Decorator) currentCar).getStatus().equals("On try")) 
        {
            JOptionPane.showMessageDialog(null, "The car is currently being tried. Please try again later.");
            return;
        }
        if (((statusandcolor_Decorator) currentCar).getStatus().equals("On buy")) 
        {
            JOptionPane.showMessageDialog(null, "This car is already on a buy.");
            return;
        }
        confirmButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                Lock carLock = carLocks.computeIfAbsent(index, k -> new ReentrantLock());

                if (carLock.tryLock()) 
                {
                    try 
                    {
                        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to buy this car?", "Confirm Purchase", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) 
                        {
                            try 
                            {
                                Thread.sleep((long) (5000 + Math.random() * 5000));
                                ((statusandcolor_Decorator) currentCar).setStatus("On buy");
                                Gui_Car_Agency.carList.remove(index);
                                JOptionPane.showMessageDialog(null, "Car purchased successfully!");
                                buyFrame.dispose();
                            } 
                            catch (Exception ex) 
                            {
                                ex.printStackTrace();
                                ((statusandcolor_Decorator) currentCar).setStatus("In stock");
                                JOptionPane.showMessageDialog(null, "An error occurred during the purchase. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            buyFrame.dispose();
                        }
                    } 
                    finally 
                    {
                        carLock.unlock();
                    }
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "The car is currently being tried. Please try again later.");
                }
            }
        });

        buyFrame.add(promptLabel);
        buyFrame.add(confirmButton);
        buyFrame.setVisible(true);
    }
}
