package Solver.TablesCFOP;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import Model.Corner;
import Model.Cube;
import Model.Edge;

public class PllIndexer {

    private static final String[] PllAlgs = {
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

    private static int calculatePllCodex(Cube cube){
        return (cube.getCornerIndex(Corner.ULB) << 0) +
        (cube.getEdgeIndex(Edge.UB) << 2) +
        (cube.getCornerIndex(Corner.URB) << 4) +
        (cube.getEdgeIndex(Edge.UR) << 6) +
        (cube.getCornerIndex(Corner.URF) << 8) +
        (cube.getEdgeIndex(Edge.UF) << 10) +
        (cube.getCornerIndex(Corner.ULF) << 12) +
        (cube.getEdgeIndex(Edge.UL) << 14);
     }

    public static String getMovesPLL(Cube cube) {
        String moves = "";
        Integer index = PllMap.pllMap.get(calculatePllCodex(cube));

        if (index == null)
            return moves;

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

        moves += PllAlgs[(index / 16)];

        switch ((index / 4) % 4) {
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

    private static void createPllHashMap() {
        HashSet<Integer> keys = new HashSet<>();
        try (FileWriter fw = new FileWriter("src/Solver/PllMap.java")) {

            fw.write("package Solver;");
            fw.write("\n\n");

            fw.write("import java.util.HashMap;");
            fw.write("\n\n");

            fw.write("public class PllMap {");
            fw.write("\n\n");

            fw.write("    public static final HashMap<Integer, Integer> pllMap = new HashMap<>() {");
            fw.write("\n");

            fw.write("		{");
            fw.write("\n");

            int index = 0;
            Cube cube = Cube.Solved();

            for (String pll : PllAlgs) {
                for (int j = 0; j < 4; j++) {
                    cube.undoMoves(pll);
                    for (int i = 0; i < 4; i++) {
                        int codex = (cube.getCornerIndex(Corner.ULB) << 0) +
                                (cube.getEdgeIndex(Edge.UB) << 2) +
                                (cube.getCornerIndex(Corner.URB) << 4) +
                                (cube.getEdgeIndex(Edge.UR) << 6) +
                                (cube.getCornerIndex(Corner.URF) << 8) +
                                (cube.getEdgeIndex(Edge.UF) << 10) +
                                (cube.getCornerIndex(Corner.ULF) << 12) +
                                (cube.getEdgeIndex(Edge.UL) << 14);

                        if (!keys.contains(codex)) {
                            keys.add(codex);
                            fw.write(String.format("			put(0x%04x, %d);", codex, index));
                            fw.write("\n");
                        }
                        index++;
                        cube.doMoves("U");
                    }
                    cube.doMoves(pll);
                    cube.doMoves("U");
                }
            }

            fw.write("\n");
            fw.write("		}");
            fw.write("\n");
            fw.write("	};");

            fw.write("\n");
            fw.write("}");

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public static void main(String[] args) {
        createPllHashMap();
    }

}
