package br.com.bbasset.inova.portifolio.service;

import java.util.List;
import java.util.Map;

import br.com.bbasset.inova.portifolio.model.CotacaoFundoInvestimento;
import br.com.bbasset.inova.portifolio.model.FundoInvestimento;


public interface FundoInvestimentoDao {
	
    
	public List<FundoInvestimento> getFundoInvestimentoPorPerfil(String perfil);
	
	
	public List<CotacaoFundoInvestimento> getCotacoesFundosInvestimentos();


	public List<CotacaoFundoInvestimento> getCotacoesPorCodFundo(Long codFundo);


	public Map<String, Object> getRetornoFundo(long codigoFundo, List<CotacaoFundoInvestimento> listCotacoes);
	

}
