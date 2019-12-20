package pkg.db;

import java.io.*;
import java.net.InetAddress;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public abstract class DataBase
{

    private Connection conn;
    private Hashtable<String, String> statements;
    private Hashtable<String, PreparedStatement> statementsPrepareds;
    private String identificacao;
    private Date requisicao;
    private Date devolucao;
    private String instance;
    private String operador;
    private String ambiente;
    private final String stmtInstance = "stmtInstance";

    protected abstract Connection Connect()
        throws SQLException;

    protected abstract void initStatements()
        throws SQLException;

    public DataBase()
        throws SQLException
    {
        statements = new Hashtable<String, String>();
        statementsPrepareds = new Hashtable<String, PreparedStatement>();
        conn = Connect();
        initDbInstance();
        initStatements();
    }

    public Connection getConnection()
    {
        return conn;
    }

    private void initDbInstance()
        throws SQLException
    {
        statements.put("stmtInstance", "select host_name from v$instance");
        PreparedStatement stins = getStatement("stmtInstance");
        ResultSet rsins = stins.executeQuery();
        if(rsins.next())
        {
            instance = rsins.getString(1);
        }
    }

    public String getDbInstance()
    {
        return instance;
    }

    public Hashtable<String, String> getStatements()
    {
        return (Hashtable<String, String>)statements.clone();
    }

    public Hashtable<String, PreparedStatement> getStatementsPrepareds()
    {
        return (Hashtable<String, PreparedStatement>)statementsPrepareds.clone();
    }

    public void close()
        throws SQLException
    {
        statements.clear();
        conn.close();
    }

    public void finalize()
        throws SQLException
    {
        statements.clear();
        conn.close();
    }

    protected void AddStatement(String idSt, String sql)
        throws SQLException
    {
        statements.put(idSt, sql);
    }

    public PreparedStatement getStatement(String idSt)
    {
        PreparedStatement sttemp=null;
        try {
	        if(statementsPrepareds.containsKey(idSt))
	        {
	            sttemp = statementsPrepareds.get(idSt);
	        } else
	        {
	            String sql = statements.get(idSt);
	            statementsPrepareds.put(idSt, conn.prepareStatement(sql));
	           
					sttemp = conn.prepareStatement(sql);
				
	        }
	        statementsPrepareds.put(idSt, sttemp);
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return sttemp;
  
    }

    public String getDataRequisicao()
    {
        if(requisicao != null)
        {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");
            String data = formatter.format(requisicao);
            return data;
        } else
        {
            return "";
        }
    }

    public String getDataDevolucao()
    {
        if(devolucao != null)
        {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");
            String data = formatter.format(devolucao);
            return (new StringBuilder("Data:")).append(data).toString();
        } else
        {
            return "";
        }
    }

    public String getIdentificacao()
    {
        return identificacao;
    }

    public void setIdentificacao(String identificacao)
    {
        this.identificacao = identificacao;
    }

    public void setRequisicao(Date dataRequisicao, String identificacao)
    {
        requisicao = dataRequisicao;
        this.identificacao = identificacao;
        devolucao = null;
    }

    public void setDataDevolucao(Date devolucao)
    {
        this.devolucao = devolucao;
    }

    private void closeStatement()
    {
    }

    public String getConnProducao(String servico, String user, String senha)
    {
        return (new StringBuilder("jdbc:oracle:thin:")).append(user).append("/").append(senha).append("@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = racprod1.pucrs.br)(PORT = 152" +
"1)) (ADDRESS = (PROTOCOL = TCP)(HOST = racprod2.pucrs.br)(PORT = 1521)) (ADDRESS" +
" = (PROTOCOL = TCP)(HOST = racprod3.pucrs.br)(PORT = 1521)) (LOAD_BALANCE = yes)" +
" (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = SRVCO_GERAL)))"
).toString();
    }

    public String getConnDesenv(String servico, String user, String senha)
    {
        return (new StringBuilder("jdbc:oracle:thin:")).append(user).append("/").append(senha).append("@sagitarius:1521:dbdesenv").toString();
    }

    protected void setOperador(String operador)
        throws SQLException
    {
        this.operador = operador;
        CallableStatement psContext = conn.prepareCall("call  PRC_CONTEXTO(?)");
        psContext.clearParameters();
        psContext.setString(1, operador);
        psContext.execute();
        psContext.close();
    }

    protected void limpaOperador()
    {
        if(operador != null && !operador.equals(""))
        {
            try
            {
                CallableStatement psContextClear = conn.prepareCall("call  P_CONTEXTO_clear('contexto_usuario')");
                psContextClear.execute();
                psContextClear.close();
                operador = "";
            }
            catch(SQLException e)
            {
                try
                {
                    conn.close();
                }
                catch(SQLException sqlexception) { }
            }
        }
    }

    protected Connection conectaBanco(Properties props, String sistema)
        throws SQLException, Exception
    {
        Connection conn = null;
        String barra = File.separator;
        String catalinaHome = System.getProperty("db.home");
        String driver = "";
        try
        {
            String dbPropertiesPath = (new StringBuilder(String.valueOf(catalinaHome))).append(barra).append(sistema).append(".db.properties").toString();
            FileInputStream file = new FileInputStream(dbPropertiesPath);
            props.load(file);
            driver = props.getProperty((new StringBuilder(String.valueOf(sistema))).append(".db.driver").toString());
            ambiente = props.getProperty((new StringBuilder(String.valueOf(sistema))).append(".db.ambiente").toString());
            props.put("v$session.program", sistema);
            props.put("v$session.machine", InetAddress.getLocalHost().getCanonicalHostName());
            props.put("v$session.computer", InetAddress.getLocalHost().getCanonicalHostName());
            props.put("v$session.terminal", InetAddress.getLocalHost().getCanonicalHostName());
            props.put("v$session.osuser", System.getProperty("user.name").toString());
            Class.forName(driver);
            conn = DriverManager.getConnection(props.getProperty((new StringBuilder(String.valueOf(sistema))).append(".db.url").toString()), props.getProperty((new StringBuilder(String.valueOf(sistema))).append(".db.user").toString()), props.getProperty((new StringBuilder(String.valueOf(sistema))).append(".db.passwd").toString()));
            props.setProperty((new StringBuilder(String.valueOf(sistema))).append(".db.passwd").toString(), "");
        }
        catch(FileNotFoundException e)
        {
            throw new Exception("FileNotFoundException", e);
        }
        catch(IOException e)
        {
            throw new Exception("IOException", e);
        }
        catch(ClassNotFoundException e)
        {
            throw new Exception("ClassNotFoundException", e);
        }
        return conn;
    }

    public String getAmbiente()
        throws SQLException, Exception
    {
        return ambiente;
    }
    
	public static Connection getDBConnection(String ambiente, String sistema, String senha) {
		Connection conn = null;
        String driver = "";
        ambiente = ambiente.toUpperCase();
        try
        {
            driver = "oracle.jdbc.driver.OracleDriver";
            HashMap<String, String> urls = new HashMap<String, String>();
            urls.put("RACDES",  "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST = (LOAD_BALANCE=on)(FAILOVER=ON) (ADDRESS = (PROTOCOL = TCP)(HOST = racdes1.pucrs.br)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = racdes2.pucrs.br)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = racdes3.pucrs.br)(PORT = 1521))) (CONNECT_DATA = (SERVICE_NAME = SRVCO_RACDES)))");
            urls.put("RACTST",  "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST = (LOAD_BALANCE=on)(FAILOVER=ON) (ADDRESS = (PROTOCOL = TCP)(HOST = ractst1.pucrs.br)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ractst2.pucrs.br)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = ractst3.pucrs.br)(PORT = 1521))) (CONNECT_DATA = (SERVICE_NAME = SRVCO_RACTST)))");
            urls.put("RACHMG",  "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST = (LOAD_BALANCE=on)(FAILOVER=ON) (ADDRESS = (PROTOCOL = TCP)(HOST = rachmg1.pucrs.br)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = rachmg2.pucrs.br)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = rachmg3.pucrs.br)(PORT = 1521))) (CONNECT_DATA = (SERVICE_NAME = SRVCO_RACHMG)))");
            urls.put("RACESP",  "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST = (LOAD_BALANCE=on)(FAILOVER=ON) (ADDRESS = (PROTOCOL = TCP)(HOST = racesp1.pucrs.br)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = racesp2.pucrs.br)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = racesp3.pucrs.br)(PORT = 1521))) (CONNECT_DATA = (SERVICE_NAME = SRVCO_RACESP)))");
            urls.put("RACPROD", "jdbc:oracle:thin:@(DESCRIPTION = (LOAD_BALANCE = on) (ADDRESS = (PROTOCOL = TCP)(HOST = racprod1.pucrs.br)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = racprod2.pucrs.br)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = racprod3.pucrs.br)(PORT = 1521)) (CONNECT_DATA=(SERVICE_NAME=SRVCO_I_GERAL))))))");
            
            HashMap<String, String> password = new HashMap<String, String>();
            password.put("RACDES", "R4CD3S18");
            password.put("RACTST", "R4CTST18");
            password.put("RACHMG", "R4CHMG18");
            password.put("RACESP", "R4C3SP18");
            password.put("RACPROD", "");
            
            if(urls.get(ambiente.toUpperCase()) == null || password.get(ambiente.toUpperCase()) == null )
            	throw new IllegalArgumentException("Ambiente invalido");
            
           	Class.forName(driver);
           	
           	senha = "".equals(senha)?(String) password.get(ambiente.toUpperCase()).toString():senha;
		
            conn = DriverManager.getConnection(urls.get(ambiente.toUpperCase()), sistema, senha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
    	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return conn;
	}
}
