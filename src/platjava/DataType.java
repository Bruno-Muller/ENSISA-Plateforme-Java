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
public enum DataType
{
    UNIQUE_ID,VESSEL_NAME,MISSION_TIME,REFERENCE_BODY,ALTITUDE,LATITUDE
    ,LONGITUDE,VERTICAL_SPEED,ORBITAL_VELOCITY,SURFACE_VELOCITY,APOAPSIS,
    PERIAPSIS,ECCENTRICITY,TOTAL_MASSE,ATMOSPHERE_DENSITY,TEMPERATURE,
    STATIC_PRESSURE,DYNAMIC_PRESSURE;
    
    private static final String[] LABELS = {"Unique id","Vessel name",
        "Mission time","Reference Body","Altitude","Latitude",
        "Vertical speed","Orbital velocity","Surface velocity",
    "Apoasis","Periapsis","Eccentricity","Total mass",
    "Atmosphere density", "Temperature","Static pressure",
    "Dynamic pressure"};
    
    public Class getType()
    {
        switch(this)
        {
            case UNIQUE_ID:
            case VESSEL_NAME:
            case REFERENCE_BODY:
                return String.class;
            default:
                return float.class;
        }
    }
    
    public String getLabel()
    {
        return this.LABELS[this.ordinal()];
    }
}

