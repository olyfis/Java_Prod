package com.olympus.fis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetBobjData extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	/*
	public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");

		String paramName = "myparam";
		String paramValue = req.getParameter(paramName);

		out.write(paramName);
		out.write(" = ");
		out.write(paramValue);
		out.write("n");

		paramName = "UNKNOWN";
		paramValue = req.getParameter(paramName);

		if (paramValue==null) {
			out.write("Parameter " + paramName + " not found");
			System.out.println("PARAM:" + paramValue + "---");
			res.getWriter().println("****PARMVal=" + paramValue + "---");
		}

		out.close();

	}
	*/
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/plain");

		String paramName = "appID";
		String paramValue = req.getParameter(paramName);

		out.write(paramName);
		out.write(" = ");
		out.write(paramValue);
		out.write("\n");

		/*paramName = "UNKNOWN";
		paramValue = req.getParameter(paramName);
		*/

		if (paramValue != null) {
			out.write("Parameter " + paramName + " found");
			System.out.println("PARAM:" + paramValue + "---");
			res.getWriter().println("****PARMVal=" + paramValue + "---<BR>");
		}

		out.close();
		

	}
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started");
	}
	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}

}
