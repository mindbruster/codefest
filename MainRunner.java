/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

public class MainRunner 
{
    static LoginGUI frame ;
    
    public static void main(String[] args) 
    {
        frame = new LoginGUI();
        frame.display();
        //  Frontend("Juttsab");

        
    }
    public static void Frontend(String username,String role)
    {
        // System.out.println(role + "rt");
        frame.dispose();
        FrontendGUI MainPage = new FrontendGUI(username,role);
    }
    public static boolean comparestring(String a , String b )
    {
        System.out.println(a + " : " + b);
        if(a.length() != b.length())
            return false;
         for(int i = 0 ; i < a.length() ; i++)
                 if(a.charAt(i) != b.charAt(i))
                     return false;
         return true;

    }

  // POJO (Plain Old Java Object) class defining a recipe. This class is a POJO because it contains getters and
  // setters for every member variable as well as an empty constructor.
  
}

