package teste;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class properties {

	public static String PATH = "path";
	public static String NPASS = "newPass";

	public static String HMG = "hmg";
	public static String ESP = "esp";
	public static String TST = "tst";
	public static String DES = "de";

	public static void main(String[] args) {
		System.out.println("Properties Manager");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		System.out.print("Please enter user name : ");
//		String username = null;
//		try {
//			username = reader.readLine();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("You entered : " + username);

		HashMap<String, HashMap<String, String>> directoryList = new HashMap<String, HashMap<String, String>>();

		HashMap<String, String> item = new HashMap<String, String>();

		//             0 1 2 3 
		int[] setar = {1,0,0,0};
		//             H T D E
		
		//String changeField = "url";
		String changeField = "ambiente";
		//String changeField = "user";
		//String changeField = "passwd";
		
		String ambientes[]= {"HOMOLOGACAO","TESTE","DESENVOVIMENTO","ESPELHO"};
		String basePath = "C:\\Users\\schroeder\\Documents\\deployFiles\\standalone\\";
		String finalPath = "\\configs\\tomcat_secure";
		
		//String ambientes[]= {"tomcat_secure_hmg","tomcat_secure_tst","tomcat_secure_des","tomcat_secure_esp"};
		//String basePath = "C:\\Users\\schroeder\\Documents\\tomcat\\";
		//String finalPath = "";

		
		
		
		if(setar[0]==1) {
			item = new HashMap<String, String>();
			item.put(PATH, basePath+ambientes[0]+finalPath);
			item.put(NPASS, "R4CHMG18");
			directoryList.put(HMG, item);
		}
		if(setar[1]==1) {
			item = new HashMap<String, String>();
			item.put(PATH, basePath+ambientes[1]+finalPath);
			item.put(NPASS, "R4CTST18");
			directoryList.put(TST, item);
		}
		if(setar[2]==1) {
			item = new HashMap<String, String>();
			item.put(PATH, basePath+ambientes[2]+finalPath);
			item.put(NPASS, "R4CD3S18");
			directoryList.put(DES, item);
		}
		if(setar[3]==1) {
			item = new HashMap<String, String>();
			item.put(PATH, basePath+ambientes[3]+finalPath);
			item.put(NPASS, "R4C3SP18");
			directoryList.put(ESP, item);
		}
		
		try {
			listFilesAndFolders(directoryList, changeField);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void listFilesAndFolders(HashMap<String, HashMap<String, String>> directoryList,String changeField) throws IOException {
		int totalFiles = 0;
		String senhaNova = "";
		String directoryName = "";
		boolean gravar = false;
		InputStream is = null;
		

		for (HashMap<String, String> directoryProps : directoryList.values()) {
			System.out.println(" Folder: "+ directoryProps.get(PATH) );
			File directory = new File(directoryProps.get(PATH));
			senhaNova = directoryProps.get(NPASS);

			// get all the files from a directory
			File[] fList = directory.listFiles();
			totalFiles = 0;
			for (File file : fList) {

				is = new FileInputStream(file);
				// System.out.println(file.getAbsolutePath());
				// System.out.println(file.getName());
				String propertiesFile = file.getAbsolutePath();
				Properties properties = new Properties();
				if(file.getName().endsWith(".db.properties")) {
					totalFiles++;
					try {
						properties.load(new FileInputStream(propertiesFile));
						Enumeration enume = properties.propertyNames();
						for (; enume.hasMoreElements();) {
							String indexElement = null;
							try {
								indexElement = enume.nextElement().toString();
							} catch (Exception e) {
								indexElement = null;
								continue;
							}
							if (indexElement != null && indexElement.contains(changeField)) {
								System.out.println(indexElement + "=" + properties.get(indexElement));
								properties.setProperty(indexElement, senhaNova);
							}
						}
						if (gravar) {
							OutputStream out = new FileOutputStream(propertiesFile);
							properties.store(out, "Atualização");
						}
	
					} catch (FileNotFoundException e) {
						continue;
					} catch (IOException e) {
						continue;
					}
				}
			}
			System.out.println("TotalFiles: "+totalFiles);
			for(int x=0;x<80;x++)System.out.print("--------");
			System.out.println("-");
		}
		
	}

}
