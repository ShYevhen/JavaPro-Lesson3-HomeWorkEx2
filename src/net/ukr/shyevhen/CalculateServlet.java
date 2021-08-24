package net.ukr.shyevhen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (BufferedReader br = new BufferedReader(
				new FileReader(new File("../work/JavaProg/Lesson3HomeWorkEx2Pro/WebContent/index.html")))) {
			double x = Double.parseDouble(req.getParameter("first"));
			double y = Double.parseDouble(req.getParameter("second"));
			StringBuilder sb = new StringBuilder();
			String line = "";
			for (; (line = br.readLine()) != null;) {
				sb.append(line);
			}
			line = sb.toString();
			String respon = line.substring(0, line.indexOf("</table>")) + "<tr class='result'><th>Result: %s"
					+ line.substring(line.indexOf("</table>"), line.length());
			if (req.getParameter("plus") != null) {
				respon = String.format(respon, (x + " + " + y + " = " + (x + y)));
			} else if (req.getParameter("minus") != null) {
				respon = String.format(respon, (x + " - " + y + " = " + (x - y)));
			} else if (req.getParameter("multiply") != null) {
				respon = String.format(respon, (x + " x " + y + " = " + (x * y)));
			} else if (req.getParameter("divided") != null) {
				respon = String.format(respon, (x + " / " + y + " = " + (x / y)));
			}
			PrintWriter pw = resp.getWriter();
			pw.print(respon);
		} catch (NumberFormatException e) {
			req.getRequestDispatcher("/").forward(req, resp);
			return;
		}
	}
}
// Some changes
