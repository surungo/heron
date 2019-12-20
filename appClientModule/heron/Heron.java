package heron;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

public class Heron {

	private static HashMap<String,String> listFiles(String path, HashMap<String,String> dados)
    {
        File folder = new File(path);
 
		File[] files = folder.listFiles();
		
		String servTX = "";
		String mappTX = "";		
		int totalServlets = 0;
		int totalNoServlets = 0;
				 
		
        for (File file : files)
        {
            if (file.isFile())
            {
            	String classe = path+"\\"+file.getName();
            	if(file.getName().contains(".java") ){
            		if( isServlet(classe) ){
	            		classe = classe.replace(path+"\\", "");
	            		classe = classe.replace(".java", "");
	            		classe = classe.replace("\\", ".");
	            		
	            		servTX += "\t\n";
	            		servTX += "\t<servlet>\n";
	            		servTX += "\t\t<servlet-name>"+classe+"</servlet-name>\n";
	            		servTX += "\t\t<servlet-class>"+classe+"</servlet-class>\n";
	            		servTX += "\t</servlet>\n";
	            		
	            		mappTX += "\t\n";
	            		mappTX += "\t<servlet-mapping>\n";
	            		mappTX += "\t\t<servlet-name>"+classe+"</servlet-name>\n";
	            		mappTX += "\t\t<url-pattern>/servlet/"+classe+"</url-pattern>\n";
	            		mappTX += "\t</servlet-mapping>\n";
	            		totalServlets++;
	
	            	//System.out.println(file.getName());
	            	}else{
	            		totalNoServlets++;
	            	}
	            }
            	
            }
            else if (file.isDirectory())
            {
				dados = listFiles(file.getAbsolutePath(), dados);
            }
		}
		
		
        dados.put("servTX",dados.get("servTX")+ servTX + "\t\n");
		dados.put("mappTX",dados.get("mappTX")+ mappTX + "\t\n");
		dados.put("totalServlets",  String.valueOf( totalServlets   +Integer.parseInt(dados.get("totalServlets"  ))));
		dados.put("totalNoServlets",String.valueOf( totalNoServlets +Integer.parseInt(dados.get("totalNoServlets"))));
		
		return dados;
    	
    }
	
	public static boolean isServlet(String filePath) {
		boolean retorno = false;
		String conteudo = readLineByLine(filePath);
		if(conteudo.contains("import javax.servlet") 
				|| conteudo.contains("ServletException")
				|| conteudo.contains("ServletConfig")
					) {
			 retorno = true;
		}
		
		return retorno;
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
  
	
    public static void gerar(String origemPath,String destinoPath) throws IOException
    {
		
		
		/*
		String top = "<!DOCTYPE web-app PUBLIC \"-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN\" \"http://java.sun.com/dtd/web-app_2_3.dtd\" >\n";
		top += "<web-app>\n";
		top += "\t<display-name>Web Application</display-name>\n";
		String botton = "</web-app>\n";
		*/
		
		String top = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		top += "<web-app xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\" version=\"2.5\">";
		top += "\t<display-name>portalalunopos</display-name>\n";
		String botton = "</web-app>\n";
		
		HashMap<String,String> dados = new HashMap<String,String>();
		dados.put("servTX"," ");
		dados.put("mappTX"," ");
		dados.put("totalServlets","0");
		dados.put("totalNoServlets","0");
		
		
		dados = listFiles(origemPath,dados);
		
		System.out.println( dados.get("servTX") );
    	
    	System.out.println( dados.get("mappTX") );
		    	
	    String content = top+dados.get("servTX")+dados.get("mappTX")+botton;
	    
		writeFile(destinoPath,content);
		
		System.out.println( "totalServlets: "+ dados.get("totalServlets"));
		System.out.println( "totalNoServlets: "+ dados.get("totalNoServlets"));
		System.out.println( "FOI" );
		
    }
    
    public static String getOrigem() {
    	return "C:\\workspace\\projeto\\src\\main\\java";
    	//return "C:\\Users\\schroeder\\Documents\\workspace\\eclipse201906\\portalalunopos\\src\\main\\java";
    }
    
    public static String getDestino() {
    	return "C:\\temp\\web.xml";
    }
    
}
