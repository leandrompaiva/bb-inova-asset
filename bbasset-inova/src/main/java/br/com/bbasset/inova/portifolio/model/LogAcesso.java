package br.com.bbasset.inova.portifolio.model;

import java.util.Date;

public class LogAcesso {

	private Long idLogAcesso;
	private String sessionID;
	private String userAgent;
	private String ipSession;
	private Date dataHoraLogacesso;

	
	public Long getIdLogAcesso() {
		return idLogAcesso;
	}

	public void setIdLogAcesso(Long idLogAcesso) {
		this.idLogAcesso = idLogAcesso;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getIpSession() {
		return ipSession;
	}

	public void setIpSession(String ipSession) {
		this.ipSession = ipSession;
	}

	public Date getDataHoraLogacesso() {
		return dataHoraLogacesso;
	}

	public void setDataHoraLogacesso(Date dataHoraLogacesso) {
		this.dataHoraLogacesso = dataHoraLogacesso;
	}

}
