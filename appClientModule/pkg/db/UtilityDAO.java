package pkg.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import teste.AbstractBean;

public class UtilityDAO {

	private static final long serialVersionUID = 1L;
	protected Connection conn;

	public UtilityDAO(Connection conn) {
		this.conn = conn;
	}

	public String sendEmail(String from, String to, String title, String body) throws ExceptionDAO {
		PreparedStatement pstm = null;
		ResultSet rsQuery = null;
		String messages = "";
		String retorno = null;
		try {
			String sql = "";
			sql = " DECLARE                                                    "
					+ "    vSender       varchar2(100) := ? ;  					"
					+ "    vRecipient varchar2(100) := ? ;     					"
					+ "    vTitulo       varchar2(200) := ? ;         	        "
					+ "    vBode         varchar2(4000):= ? ;     	            "
					+ "    vErrorMessage varchar2(4000);                           "
					+ "    vRetorno      number;                                   " +

					" begin                                                      "
					+ "   vRetorno := sendmailjpkg.sendmail (                      "
					+ "         SMTPServerName=>'smart.pucrs.br',                  "
					+ "         Sender=>vSender,                                   "
					+ "         Recipient=>vRecipient,                             "
					+ "         Subject=>vTitulo,                                  "
					+ "         Body=>vBode,                                       "
					+ "         ErrorMessage=>vErrorMessage,                       "
					+ "         Attachments=>null);                                "
					+ "                                                            "
					+ " END;			                                             ";

			pstm = conn.prepareCall(sql);
			pstm.clearParameters();

			pstm.setString(1, from);
			pstm.setString(2, to);
			pstm.setString(3, title);
			pstm.setString(4, body);

			pstm.execute();
			System.out.println(messages);
			retorno = messages;
		} catch (SQLException e) {
			throw new ExceptionDAO(e.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ExceptionDAO(ex.getMessage());
		} finally {
			if (rsQuery != null) {
				try {
					rsQuery.close();
				} catch (SQLException e1) {
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e1) {
				}
			}

		}
		return retorno;
	}

	public Date oracleSYSDATE() throws ExceptionDAO {
		CallableStatement cstm = null;
		PreparedStatement pstm = null;
		ResultSet rsQuery = null;
		Date sysdate = null;
		Date retorno = null;
		try {
			String sql = "";
			sql = "select sysdate from dual";
			pstm = conn.prepareCall(sql);
			rsQuery = pstm.executeQuery();
			rsQuery.next();
			sysdate = rsQuery.getTimestamp("sysdate");
			retorno = sysdate;
			// Utility.getStringToDate(sysdate, "dd/MM/yyyy HH:mm:ss");
		} catch (SQLException e) {
			throw new ExceptionDAO(e.getMessage());
		}  catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ExceptionDAO(ex.getMessage());
		} finally {
			if (rsQuery != null) {
				try {
					rsQuery.close();
				} catch (SQLException e1) {
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e1) {
				}
			}
			if (cstm != null) {
				try {
					cstm.close();
				} catch (SQLException e1) {
				}
			}

		}
		return retorno;
	}

	
	public String v_instance() throws ExceptionDAO {
		CallableStatement cstm = null;
		PreparedStatement pstm = null;
		ResultSet rsQuery = null;
		String retorno = "";
		try {
			String sql = "";
			sql = "select instance_name,host_name,startup_time from V$INSTANCE";
			pstm = conn.prepareCall(sql);
			rsQuery = pstm.executeQuery();
			rsQuery.next();
			retorno = "instance_name: "+rsQuery.getString("instance_name");
			retorno += ", host_name: "+rsQuery.getString("host_name");
			retorno += ", startup_time: "+rsQuery.getString("startup_time");
			
		} catch (SQLException e) {
			throw new ExceptionDAO(e.getMessage());
		}  catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ExceptionDAO(ex.getMessage());
		} finally {
			if (rsQuery != null) {
				try {
					rsQuery.close();
				} catch (SQLException e1) {
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e1) {
				}
			}
			if (cstm != null) {
				try {
					cstm.close();
				} catch (SQLException e1) {
				}
			}

		}
		return retorno;
	}
	
	public String ambiente() throws ExceptionDAO {
		CallableStatement cstm = null;
		PreparedStatement pstm = null;
		ResultSet rsQuery = null;
		String retorno = "";
		try {
			String sql = "";
			sql = "SELECT GENERICO.FUN_GEN_VERIFICA_AMBIENTE ambiente FROM DUAL";
			pstm = conn.prepareCall(sql);
			rsQuery = pstm.executeQuery();
			rsQuery.next();
			retorno = rsQuery.getString("ambiente");
						
		} catch (SQLException e) {
			throw new ExceptionDAO(e.getMessage());
		}  catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ExceptionDAO(ex.getMessage());
		} finally {
			if (rsQuery != null) {
				try {
					rsQuery.close();
				} catch (SQLException e1) {
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e1) {
				}
			}
			if (cstm != null) {
				try {
					cstm.close();
				} catch (SQLException e1) {
				}
			}

		}
		return retorno;
	}
	
	public void updateSenha(String operador) throws ExceptionDAO {
		CallableStatement cstm = null;
		PreparedStatement pstm = null;
		ResultSet rsQuery = null;
		
		try {
			String sql = "";
			sql = "update corporat.SSOPER set\r\n" + 
					"TXSENHA = 'vopu/vopu/vopu,vopu+',\r\n" + 
					"dtexpiracao = to_date('21/12/2050 11:35:50','DD/MM/YYYY HH24:MI:SS'),\r\n" + 
					"STOPERADOR = 'L',\r\n" + 
					"NRTENTATIVASENHA = '0',\r\n" + 
					"DTLIBBLOQUEIO = null\r\n" + 
					"where nuoperador in (?) ";
			pstm = conn.prepareCall(sql);
			pstm.clearParameters();
			pstm.setString(1, operador);
			rsQuery = pstm.executeQuery();
			rsQuery.next();
			
						
		} catch (SQLException e) {
			throw new ExceptionDAO(e.getMessage());
		}  catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ExceptionDAO(ex.getMessage());
		} finally {
			if (rsQuery != null) {
				try {
					rsQuery.close();
				} catch (SQLException e1) {
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e1) {
				}
			}
			if (cstm != null) {
				try {
					cstm.close();
				} catch (SQLException e1) {
				}
			}

		}
		
	}


	public HashMap<String, String> oracleSYSDATEYear(String dia) throws ExceptionDAO {
		HashMap<String, String> results = new HashMap<String, String>();
		CallableStatement cstm = null;
		PreparedStatement pstm = null;
		ResultSet rsQuery = null;
		Date sysdate = null;
		Date retorno = null;

		Calendar c = new GregorianCalendar(Integer.parseInt(dia.substring(0, 1)),
				Integer.parseInt(dia.substring(3, 4)) - 1, Integer.parseInt(dia.substring(6, 9)));

		try {
			String sql = " select (to_date( ? ,'dd/mm/yyyy')) data  " + "  from dual              "
					+ "  union                  " + "  select trunc(add_months(last_day( ? ),-1))  "
					+ "  from dual                                       "
					+ "  union                                           "
					+ "  select trunc(add_months(last_day( ? ),-2))  "
					+ "  from dual                                       "
					+ "  union                                           "
					+ "  select trunc(add_months(last_day( ? ),-3))  "
					+ "  from dual                                       "
					+ "  union                                           "
					+ "  select trunc(add_months(last_day( ? ),-4))  "
					+ "  from dual                                       "
					+ "  union                                           "
					+ "  select trunc(add_months(last_day( ? ),-5))  "
					+ "  from dual                                       "
					+ "  union                                           "
					+ "  select trunc(add_months(last_day( ? ),-6))  "
					+ "  from dual                                       "
					+ "  union                                           "
					+ "  select trunc(add_months(last_day( ? ),-7))  "
					+ "  from dual                                       "
					+ "  union                                           "
					+ "  select trunc(add_months(last_day( ? ),-8))  "
					+ "  from dual                                       "
					+ "  union                                           "
					+ "  select trunc(add_months(last_day( ? ),-9))  "
					+ "  from dual                                       "
					+ "  union                                           "
					+ "  select trunc(add_months(last_day( ? ),-10)) "
					+ "  from dual                                       "
					+ "  union                                           "
					+ "  select trunc(add_months(last_day( ? ),-11)) "
					+ "  from dual                                       ";

			pstm = conn.prepareStatement(sql);

			pstm.setDate(1, new java.sql.Date(c.getTimeInMillis()));
			c.add(Calendar.MONTH, -1);
			pstm.setDate(2, new java.sql.Date(c.getTimeInMillis()));
			c.add(Calendar.MONTH, -1);
			pstm.setDate(3, new java.sql.Date(c.getTimeInMillis()));
			c.add(Calendar.MONTH, -1);
			pstm.setDate(4, new java.sql.Date(c.getTimeInMillis()));
			c.add(Calendar.MONTH, -1);
			pstm.setDate(5, new java.sql.Date(c.getTimeInMillis()));
			c.add(Calendar.MONTH, -1);
			pstm.setDate(6, new java.sql.Date(c.getTimeInMillis()));
			c.add(Calendar.MONTH, -1);
			pstm.setDate(7, new java.sql.Date(c.getTimeInMillis()));
			c.add(Calendar.MONTH, -1);
			pstm.setDate(8, new java.sql.Date(c.getTimeInMillis()));
			c.add(Calendar.MONTH, -1);
			pstm.setDate(9, new java.sql.Date(c.getTimeInMillis()));
			c.add(Calendar.MONTH, -1);
			pstm.setDate(10, new java.sql.Date(c.getTimeInMillis()));
			c.add(Calendar.MONTH, -1);
			pstm.setDate(11, new java.sql.Date(c.getTimeInMillis()));
			c.add(Calendar.MONTH, -1);
			pstm.setDate(12, new java.sql.Date(c.getTimeInMillis()));

			rsQuery = pstm.executeQuery();
			rsQuery.next();

			while (rsQuery.next()) {
				results.put(rsQuery.getString("data"), "");
			}

		} catch (SQLException e) {
			throw new ExceptionDAO(e.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ExceptionDAO(ex.getMessage());
		} finally {
			if (rsQuery != null) {
				try {
					rsQuery.close();
				} catch (SQLException e1) {
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e1) {
				}
			}
			if (cstm != null) {
				try {
					cstm.close();
				} catch (SQLException e1) {
				}
			}

		}
		return results;
	}

	public String oracleDVMatricula(String matricula) throws ExceptionDAO {
		CallableStatement cstm = null;
		DbmsOutput dbmsOutput = null;
		PreparedStatement pstm = null;
		ResultSet rsQuery = null;
		String messages = "";
		try {
			String sql = "";
			dbmsOutput = new DbmsOutput(conn);
			dbmsOutput.enable(1000000);
			sql = "begin Dbms_Output.PUT_LINE(trim(dvmatricula('" + matricula + "'))); end;";
			pstm = conn.prepareCall(sql);
			pstm.execute();
			messages = dbmsOutput.getMessages();
			//System.out.println(messages);

			dbmsOutput.close();
		} catch (SQLException e) {
			throw new ExceptionDAO(e.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ExceptionDAO(ex.getMessage());
		} finally {
			if (rsQuery != null) {
				try {
					rsQuery.close();
				} catch (SQLException e1) {
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e1) {
				}
			}
			if (cstm != null) {
				try {
					cstm.close();
				} catch (SQLException e1) {
				}
			}

		}
		return messages.trim();
	}

	public void deleteTentativas(String operador) throws ExceptionDAO {
		CallableStatement cstm = null;
		PreparedStatement pstm = null;
		ResultSet rsQuery = null;
		
		try {
			String sql = "";
			sql = "delete corporat.sssenha where nuoperador = (?)";
			pstm = conn.prepareCall(sql);
			pstm.clearParameters();
			pstm.setString(1, operador);
			rsQuery = pstm.executeQuery();
			rsQuery.next();
			
						
		} catch (SQLException e) {
			throw new ExceptionDAO(e.getMessage());
		}  catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ExceptionDAO(ex.getMessage());
		} finally {
			if (rsQuery != null) {
				try {
					rsQuery.close();
				} catch (SQLException e1) {
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e1) {
				}
			}
			if (cstm != null) {
				try {
					cstm.close();
				} catch (SQLException e1) {
				}
			}

		}
		
	}

	public String findTentativas(String operador) throws ExceptionDAO {
		CallableStatement cstm = null;
		PreparedStatement pstm = null;
		ResultSet rsQuery = null;
		String retorno = "";
		try {
			String sql = "";
			sql = "SELECT nuoperador from corporat.sssenha where nuoperador = (?)";
			pstm = conn.prepareCall(sql);
			pstm.clearParameters();
			pstm.setString(1, operador);
			rsQuery = pstm.executeQuery();
			if(rsQuery.next())
				retorno = rsQuery.getString("nuoperador");
						
		} catch (SQLException e) {
			throw new ExceptionDAO(e.getMessage());
		}  catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ExceptionDAO(ex.getMessage());
		} finally {
			if (rsQuery != null) {
				try {
					rsQuery.close();
				} catch (SQLException e1) {
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e1) {
				}
			}
			if (cstm != null) {
				try {
					cstm.close();
				} catch (SQLException e1) {
				}
			}

		}
		return retorno;
		
	}
	
	public String insertIntoOneLIne(String table, HashMap<String,String> dados) throws ExceptionDAO {
		CallableStatement cstm = null;
		PreparedStatement pstm = null;
		ResultSet rsQuery = null;
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
			
			pstm = conn.prepareCall(sql);
			
			rsQuery = pstm.executeQuery();
			rsQuery.next();
			
						
		} catch (SQLException e) {
			throw new ExceptionDAO(e.getMessage());
		}  catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ExceptionDAO(ex.getMessage());
		} finally {
			if (rsQuery != null) {
				try {
					rsQuery.close();
				} catch (SQLException e1) {
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e1) {
				}
			}
			if (cstm != null) {
				try {
					cstm.close();
				} catch (SQLException e1) {
				}
			}

		}
		return sql;
		
	}
	
	public String insertIntoPlusLines(String sql) throws ExceptionDAO {
		CallableStatement cstm = null;
		PreparedStatement pstm = null;
		ResultSet rsQuery = null;
		
		try {
			
			String colunas = "";
			String valores = "";
			
			pstm = conn.prepareCall(sql);
			
			rsQuery = pstm.executeQuery();
			rsQuery.next();
			
						
		} catch (SQLException e) {
			throw new ExceptionDAO(e.getMessage());
		}  catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new ExceptionDAO(ex.getMessage());
		} finally {
			if (rsQuery != null) {
				try {
					rsQuery.close();
				} catch (SQLException e1) {
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e1) {
				}
			}
			if (cstm != null) {
				try {
					cstm.close();
				} catch (SQLException e1) {
				}
			}

		}
		return sql;
		
	}
	
}
