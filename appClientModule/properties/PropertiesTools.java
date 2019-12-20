package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTools extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PropertiesTools() {

	}

	public String getProperty(String sistema, String tag) {
		String result = "";
		String barra = (System.getProperty("os.name").indexOf("Win") >= 0) ? "\\" : "/";
		String catalinaHome = System.getProperty("db.home");

		try {
			String dbPropertiesPath = catalinaHome + barra + sistema + ".db.properties";
			FileInputStream file = new FileInputStream(dbPropertiesPath);
			this.load(file);
			result = this.getProperty(tag);

		} catch (FileNotFoundException e) {
			erro(tag+" "+e.getMessage());
		} catch (IOException e) {
			erro(tag+" "+e.getMessage());
		} 

		return result;
	}
	
	private void erro(String msg) {
		System.out.println("PropertiesTools "+msg);
	}

}

/*
 * 
 * String barra = Utility.getBarraSistemaOperacional(); Properties properties =
 * new Properties(); String ambiente = ""; Properties sisdocProperties = new
 * Properties(); try {
 * sisdocProperties.load(ListControlUtil.class.getClassLoader().
 * getResourceAsStream("br/pucrs/sisdoc/conf/sisdoc.properties"));
 * Utility.SISDOC_CABECALHO = sisdocProperties.getProperty("cabecalho");
 * Utility.SISDOC_RODAPE = sisdocProperties.getProperty("rodape"); }
 * catch(FileNotFoundException filenotfoundexception) { } catch(IOException e1)
 * { e1.printStackTrace(); } if(System.getProperty("graduacao.path") == null ||
 * System.getProperty("graduacao.conf.log.file") == null) { String path =
 * getServletContext().getRealPath(""); System.setProperty("graduacao.path",
 * path); String pathLog = (new
 * StringBuilder(String.valueOf(path))).append(barra).append("WEB-INF").append(
 * barra).append("conf").append(barra).append("log.properties").toString();
 * PropertyConfigurator.configure(pathLog); String catalinaHome =
 * System.getProperty("catalina.home"); String dbPropertiesPath = (new
 * StringBuilder(String.valueOf(catalinaHome))).append(barra).append("conf").
 * append(barra).append("sigra.db.properties").toString(); try {
 * properties.load(new FileInputStream(dbPropertiesPath)); ambiente =
 * properties.getProperty("graduacao.db.ambiente"); Utility.AMBIENTE =
 * ambiente.toUpperCase(); } catch(FileNotFoundException filenotfoundexception1)
 * { } catch(IOException ioexception) { } }
 */