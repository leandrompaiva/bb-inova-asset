package br.com.bbasset.inova.portifolio.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.text.MaskFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import br.com.bbasset.inova.portifolio.model.CotacaoFundoInvestimento;
import br.com.bbasset.inova.portifolio.model.FundoInvestimento;


@Component
public class FundoInvestimentoImpl implements FundoInvestimentoDao {
	
	JdbcTemplate jdbcTemplate;	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;	
	
	@Autowired
	public FundoInvestimentoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}	
	
	
	

	@Override
	public List<FundoInvestimento> getFundoInvestimentoPorPerfil(String perfil) {
		
		final List<CotacaoFundoInvestimento> listCotacoes = getCotacoesFundosInvestimentos();
		
		String query = "SELECT codFundoInvestimento, nomeFundoInvestimento, "
				+ "cnpjFundoInvestimento, dataAberturaFundoInvestimento,"
				+ "dataEncerramentoFundoInvestimento, riscoFundoInvestimento "
				+ "FROM bbassetinova.fundoinvestimento WHERE (riscoFundoInvestimento = ?)";


		Object[] argsFiltraFundo = new Object[] {
				perfil	
		};
		
		List<FundoInvestimento> fundoInvestimentoList = null;

		try {
			fundoInvestimentoList = jdbcTemplate.query(query, argsFiltraFundo, new RowMapper<FundoInvestimento>() {

				@Override
				public FundoInvestimento mapRow(ResultSet rs, int rowNum) throws SQLException {

					FundoInvestimento fundoInvestimento = new FundoInvestimento();

					fundoInvestimento.setCodFundoInvestimento(rs.getLong("codFundoInvestimento"));
					fundoInvestimento.setNomeFundoInvestimento(rs.getString("nomeFundoInvestimento"));
					fundoInvestimento.setCnpjFundoInvestimento(format("##.###.###/####-##", rs.getString("cnpjFundoInvestimento")));
					fundoInvestimento.setDataAberturaFundoInvestimento(rs.getDate("dataAberturaFundoInvestimento"));
					fundoInvestimento.setDataEncerramentoFundoInvestimento(rs.getDate("dataEncerramentoFundoInvestimento"));
					fundoInvestimento.setRiscoFundoInvestimento(rs.getString("riscoFundoInvestimento"));
					
					if (rs.getDate("dataEncerramentoFundoInvestimento") == null) {
						fundoInvestimento.setStatusFundo("ABERTO");
					} else {
						fundoInvestimento.setStatusFundo("ENCERRADO");
					}
					
					
					Map<String,String> pegaRetornoFundo = getRetornoFundo(rs.getLong("codFundoInvestimento"), listCotacoes);
					
					fundoInvestimento.setRetorno12Meses(pegaRetornoFundo.get("retorno12Meses"));
					fundoInvestimento.setMaiorRetornoMensal(pegaRetornoFundo.get("maiorRetornoMensal"));
					fundoInvestimento.setMenorRetornoMensal(pegaRetornoFundo.get("menorRetornoMensal"));
					fundoInvestimento.setNumMesesPositivo(pegaRetornoFundo.get("numMesesPositivo"));
					fundoInvestimento.setNumMesesNegativo(pegaRetornoFundo.get("numMesesNegativo"));
							
					return fundoInvestimento;
				}



			});
		} catch (DataAccessException e) {
			System.out.println("##ERRO - Não foi possível ao recuperar listagens de fundos de investimentos:" + e.getMessage());
			return null;
		}

		return fundoInvestimentoList;		
		
		
		
	}
	
	
	
	
	@Override
	public List<CotacaoFundoInvestimento> getCotacoesFundosInvestimentos() {
		
		String query = "SELECT dataCota,EXTRACT(YEAR_MONTH FROM dataCota) AS mesAnoCota,YEAR(dataCota) AS anoCota, "
				+ "MONTH(dataCota) AS mesCota,  DAY(dataCota) AS diaCota, codigoFundo, valorCota "
				+ "FROM bbassetinova.cotacaofundoinvestimento ORDER BY dataCota;";

		List<CotacaoFundoInvestimento> cotacoesList = null;

		try {
			cotacoesList = jdbcTemplate.query(query, new RowMapper<CotacaoFundoInvestimento>() {

				@Override
				public CotacaoFundoInvestimento mapRow(ResultSet rs, int rowNum) throws SQLException {

					CotacaoFundoInvestimento cotacao = new CotacaoFundoInvestimento();
					cotacao.setDataCota(rs.getDate("dataCota"));
					cotacao.setMesAnoCota(rs.getString("mesAnoCota"));
					cotacao.setAnoCota(rs.getString("anoCota"));
					cotacao.setMesCota(rs.getString("mesCota"));
					cotacao.setDiaCota(rs.getString("diaCota"));
					cotacao.setCodigoFundo(rs.getLong("codigoFundo"));
					cotacao.setValorCota(rs.getDouble("valorCota"));
					return cotacao;
				}

			});
		} catch (DataAccessException e) {
			System.out.println("##ERRO - Não foi possível ao recuperar cotacoes para calculo de retorno:" + e.getMessage());
			return null;
		}

		return cotacoesList;		
		
		
		
	}	
	
	
	
	private Map<String, String> getRetornoFundo(long codigoFundo, List<CotacaoFundoInvestimento> listCotacoes) {
		
		Map<String,String> retornaAnaliseFundo = new LinkedHashMap<String,String>();
		
		List<CotacaoFundoInvestimento> cotacoesPorFundo = new ArrayList<CotacaoFundoInvestimento>();
		
		for (CotacaoFundoInvestimento cotacao : listCotacoes) {
			if (codigoFundo == cotacao.getCodigoFundo()) {
				cotacoesPorFundo.add(cotacao);
			}
		}
		

		// VOU PEGAR O PRIMEIRO E O ULTIMO PARA FACILITAR POIS A 
		// LISTA TEM MENOS QUE 360 DIAS - O IDEAL SERIA PARA UM PARAMETRO
		// (DATA-360)
        Double cotaInicial = cotacoesPorFundo.get(0).getValorCota();
        Double cotaFinal = cotacoesPorFundo.get(cotacoesPorFundo.size()-1).getValorCota();
        
		Double retorno12Meses = round(((cotaFinal/cotaInicial)-1)*100,2);
		
		Double cotacaoInicialMes = 0d;
		Double cotacaoFinalMes = 0d;
		Double rentabilidadeMes = 0d;
		Double maiorRetornoMensal = -9999d;
		Double menorRetornoMensal = 9999d;
		int numMesesPositivo = 0;
		int numMesesNegativo = 0;		
		
		String mes = "zz";
		String mesPosterior = "aa";
						
		int i = 0;
		
		while (i <= cotacoesPorFundo.size() - 1) {

			mes = cotacoesPorFundo.get(i).getMesCota();

			if (!mes.equalsIgnoreCase(mesPosterior)) {

				rentabilidadeMes = (((cotacaoFinalMes / cotacaoInicialMes) - 1) * 100);

				cotacaoInicialMes = cotacoesPorFundo.get(i).getValorCota();
				mesPosterior = cotacoesPorFundo.get(i).getMesCota();

				if (rentabilidadeMes > maiorRetornoMensal) {
					maiorRetornoMensal = rentabilidadeMes;
				}

				if (rentabilidadeMes < menorRetornoMensal) {
					menorRetornoMensal = rentabilidadeMes;
				}

				if (rentabilidadeMes > 0) {
					numMesesPositivo++;
				}

				if (rentabilidadeMes < 0) {
					numMesesNegativo++;
				}

			} else {
				cotacaoFinalMes = cotacoesPorFundo.get(i).getValorCota();
			}

			i++;
		}	
		
		retornaAnaliseFundo.put("retorno12Meses", Double.valueOf(retorno12Meses)+"%");
		retornaAnaliseFundo.put("maiorRetornoMensal", round(Double.valueOf(maiorRetornoMensal),2)+"%");
		retornaAnaliseFundo.put("menorRetornoMensal", round(Double.valueOf(menorRetornoMensal),2)+"%");
		retornaAnaliseFundo.put("numMesesPositivo", String.valueOf(numMesesPositivo));
		retornaAnaliseFundo.put("numMesesNegativo",  String.valueOf(numMesesNegativo));
		
		
		return retornaAnaliseFundo;
		
	}    
	





	@Override
	public List<CotacaoFundoInvestimento> getCotacoesPorCodFundo(Long codFundo) {
		
		String query = "SELECT dataCota,EXTRACT(YEAR_MONTH FROM dataCota) AS mesAnoCota,YEAR(dataCota) AS anoCota, "
				+ "MONTH(dataCota) AS mesCota,  DAY(dataCota) AS diaCota, codigoFundo, valorCota "
				+ "FROM bbassetinova.cotacaofundoinvestimento WHERE (codigoFundo = ?)"
				+ " ORDER BY dataCota;";

		List<CotacaoFundoInvestimento> cotacoesList = null;

		try {
			cotacoesList = jdbcTemplate.query(query, new Object[] {codFundo}, new RowMapper<CotacaoFundoInvestimento>() {

				@Override
				public CotacaoFundoInvestimento mapRow(ResultSet rs, int rowNum) throws SQLException {

					CotacaoFundoInvestimento cotacao = new CotacaoFundoInvestimento();
					cotacao.setDataCota(rs.getDate("dataCota"));
					cotacao.setMesAnoCota(rs.getString("mesAnoCota"));
					cotacao.setAnoCota(rs.getString("anoCota"));
					cotacao.setMesCota(rs.getString("mesCota"));
					cotacao.setDiaCota(rs.getString("diaCota"));
					cotacao.setCodigoFundo(rs.getLong("codigoFundo"));
					cotacao.setValorCota(rs.getDouble("valorCota"));
					return cotacao;
				}

			});
		} catch (DataAccessException e) {
			System.out.println("##ERRO - Não foi possível ao recuperar cotacoes para um determinado fundo:" + e.getMessage());
			return null;
		}

		return cotacoesList;		
		
	}	
	
	
	
	
	
	
	
	
	
	private static String format(String pattern, Object value) {
		MaskFormatter mask;
		try {
			mask = new MaskFormatter(pattern);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}
	
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}	
	
	
	
	
	
	
	
	
	
	
}
