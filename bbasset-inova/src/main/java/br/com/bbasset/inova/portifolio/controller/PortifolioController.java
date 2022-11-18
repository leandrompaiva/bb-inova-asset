package br.com.bbasset.inova.portifolio.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bbasset.inova.portifolio.model.CotacaoFundoInvestimento;
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
	// VISUALIZA GRAFICO DO FUNDO
	// ############## ############## ############## ############## 	
	@RequestMapping(value={"fundos/InvestimentoAnaliseGrafica"})
	public String retornarGraficoFundo(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		
    	Long codFundo = Long.parseLong(request.getParameter("codFundo"));
    	
    	Map<Object,Object> mapCotacoesFundo = new LinkedHashMap<Object,Object>();    	
    	
    	List<CotacaoFundoInvestimento> listCotacoes = fundoInvestimentoDao.getCotacoesPorCodFundo(codFundo);
    	
    	for (CotacaoFundoInvestimento cotacao : listCotacoes) {
    		mapCotacoesFundo.put(cotacao.getDataCota(), cotacao.getValorCota());
    	}
    	
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			model.addAttribute("json", mapper.writeValueAsString(listCotacoes));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	
		return ("portifolio/analiseGrafica");
	}    
    
    
    
    
    
    
    
	
}
