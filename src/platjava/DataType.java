/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package platjava;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class Data
{
    private double altitude; //Au vu de la précision j'ai mis un double.
    private double speed;
    private double longitude;
    private JSONObject obj;
    
    public Data()
    {
        
    }
    
    public double getAltitude()
    {
        return this.altitude;
    }
    

    /**
     * @param altitude the altitude to set
     */
    public void setAltitude(double altitude)
    {
        this.altitude = altitude;
    }

    /**
     * @return the speed
     */
    public double getSpeed()
    {
        return this.speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    /**
     * @return the longitude
     */
    public double getLongitude()
    {
        return this.longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    /**
     * @return the obj
     */
    public JSONObject getObj()
    {
        return obj;
    }

    /**
     * @param obj the obj to set
     */
    public void setObj(JSONObject obj)
    {
        this.obj = obj;
    }
}
