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
        cube = Cube.Solved();
    }

    @Override
    public void run() {
        scrambleCube(20);
        makeMoves("y z2");
        solveCube();
    }

    private void makeMoves(String algorithm) {
        algorithm = algorithm.toUpperCase();
        algorithm = algorithm.replace("'", "_");

        ArrayList<Move> moves = new ArrayList<>();
        for (String s : algorithm.split(" ")) {
            Move move = Move.getByName(s);
            moves.add(move);
            cube.move(move);
        }
        renderer.addMoves(moves);
    }

    private void scrambleCube(int nMoves) {
        ArrayList<Move> scramble = Move.getRandomMoves(nMoves);
        for (Move move : scramble) 
            cube.move(move);
        
        renderer.addMoves(scramble);
    }

    private void solveCube() {
        // TODO: solve cube

        // for (int i = 0; i < 8; i++) {
        //     Corner corner = Corner.getByIndex(i);
        //     System.out.print(cube.getCornerIndex(corner));
        //     System.out.print(", ");
        //     System.out.println(cube.getCornerOrientation(corner));
        // }

        // for (int i = 0; i < 12; i++) {
        //     Edge edge = Edge.getByIndex(i);
        //     System.out.print(cube.getEdgeIndex(edge));
        //     System.out.print(", ");
        //     System.out.println(cube.getEdgeOrientation(edge));
        // }
    }

}
