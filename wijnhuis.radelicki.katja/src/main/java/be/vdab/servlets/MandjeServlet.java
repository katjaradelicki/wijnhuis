package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Bestelbon;
import be.vdab.enums.Bestelwijze;
import be.vdab.services.BestelbonService;
import be.vdab.services.utility.Mandje;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;

/**
 * MandjeServlet geeft je een overzicht van het mandje met de totale kost 
 * en geeft je de mogelijkheid een bestelbon in te vullen om zo de wijnen uit het madje aan te kopen
 */
@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="WEB-INF/JSP/mandje.jsp";
	private static final String REDIRECT_URL="%s/mandjeToegevoegd.htm";
	private static final BestelbonService bestelbonService=new BestelbonService();
	private static final String REG_EXPR_LETTERS="[a-zA-Z-]+";
	private static final String REG_EXPR_LETTERS_EN_CIJFERS="[a-zA-Z0-9]+";
       


	/**
	 * Geeft je een overzicht van het mandje met de kostprijs per bestelbonlijn en de totale kostprijs van het mandje
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("mandje")!=null){
			//@SuppressWarnings("unchecked")
			//BigDecimal totaal=mandjeService.getTotaleKostMandje((List<Bestelbonlijn>)request.getSession().getAttribute("mandje"));
			BigDecimal totaal=((Mandje)request.getSession().getAttribute("mandje")).getTotaleKost();
			request.setAttribute("totaal", totaal);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * Hier kan je je gegevens invullen om je bestelling te plaatsen
	 * Lege velden en vreemde tekens worden niet toegelaten als input
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Boolean foutAanwezig=false;
		String naam=request.getParameter("naam");
		if(naam.isEmpty()){
			foutAanwezig=true;
			request.setAttribute("foutNaam", "Naam invullen");
		}else if(!naam.matches(REG_EXPR_LETTERS)){
			foutAanwezig=true;
			request.setAttribute("foutNaam", "Enkel letters en - zijn toegelaten");
			
		}
		String straat=request.getParameter("straat");
		if(straat.isEmpty()){
			foutAanwezig=true;
			request.setAttribute("foutStraat", "Straat invullen");
		}else if(!straat.matches(REG_EXPR_LETTERS)){
			foutAanwezig=true;
			request.setAttribute("foutStraat", "Enkel letters en - zijn toegelaten");
		}
		String huisnr=request.getParameter("huisnr");
		if(huisnr.isEmpty()){
			foutAanwezig=true;
			request.setAttribute("foutHuisnr", "huisnr invullen");
		}else if(!huisnr.matches(REG_EXPR_LETTERS_EN_CIJFERS)){
			foutAanwezig=true;
			request.setAttribute("foutHuisnr", "Enkel letters en cijfers zijn toegelaten");
		}
		String postcode=request.getParameter("postcode");
		if(postcode.isEmpty()){
			foutAanwezig=true;
			request.setAttribute("foutPostcode", "PostCode invullen");
		}else if(!postcode.matches(REG_EXPR_LETTERS_EN_CIJFERS)){
			foutAanwezig=true;
			request.setAttribute("foutPostcode", "Enkel letters en cijfers zijn toegelaten");
		}
		String gemeente=request.getParameter("gemeente");
		if(gemeente.isEmpty()){
			foutAanwezig=true;
			request.setAttribute("foutGemeente", "Gemeente invullen");
		}else if(!gemeente.matches(REG_EXPR_LETTERS)){
			foutAanwezig=true;
			request.setAttribute("foutGemeente", "Enkel letters en - zijn toegelaten");
		}
		String bezorging=request.getParameter("bezorging");
		if(bezorging==null){
			foutAanwezig=true;
			request.setAttribute("foutBezorging", "Wijze van bezorging aanduiden");
		}
		
		if(!foutAanwezig)	{
		//bestelbon met bestellijnen toevoegen aan databank
			Adres adres=new Adres(straat, huisnr, postcode, gemeente);
			Bestelwijze bestelwijze;
			if(bezorging.equals("afhalen")){
				bestelwijze=Bestelwijze.AFHALEN;
			}else{
				bestelwijze=Bestelwijze.LEVERING_AAN_HUIS;
			}
			
			Bestelbon bestelbon=new Bestelbon(new Date(), naam, adres, bestelwijze);
			//bestellijnen uit mandje toevoegen aan bestelbon en wijn.aantalInbestelling juist (laten) invullen
			//daarna bestelbon met zijn bestellijnen creëren op de db
			@SuppressWarnings("unchecked")
			List<Bestelbonlijn> mandje=(List<Bestelbonlijn>)request.getSession().getAttribute("mandje");
			for(Bestelbonlijn lijn:mandje){
				bestelbon.addBestelbonlijn(lijn);//voegt de bestelbonlijnen toe aan de bestelbon en verhoogt aantalInBestelingen van de wijn van de bestelbonlijn
			}
			bestelbonService.create(bestelbon);
			//sessie-object mandje mag je wegdoen als bestelbon in opgeslagen op de db
			request.getSession().removeAttribute("mandje");
		response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath()))+"?bestelbonNr="+bestelbon.getBonNr());
		}else{
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
	
	

}
