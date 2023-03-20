package Solver;

import Model.Cube;
import Solver.TablesCFOP.OllIndexer;
import Solver.TablesCFOP.PllIndexer;
import Solver.TablesCFOP.CrossIndexer;
import Solver.TablesCFOP.F2lIndexer;
import UI.ProcessingRenderer;

public class CFOPSolver extends Solver {

    public CFOPSolver(ProcessingRenderer renderer, String scramble) {
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
        
        setSolution(solution);
    }
}
