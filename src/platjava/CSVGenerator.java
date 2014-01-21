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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class CSVGenerator {

    public CSVGenerator() {

    }

    public static void generateCsvFile(File file, ArrayList<Telemetry> tele) throws JSONException, IOException {
        try (FileWriter writer = new FileWriter(file)) {
            int count = DataType.values().length - 1;

            for (DataType c : DataType.values()) {
                writer.append("\"" + c.getLabel() + "\"");
                if (!(c.ordinal() == count)) {
                    writer.append(',');
                }
            }
            writer.append('\n');

            Iterator<Telemetry> ittele = tele.iterator();
            while (ittele.hasNext()) {
                Telemetry obj = ittele.next();
                for (DataType v : DataType.values()) 
                {
                    if (v.getType() == String.class) 
                    {
                        writer.append("\""+obj.getData(v).toString()+"\"");
                        if (!(v.ordinal() == count)) 
                        {
                            writer.append(',');
                        }
                    } 
                    else 
                    {
                        writer.append(obj.getData(v).toString());
                        if (!(v.ordinal() == count)) 
                        {
                            writer.append(',');
                        }
                    }
                }

                writer.append('\n');
            }

            writer.flush();
            writer.close();
        }
    }
}
