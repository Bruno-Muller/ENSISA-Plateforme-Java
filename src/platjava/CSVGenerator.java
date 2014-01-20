/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platjava;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class CSVGenerator
{

    public CSVGenerator()
    {

    }

    public static void generateCsvFile(File file, ArrayList<Telemetry> tele) throws JSONException {
        try {
            try (FileWriter writer = new FileWriter(file)) 
            {
                int count =DataType.values().length;
                
                for(DataType c : DataType.values())
                {
                    writer.append(c.getLabel());
                    if(!(c.ordinal()==count))
                    writer.append(',');
                }
                writer.append('\n');
                
                Iterator<Telemetry> ittele= tele.iterator();
                while(ittele.hasNext())
                {
                    Telemetry obj = ittele.next();
                    for(DataType v : DataType.values())
                    {
                        writer.append((obj.getData(v)).toString());
                        if(!(v.ordinal()==count))
                        writer.append(',');
                    }
         
                writer.append('\n');
                }
                //generate whatever data you want
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
