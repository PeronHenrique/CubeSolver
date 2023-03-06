package Solver;

import java.util.HashMap;

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

	private static final HashMap<Integer, Integer> ollMap = new HashMap<>() {
		{
			put(0x0000, 0);
			put(0x6565, 4);
			put(0x5656, 5);
			put(0x6556, 8);
			put(0x5566, 9);
			put(0x5665, 10);
			put(0x6655, 11);
			put(0x4666, 12);
			put(0x6664, 13);
			put(0x6646, 14);
			put(0x6466, 15);
			put(0x5455, 16);
			put(0x4555, 17);
			put(0x5554, 18);
			put(0x5545, 19);
			put(0x2660, 20);
			put(0x6602, 21);
			put(0x6026, 22);
			put(0x0266, 23);
			put(0x5501, 24);
			put(0x5015, 25);
			put(0x0155, 26);
			put(0x1550, 27);
			put(0x0662, 28);
			put(0x6620, 29);
			put(0x6206, 30);
			put(0x2066, 31);
			put(0x5411, 32);
			put(0x4115, 33);
			put(0x1154, 34);
			put(0x1541, 35);
			put(0x1450, 36);
			put(0x4501, 37);
			put(0x5014, 38);
			put(0x0145, 39);
			put(0x2246, 40);
			put(0x2462, 41);
			put(0x4622, 42);
			put(0x6224, 43);
			put(0x2642, 44);
			put(0x6422, 45);
			put(0x4226, 46);
			put(0x2264, 47);
			put(0x5510, 48);
			put(0x5105, 49);
			put(0x1055, 50);
			put(0x0551, 51);
			put(0x0626, 52);
			put(0x6260, 53);
			put(0x2606, 54);
			put(0x6062, 55);
			put(0x1415, 56);
			put(0x4151, 57);
			put(0x1514, 58);
			put(0x5141, 59);
			put(0x2624, 60);
			put(0x6242, 61);
			put(0x2426, 62);
			put(0x4262, 63);
			put(0x1505, 64);
			put(0x5051, 65);
			put(0x0515, 66);
			put(0x5150, 67);
			put(0x5464, 68);
			put(0x4645, 69);
			put(0x6454, 70);
			put(0x4546, 71);
			put(0x5644, 72);
			put(0x6445, 73);
			put(0x4456, 74);
			put(0x4564, 75);
			put(0x6544, 76);
			put(0x5446, 77);
			put(0x4465, 78);
			put(0x4654, 79);
			put(0x4444, 80);
			put(0x1212, 84);
			put(0x2121, 85);
			put(0x2211, 88);
			put(0x2112, 89);
			put(0x1122, 90);
			put(0x1221, 91);
			put(0x0012, 92);
			put(0x0120, 93);
			put(0x1200, 94);
			put(0x2001, 95);
			put(0x1002, 96);
			put(0x0021, 97);
			put(0x0210, 98);
			put(0x2100, 99);
			put(0x0201, 100);
			put(0x2010, 101);
			put(0x0102, 102);
			put(0x1020, 103);
			put(0x1101, 104);
			put(0x1011, 105);
			put(0x0111, 106);
			put(0x1110, 107);
			put(0x0222, 108);
			put(0x2220, 109);
			put(0x2202, 110);
			put(0x2022, 111);
			put(0x0440, 112);
			put(0x4400, 113);
			put(0x4004, 114);
			put(0x0044, 115);
			put(0x1442, 116);
			put(0x4421, 117);
			put(0x4214, 118);
			put(0x2144, 119);
			put(0x0461, 120);
			put(0x4610, 121);
			put(0x6104, 122);
			put(0x1046, 123);
			put(0x5402, 124);
			put(0x4025, 125);
			put(0x0254, 126);
			put(0x2540, 127);
			put(0x0650, 128);
			put(0x6500, 129);
			put(0x5006, 130);
			put(0x0065, 131);
			put(0x1406, 132);
			put(0x4061, 133);
			put(0x0614, 134);
			put(0x6140, 135);
			put(0x0425, 136);
			put(0x4250, 137);
			put(0x2504, 138);
			put(0x5042, 139);
			put(0x5024, 140);
			put(0x0245, 141);
			put(0x2450, 142);
			put(0x4502, 143);
			put(0x6410, 144);
			put(0x4106, 145);
			put(0x1064, 146);
			put(0x0641, 147);
			put(0x1460, 148);
			put(0x4601, 149);
			put(0x6014, 150);
			put(0x0146, 151);
			put(0x0542, 152);
			put(0x5420, 153);
			put(0x4205, 154);
			put(0x2054, 155);
			put(0x0506, 156);
			put(0x5060, 157);
			put(0x0605, 158);
			put(0x6050, 159);
			put(0x2414, 160);
			put(0x4142, 161);
			put(0x1424, 162);
			put(0x4241, 163);
			put(0x0452, 164);
			put(0x4520, 165);
			put(0x5204, 166);
			put(0x2045, 167);
			put(0x1244, 168);
			put(0x2441, 169);
			put(0x4412, 170);
			put(0x4124, 171);
			put(0x6401, 172);
			put(0x4016, 173);
			put(0x0164, 174);
			put(0x1640, 175);
			put(0x0560, 176);
			put(0x5600, 177);
			put(0x6005, 178);
			put(0x0056, 179);
			put(0x2405, 180);
			put(0x4052, 181);
			put(0x0524, 182);
			put(0x5240, 183);
			put(0x4160, 184);
			put(0x1604, 185);
			put(0x6041, 186);
			put(0x0416, 187);
			put(0x5522, 188);
			put(0x5225, 189);
			put(0x2255, 190);
			put(0x2552, 191);
			put(0x2651, 192);
			put(0x6512, 193);
			put(0x5126, 194);
			put(0x1265, 195);
			put(0x6611, 196);
			put(0x6116, 197);
			put(0x1166, 198);
			put(0x1661, 199);
			put(0x6215, 200);
			put(0x2156, 201);
			put(0x1562, 202);
			put(0x5621, 203);
			put(0x1526, 204);
			put(0x5261, 205);
			put(0x2615, 206);
			put(0x6152, 207);
			put(0x5162, 208);
			put(0x1625, 209);
			put(0x6251, 210);
			put(0x2516, 211);
			put(0x5612, 212);
			put(0x6125, 213);
			put(0x1256, 214);
			put(0x2561, 215);
			put(0x1652, 216);
			put(0x6521, 217);
			put(0x5216, 218);
			put(0x2165, 219);
			put(0x1616, 220);
			put(0x6161, 221);
			put(0x2525, 224);
			put(0x5252, 225);
			put(0x0404, 228);
			put(0x4040, 229);
		}
	};

	public static String getMovesOLL(Cube cube) {
		String moves = "";
		int codex;
		codex = (cube.cornerOrientation[0] << 0) +
				(cube.edgeOrientation[0] << 2) +
				(cube.cornerOrientation[1] << 4) +
				(cube.edgeOrientation[1] << 6) +
				(cube.cornerOrientation[2] << 8) +
				(cube.edgeOrientation[2] << 10) +
				(cube.cornerOrientation[3] << 12) +
				(cube.edgeOrientation[3] << 14);

		Integer index = SolverTables.ollMap.get(codex);

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

		// System.out.println("\n" + (index / 4));
		moves += SolverTables.OLL[(index / 4)];

		return moves;
	}

	public static final String OLL[] = {
			"",
			"R U2 R2 F R F' U2 R' F R F'",
			"r U r' U2 r U2 R' U2 R U' r'",
			"M R U R' U r U2 r' U M'",
			"M U' r U2 r' U' R U' R' M",
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

	private static final HashMap<Integer, Integer> pllMap = new HashMap<>() {
		{
			put(0xFA50, 0);
			put(0xA50F, 1);
			put(0x50FA, 2);
			put(0x0FA5, 3);
		}
	};

	public static String getMovesPLL(Cube cube) {
		String moves = "";
		int codex;
        // TODO: fix indexing of PLL
        codex = (cube.getCornerIndex(Corner.ULB) << 0) +
                (cube.getEdgeIndex(Edge.UB) << 2) +
                (cube.getCornerIndex(Corner.URB) << 4) +
                (cube.getEdgeIndex(Edge.UR) << 6) +
                (cube.getCornerIndex(Corner.URF) << 8) +
                (cube.getEdgeIndex(Edge.UF) << 10) +
                (cube.getCornerIndex(Corner.ULF) << 12) +
                (cube.getEdgeIndex(Edge.UL) << 14);

		System.out.println("\n" + codex);

		Integer index = SolverTables.pllMap.get(codex);

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

		System.out.println("\n" + (index / 4));
		moves += SolverTables.PLL[(index / 4)];

		return moves;
	}

	public static final String[] PLL = {
			"",
			"x L2 D2 L' U' L D2 L' U L'",
			"x' L2 D2 L U L' D2 L U' L",
			"x' L' U L D' L' U' L D L' U' L D' L' U L D",
			"R' U' F' R U R' U' R' F R2 U' R' U' R U R' U R",
			"R2 U R' U R' U' R U' R2 U' D R' U R D'",
			"R' U' R U D' R2 U R' U R U' R U' R2 D",
			"R2 U' R U' R U R' U R2 U D' R U' R' D",
			"R U R' U' D R2 U' R U' R' U R' U R2 D'",
			"M2 U M2 U2 M2 U M2",
			"x R2 F R F' R U2 r' U r U2",
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
