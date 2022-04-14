/**
 * Geela Margo Ramos
 * COP4520 Assignment 3
 * Due Date: April 13, 2022
 * 
 * Design and implement a solution using 8 threads that will offer a solution for this task.
 * Assume that the temperature readings are taken every 1 minute. In your solution, simulate
 * the operation of the temperature reading sensor by generating a random number from 
 * -100F to 70F at every reading.
 * 
 */

import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.io.IOException;

class Node<T> {
    T item;
    int key;
    Node next;
    
    public Node(T temp) {
        item = temp;
    }
}

class CoarseList<T> {
    private Node head;
    private ReentrantLock lock = new ReentrantLock();
    
    public CoarseList() {
        head = new Node(Integer.MIN_VALUE);
        head.next = new Node(Integer.MAX_VALUE);
    }
    
    public boolean add(T item) {
        Node pred, curr;
        int key = item.hashCode();
        lock.lock();
        
        try {
            pred = head;
            curr = pred.next;
            while(curr.next != null) {
                pred = curr;
                curr = curr.next;
            }
            
            /* Add new temperature reading */
            Node node = new Node(item);
            node.next = curr;
            pred.next = node;
            return true;
        } finally {
            lock.unlock();
        }
    }    
}

class Sensor extends Thread implements Runnable {
    int identifier;
    AtmosphereTemp mainThread;
    
    public Sensor(int i, AtmosphereTemp main){
        this.identifier = i;
        this.mainThread = main;
    }
    
    @Override
    public void run() {
        try {
            /* Generate random temperature as part of reading */
            Random atmosphere = new Random();
            int temperature = 1 + atmosphere.nextInt(100);
            if(temperature > 70) {temperature *= -1;}
            else {temperature = temperature * (atmosphere.nextBoolean() ? -1 : 1);}
            
            System.out.println("Sensor " + identifier + ": " + temperature + " F");
            
            //Add temperature to concurrent list
            
            
        }
        catch (Exception e) {
            System.out.println("Exception Message: " + e);
        }
    }
    
}

public class AtmosphereTemp
{
    //variables to meet homework assignment requirements
    public int numMinutes; public int numHours;
    public boolean readingCont;
    
    public static final int numSensors = 8;
    private List<Thread> threads;
    
    public AtmosphereTemp() {
        this.numMinutes = 0;
        this.numHours = 0; 
        this.readingCont = true;
    }
    
    //Method for retrieving the five highest temperatures from the hour
    public void findHighestTemperatures(){
        
    }
    
    //Method for retrieving the five lowest temperatures from the hour
    public void findLowestTemperatures(){
        
    }
    
    //Method for retrieving 10 minute interval of time when the largest temperature difference was observed
    public void findInterval() {
        
    }
    
    /* Full simulation */
    public static void main(String[] args) throws InterruptedException {
        AtmosphereTemp rover = new AtmosphereTemp();
        long start, end;
        
        /* Spawning of sensors(threads) */
        rover.threads = new ArrayList<>();
        for(int i = 1; i <= numSensors; i++){
            Sensor s = new Sensor(i, rover);
            Thread t = new Thread(s);
            rover.threads.add(t);
        }
        
        /* Prepares Scanner object for user response */
        Scanner scan = new Scanner(System.in);
        
        /* Simulation of temperature readings */
        while(rover.readingCont == true) {
            start = System.currentTimeMillis();
            
            /* Simulates running for an hour */
            while(rover.numMinutes < 60) {
                /* Each sensor conducts a reading at the minute */
                for(Thread t : rover.threads) {
                    try {
                        t.run();
                        t.join();
                    }
                    catch (Exception e) {
                        System.out.println("Exception Message: " + e);
                    }
                }
            
                rover.numMinutes++;
            }
            
            
            //Retrieves the five highest temperatures recorded for that hour
            /* call respective method */
            
            //Retrieves the five lowest temperatures recorded for that hour
            /* call respective method */
            
            //Retrieves the 10 minute interval of time when the largest temperature difference was observed
            /* call respective method */
            
            end = System.currentTimeMillis();
            long time = end - start;
            System.out.println("Simulated Hour Equivalent to Real-Time Passed: " + time + "ms ");
        
            /* Asks for user's input to continue */
            System.out.println("One hour has passed. Do you want to continue reading? (yes or no)");
            String response = scan.nextLine();
            if(response.equals("yes")) {
                rover.readingCont = true;
                rover.numMinutes = 0; //resets for the next hour
            }
            else {rover.readingCont = false;}
        }
        
        /* Finish simulation */
        for(Thread t : rover.threads) {
            t.interrupt();
        }
    }
}
