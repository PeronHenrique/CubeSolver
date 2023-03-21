package test;

import Model.Move;
import Solver.Solver;
import Solver.CFOP.OptimizedCFOP;
import UI.Renderer;

public class TestOptimizedCFOPSolver implements Renderer { 
    //average 83,5
    //average 82 after pll changes
    //average 81.5 after oll changes
    //average 73.5 after remove rotations

    private static final String testScramble = "";
    private int solveCount = 1;
    private int moveCount = 0;
    private static int TEST_CUBES = 10000;

    private TestOptimizedCFOPSolver() {
        initSolve();
        if (!"".equals(testScramble))
            TEST_CUBES = 1;
    }

    private void initSolve() {
        if (solveCount > TEST_CUBES)
            return;

        String scramble = Move.getStringNotation(Move.getScramble(20));
        if ("".equals(testScramble))
            new OptimizedCFOP(this, scramble);
        else
            new OptimizedCFOP(this, testScramble);
        

        if (solveCount % 1000 == 0) {
            System.out.println("Corretctly solved " + solveCount + " cubes!!");
            System.out.println("Average move count: " + (moveCount/(float)solveCount) + " moves!!");
        }
    }

    public static void main(String[] args) throws Exception {
        new TestOptimizedCFOPSolver();
    }

    @Override
    public void setSolution(Solver solver, String solution) {
        if (solver.getCube().isSolved()) {
            initSolve();
            solveCount++;
            moveCount += Move.getMoves(solution).length;
        } else {
            System.out.println("Error on solve " + solveCount);
            System.out.println("Scramble:\n" + solver.getScramble() + "\n");
            System.out.println("Solution:\n" + solver.getSolution());
        }
    }
}
