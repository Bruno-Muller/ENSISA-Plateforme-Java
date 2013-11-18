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
    
    public Converter()
    {
        
    }
    
    public String ConvertBytesToString(byte[] tab_byte)
    {
        int k = tab_byte.length;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<k ;i ++)
                {
                 sb.append((char)tab_byte[i]); 
                }
        ResultingString = sb.toString();
        return ResultingString;      
    }
    
    JSONObject jsonObj = new JSONObject("{\"phonetype\":\"N95\",\"cat\":\"WP\"}");
