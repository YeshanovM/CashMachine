package controller;

import model.ReportModel;
import model.entity.Report;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ReportServlet")
public class ReportServlet extends HttpServlet {

    private static final String REPORT_URL = "/WEB-INF/view/report.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Report report;
        ReportModel model = new ReportModel();
        if(request.getParameterMap().containsKey("z")) {
            report = model.ZReport();
        }
        else {
            report = model.XReport();
        }
        request.setAttribute("report", report);
        request.getRequestDispatcher(REPORT_URL).forward(request, response);
    }
}
