import javax.swing.*;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tom Wulf Tom.Wulf@uc.edu
 */
public class SafeInput 
{
   /**
    * Get a String which contains at least one character
    * @param prompt prompt for the user
    * @return a String response that is not zero length
    */ 
   public static String getNonZeroLenString(String prompt)
   {
       String retString = JOptionPane.showInputDialog(prompt + ": ");
       do
       {
           if(retString.length() == 0) {
               JOptionPane.showMessageDialog(null, "Please enter something.");
               retString = JOptionPane.showInputDialog(prompt + ": ");
           }
       }while(retString.length() == 0); // until we have some characters
       
       return retString;
       
   }
    /**
     * Get an int value within a specified numeric range
     * @param pipe - Scanner instance to read the data System.in in most cases
     * @param prompt - input prompt msg should not include range info
     * @param low - low end of inclusive range
     * @param high - high end of inclusive range
     * @return - int value within the inclusive range
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
       int retVal = 0;
       String trash = "";
       boolean done = false;
       
       do
       {
           System.out.print("\n" + prompt + "[" + low + "-" + high + "]: ");
           if(pipe.hasNextInt())
           {
               retVal = pipe.nextInt();
               pipe.nextLine();
               if(retVal >= low && retVal <= high)
               {
                  done = true;
               }
               else
               {
                   System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
               }
           }
           else
           {
               trash = pipe.nextLine();
               System.out.println("You must enter an int: " + trash);
           }
       }while(!done);
       
       return retVal;
    }

    /**
     * Get an int value with no constraints
     * @param pipe - Scanner instance to read the data System.in in most cases
     * @param prompt - input prompt msg should not include range info
     * @return - unconstrained int value 
     */
    public static int getInt(Scanner pipe, String prompt)
    {
       int retVal = 0;
       String trash = "";
       boolean done = false;
       
       do
       {
           System.out.print("\n" + prompt + ": ");
           if(pipe.hasNextInt())
           {
               retVal = pipe.nextInt();
               pipe.nextLine();
               done = true;               
           }
           else
           {
               trash = pipe.nextLine();
               System.out.println("You must enter an int: " + trash);
           }
       }while(!done);
       
       return retVal;
    }

    
    /**
     * get a double value within an inclusive range
     * @param pipe - Scanner instance to read the data System.in in most cases
     * @param prompt - input prompt msg should not contain range info
     * @param low - low value inclusive
     * @param high - high value inclusive
     * @return  - double value within the specified inclusive range
     */
    public static double getRangedDouble(Scanner pipe, String prompt, int low, int high)
    {
       double retVal = 0;
       String trash = "";
       boolean done = false;
       
       do
       {
           System.out.print("\n" + prompt + "[" + low + "-" + high + "]: ");
           if(pipe.hasNextDouble())
           {
               retVal = pipe.nextDouble();
               pipe.nextLine();
               if(retVal >= low && retVal <= high)
               {
                  done = true;
               }
               else
               {
                   System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
               }
           }
           else
           {
               trash = pipe.nextLine();
               System.out.println("You must enter a double: " + trash);
           }
       }while(!done);
       
       return retVal;
    } 
    
/**
     * Get an unconstrained double value
     * @param prompt - input prompt msg should not contain range info
     * @return  - an unconstrained double value 
     */

    public static double getDouble(String prompt)
    {
       double retVal = 0;
       String hold = "";
       String trash = "";
       boolean done = false;
       
       do
       {
           hold = JOptionPane.showInputDialog(prompt + ": ");
           try
           {
               retVal = Double.parseDouble(hold);
               done = true;
           }
           catch (NumberFormatException e)
           {
               trash = hold;
               JOptionPane.showMessageDialog(null, "Please enter a number. You entered: " + trash);
           }
       }while(!done);
       
       return retVal;
    }

    
    /**
     * Get a [Y/N] confirmation from the user
     * @param prompt -input prompt msg for user does not need [Y/N]
     * @return - true for yes false for no
     */
    public static boolean getYNConfirm(String prompt)
    {
        boolean retVal = true;
        String response = "";
        boolean gotAVal = false;
        
        do
        {
            response = JOptionPane.showInputDialog(prompt + " [Y/N] ");
            if(response.equalsIgnoreCase("Y"))
            {
                gotAVal = true;
                retVal = true;
            }
            else if(response.equalsIgnoreCase("N"))
            {
                gotAVal = true;
                retVal = false;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"You must answer [Y/N]! " + response );
            }
            
        }while(!gotAVal);
        
        return retVal;
    }
    /**
     * Get a string that matches a RegEx pattern! This is a very powerful method 
     * @param pipe - Scanner instance to read the data System.in in most cases
     * @param prompt - prompt for user
     * @param regExPattern - java style RegEx pattern to constrain the input
     * @return a String that matches the RegEx pattern supplied
     */
    
    public static String getRegExString(Scanner pipe, String prompt, String regExPattern)
    {
        String response = "";
        boolean gotAVal = false;
        
        do
        {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if(response.matches(regExPattern))
            {
                gotAVal = true;                
            }
            else
            {
                System.out.println("\n" + response + " must match the pattern " + regExPattern);
                System.out.println("Try again!");
            } 
            
        }while(!gotAVal);
        
        return response;
    }
}
