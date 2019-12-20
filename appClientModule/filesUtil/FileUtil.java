package filesUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileUtil 
{
	public static void main(String[] args) throws IOException 
	{
		String fileName = "C:/"+ "temp/"+ "test.txt" ;
	    String content = "Hello";
	    
		writeFile(fileName,content);
	}
	
	public static void  writeFile(String fileName,String content) 
		  throws IOException {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		    writer.write(content);
		    writer.close();
	}
	
	public void writeFile2() {
		Path filePath = Paths.get("C:/", "temp", "test.txt");
	
		try 
		{
			//Write content to file
			Files.write(filePath, "String".getBytes(), StandardOpenOption.APPEND);
			//writeString(filePath, "Hello World !!", StandardOpenOption.APPEND);
	
			//Verify file content
			List<String> content = Files.readAllLines(filePath);
	
			System.out.println(content);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}