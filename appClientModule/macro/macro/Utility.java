package macro.macro;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Utility {

	public static String getDateToString(Date pDate, String pFormato) {
		SimpleDateFormat DtFormat = new SimpleDateFormat(pFormato);
		return (pDate == null || pDate.equals("")) ? "" : DtFormat.format(pDate);
	}

	public static String getDateToString(Date pDate) {
		SimpleDateFormat DtFormat = new SimpleDateFormat("dd/MM/yyyy");
		return (pDate == null || pDate.equals("")) ? "" : DtFormat.format(pDate);
	}

	public static String getCompleteDateToString(Date pDate) {
		SimpleDateFormat DtFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return (pDate == null || pDate.equals("")) ? "" : DtFormat.format(pDate);
	}
	
	public static String getDateToStringLog(Date pDate) {
		SimpleDateFormat DtFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		return (pDate == null || pDate.equals("")) ? "" : DtFormat.format(pDate);
	}

	public static Date getStringToDate(String pDate) throws Exception {
		SimpleDateFormat DtFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date DateTmp = null;
		try {
			DateTmp = (pDate == null || pDate.equals("")) ? null : DtFormat.parse(pDate);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return DateTmp;
	}

	public static Date getCompleteStringToDate(String pDate) throws Exception {
		SimpleDateFormat DtFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date DateTmp = null;
		try {
			DateTmp = (pDate == null || pDate.equals("")) ? null : DtFormat.parse(pDate);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return DateTmp;
	}

	public static String getNomeDoMes(int i) {
	    String mes[] = {"janeiro", "fevereiro", "março", "abril",
	      "maio", "junho", "julho", "agosto", "setembro", "outubro",
	      "novembro", "dezembro"};
	    
	    return mes[i];
	}
	
	public static String getBarraSistemaOperacional() {
		String sistema = System.getProperty("os.name");
		if (sistema.indexOf("Win") >= 0)
			return "\\";
		else
			return "/";
	}

	public static String LPAD(String texto, int tamanho, String com) {
		StringBuffer valorretorno = new StringBuffer();

		if (texto == null) {
			texto = "";
		}

		if (texto.length() < tamanho) {
			for (int i = 0; i < tamanho - texto.length(); i++) {
				valorretorno.append(com);
			}
			return valorretorno.toString() + texto;
		} else {
			return texto;
		}
	}

	public static String LPAD(long texto, int tamanho, String com) {
		return LPAD(String.valueOf(texto), tamanho, com);
	}

	public static String LPAD(int texto, int tamanho, String com) {
		return LPAD(String.valueOf(texto), tamanho, com);
	}

	public static String RPAD(String texto, int tamanho, String com) {
		StringBuffer valorretorno = new StringBuffer();

		if (texto == null) {
			texto = "";
		}
		if (texto.length() < tamanho) {
			for (int i = 0; i < tamanho - texto.length(); i++) {
				valorretorno.append(com);
			}
			return texto + valorretorno.toString();
		} else {
			return texto;
		}
	}

	public static String RPAD(long texto, int tamanho, String com) {
		return RPAD(String.valueOf(texto), tamanho, com);
	}

	public static String RPAD(int texto, int tamanho, String com) {
		return RPAD(String.valueOf(texto), tamanho, com);
	}

	public static String getNumberToString(double num, String format) {
		DecimalFormat formatador = new DecimalFormat();
		formatador.applyPattern(format);
		return formatador.format(num);
	}

	public static String getCPFFormatado(long valor) {
		String aux = valor + "";
		while (aux.length() < 11)
			aux = "0" + aux;

		return aux.substring(0, 3) + "." + aux.substring(3, 6) + "." + aux.substring(6, 9) + "-" + aux.substring(9);

	}

	public static boolean validaStringComNumero(String valor) {
		for (int i = 0; i < valor.length(); i++) {
			char aux = valor.charAt(i);
			if (Character.isDigit(aux)) {
				return true;
			}
		}
		return false;
	}

	public static boolean validaStringEmSequencia(String valor, int quantidade) {

		int cont = 1;

		for (int i = 0; i < valor.length() - 1; i++) {
			char aux = valor.charAt(i);
			char aux2 = valor.charAt(i + 1);
			if (aux == aux2) {
				cont++;
				if (cont == quantidade)
					return true;
			} else {
				cont = 1;
			}
		}
		return false;
	}

	public static String[] splitString(String str, String delim) {
		return splitString(str, delim, false);
	}

	public static String[] splitString(String str, String delim, boolean nulls) {
		StringTokenizer st = new StringTokenizer(str, delim);
		String vetStr[] = new String[st.countTokens()];
		String command = "";
		int countCommand = 0;

		for (int i = 0; st.hasMoreTokens(); i++) {
			command = st.nextToken().trim();
			if (nulls == true || (nulls == false && command != null && !command.equals(""))) {
				vetStr[countCommand] = command;
				countCommand++;
			}
		}
		return vetStr;

	}

	public static String replaceAllStringDoisPipe(String str) {
		StringBuffer sbuff = new StringBuffer();

		int iLength = str.length();
		int iLen = iLength - 1;

		for (int i = 0; i < iLength; i++) {
			if (i < iLen && str.charAt(i) == '|' && str.charAt(i + 1) == '|') {
				sbuff.append("| ");
			} else
				sbuff.append(str.charAt(i));
		}

		return sbuff.toString();
	}

	public static void log(String msg) {
		System.out.println(msg);
	}

}