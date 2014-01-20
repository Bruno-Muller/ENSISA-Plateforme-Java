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

    public static void generateCsvFile(File file, ArrayList<Telemetry> data) {
        try {
            try (FileWriter writer = new FileWriter(file)) 
            {
                Iterator<Telemetry> ite= data.iterator();
                while(ite.hasNext())
                {
                
                writer.append("DisplayName");
                writer.append(',');
                writer.append("Age");
                writer.append('\n');
                
                writer.append("MKYONG");
                writer.append(',');
                writer.append("26");
                writer.append('\n');
                
                writer.append("YOUR NAME");
                writer.append(',');
                writer.append("29");
                writer.append('\n');
                
                ite.next();
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
