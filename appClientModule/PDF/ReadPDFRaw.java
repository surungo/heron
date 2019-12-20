package PDF;

import java.io.IOException;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class ReadPDFRaw {
	
	public static void main(String args[]) {
	
	    String parsedText;
	    String fileName = "C:\\temp\\enduvv.pdf";
	    
	    
	    try {

	        PdfReader reader = new PdfReader(fileName);
	        System.out.println("This PDF has "+reader.getNumberOfPages()+" pages.");
	        String page = PdfTextExtractor.getTextFromPage(reader, 1);
	        System.out.println("Page Content:\n\n"+page+"\n\n");
	        System.out.println("Is this document tampered: "+reader.isTampered());
	        System.out.println("Is this document encrypted: "+reader.isEncrypted());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	}
}