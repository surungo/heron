package teste;

/**
 * Title:     graduacao
 * Copyright: Copyright (c) 2008
 * Company:   pucrs
 * @author    Sergio Asturizaga e Roberto Schroeder
 * @version   1
 * Created on 1/2/2008
 */



import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

public class AbstractBean implements Serializable {
	public  String getKey() {
		return "0";
	}

	
	public String geraScriptUpdate() {
		
		Field campos[] = this.getClass().getDeclaredFields();
		Field oracle_campo = null;
		
		String vlr_campo_oracle = null;
		AbstractBean vlr_campo = null;
		String vlr_campo_string = null;
		
		
		
		String retorno = "";
		long contador = 0;
		
		for (int i=0;i<campos.length;i++) {
			Field campo = campos[i];
			
			campo.setAccessible(true);
			
			try {
				oracle_campo = this.getClass().getDeclaredField("oracle_" + campo.getName());
				oracle_campo.setAccessible(true);
				
				vlr_campo_oracle = (String) oracle_campo.get(this);
				
				if (campo.getType().getName().indexOf("long") > 0) {
					vlr_campo_string = String.valueOf(campo.getLong(this));
					
					 
				} else
				
				if (campo.getType().getName().indexOf("float") > 0) {
					vlr_campo_string = String.valueOf(campo.getFloat(this));
				} else

				if (campo.getType().getName().indexOf("String") > 0) {
					vlr_campo_string = String.valueOf(campo.get(this));
				} else
				
				if (campo.getType().getName().indexOf("Date") >0) {
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					
					if (campo.get(this) != null)
					  vlr_campo_string = formatter.format(campo.get(this));
					else 
					  vlr_campo_string = null;
				} else {
					vlr_campo = (AbstractBean)campo.get(this);
					if (vlr_campo.getKey() == null || vlr_campo.getKey().equals("") || vlr_campo.getKey().equals("-1") || vlr_campo.getKey().equals("0"))					
						vlr_campo_string = "";
					else
					    vlr_campo_string = String.valueOf(vlr_campo.getKey());
						
				}
				
				vlr_campo_string = vlr_campo_string.replaceAll("'","'||chr(39)||'");
				
				vlr_campo_string = vlr_campo_string == null ? "" : vlr_campo_string;			
				String cabecalho = " PTABDADOS.EXTEND; \n" +
				                " PTABDADOS (" + ++contador + ") := SIU.REG_UPDATE (NULL, NULL); \n " ;
				
				String miolo = " PTABDADOS(" + contador + ").COLUNA  := '" + vlr_campo_oracle + "'; \n ";
		
				if (vlr_campo_string.equals("")) {
					miolo += " PTABDADOS(" + contador + ").CONTEUDO  := NULL; \n\n";
				} else {
					miolo += " PTABDADOS(" + contador + ").CONTEUDO  := '" + vlr_campo_string + "'; \n\n" ;
				}
				
				 
				
				//retorno += retorno.equals("") ? cabecalho + miolo  
			//			                      : ", " +vlr_campo_oracle + " = " + vlr_campo_string;
				retorno += cabecalho + miolo;
				
			} catch (Exception e) {				
				System.out.println(e.getMessage());
			}
			
			
			
			
			
			
		}
		
		return retorno;
		//return retorno.replaceAll("'", "'||CHR(39)||'");
		
		
		
	}	
}

