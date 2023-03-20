package Solver.CFOP.Tables;

import Model.Cube;
import Model.Edge;

public class CrossIndexer {

	public static final String[] cross = {
			// 0
			"U2 F2",
			"U R' F R",
			// 1
			"U F2",
			"R' F R",
			// 2
			"F2",
			"U' R' F R",
			// 3
			"U' F2",
			"L F' L'",
			// 4
			"D' L' D",
			"L2 F' L2",
			// 5
			"D R D'",
			"R2 F R2",
			// 6
			"D R' D'",
			"F",
			// 7
			"D' L D",
			"F'",
			// 8
			"B2 U2 F2",
			"B2 U R' F R",
			// 9
			"R2 U F2",
			"R F",
			// 10
			"",
			"F D' L D",
			// 11
			"L2 U' F2",
			"L' F'",
	};

	
    public static String solveCross(Cube cube) {
		String solution = "";
		
        for (int i = 0; i < 4; i++) {
            if (i != 0) {
                solution += " y ";
                cube.doMoves(" y ");
            }
            int position = cube.getEdgePosition(Edge.DF);
            int crossindex = position * 2 + cube.edgeOrientation[position];
            solution += " " + CrossIndexer.cross[crossindex] + " ";
            cube.doMoves(CrossIndexer.cross[crossindex]);
        }

		return solution;
    }
}
