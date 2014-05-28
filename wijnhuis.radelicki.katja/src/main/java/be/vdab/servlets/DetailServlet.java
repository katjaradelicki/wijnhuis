package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Wijn;
import be.vdab.services.WijnService;
import be.vdab.services.utility.Mandje;
import be.vdab.valueobjects.Bestelbonlijn;

/**
 * DetailServlet: toont de datails van de gekozen wijn. 
 * En hier kan je ook een aantal flessen van deze wijn toevoegen aan je mandje
 */
@WebServlet("/detail.htm")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final WijnService wijnService=new WijnService();
	private static final String VIEW="/WEB-INF/JSP/detail.jsp";
	private static final String REDIRECT_URL="%s/mandje.htm";
	
       
   

	/**
	 * gekozen wijn lezen uit de databank en in een request attribuut steken
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Wijn gekozenWijn= wijnService.read( Integer.parseInt(request.getParameter("wijnNr")));
		//wijn terug van de databank lezen: wijn is dan opgevuld met Soort en Land
		request.setAttribute("gekozenWijn", gekozenWijn);
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

	/**
	 * aantal flessen wijn aan mandje toevoegen
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> fouten=new ArrayList<String>();
		int aantalFlessen=0;
		
		if(request.getParameter("aantalFlessen").isEmpty()){
			fouten.add("vul het aantal flessen in");
		}else{
			try{
				aantalFlessen=Integer.parseInt(request.getParameter("aantalFlessen"));
				if(aantalFlessen<=0){
					fouten.add("het aantal flessen moet groter zijn dan 0");
				}
			}catch(NumberFormatException nfe){
				fouten.add("het aantal flessen moet een geheel getal zijn");
			}
			
		}
		if(fouten.isEmpty()){
			//winkelmandje uit de sessie halen en bestelbonlijn toevoegen in intern geheugen (nl List<Bestelbonlijn> mandje) (niet in db)
			//als gebruiker nog geen mandje heeft, een mandje aanmaken
			//daarna naar mandje.htm gaan
			
			Bestelbonlijn bestelbonlijn=new Bestelbonlijn(wijnService.read(Integer.parseInt(request.getParameter("wijnNr"))), aantalFlessen);
			@SuppressWarnings("unchecked")
			List<Bestelbonlijn> mandje=(List<Bestelbonlijn>)request.getSession().getAttribute("mandje");
			
			if(mandje==null){
				mandje=new Mandje(); //enkel hier ArrayList door Mandje vervangen + waar ik mandje-specifieke methodes zoals getTotaleKost gebruik. De rest is tegen de interface List<Bestelbonlijn> geprogrammeerd.
			}
			
			//mandjeService.voegLijnToeAanMandje(mandje,bestelbonlijn);
			mandje.add(bestelbonlijn);
			request.getSession().setAttribute("mandje", mandje);
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
		}else{
			request.setAttribute("fouten", fouten);
			request.setAttribute("gekozenWijn", wijnService.read(Integer.parseInt(request.getParameter("wijnNr"))));
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}


}
