/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import platjava.DataType;

public class ExampleOfXcel {

    public static void main(String[] args) {
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(new File("sortie.xls"));
            WritableSheet sheet = workbook.createSheet("Premier classeur", 0);
//Crée le format d’une cellule
            WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 20, WritableFont.BOLD, true, UnderlineStyle.NO_UNDERLINE, Colour.BLUE, ScriptStyle.NORMAL_SCRIPT);
            WritableCellFormat arial10format = new WritableCellFormat(arial10font);
//Crée un label à la ligne 0, colonne 0 avec le format spécifique
            Label label = new Label(0, 0, "Arial 10 point label", arial10format);
//Crée un label à la ligne 2, colonne 0 sans style prédéfini
            Label label2 = new Label(0, 2, "Résultat");

            Number number = new Number(1, 2, 3.1459);
            sheet.addCell(number);
            int i = 0;
            for (DataType d : DataType.values()) 
            {
                Label label3 = new Label(i, 4, d.getLabel());
                sheet.addCell(label3);
                i++;
            }
            
            

            workbook.write();
            workbook.close();
        } catch (RowsExceededException e1) {
            e1.printStackTrace();
        } catch (WriteException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Le fichier \"sortie.xls\" à été généré correctement.");
        }
    }
}
