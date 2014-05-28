package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Land;
import be.vdab.entities.Soort;
import be.vdab.services.LandService;

/**
 * LandenServlet
 */
@WebServlet("/wijnen.htm")
public class WijnenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/wijnen.jsp";
	private static final LandService landService=new LandService();
       
  

	/**
	 * Alle landen worden getoond. Als er een land geselecteerd is worden ook alle soorten van dat land getoond.
	 * En als ook de soort gescelecteerd is worden alle wijnen (meer bepaald de jaartallen) van die soort getoond.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("landen", landService.findAll());//alternatief: de landen in een application scope attribuut steken
		if(request.getParameter("landNr")!=null){
			Land land=landService.read(Integer.parseInt( request.getParameter("landNr")));
			request.setAttribute("gekozenLand", land);
			if(request.getParameter("soortNr")!=null){
				//soort is gekozen, we laten de jaartallen van de wijnen zien. Hiervoor steken we de gekozen soort in een attribuut gekozenSoort
				//ik moet soort niet meer uit de databank halen, want ik kan de wijnen ophalen van soort en soort haal ik uit land
				//(land die ik eerder uit de databank heb gelezen)
				Soort soort=land.getSoort(Integer.parseInt(request.getParameter("soortNr")));
				request.setAttribute("gekozenSoort", soort);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request,response);
	}



}
