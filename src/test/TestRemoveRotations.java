package test;

import Model.Cube;
import Model.Move;
import Solver.OptimizeMoves;

public class TestRemoveRotations {

    public static void main(String[] args) throws Exception {
        for (Move m : Move.values()) {
            if (!OptimizeMoves.isRotationMove(m)) {
                verifyRotation(m.name(), Move.X);
                verifyRotation(m.name(), Move.X2);
                verifyRotation(m.name(), Move.X_);
                verifyRotation(m.name(), Move.Y);
                verifyRotation(m.name(), Move.Y2);
                verifyRotation(m.name(), Move.Y_);
                verifyRotation(m.name(), Move.Z);
                verifyRotation(m.name(), Move.Z2);
                verifyRotation(m.name(), Move.Z_);
            }
        }
    }

    private static void verifyRotation(String scramble, Move rotation) {
        Cube control = Cube.Scramble(rotation.name() + " " + scramble);
        String equivalentScramble = OptimizeMoves.removeCubeRotations(rotation.name() + " " + scramble);

        Cube test = Cube.Scramble(equivalentScramble);
        test.doMoves(rotation.name());

        if (!Cube.compare(test, control)) {
            System.out.println("Scramble: " + scramble);
            System.out.println("Faild test with rotation: " + rotation.name());
        }
    }

}
