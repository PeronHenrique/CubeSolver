package Solver.TablesCFOP;

import Model.Corner;
import Model.Cube;
import Model.Edge;

public class F2lIndexer {
    private static final String[] F2L = {
            // 0
            "U R U2 R' U R U' R'",
            "R U R'",
            "U' R U R' U' R U2 R'",

            "U2 F' U' F2 R' F' R",
            "U F' U2 F U' F R' F' R",
            "U' R U' R' U F' U' F",

            // 1
            "R U2 R' U' R U R'",
            "U' R U' R' U R U R'",
            "R' F R F'",

            "R U R' U R U R' U' F R' F' R",
            "R U' R' U2 F' U' F",
            "U' R U2 R' U F' U' F",

            // 2
            "U R U' R' U' R U' R' U R U' R'",
            "R' U2 R2 U R2 U R",
            "F' U F U2 R U R'",

            "F' U2 F U F' U' F",
            "F R' F' R",
            "U F' U F U' F' U' F",

            // 3
            "U2 R U R' U R U' R'",
            "U' R U R' U R U R'",
            "U' R U2 R' U' R U2 R'",

            "U' F' U2 F2 R' F' R",
            "U F' U' F U' F R' F' R",
            "F' U' F",
    };

    public static String solveF2L(Cube cube) {
        String solution = "";

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

            solution += " " + setupF2L(cube) + " ";
            int F2Lindex = calculateF2Lindex(cube);
            solution += " " + F2L[F2Lindex] + " ";
            cube.doMoves(F2L[F2Lindex]);
        }

        return solution;
    }

    private static String setupF2L(Cube cube) {
        String solution = "";

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

        return solution;
    }

    private static int calculateF2Lindex(Cube cube) {
        int edgePosition = cube.getEdgePosition(Edge.FR);
        return 6 * edgePosition + 3 * cube.edgeOrientation[edgePosition] + cube.cornerOrientation[2];
    }
}
