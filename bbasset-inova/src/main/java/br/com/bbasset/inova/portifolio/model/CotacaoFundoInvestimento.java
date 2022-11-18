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

	public CotacaoFundoInvestimento() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anoCota == null) ? 0 : anoCota.hashCode());
		result = prime * result + ((codigoFundo == null) ? 0 : codigoFundo.hashCode());
		result = prime * result + ((dataCota == null) ? 0 : dataCota.hashCode());
		result = prime * result + ((diaCota == null) ? 0 : diaCota.hashCode());
		result = prime * result + ((mesAnoCota == null) ? 0 : mesAnoCota.hashCode());
		result = prime * result + ((mesCota == null) ? 0 : mesCota.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorCota);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CotacaoFundoInvestimento other = (CotacaoFundoInvestimento) obj;
		if (anoCota == null) {
			if (other.anoCota != null)
				return false;
		} else if (!anoCota.equals(other.anoCota))
			return false;
		if (codigoFundo == null) {
			if (other.codigoFundo != null)
				return false;
		} else if (!codigoFundo.equals(other.codigoFundo))
			return false;
		if (dataCota == null) {
			if (other.dataCota != null)
				return false;
		} else if (!dataCota.equals(other.dataCota))
			return false;
		if (diaCota == null) {
			if (other.diaCota != null)
				return false;
		} else if (!diaCota.equals(other.diaCota))
			return false;
		if (mesAnoCota == null) {
			if (other.mesAnoCota != null)
				return false;
		} else if (!mesAnoCota.equals(other.mesAnoCota))
			return false;
		if (mesCota == null) {
			if (other.mesCota != null)
				return false;
		} else if (!mesCota.equals(other.mesCota))
			return false;
		if (Double.doubleToLongBits(valorCota) != Double.doubleToLongBits(other.valorCota))
			return false;
		return true;
	}

}
