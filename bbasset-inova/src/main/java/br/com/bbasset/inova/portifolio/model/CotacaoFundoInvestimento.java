package br.com.bbasset.inova.portifolio.model;

import java.util.Date;

public class CotacaoFundoInvestimento {

	private Date dataCota;
	private Long codigoFundo;
	private double valorCota;

	private String mesAnoCota;
	private String anoCota;
	private String mesCota;
	private String diaCota;

	
	
	public Date getDataCota() {
		return dataCota;
	}

	public void setDataCota(Date dataCota) {
		this.dataCota = dataCota;
	}

	public Long getCodigoFundo() {
		return codigoFundo;
	}

	public void setCodigoFundo(Long codigoFundo) {
		this.codigoFundo = codigoFundo;
	}

	public double getValorCota() {
		return valorCota;
	}

	public void setValorCota(double valorCota) {
		this.valorCota = valorCota;
	}

	public String getMesAnoCota() {
		return mesAnoCota;
	}

	public void setMesAnoCota(String mesAnoCota) {
		this.mesAnoCota = mesAnoCota;
	}

	public String getAnoCota() {
		return anoCota;
	}

	public void setAnoCota(String anoCota) {
		this.anoCota = anoCota;
	}

	public String getMesCota() {
		return mesCota;
	}

	public void setMesCota(String mesCota) {
		this.mesCota = mesCota;
	}

	public String getDiaCota() {
		return diaCota;
	}

	public void setDiaCota(String diaCota) {
		this.diaCota = diaCota;
	}

}
