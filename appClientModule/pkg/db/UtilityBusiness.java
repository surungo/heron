package pkg.db;

import java.sql.Connection;
import java.util.HashMap;

public class UtilityBusiness {

	private String sistema = "";
	private String ambiente = "";
	private String senha = "";
	
	public UtilityBusiness(String ambiente, String sistema) {
		this.sistema = sistema;
		this.ambiente = ambiente;
		
	}
	
	public UtilityBusiness(String ambiente, String sistema, String senha) {
		this.sistema = sistema;
		this.ambiente = ambiente;
		this.senha = senha;
		
	}
		
	
	public String sendEmail(String from, String to, String title, String body) {
		String retorno = "";
		Connection conn = null;
		UtilityDAO utilityDAO = null;
		try {
			conn = DataBase.getDBConnection(this.ambiente, this.sistema, this.senha);
			
			utilityDAO = new UtilityDAO(conn);
			retorno = utilityDAO.sendEmail( from,  to,  title,  body);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}		
	
	
	public java.util.Date oracleSYSDATE() throws ExceptionBusiness {
		java.util.Date datasistema = null;
		Connection conn = null;
		UtilityDAO utilityDAO = null;
		try {
			conn = DataBase.getDBConnection(this.ambiente, this.sistema, this.senha);
			
			utilityDAO = new UtilityDAO(conn);
			datasistema = utilityDAO.oracleSYSDATE();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return datasistema;
	}	
	
	public String ambiente() throws ExceptionBusiness {
		String retorno = "";
		Connection conn = null;
		UtilityDAO utilityDAO = null;
		try {
			conn = DataBase.getDBConnection(this.ambiente, this.sistema, this.senha);
			
			utilityDAO = new UtilityDAO(conn);
			retorno = utilityDAO.ambiente();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public String resetSenha(String operador) throws ExceptionBusiness {
		String retorno = "";
		Connection conn = null;
		UtilityDAO utilityDAO = null;
		try {
			conn = DataBase.getDBConnection(this.ambiente, this.sistema, this.senha);
			
			utilityDAO = new UtilityDAO(conn);
			utilityDAO.updateSenha(operador);
			if( !("".equals(utilityDAO.findTentativas(operador) ) ) )
					utilityDAO.deleteTentativas(operador);
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public String insertIntoOneLIne(String table, HashMap<String,String> dados) throws ExceptionBusiness {
		String retorno = "";
		Connection conn = null;
		UtilityDAO utilityDAO = null;
		try {
			conn = DataBase.getDBConnection(this.ambiente, this.sistema, this.senha);
			
			utilityDAO = new UtilityDAO(conn);
			retorno = utilityDAO.insertIntoOneLIne( table, dados);
			conn.commit();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	
	/*public String insertIntoPlusLines(String table, HashMap<String,String> dados) throws ExceptionBusiness {
		String retorno = "";
		Connection conn = null;
		UtilityDAO utilityDAO = null;
		try {
			conn = DataBase.getDBConnection(this.ambiente, this.sistema, this.senha);
			
			utilityDAO = new UtilityDAO(conn);
			
			String sql = "";
			
			try {
				
				String colunas = "";
				String valores = "";
				for (HashMap.Entry me : dados.entrySet()) {
					colunas += (String) me.getKey()+",";
					valores += "'"+(String) me.getValue()+"',";			
				}
				
				if (colunas.length() > 0) {
					colunas = colunas.substring (0, colunas.length() - 1);
				}
				if (valores.length() > 0) {
					valores = valores.substring (0, valores.length() - 1);
				}
				
				sql = "insert into  "+table+" (data,"+colunas+") values (sysdate,"+valores+")";
			
			retorno = utilityDAO.insertIntoPlusLines(sql);
			conn.commit();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}*/
	
	
	
	
	public String v_instance() throws ExceptionBusiness {
		String retorno = "";
		Connection conn = null;
		UtilityDAO utilityDAO = null;
		try {
			conn = DataBase.getDBConnection(this.ambiente, this.sistema, this.senha);
			
			utilityDAO = new UtilityDAO(conn);
			retorno = utilityDAO.v_instance();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public String oracleDVMatricula(String matricula) throws ExceptionBusiness {
		String mensages = "";
		Connection conn = null;
		UtilityDAO utilityDAO = null;
		try {
			conn = DataBase.getDBConnection(this.ambiente, this.sistema, this.senha);
			
			utilityDAO = new UtilityDAO(conn);
			mensages = utilityDAO.oracleDVMatricula(matricula);
	

		}  catch (Exception e) {
			e.printStackTrace();
		}
		return mensages;
	}
}

