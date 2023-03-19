package Model;

import java.util.ArrayList;
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

    public static String getRandomMoves(int size) {
        ArrayList<Move> moves = new ArrayList<>(size);
        String s = " ";

        if (size <= 0)
            return s;

        while (moves.size() < size) {
            Move move = Move.getByIndex(new Random().nextInt(Move.NONE.ordinal()));

            if (isValidMove(move, moves)) {
                s += move.name() + " ";
                moves.add(move);
            }
        }

        for (int i = 0; i < 2; i++) {
            Move move = Move.getByIndex(new Random().nextInt(Move.X.ordinal(), Move.M.ordinal()));
            s += move.name() + " ";
            moves.add(move);
        }

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

        return s;
    }

    private static boolean isValidMove(Move move, ArrayList<Move> moves) {
        if (moves.size() == 0)
            return true;

        Move last = moves.get(moves.size() - 1);

        if (moves.size() == 1)
            return checkLastMove(move, last);

        Move secondLast = moves.get(moves.size() - 2);

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

}
