package Solver;

import Model.Corner;
import Model.Edge;
import Solver.Tables.SolverTables;
import UI.ProcessingRenderer;

public class CFOPSolver extends Solver {

    public CFOPSolver(ProcessingRenderer renderer, String scramble) {
        super(renderer, scramble);
    }

    @Override
    public void run() {
        solveCross();
        solveF2L();
        solution += " " + SolverTables.getMovesOLL(cube) + " ";
        cube.doMoves(SolverTables.getMovesOLL(cube));
        solution += " " + SolverTables.getMovesPLL(cube) + " ";
        cube.doMoves(SolverTables.getMovesPLL(cube));

        // TODO: optimize wide moves, cube rotations, setup...
        setSolution();
    }

    private void solveCross() {
        for (int i = 0; i < 4; i++) {
            if (i != 0) {
                solution += " y ";
                cube.doMoves(" y ");
            }
            int position = cube.getEdgePosition(Edge.DF);
            int crossindex = position * 2 + cube.edgeOrientation[position];
            solution += " " + SolverTables.cross[crossindex] + " ";
            cube.doMoves(SolverTables.cross[crossindex]);
        }
    }

    private void solveF2L() {
        for (int i = 0; i < 4; i++) {
            if (i != 0) {
                solution += " y ";
                cube.doMoves(" y ");
            }

            if (cube.cornerOrientation[6] == 0 &&
                    cube.getCornerPosition(Corner.DRF) == 6 &&
                    cube.edgeOrientation[6] == 0 &&
                    cube.getEdgePosition(Edge.FR) == 6)
                continue;

            setupF2L();
            solution += " " + SolverTables.F2L[calculateF2Lindex()] + " ";
            cube.doMoves(SolverTables.F2L[calculateF2Lindex()]);
        }
    }

    private void setupF2L() {
        int cornerPosition = cube.getCornerPosition(Corner.DRF);

        if (cornerPosition < 4) {
            if (cornerPosition == 0) {
                solution += " U2 ";
                cube.doMoves(" U2 ");
            }

            if (cornerPosition == 1) {
                solution += " U ";
                cube.doMoves(" U ");
            }

            if (cornerPosition == 3) {
                solution += " U' ";
                cube.doMoves(" U' ");
            }
        } else {
            if (cornerPosition == 4) {
                solution += " L U2 L' ";
                cube.doMoves(" L U2 L' ");
            }

            if (cornerPosition == 5) {
                solution += " R' U2 R U' ";
                cube.doMoves(" R' U2 R U' ");
            }

            if (cornerPosition == 6) {
                solution += " R U R' U' ";
                cube.doMoves(" R U R' U' ");
            }

            if (cornerPosition == 7) {
                solution += " L' U' L ";
                cube.doMoves(" L' U' L ");
            }
        }

        int edgePosition = cube.getEdgePosition(Edge.FR);

        if (edgePosition >= 4) {
            if (edgePosition == 4) {
                solution += " L U' L' U ";
                cube.doMoves(" L U' L' U ");
            }

            if (edgePosition == 5) {
                solution += " R' U R ";
                cube.doMoves(" R' U R ");
            }

            if (edgePosition == 6) {
                solution += " R U' R' U2 ";
                cube.doMoves(" R U' R' U2 ");
            }

            if (edgePosition == 7) {
                solution += " L' U' L U ";
                cube.doMoves(" L' U' L U ");
            }
        }
    }

    private int calculateF2Lindex() {
        int edgePosition = cube.getEdgePosition(Edge.FR);
        return 6 * edgePosition + 3 * cube.edgeOrientation[edgePosition] + cube.cornerOrientation[2];
    }
}
