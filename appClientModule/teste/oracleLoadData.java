package teste;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.xml.crypto.Data;

import pkg.db.ExceptionBusiness;
import pkg.db.UtilityBusiness;

public class oracleLoadData {

	public static void main(String[] args) throws InterruptedException {

		//String sistema = "corporat";	
		//String ambiente = "rachmg";	
		//String senha = "R4CHMG18";	
		String sistema = "roberto_schroeder";	
		String ambiente = "racprod";	
		String senha = "agosto!";	
		
		UtilityBusiness ub = new UtilityBusiness(ambiente, sistema, senha);
			
		try {
			
			//System.out.println(ambiente+".ambiente()");
			//System.out.println(ub.ambiente());
			
			File file = new File("C:\\temp\\UsuariosAD.csv"); 
				  
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String sql= "";  
			String texto = "";
			int count = 0;
			Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
			Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
			int tempo = 0;
			
			String st;
			HashMap<String,String> dados = null;
			String array[] = null;
			//drop table tempAD1;  CREATE TABLE tempAD2 ( data DATE NULL, SamAccountName VARCHAR2(1000) NULL, displayName VARCHAR2(1000) NULL,UserPrincipalName VARCHAR2(1000) NULL )
			//String colunas[] = new String[] {"Description","SamAccountName","UserPrincipalName","Email","Enabled","DistinguishedName" };
			//String colunas[] = new String[] {"SamAccountName","displayName","UserPrincipalName" };
			String colunas[] = new String[] {"DistinguishedName","EmailAddress","Enabled","GivenName","Name","ObjectClass","ObjectGUID","SamAccountName","SID","Surname","UserPrincipalName"};
			while ((st = br.readLine()) != null){
				count++;
				if(count<0) // 0 10002
					continue;
				
				//ub.insertIntoOneLIne(table, dados);
				array = st.split(";");
				dados = new HashMap<String, String>();
				if(array.length == colunas.length ) {
					for(int x=0; x < colunas.length; x++) {
						String valor = (array[x]!=null)?array[x]:"";
						valor = valor.replace("\"", "");
						valor = valor.replace("\'", "#");
						
//						dados.put(colunas[x],utfToIso( (array[x]!=null)?array[x]:"" ) );
						dados.put(colunas[x], valor);
						
					}
					
					//System.out.println(dados);
					//System.out.println(".");
					//System.out.println(st);
					sql=ub.insertIntoOneLIne("tempAD1", dados);
					texto += "\n - "+sql;
					if(count%5000==0) {
						timestamp2 = new Timestamp(System.currentTimeMillis());
						tempo = timeDiff(timestamp1, timestamp2); 
						timestamp1 = new Timestamp(System.currentTimeMillis());
						System.out.println(texto);
						System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(timestamp1.getTime())+"   - tempo "+tempo+"  - total "+count);
						Thread.sleep(1);
						texto="";					
					}
					if(count>50000) // 10000 20000
						break;
					} 	
			}
			
			// 5001 Marcos.Santos81
		} catch (ExceptionBusiness e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ex) { 
			ex.printStackTrace();
	    }
		
		
	}
	
	private static int timeDiff(Timestamp timestamp1, Timestamp timestamp2) {
		long milliseconds = timestamp2.getTime() - timestamp1.getTime();
		return (int) milliseconds / 1000;
	}
	
	public static String utfToIso(String mensagem){
        String retorno = "";
        byte[] iso;
        try {
            iso = new String(mensagem.getBytes(), "UTF-8").getBytes("ISO-8859-1");

            retorno = new String(iso);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        return retorno;
    }
}

