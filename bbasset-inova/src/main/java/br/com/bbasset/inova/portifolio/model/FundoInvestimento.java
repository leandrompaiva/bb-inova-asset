package br.com.bbasset.inova.portifolio.model;

import java.util.Date;

public class FundoInvestimento {

	private Long codFundoInvestimento;
	private String nomeFundoInvestimento;
	private String cnpjFundoInvestimento;
	private Date dataAberturaFundoInvestimento;
	private Date dataEncerramentoFundoInvestimento;
	private String riscoFundoInvestimento;

	// RETORNO FINANCEIRO
	private String retorno12Meses;
	private String maiorRetornoMensal;
	private String menorRetornoMensal;
	private String numMesesPositivo;
	private String numMesesNegativo;

	private String statusFundo;
	
	
	
	

	public Long getCodFundoInvestimento() {
		return codFundoInvestimento;
	}

	public void setCodFundoInvestimento(Long codFundoInvestimento) {
		this.codFundoInvestimento = codFundoInvestimento;
	}

	public String getNomeFundoInvestimento() {
		return nomeFundoInvestimento;
	}

	public void setNomeFundoInvestimento(String nomeFundoInvestimento) {
		this.nomeFundoInvestimento = nomeFundoInvestimento;
	}

	public String getCnpjFundoInvestimento() {
		return cnpjFundoInvestimento;
	}

	public void setCnpjFundoInvestimento(String cnpjFundoInvestimento) {
		this.cnpjFundoInvestimento = cnpjFundoInvestimento;
	}

	public Date getDataAberturaFundoInvestimento() {
		return dataAberturaFundoInvestimento;
	}

	public void setDataAberturaFundoInvestimento(Date dataAberturaFundoInvestimento) {
		this.dataAberturaFundoInvestimento = dataAberturaFundoInvestimento;
	}

	public Date getDataEncerramentoFundoInvestimento() {
		return dataEncerramentoFundoInvestimento;
	}

	public void setDataEncerramentoFundoInvestimento(Date dataEncerramentoFundoInvestimento) {
		this.dataEncerramentoFundoInvestimento = dataEncerramentoFundoInvestimento;
	}

	public String getRiscoFundoInvestimento() {
		return riscoFundoInvestimento;
	}

	public void setRiscoFundoInvestimento(String riscoFundoInvestimento) {
		this.riscoFundoInvestimento = riscoFundoInvestimento;
	}

	public String getStatusFundo() {
		return statusFundo;
	}

	public void setStatusFundo(String statusFundo) {
		this.statusFundo = statusFundo;
	}

	public String getRetorno12Meses() {
		return retorno12Meses;
	}

	public void setRetorno12Meses(String retorno12Meses) {
		this.retorno12Meses = retorno12Meses;
	}

	public String getMaiorRetornoMensal() {
		return maiorRetornoMensal;
	}

	public void setMaiorRetornoMensal(String maiorRetornoMensal) {
		this.maiorRetornoMensal = maiorRetornoMensal;
	}

	public String getMenorRetornoMensal() {
		return menorRetornoMensal;
	}

	public void setMenorRetornoMensal(String menorRetornoMensal) {
		this.menorRetornoMensal = menorRetornoMensal;
	}

	public String getNumMesesPositivo() {
		return numMesesPositivo;
	}

	public void setNumMesesPositivo(String numMesesPositivo) {
		this.numMesesPositivo = numMesesPositivo;
	}

	public String getNumMesesNegativo() {
		return numMesesNegativo;
	}

	public void setNumMesesNegativo(String numMesesNegativo) {
		this.numMesesNegativo = numMesesNegativo;
	}

}
