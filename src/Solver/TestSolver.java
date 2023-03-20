package Solver;

import Model.Move;
import UI.ProcessingRenderer;

public class TestSolver extends Solver{

    public TestSolver(ProcessingRenderer renderer, String scramble) {
        super(renderer, scramble);
    }

    @Override
    public void run() {
        solution = Move.getStringNotation(Move.getUndoMoves(scramble));
        cube.doMoves(solution);
        
        setSolution(solution);
    }
    
}
