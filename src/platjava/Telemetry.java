/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package platjava;

import java.net.DatagramPacket;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class Telemetry
{
    public JSONObject data;
    
    public Telemetry(DatagramPacket dp) throws JSONException
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<dp.getLength() ;i ++)
                 sb.append((char)dp.getData()[i]);
        this.data = new JSONObject(sb.toString());
    }
    
    public Object getData(DataType dt) throws JSONException
    {
        return this.data.get(dt.toString().toLowerCase());
    }
}
