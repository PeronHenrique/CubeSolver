package Solver.Reverse;

import Model.Move;
import Solver.Solver;
import UI.Renderer;

public class ReverseSolver extends Solver{

    public ReverseSolver(Renderer renderer, String scramble) {
        super(renderer, scramble);
    }

    @Override
    public void run() {
        solution = Move.getStringNotation(Move.getReversedMoves(scramble));
        cube.doMoves(solution);
        
        setSolution(solution);
    }
    
}
