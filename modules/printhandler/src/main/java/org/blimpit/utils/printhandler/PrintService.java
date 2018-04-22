package org.blimpit.utils.printhandler;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintService implements Printable, PrintHandler, PrintFormat {

	private String printingStr;

    public int print(Graphics grphcs, PageFormat pf, int i) throws PrinterException {

        if (i > 0) { // We have only one page, and 'page' is zero-based 
            return NO_SUCH_PAGE;
        }
 
        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)grphcs;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
 
        /* Now we perform our rendering */
        grphcs.drawString(printingStr, 50, 50);
 
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

    public boolean print(String printableStr, String printerName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean print(String printableStr) {
        this.printingStr = printableStr;    // assign given string value to printing string
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        
        if(job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                return false;
            }
        }
        
        return true;
    }

    public boolean print(String printableStr, PrintFormat format, String printerName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean print(String printableStr, PrintFormat format) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	public boolean showPrintPreview(String printableStr) {
		return false;
	}

}