package org.acme.planner;

import org.acme.planner.problem_entity.Board;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import javax.inject.Singleton;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.currentTimeMillis;

@Singleton
public class NQueenSolver {

    public static Solver<Board> solver;

    public NQueenSolver() {
        SolverFactory<Board> solverFactory = SolverFactory.createFromXmlResource("planner/nQueenSolverConfig.xml");
        solver = solverFactory.buildSolver();
    }

    public Map<String, String> solve (int size) {
        Board initialBoard = new Board(size);
        long time = currentTimeMillis();
        Board result = solver.solve(initialBoard);
        long timeDifference = (currentTimeMillis() - time);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("time", String.valueOf(timeDifference));
        resultMap.put("board", result.toString());
        resultMap.put("score", result.getScore().toString());
        return resultMap;
    }

}
