package Solver.CFOP;


import Model.Cube;
import Solver.OptimizeMoves;
import Solver.CFOP.Tables.CrossIndexer;
import Solver.CFOP.Tables.F2lIndexer;
import Solver.CFOP.Tables.OllIndexer;
import Solver.CFOP.Tables.PllIndexer;
import UI.Renderer;

public class OptimizedCFOP extends CFOPSolver {

    public OptimizedCFOP(Renderer renderer, String scramble) {
        super(renderer, scramble);
    }

    @Override
    public void run() {
        String cross = CrossIndexer.solveCross(Cube.Copy(cube));
        solution += " " + cross + " ";
        cube.doMoves(cross);

        String f2l = F2lIndexer.solveF2L(Cube.Copy(cube));
        solution += " " + f2l + " ";
        cube.doMoves(f2l);

        String oll = OllIndexer.getMovesOLL(cube);
        solution += " " + oll + " ";
        cube.doMoves(oll);

        String pll = PllIndexer.getMovesPLL(cube);
        solution += " " + pll + " ";
        cube.doMoves(pll);

        solution = OptimizeMoves.removeCubeRotations(solution);
        solution = OptimizeMoves.simplifyRepeatedMoves(solution);
        solution = OptimizeMoves.makeMoveCancelations(solution);

        cube = Cube.Solved();
        cube.doMoves(scramble);
        cube.doMoves(solution);

        setSolution(solution);
    }


}
