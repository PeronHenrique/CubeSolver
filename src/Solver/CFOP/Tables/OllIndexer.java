package Solver.CFOP.Tables;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import Model.Cube;
public class OllIndexer {

    private static final String OllAlgs[] = {
            "",
            "R U2 R2 F R F' U2 R' F R F'",
            "r U r' U2 r U2 R' U2 R U' r'",
            "M R U R' U r U2 r' U M'",
            "M U' r U2 r' U' R U' R' M'",
            "l' U2 L U L' U l",
            "r U2 R' U' R U' r'",
            "r U R' U R U2 r'",
            "l' U' L U' L' U2 l",
            "r' R2 U2 R' U' R U' R' U' M'",
            "R U R' B' R B U' B' R' B",
            "M R U R' U R U2 R' U M'",
            "M' R' U' R U' R' U2 R U' M",
            "r U' r' U' r U r' F' U F",
            "R' F R U R' F' R F U' F'",
            "l' U' M U' L U l' U l",
            "r U M U R' U' r U' r'",
            "F R' F' R2 r' U R U' R' U' M'",
            "F R U R' d R' U2 R' F R F'",
            "M U R U R' U' M' R' F R F'",
            "r U R' U' M2 U R U' R' U' M'",
            "R U2 R' U' R U R' U' R U' R'",
            "R U2 R2 U' R2 U' R2 U2 R",
            "R2 D' R U2 R' D R U2 R",
            "r U R' U' r' F R F'",
            "F' r U R' U' r' F R",
            "R U2 R' U' R U' R'",
            "R U R' U R U2 R'",
            "M' U M U2 M' U M",
            "M U R U R' U' R' F R F' M'",
            "F R' F R2 U' R' U' R U R' F2",
            "S R U R' U' f' U' F",
            "L U F' U' L' U L F L'",
            "R U R' U' R' F R F'",
            "R U R' U' B' R' F R F' B",
            "R U2 R2 F R F' R U2 R'",
            "R U R' U' F' U2 F U R U R'",
            "F R' F' R U R U' R'",
            "R' U2 r' D' r U2 r' D R r",
            "L F' L' U' L U F U' L'",
            "R' F R U R' U' F' U R",
            "R U' R' U2 R U B U' B' U' R'",
            "M U F R U R' U' F' M'",
            "F' U' L' U L F",
            "F U R U' R' F'",
            "F R U R' U' F'",
            "R' U' R' F R F' U R",
            "F' L' U' L U L' U' L U F",
            "F R U R' U' R U R' U' F'",
            "r U' r2 U r2 U r2 U' r",
            "r' U r2 U' r2 U' r2 U r'",
            "F U R U' R' U R U' R' F'",
            "R U R' U R U' B U' B' R'",
            "l' U2 L U L' U' L U L' U l",
            "r U2 R' U' R U R' U' R U' r'",
            "R U2 R2 U' R U' R' U2 F R F'",
            "F R U R' U' R F' r U R' U' r'",
            "R U R' U' M' U R U' r'",
    };

    private static int calculateOllCodex(Cube cube){
       return (cube.cornerOrientation[0] << 0) +
        (cube.edgeOrientation[0] << 2) +
        (cube.cornerOrientation[1] << 4) +
        (cube.edgeOrientation[1] << 6) +
        (cube.cornerOrientation[2] << 8) +
        (cube.edgeOrientation[2] << 10) +
        (cube.cornerOrientation[3] << 12) +
        (cube.edgeOrientation[3] << 14);
    }

    public static String getMovesOLL(Cube cube) {
        String moves = "";
        Integer index = OllMap.ollMap.get(calculateOllCodex(cube));

        if (index == null)
            return moves;

        switch (index % 4) {
            default:
            case 0:
                break;
            case 1:
                moves += "y' ";
                break;
            case 2:
                moves += "y2 ";
                break;
            case 3:
                moves += "y ";
                break;
        }
        
        moves += OllAlgs[(index / 4)];
        return moves;
    }

    private static void createOllHashMap() {
        HashSet<Integer> keys = new HashSet<>();
        try (FileWriter fw = new FileWriter("src/Solver/CFOP/Tables/OllMap.java")) {

            fw.write("package Solver.CFOP.Tables;");
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

            for (String oll : OllAlgs) {
                cube.undoMoves(oll);
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
                        fw.write("\n");
                    }
                    index++;

                    cube.doMoves("U");
                }
                cube.doMoves(oll);
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

    public static void main(String[] args) {
        createOllHashMap();
    }
}
