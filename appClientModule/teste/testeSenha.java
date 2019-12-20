package teste;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testeSenha {

	private final static String EXPRESSAO_REGULAR_SENHA_FORTE = 
			"^(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	
	public static void main(String[] args) {
		
		String passwords[] = {
				"2345aB@",
				"23456B@",
				"12345678",
				"1234567a",
				"123456aB",
				"12345aB@",
				"aB@abcde",
				"aa@abcde"
				};
		System.out.println("programa.");
		
		for(String password : passwords) {
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("password: "+password);
			
			Map<String, Object> maps = validaPassword(password);
			System.out.println(maps);

			
		}
	
	}

	
	
	
	public static Map<String, Object> validaPassword(final String password) {
		Map<String, Object> maps = new HashMap<String, Object>();
		Map<String, String> retorno = new HashMap<String, String>();

		retorno.put("valida", "S");
	    retorno.put("msg", "Senha valida");
		maps.put("retorno",retorno);
			
		// validar complexidade
		// deve passar em 3 dos 4 requisitos abaixo
		int passar = 3;
		
		String expressions[][] = 
			{
					{"Caracteres maiúsculos (A-Z)","[A-Z]"},
					{"Caracteres minúsculos (a-z)","[a-z]"},
					{"Dígitos básicos (0-9)","[0-9]"},
					{"Caracteres não-alfabéticos ( !, $, #, %, @, ^, &, +, =)","[!$#%@^&+=]"}
			};
		int pass = 0;
				
		for(String[] expression : expressions) {
			Map<String, String> map = new HashMap<String, String>();
			Pattern p = Pattern.compile(expression[1]);
			Matcher m = p.matcher(password);
			map.put("msg", expression[0]);
		    if ( p.matcher (password).find()) { 
		    	pass++;
		    } else {
		    	map.put("valida", "N");
		    	maps.put(expression[1], map);
		    }
		    				    
		}
				
	    if(pass < passar) {
	    	retorno = (Map<String, String>) maps.get("retorno");
	    	retorno.put("valida", "N");
		    retorno.put("msg", "A senha não satisfaz os requisitos de complexidade, possuindo no mínimo três das quatro categorias" );
		    maps.put("retorno",retorno);
	    }
	    
	    // validar tamanho
	    int minLength = 8;
		
		if (password.length() < minLength) {
			retorno = (Map<String, String>) maps.get("retorno");
	    	retorno.put("valida", "N");
		    retorno.put("msg", "A senha deve possuir no mínimo "+minLength+" caracteres, ");
		    maps.put("retorno",retorno);
		}
	    
		return maps;
	}
	
}


