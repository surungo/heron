package pkg.db;

import teste.AbstractBean;

public class UserLoginBean extends AbstractBean {
	private String nuOperador = "";
	private String remoteIP = "";
	private String remoteHost = "";

	public UserLoginBean() {

	}

	public String getRemoteIP() {
		return this.remoteIP;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = (remoteIP == null) ? "" : remoteIP.trim();
	}

	public String getRemoteHost() {
		return this.remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = (remoteHost == null) ? "" : remoteHost.trim();
	}

	public String getNuOperador() {
		return nuOperador;
	}

	public void setNuOperador(String nuOperador) {
		this.nuOperador = (nuOperador == null) ? "" : nuOperador.trim();
	}

}
