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
    private Corner[] corners;
    private int[] cornerOrientation;
    private Edge[] edges;
    private int[] edgeOrientation;
    private final int scrambleSpeed = 1;
    private final int solveSpeed = 1;

    public Solver(ProcessingRenderer renderer) {
        this.renderer = renderer;
        cube = Cube.Solved();
        corners = new Corner[8];
        cornerOrientation = new int[8];
        edges = new Edge[12];
        edgeOrientation = new int[12];
    }

    @Override
    public void run() {
        System.out.println("Scramble:");
        scrambleCube(20);
        // makeMoves("R2 L2 D2 L' F R' U L2 F' B L' B D2 F L2 B' F U2 D F'", scrambleSpeed);
        System.out.println("\nReorienting:");
        makeMoves("y z2", scrambleSpeed);
        solveCube();
    }

    private void makeMoves(String algorithm, int maxStep) {
        algorithm = algorithm.toUpperCase();
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

    private void updateCube() {
        for (int i = 0; i < 8; i++) {
            corners[i] = cube.getCornerIndex(Corner.getByIndex(i));
            cornerOrientation[i] = cube.getCornerOrientation(Corner.getByIndex(i));
        }

        for (int i = 0; i < 12; i++) {
            edges[i] = cube.getEdgeIndex(Edge.getByIndex(i));
            edgeOrientation[i] = cube.getEdgeOrientation(Edge.getByIndex(i));
        }
    }

    private int getCornerPosition(Corner corner) {
        for (int i = 0; i < 8; i++) {
            if (corners[i] == corner)
                return i;
        }

        return -1;
    }

    private int getEdgePosition(Edge edge) {
        for (int i = 0; i < 12; i++) {
            if (edges[i] == edge)
                return i;
        }

        return -1;
    }

    private void solveCube() {
        // TODO: solve cube
        System.out.println("\nCross:");
        solveCross();
        System.out.println("\nF2L:");
        solveF2L();
    }

    private void solveCross() {
        for (int i = 0; i < 4; i++) {
            makeMoves("y", solveSpeed);
            updateCube();
            int position = getEdgePosition(Edge.DF);
            System.out.println("\n" + (position * 2 + edgeOrientation[position]));
            makeMoves(Algs.cross[position * 2 + edgeOrientation[position]], solveSpeed);
        }
    }

    private void solveF2L() {
        for (int i = 0; i < 4; i++) {
            makeMoves("y", solveSpeed);
            updateCube();
            setupF2L();
            updateCube();
            makeMoves(Algs.F2L[calculateF2Lindex()], solveSpeed);
        }
    }

    private void setupF2L() {
        int cornerPosition = getCornerPosition(Corner.DRF);

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

        updateCube();
        int edgePosition = getEdgePosition(Edge.FR);

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
        int edgePosition = getEdgePosition(Edge.FR);
        System.out.println("\n" + (6 * edgePosition + 3 * edgeOrientation[edgePosition] + cornerOrientation[2]));
        return 6 * edgePosition + 3 * edgeOrientation[edgePosition] + cornerOrientation[2];
    }
}
