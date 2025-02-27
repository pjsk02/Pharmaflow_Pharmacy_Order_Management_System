/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.validation;

import java.awt.Color;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * @author priyankasatish
 */
public class Validation {
    public static boolean isValidString(String s) {
        Boolean isValid = true;
        if (s.isEmpty() || s.isBlank()) 
            isValid = false;
        return isValid;
    }
    
    public static boolean isValidInteger(String s, int radix) {
        Boolean isValid = true;
        if(s.isEmpty()) isValid = false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return isValid;
    }
    
    public static boolean isValidEmail(String email) {
        Boolean isValid = true;
        if (!email.contains("@") || !email.contains(".com")) isValid = false;
        return isValid;
    }
    
    public static void isValidContact(String contact, JTextField field)
    {
        if(contact.matches("[0-9]*$") && contact.length() == 10){
            field.setBackground(new Color(154, 228, 158));
        
    }
        else{
            field.setBackground(new Color(245, 158, 133));  // setting colour ot red       
        }
    }

    
    public static void checkValidEmail(String email, JTextField field)
    {
               
        if((Pattern.matches("[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", email))){
            field.setBackground(new Color(154, 228, 158));
            
        }
        else{
            
            field.setBackground(new Color(246, 159, 132));
           
        }
    }
    
    public static boolean IsValidateUsername(String username){
      if(username.equals("")||username.length()<=3){
          return false;
      }
      
      return true;
    }
    
    public static boolean IsValidPassword(String password) {

        if (password.length() < 8) return false;

        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (is_Numeric(ch)) numCount++;
            else if (is_Letter(ch)) charCount++;
            else return false;
        }
        return (charCount >= 2 && numCount >= 2);
    }

    public static boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }


    public static boolean is_Numeric(char ch) {
        return (ch >= '0' && ch <= '9');
    }

}


