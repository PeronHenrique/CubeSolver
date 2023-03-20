package test;

import Model.Move;
import Solver.Solver;
import Solver.CFOP.CFOPSolver;
import UI.Renderer;

public class TestCFOPSolver implements Renderer {

    private static final String testScramble = "";
    private int count = 0;
    private static int TEST_CUBES = 10000;

    private TestCFOPSolver() {
        initSolve();

    }

    private void initSolve() {
        if (count == TEST_CUBES)
            return;

        String scramble = Move.getStringNotation(Move.getRandomMoves(20));
        if ("".equals(testScramble))
            new CFOPSolver(this, scramble);
        else {
            TEST_CUBES = 1;
            new CFOPSolver(this, testScramble);
        }

        count++;
        if (count % 1000 == 0)
            System.out.println("Corretctly solved " + count + " cubes!!");
    }

    public static void main(String[] args) throws Exception {
        new TestCFOPSolver();
    }

    @Override
    public void setSolution(Solver solver, String solution) {
        if (solver.getCube().isSolved())
            initSolve();
        else {
            System.out.println("Error on solve " + count);
            System.out.println("Scramble:\n" + solver.getScramble() + "\n");
            System.out.println("Solution:\n" + solver.getSolution());
        }
    }
}
