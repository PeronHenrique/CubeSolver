package Solver;

import java.util.ArrayList;

import Model.Corner;
import Model.Cube;
import Model.Edge;
import Model.Move;
import UI.ProcessingRenderer;

public class Solver implements Runnable {

    private Cube cube;
    private ProcessingRenderer renderer;

    public Solver(ProcessingRenderer renderer) {
        this.renderer = renderer;
        this.cube = Cube.Solved();
    }

    private final String testScramble = "";
    private final int scrambleSpeed = 50;
    private final int solveSpeed = 50;

    @Override
    public void run() {
        System.out.println("Scramble:");
        if ("".equals(testScramble))
            scrambleCube(20);
        else
            makeMoves(testScramble, scrambleSpeed);

        System.out.println("\nReorienting:");
        makeMoves("y z2", scrambleSpeed);

        System.out.println("\nCross:");
        solveCross();

        System.out.println("\nF2L:");
        solveF2L();

        // TODO: solve LL
        System.out.println("\nOLL:");
        solveOLL();
    }

    private void makeMoves(String algorithm, int maxStep) {
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

        ArrayList<Move> moves = new ArrayList<>();
        for (String s : algorithm.split(" ")) {
            Move move = Move.getByName(s);
            moves.add(move);
            cube.move(move);
        }

        renderer.addMoves(moves, maxStep);
    }

    private void scrambleCube(int nMoves) {
        ArrayList<Move> scramble = Move.getRandomMoves(nMoves);
        for (Move move : scramble)
            cube.move(move);

        renderer.addMoves(scramble, scrambleSpeed);
    }

    private void solveCross() {
        for (int i = 0; i < 4; i++) {
            if (i != 0)
                makeMoves("y", solveSpeed);
            int position = cube.getEdgePosition(Edge.DF);
            // System.out.println("\n" + (position * 2 + edgeOrientation[position]));
            makeMoves(Algs.cross[position * 2 + cube.edgeOrientation[position]], solveSpeed);
        }
    }

    private void solveF2L() {
        for (int i = 0; i < 4; i++) {
            if (i != 0)
                makeMoves("y", solveSpeed);
            setupF2L();
            makeMoves(Algs.F2L[calculateF2Lindex()], solveSpeed);
        }
    }

    private void setupF2L() {
        int cornerPosition = cube.getCornerPosition(Corner.DRF);

        if (cornerPosition < 4) {
            if (cornerPosition == 0)
                makeMoves("U2", solveSpeed);

            if (cornerPosition == 1)
                makeMoves("U", solveSpeed);

            if (cornerPosition == 3)
                makeMoves("U'", solveSpeed);
        } else {
            if (cornerPosition == 4)
                makeMoves("L U2 L'", solveSpeed);

            if (cornerPosition == 5)
                makeMoves("R' U2 R U'", solveSpeed);

            if (cornerPosition == 6)
                makeMoves("R U R' U'", solveSpeed);

            if (cornerPosition == 7)
                makeMoves("L' U' L", solveSpeed);
        }

        int edgePosition = cube.getEdgePosition(Edge.FR);

        if (edgePosition >= 4) {
            if (edgePosition == 4)
                makeMoves("L U' L' U", solveSpeed);

            if (edgePosition == 5)
                makeMoves("R' U R", solveSpeed);

            if (edgePosition == 6)
                makeMoves("R U' R' U2", solveSpeed);

            if (edgePosition == 7)
                makeMoves("L' U' L U", solveSpeed);
        }
    }

    private int calculateF2Lindex() {
        int edgePosition = cube.getEdgePosition(Edge.FR);
        // System.out.println("\n" + (6 * edgePosition + 3 *
        // edgeOrientation[edgePosition] + cornerOrientation[2]));
        return 6 * edgePosition + 3 * cube.edgeOrientation[edgePosition] + cube.cornerOrientation[2];
    }

    private void solveOLL() {
        makeMoves(Algs.F2L[calculatOLLindex()], solveSpeed);
    }

    private int calculatOLLindex() {
        int codex = 0;
        int index = 0;

        codex = (cube.cornerOrientation[0] << 0) +
                (cube.edgeOrientation[0] << 2) +
                (cube.cornerOrientation[1] << 4) +
                (cube.edgeOrientation[1] << 6) +
                (cube.cornerOrientation[2] << 8) +
                (cube.edgeOrientation[2] << 10) +
                (cube.cornerOrientation[3] << 12) +
                (cube.edgeOrientation[3] << 14);

        switch (codex) {
            default:
            case 0:
                index = 0;
                break;
        }

        System.out.println("\n" + index);
        return index;
    }
}
