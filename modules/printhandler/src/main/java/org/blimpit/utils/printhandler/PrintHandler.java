package org.blimpit.utils.printhandler;

import java.util.List;


/**
 * An interface which describes print handling APIs.
 */
public interface PrintHandler {

    /**
     * Print the given string using given printer
     *
     * @param printableStr Formatted String to be printed
     * @param printerName  Name of the printer
     * @return Indicate the success of the printer job
     * @throws org.blimpit.utils.printhandler.PrintServiceException
     */
    boolean print(String printableStr, String printerName) throws PrintServiceException;

    /**
     * Print using first available printer
     *
     * @param printableStr Formatted String to be printed
     * @return Indicate the success of the printer job
     * @throws org.blimpit.utils.printhandler.PrintServiceException
     */
    boolean print(String printableStr) throws PrintServiceException;

    /**
     * Can provide the customized page formats for the printer
     *
     * @param printableStr Formatted String to be printed
     * @param format       customized parameters of the document to be printed
     * @param printerName  Name of the printer
     * @return Indicate the success of the printer job
     * @throws org.blimpit.utils.printhandler.PrintServiceException
     */
    boolean print(String printableStr, PrintFormat format, String printerName) throws PrintServiceException;

    /**
     * Can provide the customized page format for the printer
     *
     * @param printableStr Formatted String to be printed
     * @param format       customized parameters of the document to be printed
     * @return Indicate the success of the printer job
     * @throws org.blimpit.utils.printhandler.PrintServiceException
     */
    boolean print(String printableStr, PrintFormat format) throws PrintServiceException;

    /**
     * open up the document viewer and show print view
     *
     * @param printableStr
     * @return Indicate the success of the printer job
     */
    boolean showPrintPreview(String printableStr);
    
    /**
     * Can provide support to find currently available printer names
     * 
     * @return list of currently available printer name list
     */
    List<String> getPrinterServices();

}
