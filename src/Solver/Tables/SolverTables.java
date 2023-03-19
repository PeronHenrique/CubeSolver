package Solver.Tables;

import Model.Corner;
import Model.Cube;
import Model.Edge;

public class SolverTables {

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

	public static final String[] F2L = {
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

	public static String getMovesOLL(Cube cube) {
		String moves = "";
		int codex = (cube.cornerOrientation[0] << 0) +
				(cube.edgeOrientation[0] << 2) +
				(cube.cornerOrientation[1] << 4) +
				(cube.edgeOrientation[1] << 6) +
				(cube.cornerOrientation[2] << 8) +
				(cube.edgeOrientation[2] << 10) +
				(cube.cornerOrientation[3] << 12) +
				(cube.edgeOrientation[3] << 14);

		Integer index = OllMap.ollMap.get(codex);

		if (index == null)
			return "";

		switch (index % 4) {
			default:
			case 0:
				break;
			case 1:
				moves += "U' ";
				break;
			case 2:
				moves += "U2 ";
				break;
			case 3:
				moves += "U ";
				break;
		}

		moves += SolverTables.OLL[(index / 4)];
		return moves;
	}

	public static final String OLL[] = {
			"",
			"R U2 R2 F R F' U2 R' F R F'",
			"r U r' U2 r U2 R' U2 R U' r'",
			"M R U R' U r U2 r' U M'",
			"M U' r U2 r' U' R U' R' M'",
			"l' U2 L U L' U l",
			"r U2 R' U' R U' r'",
			"r U R' U R U2 r'",
			"l' U' L U' L' U2 l",
			"R U R' U' R' F R2 U R' U' F'",
			"R U R' U R' F R F' R U2 R'",
			"r U R' U R' F R F' R U2 r'",
			"M' R' U' R U' R' U2 R U' M",
			"F U R U' R2 F' R U R U' R'",
			"R' F R U R' F' R F U' F'",
			"l' U' M U' L U l' U l",
			"r U M U R' U' r U' r'",
			"F R' F' R2 r' U R U' R' U' M'",
			"r U R' U R U2 r2 U' R U' R' U2 r",
			"M U R U R' U' M' R' F R F'",
			"r U R' U' M2 U R U' R' U' M'",
			"R U2 R' U' R U R' U' R U' R'",
			"R U2 R2 U' R2 U' R2 U2 R",
			"R2 D' R U2 R' D R U2 R",
			"r U R' U' r' F R F'",
			"F' r U R' U' r' F R",
			"R U2 R' U' R U' R'",
			"R U R' U R U2 R'",
			"r U R' U' M U R U' R'",
			"R U R' U' R U' R' F' U' F R U R'",
			"F R' F R2 U' R' U' R U R' F2",
			"R' U' F U R U' R' F' R",
			"L U F' U' L' U L F L'",
			"R U R' U' R' F R F'",
			"R U R2 U' R' F R U R U' F'",
			"R U2 R2 F R F' R U2 R'",
			"L' U' L U' L' U L U L F' L' F",
			"F R' F' R U R U' R'",
			"R U R' U R U' R' U' R' F R F'",
			"L F' L' U' L U F U' L'",
			"R' F R U R' U' F' U R",
			"R U R' U R U2 R' F R U R' U' F'",
			"R' U' R U' R' U2 R F R U R' U' F'",
			"F' U' L' U L F",
			"F U R U' R' F'",
			"F R U R' U' F'",
			"R' U' R' F R F' U R",
			"R' U' R' F R F' R' F R F' U R",
			"F R U R' U' R U R' U' F'",
			"r U' r2 U r2 U r2 U' r",
			"r' U r2 U' r2 U' r2 U r'",
			"F U R U' R' U R U' R' F'",
			"R U R' U R U' B U' B' R'",
			"l' U2 L U L' U' L U L' U l",
			"r U2 R' U' R U R' U' R U' r'",
			"R' F R U R U' R2 F' R2 U' R' U R U R'",
			"r' U' r U' R' U R U' R' U R r' U r",
			"R U R' U' M' U R U' r'",
	};



	public static String getMovesPLL(Cube cube) {
		String moves = "";
		int codex = (cube.getCornerIndex(Corner.ULB) << 0) +
                (cube.getEdgeIndex(Edge.UB) << 2) +
                (cube.getCornerIndex(Corner.URB) << 4) +
                (cube.getEdgeIndex(Edge.UR) << 6) +
                (cube.getCornerIndex(Corner.URF) << 8) +
                (cube.getEdgeIndex(Edge.UF) << 10) +
                (cube.getCornerIndex(Corner.ULF) << 12) +
                (cube.getEdgeIndex(Edge.UL) << 14);

		Integer index = PllMap.pllMap.get(codex);

		if (index == null)
			return "";

		switch (index % 4) {
			default:
			case 0:
				break;
			case 1:
				moves += "U' ";
				break;
			case 2:
				moves += "U2 ";
				break;
			case 3:
				moves += "U ";
				break;
		}

		moves += SolverTables.PLL[(index / 16)];

		switch ((index/4) % 4) {
			default:
			case 0:
				break;
			case 1:
				moves += " U'";
				break;
			case 2:
				moves += " U2";
				break;
			case 3:
				moves += " U";
				break;
		}

		return moves;
	}

	public static final String[] PLL = {
			"",
			"x L2 D2 L' U' L D2 L' U L' x'",
			"x' L2 D2 L U L' D2 L U' L x",
			"x' L' U L D' L' U' L D L' U' L D' L' U L D x",
			"R' U' F' R U R' U' R' F R2 U' R' U' R U R' U R",
			"R2 U R' U R' U' R U' R2 U' D R' U R D'",
			"R' U' R U D' R2 U R' U R U' R U' R2 D",
			"R2 U' R U' R U R' U R2 U D' R U' R' D",
			"R U R' U' D R2 U' R U' R' U R' U R2 D'",
			"M2 U M2 U2 M2 U M2",
			"x R2 F R F' R U2 r' U r U2 x'",
			"R U R' F' R U R' U' R' F R2 U' R'",
			"R U R' U R U R' F' R U R' U' R' F R2 U' R' U2 R U' R'",
			"R' U R U' R' F' U' F R U R' F R' F' R U' R",
			"R U' R' U' R U R D R' U' R D' R' U2 R'",
			"R2 F R U R U' R' F' R U2 R' U2 R",
			"R U R' U' R' F R2 U' R' U' R U R' F'",
			"M2 U M U2 M' U M2",
			"M2 U' M U2 M' U' M2",
			"R' U R' U' y R' F' R2 U' R' U R' F R F",
			"F R U' R' U' R U R' F' R U R' U' R' F R F'",
			"M' U M2 U M2 U M' U2 M2",
	};

}
