package br.com.bbasset.inova.portifolio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.bbasset.inova.portifolio.model.CotacaoFundoInvestimento;
import br.com.bbasset.inova.portifolio.model.EvolucaoRentabilidade;
import br.com.bbasset.inova.portifolio.model.FundoInvestimento;
import br.com.bbasset.inova.portifolio.service.FundoInvestimentoDao;

@Controller
public class PortifolioController {

	@Autowired
	FundoInvestimentoDao fundoInvestimentoDao;

	// ############## ############## ############## ##############
	// CARREGA APLICACAO PRINCIPAL
	// ############## ############## ############## ##############
	@RequestMapping("/")
	public String getIndexPage() {
		return "index";
	}

	// ############## ############## ############## ##############
	// RETORNA UMA VIEW QUE IRA RENDERIZAR OS FUNDOS DISPONIVEIS
	// ############## ############## ############## ##############
	@RequestMapping("fundos/retornaFundos")
	public String getCarPartialPage(Model model) {
		return "portifolio/retornaFundos";
	}

	// ############## ############## ############## ##############
	// CARREGA E DEVOLVE FUNDOS POR PERFIL DO INVESTIDOR
	// ############## ############## ############## ##############
	@RequestMapping("fundos/perfilConservadorlist.json")
	public @ResponseBody List<FundoInvestimento> getPerfilConservadorList(Model model) {

		List<FundoInvestimento> listFundos = fundoInvestimentoDao.getFundoInvestimentoPorPerfil("BAIXO");
		return listFundos;
	}

	@RequestMapping("fundos/perfilModeradolist.json")
	public @ResponseBody List<FundoInvestimento> getPerfilModeradoList() {

		List<FundoInvestimento> listFundos = fundoInvestimentoDao.getFundoInvestimentoPorPerfil("MEDIO");
		return listFundos;
	}

	@RequestMapping("fundos/perfilArrojadolist.json")
	public @ResponseBody List<FundoInvestimento> getPerfilArrojadoList() {

		List<FundoInvestimento> listFundos = fundoInvestimentoDao.getFundoInvestimentoPorPerfil("ALTO");
		return listFundos;
	}

	// ############## ############## ############## ##############
	// RETORNA UMA MENSAGEM PARA SELECIONAR O PERFIL
	// ############## ############## ############## ##############
	@RequestMapping("fundos/index")
	public String getFundosIndex() {
		return "portifolio/layout";
	}

	// ############## ############## ############## ##############
	// VIEWS GRAFICOS DO FUNDO
	// ############## ############## ############## ##############
	@RequestMapping(value = { "fundos/graficoEvolucaoCota" })
	public String graficoEvolucaoCota(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("codFundo", Long.parseLong(request.getParameter("codFundo")));
		return ("portifolio/evolucaoCota");
	}

	@RequestMapping("fundos/retornaDadosGraficoEvolucaoCota.json")
	public @ResponseBody List<CotacaoFundoInvestimento> retornaGraficoCotacao(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Long codFundo = Long.parseLong(request.getParameter("codFundo"));
		List<CotacaoFundoInvestimento> listCotacoes = fundoInvestimentoDao.getCotacoesPorCodFundo(codFundo);

		return listCotacoes;
	}

	@RequestMapping(value = { "fundos/graficoRentabilidade" })
	public String graficoRentabilidade(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("codFundo", Long.parseLong(request.getParameter("codFundo")));
		return ("portifolio/evolucaoRentabilidade");
	}

	@RequestMapping("fundos/retornaDadosGraficoRentabilidade.json")
	public @ResponseBody List<EvolucaoRentabilidade> retornaDadosGraficoRentabilidade(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Long codFundo = Long.parseLong(request.getParameter("codFundo"));
		List<CotacaoFundoInvestimento> listCotacoes = fundoInvestimentoDao.getCotacoesPorCodFundo(codFundo);

		Map<String, Object> retornoFundo = fundoInvestimentoDao.getRetornoFundo(codFundo, listCotacoes);

		@SuppressWarnings("unchecked")
		Map<String, Double> getRentabilidadeMesAmes = (Map<String, Double>) retornoFundo.get("rentabilidadePorMes");

		List<EvolucaoRentabilidade> listRetorno = new ArrayList<EvolucaoRentabilidade>();
		for (Entry<String, Double> entry : getRentabilidadeMesAmes.entrySet()) {
			EvolucaoRentabilidade evolucaoRentabilidade = new EvolucaoRentabilidade();
			evolucaoRentabilidade.setMesAnoRentabilidade(entry.getKey());
			evolucaoRentabilidade.setValorRentabilidade(entry.getValue());
			listRetorno.add(evolucaoRentabilidade);
		}

		return listRetorno;
	}

}
