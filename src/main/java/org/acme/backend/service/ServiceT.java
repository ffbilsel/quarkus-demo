package org.acme.backend.service;

import org.acme.planner.NQueenSolver;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.InputStream;
import java.util.Map;

@ApplicationScoped
public class ServiceT {

    @Inject
    NQueenSolver nQueenSolver;

    private Map<String, String> solveNQueen(int size) {
        return nQueenSolver.solve(size);
    }

    public InputStream createPage (int size) {
        Map<String, String> pageInfo = solveNQueen(size);
        String sb = "<html>" +
                "<head>" +
                "<title>N-Queens Solver</title>" +
                "<style>td{border: 2px solid black;display: inline-block; width: 40px; height: 40px;" +
                "text-align: center;vertical-align: middle;}</style>" +
                "</head>" +
                "<body>" +
                "<h1>N-Queens Solver</h1>" +
                "<p>Time: " + pageInfo.get("time") + "</p>" +
                "<p>Score: " + pageInfo.get("score") + "</p>" +
                drawBoard(pageInfo.get("board")) +
                "</body>" +
                "</html>";
        return new java.io.ByteArrayInputStream(sb.getBytes());
    }

    private String drawBoard (String boardInfo) {
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("<table style=\"margin-left: auto;margin-right: auto;\">");
        sb.append("<tr>");
        for (int i = 0; i < boardInfo.length(); i++){
            if (boardInfo.charAt(i) == ' ') {
                counter++;
                continue;
            }
            if (boardInfo.charAt(i) == '\n') {
                sb.append("</tr>");
                sb.append("<br>");
                if (i != boardInfo.length() - 1) {
                    sb.append("<tr>");
                }
                counter++;
            }
            else {
                if ((i - counter) % 2 == 0) {
                    sb.append("<td>");
                } else {
                    sb.append("<td style=\"background: rgba(0, 0, 0, 0.3);\">");
                }
                if (boardInfo.charAt(i) == 'Q') {
                    sb.append("<p style=\"display: block;margin: auto; \">Q</p>");
                }
                sb.append("</td>");
            }
        }
        sb.append("</table>");
        return sb.toString();
    }

}
