package pkg.db;

/**
 * Title:     graduacao
 * Copyright: Copyright (c) 2008
 * Company:   pucrs
 * @author    Sergio Asturizaga e Roberto Schroeder
 * @version   1
 * Created on 1/2/2008
 */



public class ExceptionDAO extends Exception {
	/**
	 *
	 */
	public ExceptionDAO() {
		super();
	}

	/**
	 * @param mensagem
	 */
	public ExceptionDAO(String mensagem) {
		super(mensagem);
	}
}

