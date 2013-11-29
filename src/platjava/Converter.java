/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package platjava;

import java.net.DatagramPacket;

/**
 *
 * @author ASUS
 */
public class Converter
{
    private String resultingString;
    private byte[] tab_byte;
    private DatagramPacket packet;
    
    public Converter()
    {
        
    }
    
    public Converter(DatagramPacket packet)
    {
        this.packet = packet;
        this.tab_byte = getPacket().getData();
        this.resultingString = this.ConvertBytesToString(getTab_byte());
    }
    
    public String ConvertBytesToString(byte[] tab_byte)
    {
        int k = tab_byte.length;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<k ;i ++)
                {
                 sb.append((char)tab_byte[i]); 
                }
        setResultingString(sb.toString());
        return getResultingString();      
    }
    
    //JSONObject jsonObj = new JSONObject("{\"phonetype\":\"N95\",\"cat\":\"WP\"}");

    /**
     * @return the resultingString
     */
    public String getResultingString()
    {
        return resultingString;
    }

    /**
     * @param resultingString the resultingString to set
     */
    public void setResultingString(String resultingString)
    {
        this.resultingString = resultingString;
    }

    /**
     * @return the tab_byte
     */
    public byte[] getTab_byte()
    {
        return tab_byte;
    }

    /**
     * @param tab_byte the tab_byte to set
     */
    public void setTab_byte(byte[] tab_byte)
    {
        this.setTab_byte(tab_byte);
    }

    /**
     * @return the packet
     */
    public DatagramPacket getPacket() {
        return packet;
    }

    /**
     * @param packet the packet to set
     */
    public void setPacket(DatagramPacket packet) {
        this.packet = packet;
    }
}