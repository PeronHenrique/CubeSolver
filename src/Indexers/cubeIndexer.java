package Indexers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import Model.Corner;
import Model.Cube;
import Model.Edge;
import Model.Move;
import Solver.SolverTables;

public class cubeIndexer {

    private static void makeMoves(String algorithm, Cube cube) {
        algorithm = algorithm.replace("r", "RW");
        algorithm = algorithm.replace("l", "LW");
        algorithm = algorithm.replace("u", "UW");
        algorithm = algorithm.replace("d", "DW");
        algorithm = algorithm.replace("b", "BW");
        algorithm = algorithm.replace("f", "FW");
        algorithm = algorithm.replace("x", "X");
        algorithm = algorithm.replace("y", "Y");
        algorithm = algorithm.replace("z", "Z");
        algorithm = algorithm.replace("'", "_");

        for (String s : algorithm.split(" ")) {
            Move move = Move.getByName(s);
            cube.move(move);
        }
    }

    private static void makeReverseMoves(String algorithm, Cube cube) {
        algorithm = algorithm.replace("r", "RW");
        algorithm = algorithm.replace("l", "LW");
        algorithm = algorithm.replace("u", "UW");
        algorithm = algorithm.replace("d", "DW");
        algorithm = algorithm.replace("b", "BW");
        algorithm = algorithm.replace("f", "FW");
        algorithm = algorithm.replace("x", "X");
        algorithm = algorithm.replace("y", "Y");
        algorithm = algorithm.replace("z", "Z");
        algorithm = algorithm.replace("'", "_");

        String[] moves = algorithm.split(" ");

        for (int i = moves.length - 1; i >= 0; i--) {
            String s = moves[i];
            if (s.endsWith("2")) {
                Move move = Move.getByName(s);
                cube.move(move);
            } else if (s.endsWith("_")) {
                String m = s.replace("_", "");
                Move move = Move.getByName(m);
                cube.move(move);
            } else {
                Move move = Move.getByName(s + "_");
                cube.move(move);
            }
        }
    }

    private static void createOllHashMap() {
        HashSet<Integer> keys = new HashSet<>();
        try (FileWriter fw = new FileWriter("src/Solver/OllMap.java")) {

            fw.write("package Solver;");
            fw.write("\n\n");

            fw.write("import java.util.HashMap;");
            fw.write("\n\n");

            fw.write("public class OllMap {");
            fw.write("\n\n");

            fw.write("    public static final HashMap<Integer, Integer> ollMap = new HashMap<>() {");
            fw.write("\n");

            fw.write("		{");
            fw.write("\n");

            int index = 0;
            Cube cube = Cube.Solved();

            for (String oll : SolverTables.OLL) {
                makeReverseMoves(oll, cube);
                for (int i = 0; i < 4; i++) {
                    int codex = (cube.cornerOrientation[0] << 0) +
                            (cube.edgeOrientation[0] << 2) +
                            (cube.cornerOrientation[1] << 4) +
                            (cube.edgeOrientation[1] << 6) +
                            (cube.cornerOrientation[2] << 8) +
                            (cube.edgeOrientation[2] << 10) +
                            (cube.cornerOrientation[3] << 12) +
                            (cube.edgeOrientation[3] << 14);

                    if (!keys.contains(codex)) {
                        keys.add(codex);
                        fw.write(String.format("			put(0x%04x, %d);", codex, index));
                        // fw.write(" put(" + (codex) + ", " + index + ");");
                        fw.write("\n");
                    }
                    index++;

                    makeMoves("U", cube);
                }
                makeMoves(oll, cube);
                cube.isSolved();
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

            for (String pll : SolverTables.PLL) {
                for (int j = 0; j < 4; j++) {
                    makeReverseMoves(pll, cube);
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
                        makeMoves("U", cube);
                    }
                    makeMoves(pll, cube);
                    makeMoves("U", cube);
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
        createOllHashMap();
        createPllHashMap();
    }

}
