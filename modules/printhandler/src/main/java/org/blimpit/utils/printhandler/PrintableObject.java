package org.blimpit.utils.printhandler;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 * This class will render given data from the print service
 * 
 * @author Chathura
 */
public class PrintableObject implements Printable{

    private String printString;
    private int fontSize;
    
    public PrintableObject() {
        printString = null;
        fontSize = 12;      // we use font size 12 as the default
    }
    
    @Override
    public int print(Graphics grphcs, PageFormat pf, int i) throws PrinterException {
        if(i > 0) {
            return NO_SUCH_PAGE;        // we are currently expecting single pages: also pages are zero based
        }
        
        // Now we perform our page rendering
        grphcs.translate((int) pf.getImageableX(), (int) pf.getImageableY());
        grphcs.setFont(new Font("dialog", Font.PLAIN, fontSize));     // setup a font with plain text size 12
        grphcs.drawString(printString, 0, fontSize);
        
        // send the printer this documnet is a part of a printed document
        return PAGE_EXISTS;
    }

    public void setPrintString(String printString) {
        this.printString = printString;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
