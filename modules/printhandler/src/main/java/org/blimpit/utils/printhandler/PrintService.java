package org.blimpit.utils.printhandler;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;


public class PrintService implements PrintHandler {

    @Override
    public boolean print(String printableStr, String printerName) throws PrintServiceException {
        // create a rendering object and generate printing output
        PrintableObject printerableObject = new PrintableObject();
        printerableObject.setPrintString(printableStr);
        
        // create a printer job and set default page configuration
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(printerableObject, printerJob.defaultPage());
        
        // set given printer
        javax.print.PrintService ps = null;
        
        javax.print.PrintService[] printServices = PrinterJob.lookupPrintServices();
        for (javax.print.PrintService printService : printServices) {
            System.out.println(printService.getName());
            if(printService.getName().equals(printerName)) {
                ps = printService;
                break;
            }
        }
        
        try {
            printerJob.setPrintService(ps);
        } catch (PrinterException ex) {
            throw new PrintServiceException(ex.getMessage());
        }
        
        // print with the default printer
        try {
            printerJob.print();
        } catch (PrinterException ex) {
            throw new PrintServiceException(ex.getMessage());
        }
        
        
        return true;
    }

    @Override
    public boolean print(String printableStr) throws PrintServiceException {
        // create a rendering object and generate printing output
        PrintableObject printerableObject = new PrintableObject();
        printerableObject.setPrintString(printableStr);
        
        // create a printer job and set default page configuration
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(printerableObject, printerJob.defaultPage());
        
        // print with the default printer
        try {
            printerJob.print();
        } catch (PrinterException ex) {
            throw new PrintServiceException(ex.getMessage());
        }
        
        
        return true;
    }
    
    public boolean printWithDialog(String printableStr) throws PrintServiceException {
        // create a rendering object and generate printing output
        PrintableObject printerableObject = new PrintableObject();
        printerableObject.setPrintString(printableStr);
        
        // create a printer job and set default page configuration
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(printerableObject, printerJob.defaultPage());
        
        if(printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                throw new PrintServiceException(ex.getMessage());
            }
        }
        
        return true;
    }

    @Override
    public boolean print(String printableStr, PrintFormat format, String printerName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean print(String printableStr, PrintFormat format) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean showPrintPreview(String printableStr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getPrinterServices() {
        javax.print.PrintService[] printServices = PrinterJob.lookupPrintServices();
        List<String> printerList = new ArrayList<>();
        
        for (javax.print.PrintService printService : printServices) {
            printerList.add(printService.getName());
        }
        
        return printerList;
    }
}
