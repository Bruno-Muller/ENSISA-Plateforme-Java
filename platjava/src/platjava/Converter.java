/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package platjava;

/**
 *
 * @author ASUS
 */
public class Converter
{
    private String ResultingString;
    private char[] ResultingCharTab;
    public Converter()
    {
        
    }
    
    public char[] ConvertBytesToChar(byte[] tab_byte)
    {
        int k = tab_byte.length;
        for(int i = 0; i<k ;i ++)
                {
                 ResultingCharTab[i] = (char) tab_byte[i]; 
                }
        return ResultingCharTab;      
    }
    
    public String ConvertCharToString(char[] tab_char)
    {
        int k = tab_char.length;
        for(int i = 0; i<k ;i ++)
                {
                 ResultingString += tab_char[i]; 
                }
        return ResultingString;      
    }
    
    
}
