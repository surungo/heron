package filesUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class FileCsv
{
	public static void main(String[] args) throws IOException 
	{
		String fileName = "C:\\Users\\schroeder\\Documents\\Apps\\taenduvv.csv" ;
	    String content = readLineByLine(fileName);
	    String[] lines = content.split("\\r?\\n"); // split linhas
	    
	    String equipe = "";
	    HashMap<Integer, String > linhas = new HashMap<Integer, String >();
	    
	    int volta = 0;
	    String auxeq = "";
	    String[] auxls = "".split("");	   
	    
	    String valor = "";
	    
	    
	    for (String line : lines) {
	    	 auxls = "".split("");
	    	 auxeq = "";
	    	 
	    	 if(line.contains("(")) {
	    		 line = line.replace(";", "");
	    		 if(!equipe.equals(line)) {
	    			 volta = 0;
    			 	 equipe=line;
    			 	 valor=equipe;
	    		 }
	    	 }else {
	    		 
	    		 try {
	    			 auxls = line.split(";");
	    			 volta =  Integer.parseInt( auxls[0] );
	    			 valor = auxls[1];
				 }catch (Exception e) {
					 auxls = null; 
					 volta = -1;
					 valor="";
				 }
	    		 
	    	 }	    	 
	    	 
    		 try {
				 auxeq = linhas.get(volta);
				  
			 }catch (Exception e) {
				 auxeq = null; 
			 }
    		 if(auxeq != null) {
    			 linhas.put(volta, auxeq+";"+valor); 
    		 } else {
    			 linhas.put(volta, valor);
    		 }
			 
	    }
	    
	    for(Integer key : linhas.keySet()){
	    	System.out.println(linhas.get(key));
	    }	    
	    
	}
	
	
	public static String readLineByLine(String filePath)
	{
	    StringBuilder contentBuilder = new StringBuilder();
	    try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.ISO_8859_1))
	    {
	        stream.forEach(s -> contentBuilder.append(s).append("\n"));
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    return contentBuilder.toString();
	}
	
	public static void  writeFile(String fileName,String content) 
		  throws IOException {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		    writer.write(content);
		    writer.close();
	}
	
	public List<String> writeFile2() {
		Path filePath = Paths.get("C:/", "temp", "test.txt");
		List<String> content = null;
		try 
		{
			//Write content to file
			Files.write(filePath, "String".getBytes(), StandardOpenOption.APPEND);
			//writeString(filePath, "Hello World !!", StandardOpenOption.APPEND);
	
			//Verify file content
			content = Files.readAllLines(filePath);
	
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return content;
	}
}