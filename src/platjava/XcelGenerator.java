/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package platjava;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.json.JSONException;

/**
 *
 * @author ASUS
 */
public class XcelGenerator
{
    public XcelGenerator()
    {
        
    }
    
    public static void generateXcelFile(File file, ArrayList<Telemetry> tele) throws JSONException, IOException, WriteException
    {
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        WritableSheet sheet = workbook.createSheet(file.toString(), 0);
        Label label;
        Number number;
        int i =0;
        for (DataType c : DataType.values())
        {
        label = new Label(i, 0, c.getLabel());
        sheet.addCell(label);
        i++;
        }
        
        Iterator<Telemetry> ittele = tele.iterator();
        int j=0;
        int k=2;
        while(ittele.hasNext())
        {
            Telemetry obj = ittele.next();
            for (Telemetry v : tele)
            {
                if(v.getType()==String.class)
                {
                    label = new Label(j,k,(obj.getData(v)).toString());
                    sheet.addCell(label);
                    j++;
                }
                else
                {
                    number = new Number(o);
                }

            }
            k++;
            j=0;
        }
        
    }
}
