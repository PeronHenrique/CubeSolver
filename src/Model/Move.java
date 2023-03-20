package Model;

import java.util.Random;

public enum Move {
    U, U_, U2,
    D, D_, D2,
    L, L_, L2,
    R, R_, R2,
    B, B_, B2,
    F, F_, F2,
    NONE,
    X, X_, X2,
    Y, Y_, Y2,
    Z, Z_, Z2,
    M, M_, M2,
    E, E_, E2,
    S, S_, S2,
    UW, UW_, UW2,
    DW, DW_, DW2,
    LW, LW_, LW2,
    RW, RW_, RW2,
    BW, BW_, BW2,
    FW, FW_, FW2,;

    public static Move getByIndex(int index) {
        for (Move move : Move.values()) {
            if (move.ordinal() == index)
                return move;
        }

        return Move.NONE;
    }

    public static Move getByName(String name) {
        for (Move move : Move.values()) {
            if (move.name().equals(name))
                return move;
        }

        return Move.NONE;
    }

    public static Move[] getMoves(String algorithm) {
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

        String[] movesStr = algorithm.split(" ");
        Move[] moves = new Move[movesStr.length];

        for (int i = 0; i < moves.length; i++)
            moves[i] = Move.getByName(movesStr[i]);

        return moves;
    }

    public static Move[] getUndoMoves(String algorithm) {
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


        String[] movesStr = algorithm.split(" ");
        Move[] moves = new Move[movesStr.length];

        for (int i = movesStr.length - 1; i >= 0; i--) {
            String s = movesStr[i];
            if (s.endsWith("2")) {
                Move move = Move.getByName(s);
                moves[movesStr.length - 1 - i] = move;
            } else if (s.endsWith("_")) {
                String m = s.replace("_", "");
                Move move = Move.getByName(m);
                moves[movesStr.length - 1 - i] = move;
            } else {
                Move move = Move.getByName(s + "_");
                moves[movesStr.length - 1 - i] = move;
            }
        }

        return moves;
    }

    public static Move[] getRandomMoves(int size) {
        if (size <= 0)
            return new Move[0];

        Move[] moves = new Move[size + 2];

        moves[0] = Move.getByIndex(new Random().nextInt(Move.X.ordinal(), Move.M.ordinal()));
        int index = 1;
        
        while (index < size+1) {
            Move move = Move.getByIndex(new Random().nextInt(Move.NONE.ordinal()));

            if (isValidMove(move, moves, index)) {
                moves[index] = move;
                index++;
            }
        }

        moves[size + 1] = Move.getByIndex(new Random().nextInt(Move.X.ordinal(), Move.M.ordinal()));
        return moves;
    }

    private static boolean isValidMove(Move move, Move[] moves, int index) {
        if (index == 1)
            return true;

        Move last = moves[index - 1];

        if (index == 2)
            return checkLastMove(move, last);

        Move secondLast = moves[index - 2];

        return checkSecendLastMove(move, last, secondLast);
    }

    private static boolean checkSecendLastMove(Move move, Move last, Move secondLast) {
        if (!checkLastMove(move, last))
            return false;

        switch (secondLast) {
            case B:
            case B2:
            case B_:
                if (last == F || last == F_ || last == F2)
                    return !(move == B || move == B_ || move == B2);
                return true;
            case D:
            case D2:
            case D_:
                if (last == U || last == U_ || last == U2)
                    return !(move == D || move == D_ || move == D2);
                return true;
            case F:
            case F2:
            case F_:
                if (last == B || last == B_ || last == B2)
                    return !(move == F || move == F_ || move == F2);
                return true;
            case L:
            case L2:
            case L_:
                if (last == R || last == R_ || last == R2)
                    return !(move == L || move == L_ || move == L2);
                return true;
            case R:
            case R2:
            case R_:
                if (last == L || last == L_ || last == L2)
                    return !(move == R || move == R_ || move == R2);
                return true;
            case U:
            case U2:
            case U_:
                if (last == D || last == D_ || last == D2)
                    return !(move == U || move == U_ || move == U2);
                return true;
            default:
            case NONE:
                return false;
        }
    }

    private static boolean checkLastMove(Move move, Move previus) {
        switch (previus) {
            case B:
            case B2:
            case B_:
                return !(move == B || move == B_ || move == B2);
            case D:
            case D2:
            case D_:
                return !(move == D || move == D_ || move == D2);
            case F:
            case F2:
            case F_:
                return !(move == F || move == F_ || move == F2);
            case L:
            case L2:
            case L_:
                return !(move == L || move == L_ || move == L2);
            case R:
            case R2:
            case R_:
                return !(move == R || move == R_ || move == R2);
            case U:
            case U2:
            case U_:
                return !(move == U || move == U_ || move == U2);
            default:
            case NONE:
                return false;
        }
    }

    public static String getStringNotation(Move[] moves){
        String s = "";
        for (Move move : moves) s += move.name() + " ";
        s = s.replace("RW", "r");
        s = s.replace("LW", "l");
        s = s.replace("UW", "u");
        s = s.replace("DW", "d");
        s = s.replace("BW", "b");
        s = s.replace("FW", "f");
        s = s.replace("X", "x");
        s = s.replace("Y", "y");
        s = s.replace("Z", "z");
        s = s.replace("_", "'");
        return s.trim();
    }
}
