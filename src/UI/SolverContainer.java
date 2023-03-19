package UI;

import java.util.ArrayList;

import Model.Cube;
import Model.Move;
import Solver.Solver;

public class SolverContainer {
    private Solver solver;

    public Solver getSolver() {
        return solver;
    }

    private Cube cube;

    public Cube getCube() {
        return cube;
    }

    private Move move;

    public Move getMove() {
        return move;
    }

    private ArrayList<Move> scramble;
    private ArrayList<Move> solution;

    public SolverContainer(Solver solver, String scramble) {
        this.solver = solver;
        this.cube = Cube.Solved();
        setScramble(scramble);
        setSolution(" ");
        move = Move.NONE;
        new Thread(this.solver).start();
    }

    public void setScramble(String scrambleStr) {
        scrambleStr = scrambleStr.replace("r", "RW");
        scrambleStr = scrambleStr.replace("l", "LW");
        scrambleStr = scrambleStr.replace("u", "UW");
        scrambleStr = scrambleStr.replace("d", "DW");
        scrambleStr = scrambleStr.replace("b", "BW");
        scrambleStr = scrambleStr.replace("f", "FW");
        scrambleStr = scrambleStr.replace("x", "X");
        scrambleStr = scrambleStr.replace("y", "Y");
        scrambleStr = scrambleStr.replace("z", "Z");
        scrambleStr = scrambleStr.replace("'", "_");

        scramble = new ArrayList<>();
        for (String move : scrambleStr.split(" "))
            scramble.add(Move.getByName(move));
    }

    public void setSolution(String solutionStr) {
        solutionStr = solutionStr.replace("r", "RW");
        solutionStr = solutionStr.replace("l", "LW");
        solutionStr = solutionStr.replace("u", "UW");
        solutionStr = solutionStr.replace("d", "DW");
        solutionStr = solutionStr.replace("b", "BW");
        solutionStr = solutionStr.replace("f", "FW");
        solutionStr = solutionStr.replace("x", "X");
        solutionStr = solutionStr.replace("y", "Y");
        solutionStr = solutionStr.replace("z", "Z");
        solutionStr = solutionStr.replace("'", "_");

        solution = new ArrayList<>();
        for (String move : solutionStr.split(" "))
            solution.add(Move.getByName(move));
    }

    public void getNextMove() {
        move = Move.NONE;

        if (scramble.size() != 0) {
            move = scramble.remove(0);
            return;
        }

        if (solution.size() != 0) {
            move = solution.remove(0);
            return;
        }
    }

}
