package Gui;

import vehicles.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class tryCar extends JFrame 
{
    private static final long serialVersionUID = 1L;
    private JLabel promptLabel;
    private JTextField kmTextField;
    private JButton confirmButton;
    private vehicles currentCar;
    private int index;
    private JFrame tryFrame;
    private static ExecutorService threadPool;
    private static final int MAX_CONCURRENT_TRIALS = 7;
    private static Semaphore trialSemaphore;
    private static Map<Integer, Lock> carLocks = new HashMap<>();
    private static ArrayList<observer> observers = new ArrayList<>();
    static 
    {
        // Initialize the semaphore with a maximum of 7 permits
        trialSemaphore = new Semaphore(MAX_CONCURRENT_TRIALS);
        // Initialize the thread pool
        threadPool = Executors.newFixedThreadPool(MAX_CONCURRENT_TRIALS);
    }
    public tryCar(int index) 
    {
        this.index = index;
        this.currentCar = Gui_Car_Agency.carList.get(index);
        createTry();
    }
    public static void setTryCarObserver(observer observer) 
    {
        tryCar.observers.add(observer);
    }
    private void notifyObservers(float km) 
    {
        for (observer observer : observers) 
        {
            observer.update(km);
        }
    }
    public void createTry() 
    {
        if (((statusandcolor_Decorator) currentCar).getStatus().equals("On try")) 
        {
            JOptionPane.showMessageDialog(null, "This car is already on a trial. Please try again later.");
            return;
        }
        if (((statusandcolor_Decorator) currentCar).getStatus().equals("On buy")) 
        {
            JOptionPane.showMessageDialog(null, "This car is already on a buy. Please try again later.");
            return;
        }
        if (!trialSemaphore.tryAcquire()) 
        {
            JOptionPane.showMessageDialog(null, "All trial permits are currently in use. Please try again later.");
            return;
        }
        tryFrame = new JFrame();
        tryFrame.setTitle("Enter Kilometers");
        tryFrame.setSize(300, 150);
        tryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tryFrame.setLayout(new FlowLayout());
        tryFrame.setLocationRelativeTo(null);
        promptLabel = new JLabel("Enter how many kilometers to drive:");
        kmTextField = new JTextField(10);
        confirmButton = new JButton("Confirm");

        confirmButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String stringKm = kmTextField.getText();
                if (stringKm.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number of kilometers.");
                    return;
                }

                float km;
                try 
                {
                    km = Float.parseFloat(stringKm);
                } 
                catch (NumberFormatException ex) 
                {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                    return;
                }

                threadPool.execute(new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        try 
                        {
                            Lock carLock = carLocks.computeIfAbsent(index, k -> new ReentrantLock());
                            carLock.lock(); // Acquire the lock for this car
                            ((statusandcolor_Decorator) currentCar).setStatus("On try");
                            (currentCar).move(km);
                            notifyObservers(km);
                            // Perform the trial
                            Thread.sleep((long) (km * 100));
                        } 
                        
                        catch (Exception ex) 
                        {
                            ex.printStackTrace();
                        }
                        finally 
                        {
                            ((statusandcolor_Decorator) currentCar).setStatus("In stock");
                            // Release the lock for this car
                            Lock carLock = carLocks.get(index);
                            carLock.unlock();
                            trialSemaphore.release();
                            SwingUtilities.invokeLater(new Runnable() 
                            {
                                @Override
                                public void run() 
                                {
                                    System.out.println(( currentCar).toString());
                                    JOptionPane.showMessageDialog(null, km + " km added");
                                    tryFrame.dispose();
                                }
                            });
                        }
                    }
                });
            }
        });

        tryFrame.add(promptLabel);
        tryFrame.add(kmTextField);
        tryFrame.add(confirmButton);
        tryFrame.setVisible(true);
    }
}
