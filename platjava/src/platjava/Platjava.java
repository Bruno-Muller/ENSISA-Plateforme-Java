/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package platjava;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Platjava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        List DataArray = new ArrayList<Data>();
        
     byte[] octets = new byte[] {'a', 'b', 'c'};
     String chaine = "";
 
     //si on ne met pas (char) on obtient 979899 au lie de abc
     for(int i=0; i<octets.length; i++)
       chaine = chaine + (char)octets[i];
 
     System.out.println(chaine); 
    }
    
}
