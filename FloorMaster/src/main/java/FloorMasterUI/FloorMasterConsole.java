/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasterUI;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FloorMasterConsole {

     public void print(String line){
        System.out.println(line);
    }
    
    public void soutSameLine(String prompt){
        System.out.print(prompt);
    }
    
    public String promptForString(String prompt){
        Scanner sc = new Scanner(System.in);
        boolean successful;
        String type = "string";
        String userInput;
        String input = null;
        
        do{
            successful = true;
            System.out.print(prompt);
            userInput = sc.nextLine(); 
            
            if (!scrubData(userInput, type)){
                System.out.println("ERROR: Please re-try input");
                successful = false;
            }
        } while(!successful);
        
        input = userInput;
        return input;
    }
    
    public String promptForYesOrNo(String prompt){
        boolean successful = true;
        String userInput = null;
        String input = null;
        do{
            successful = true;
            userInput = promptForString(prompt).toLowerCase();
            
            if(!userInput.contains("y") && !userInput.contains("n")){
                System.out.println("ERROR: Input is out of range, please re-try.");
                successful = false;
            }
        } while(!successful);
        input = userInput;
        return input;
    }
    
    public double promptForDouble(String prompt){
        Scanner sc = new Scanner(System.in);
        boolean successful;
        String type = "double";
        String userInput;
        double input = 0;
        
        do{
            successful = true;
            System.out.print(prompt);
            userInput = sc.nextLine(); 
            
            if (!scrubData(userInput, type)){
                System.out.println("ERROR: Please re-try input");
                successful = false;
            }
        } while(!successful);
        
        try{
            input = Double.parseDouble(userInput);
        } catch(NumberFormatException nfe){
            System.out.println("ERROR: Sorry, an issue has occured during type Conversion.");
        }
        return input;
    }
    
    public double promptForDoubleRange(String prompt, double min, double max){
        boolean successful = true;
        double userInput = 0;
        double input = 0;
        do{
            successful = true;
            userInput = promptForDouble(prompt);
            
            if(userInput < min || userInput > max){
                System.out.println("ERROR: Input is out of range, please re-try.");
                successful = false;
            }
        } while(!successful);
        
        input = userInput;
        return input;
    } 
    
    public float promptForFloat(String prompt){
        Scanner sc = new Scanner(System.in);
        boolean successful;
        String type = "float";
        String userInput;
        float input = 0f;
        
        do{
            successful = true;
            System.out.print(prompt);
            userInput = sc.nextLine(); 
            
            if (!scrubData(userInput, type)){
                System.out.println("ERROR: Please re-try input");
                successful = false;
            }
        } while(!successful);
        
        try{
            input = Float.parseFloat(userInput);
        } catch(NumberFormatException nfe){
            System.out.println("ERROR: Sorry, an issue has occured during type Conversion.");
        }
        
        return input;
    }
    
    public float promptForFloatRange(String prompt, float min, float max){
        boolean successful = true;
        float userInput = 0f;
        float input = 0f;
        do{
            successful = true;
            userInput = promptForFloat(prompt);
            
            if(userInput < min || userInput > max){
                System.out.println("ERROR: Input is out of range, please re-try.");
                successful = false;
            }
        } while(!successful);
        
        input = userInput;
        return input;
    }   
    
    public int promptForInt(String prompt){
        Scanner sc = new Scanner(System.in);
        boolean successful;
        String type = "int";
        String userInput;
        int input = 0;
        
        do{
            successful = true;
            System.out.print(prompt);
            userInput = sc.nextLine(); 
            
            if (!scrubData(userInput, type)){
                System.out.println("ERROR: Please re-try input");
                successful = false;
            }
        } while(!successful);
        
        try{
            input = Integer.parseInt(userInput);
        } catch(NumberFormatException nfe){
            System.out.println("ERROR: Sorry, an issue has occured during type Conversion.");
        }
        
        return input;
    }
    
    public int promptForIntInRange(String prompt, int min, int max){
        boolean successful = true;
        int userInput = 0;
        int input = 0;
        do{
            successful = true;
            userInput = promptForInt(prompt);
            
            if(userInput < min || userInput > max){
                System.out.println("ERROR: Input is out of range, please re-try.");
                successful = false;
            }
        } while(!successful);
        
        input = userInput;
        return input;
    } 
    
    public void addCR(int numCRs){
        for(int i = 1; i <= numCRs; i++){
            System.out.println("");
        }
    }
    
        public void soutPromptAndMoney(String prompt, double money){
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        System.out.println(prompt + currencyFormatter.format(money));
    }
    
    private static boolean scrubData(String data, String type){
         boolean isClean = true;
         
         if(type.equals("float") || type.equals("double")){
             if (!data.matches("^-?[0-9]+|-?[0-9]+\\.[0-9]+|-?\\.[0-9]")){
                 isClean = false;
             }
         } else if(type.equals("int")){
             if (!data.matches("^-?[0-9]+")){
                 isClean = false;
             }
         } else if(type.equals("string")){
             if (data.equals("") || data == null){
                 isClean = false;
             }
         }
         return isClean;
    }    
}
