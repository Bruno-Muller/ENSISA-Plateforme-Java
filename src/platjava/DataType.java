/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package platjava;

/**
 *
 * @author Sanzensekai
 */
public enum DataType
{
    UNIQUE_ID,VESSEL_NAME,MISSION_TIME,REFERENCE_BODY,ALTITUDE,LATITUDE
    ,LONGITUDE,VERTICAL_SPEED,ORBITAL_VELOCITY,SURFACE_VELOCITY,APOAPSIS,
    PERIAPSIS,ECCENTRICITY,TOTAL_MASS,ATMOSPHERE_DENSITY,TEMPERATURE,
    STATIC_PRESSURE,DYNAMIC_PRESSURE;
    
    // Labels textuels pour l'interface graphique
    private static final String[] LABELS = {"Unique id","Vessel name",
        "Mission time","Reference Body","Altitude","Latitude",
        "Longitude","Vertical speed","Orbital velocity","Surface velocity",
    "Apoapsis","Periapsis","Eccentricity","Total mass",
    "Atmosphere density", "Temperature","Static pressure",
    "Dynamic pressure"};
    
    // Pour connaitre le type de la donnée
    public Class getType()
    {
        switch(this)
        {
            case UNIQUE_ID:
                return Integer.class;
            case VESSEL_NAME:
            case REFERENCE_BODY:
                return String.class;
            default:
                return float.class;
        }
    }
    
    // Pour connaitre l'unité de la donnée
    public String getUnity()
    {
        switch(this)
        {
            case MISSION_TIME:
                return "s";
            case ALTITUDE:
            case LATITUDE:
            case LONGITUDE:
            case APOAPSIS:
            case PERIAPSIS:
                return "m";
            case VERTICAL_SPEED:
            case ORBITAL_VELOCITY:
            case SURFACE_VELOCITY:
                return "m/s";
            case TOTAL_MASS:
                return "t";
            case ATMOSPHERE_DENSITY:
                return "kg/m^3";
            case TEMPERATURE:
                return "K";
            case STATIC_PRESSURE:
            case DYNAMIC_PRESSURE:
                return "bar";
            default:
                return "";
        }
    }
    
    // Récupère le label textuel d'un type énuméré
    public String getLabel()
    {
        return DataType.LABELS[this.ordinal()];
    }
    
    // Récupère le type énuméré à partir de son label
    public DataType fromLabel(String lb)
    {
        DataType v=null;
          for(DataType c : DataType.values())
          {
              if(lb.equals(c.getLabel()))
              {
                  v = c;
              }
          }
          return v;
    }
}

