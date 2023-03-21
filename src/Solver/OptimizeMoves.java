package Solver;

import java.util.ArrayList;
import java.util.Arrays;

import Model.Move;

public class OptimizeMoves {
    
    public static String removeCubeRotations(String solution) {
        ArrayList<Move> moves = new ArrayList<>(Arrays.asList(Move.getReversedMoves(solution)));

        for (int i = 0; i < moves.size(); i++) {
            if (isRotationMove(moves.get(i))) {
                for (int j = i - 1; j >= 0; j--) {
                    moves.set(j, rotateMove(moves.get(j), moves.get(i)));
                }

                moves.remove(i);
                i--;
            }
        }

        String reversedSolution = Move.getStringNotation(moves.toArray(new Move[moves.size()]));
        return Move.getStringNotation(Move.getReversedMoves(reversedSolution));
    }

    public static String simplifyRepeatedMoves(String solution) {
        // TODO: make optimizations to simplify repeated moves

        return solution;
    }

    public static String makeMoveCancelations(String solution) {
        // TODO: make optimizations to make move cancelations

        return solution;
    }

    public static boolean isRotationMove(Move move) {
        switch (move) {
            case X:
            case X2:
            case X_:
            case Y:
            case Y2:
            case Y_:
            case Z:
            case Z2:
            case Z_:
                return true;
            default:
                return false;
        }
    }

    private static Move rotateMove(Move move, Move rotation) {
        switch (rotation) {
            case X:
                switch (move) {
                    case B:
                        return Move.D;
                    case B2:
                        return Move.D2;
                    case BW:
                        return Move.DW;
                    case BW2:
                        return Move.DW2;
                    case BW_:
                        return Move.DW_;
                    case B_:
                        return Move.D_;
                    case D:
                        return Move.F;
                    case D2:
                        return Move.F2;
                    case DW:
                        return Move.FW;
                    case DW2:
                        return Move.FW2;
                    case DW_:
                        return Move.FW_;
                    case D_:
                        return Move.F_;
                    case E:
                        return Move.S;
                    case E2:
                        return Move.S2;
                    case E_:
                        return Move.S_;
                    case F:
                        return Move.U;
                    case F2:
                        return Move.U2;
                    case FW:
                        return Move.UW;
                    case FW2:
                        return Move.UW2;
                    case FW_:
                        return Move.UW_;
                    case F_:
                        return Move.U_;
                    case S:
                        return Move.E_;
                    case S2:
                        return Move.E2;
                    case S_:
                        return Move.E;
                    case U:
                        return Move.B;
                    case U2:
                        return Move.B2;
                    case UW:
                        return Move.BW;
                    case UW2:
                        return Move.BW2;
                    case UW_:
                        return Move.BW_;
                    case U_:
                        return Move.B_;
                    default:
                        return move;
                }
            case X2:
                switch (move) {
                    case B:
                        return Move.F;
                    case B2:
                        return Move.F2;
                    case BW:
                        return Move.FW;
                    case BW2:
                        return Move.FW2;
                    case BW_:
                        return Move.FW_;
                    case B_:
                        return Move.F_;
                    case D:
                        return Move.U;
                    case D2:
                        return Move.U2;
                    case DW:
                        return Move.UW;
                    case DW2:
                        return Move.UW2;
                    case DW_:
                        return Move.UW_;
                    case D_:
                        return Move.U_;
                    case E:
                        return Move.E_;
                    case E2:
                        return Move.E2;
                    case E_:
                        return Move.E;
                    case F:
                        return Move.B;
                    case F2:
                        return Move.B2;
                    case FW:
                        return Move.BW;
                    case FW2:
                        return Move.BW2;
                    case FW_:
                        return Move.BW_;
                    case F_:
                        return Move.B_;
                    case S:
                        return Move.S_;
                    case S2:
                        return Move.S2;
                    case S_:
                        return Move.S;
                    case U:
                        return Move.D;
                    case U2:
                        return Move.D2;
                    case UW:
                        return Move.DW;
                    case UW2:
                        return Move.DW2;
                    case UW_:
                        return Move.DW_;
                    case U_:
                        return Move.D_;
                    default:
                        return move;
                }
            case X_:
                switch (move) {
                    case B:
                        return Move.U;
                    case B2:
                        return Move.U2;
                    case BW:
                        return Move.UW;
                    case BW2:
                        return Move.UW2;
                    case BW_:
                        return Move.UW_;
                    case B_:
                        return Move.U_;
                    case D:
                        return Move.B;
                    case D2:
                        return Move.B2;
                    case DW:
                        return Move.BW;
                    case DW2:
                        return Move.BW2;
                    case DW_:
                        return Move.BW_;
                    case D_:
                        return Move.B_;
                    case E:
                        return Move.S_;
                    case E2:
                        return Move.S2;
                    case E_:
                        return Move.S;
                    case F:
                        return Move.D;
                    case F2:
                        return Move.D2;
                    case FW:
                        return Move.DW;
                    case FW2:
                        return Move.DW2;
                    case FW_:
                        return Move.DW_;
                    case F_:
                        return Move.D_;
                    case S:
                        return Move.E;
                    case S2:
                        return Move.E2;
                    case S_:
                        return Move.E_;
                    case U:
                        return Move.F;
                    case U2:
                        return Move.F2;
                    case UW:
                        return Move.FW;
                    case UW2:
                        return Move.FW2;
                    case UW_:
                        return Move.FW_;
                    case U_:
                        return Move.F_;
                    default:
                        return move;
                }

            case Y:
                switch (move) {
                    case B:
                        return Move.R;
                    case B2:
                        return Move.R2;
                    case BW:
                        return Move.RW;
                    case BW2:
                        return Move.RW2;
                    case BW_:
                        return Move.RW_;
                    case B_:
                        return Move.R_;
                    case F:
                        return Move.L;
                    case F2:
                        return Move.L2;
                    case FW:
                        return Move.LW;
                    case FW2:
                        return Move.LW2;
                    case FW_:
                        return Move.LW_;
                    case F_:
                        return Move.L_;
                    case L:
                        return Move.B;
                    case L2:
                        return Move.B2;
                    case LW:
                        return Move.BW;
                    case LW2:
                        return Move.BW2;
                    case LW_:
                        return Move.BW_;
                    case L_:
                        return Move.B_;
                    case M:
                        return Move.S_;
                    case M2:
                        return Move.S2;
                    case M_:
                        return Move.S;
                    case R:
                        return Move.F;
                    case R2:
                        return Move.F2;
                    case RW:
                        return Move.FW;
                    case RW2:
                        return Move.FW2;
                    case RW_:
                        return Move.FW_;
                    case R_:
                        return Move.F_;
                    case S:
                        return Move.M;
                    case S2:
                        return Move.M2;
                    case S_:
                        return Move.M_;
                    default:
                        return move;
                }
            case Y2:
                switch (move) {
                    case B:
                        return Move.F;
                    case B2:
                        return Move.F2;
                    case BW:
                        return Move.FW;
                    case BW2:
                        return Move.FW2;
                    case BW_:
                        return Move.FW_;
                    case B_:
                        return Move.F_;
                    case F:
                        return Move.B;
                    case F2:
                        return Move.B2;
                    case FW:
                        return Move.BW;
                    case FW2:
                        return Move.BW2;
                    case FW_:
                        return Move.BW_;
                    case F_:
                        return Move.B_;
                    case L:
                        return Move.R;
                    case L2:
                        return Move.R2;
                    case LW:
                        return Move.RW;
                    case LW2:
                        return Move.RW2;
                    case LW_:
                        return Move.RW_;
                    case L_:
                        return Move.R_;
                    case M:
                        return Move.M_;
                    case M2:
                        return Move.M2;
                    case M_:
                        return Move.M;
                    case R:
                        return Move.L;
                    case R2:
                        return Move.L2;
                    case RW:
                        return Move.LW;
                    case RW2:
                        return Move.LW2;
                    case RW_:
                        return Move.LW_;
                    case R_:
                        return Move.L_;
                    case S:
                        return Move.S_;
                    case S2:
                        return Move.S2;
                    case S_:
                        return Move.S;
                    default:
                        return move;
                }
            case Y_:
                switch (move) {
                    case B:
                        return Move.L;
                    case B2:
                        return Move.L2;
                    case BW:
                        return Move.LW;
                    case BW2:
                        return Move.LW2;
                    case BW_:
                        return Move.LW_;
                    case B_:
                        return Move.L_;
                    case F:
                        return Move.R;
                    case F2:
                        return Move.R2;
                    case FW:
                        return Move.RW;
                    case FW2:
                        return Move.RW2;
                    case FW_:
                        return Move.RW_;
                    case F_:
                        return Move.R_;
                    case L:
                        return Move.F;
                    case L2:
                        return Move.F2;
                    case LW:
                        return Move.FW;
                    case LW2:
                        return Move.FW2;
                    case LW_:
                        return Move.FW_;
                    case L_:
                        return Move.F_;
                    case M:
                        return Move.S;
                    case M2:
                        return Move.S2;
                    case M_:
                        return Move.S_;
                    case R:
                        return Move.B;
                    case R2:
                        return Move.B2;
                    case RW:
                        return Move.BW;
                    case RW2:
                        return Move.BW2;
                    case RW_:
                        return Move.BW_;
                    case R_:
                        return Move.B_;
                    case S:
                        return Move.M_;
                    case S2:
                        return Move.M2;
                    case S_:
                        return Move.M;
                    default:
                        return move;
                }
                
            case Z:
                switch (move) {
                    case D:
                        return Move.L;
                    case D2:
                        return Move.L2;
                    case DW:
                        return Move.LW;
                    case DW2:
                        return Move.LW2;
                    case DW_:
                        return Move.LW_;
                    case D_:
                        return Move.L_;
                    case E:
                        return Move.M;
                    case E2:
                        return Move.M2;
                    case E_:
                        return Move.M_;
                    case L:
                        return Move.U;
                    case L2:
                        return Move.U2;
                    case LW:
                        return Move.UW;
                    case LW2:
                        return Move.UW2;
                    case LW_:
                        return Move.UW_;
                    case L_:
                        return Move.U_;
                    case M:
                        return Move.E_;
                    case M2:
                        return Move.E2;
                    case M_:
                        return Move.E;
                    case R:
                        return Move.D;
                    case R2:
                        return Move.D2;
                    case RW:
                        return Move.DW;
                    case RW2:
                        return Move.DW2;
                    case RW_:
                        return Move.DW_;
                    case R_:
                        return Move.D_;
                    case U:
                        return Move.R;
                    case U2:
                        return Move.R2;
                    case UW:
                        return Move.RW;
                    case UW2:
                        return Move.RW2;
                    case UW_:
                        return Move.RW_;
                    case U_:
                        return Move.R_;
                    default:
                        return move;
                }
            case Z2:
                switch (move) {
                    case D:
                        return Move.U;
                    case D2:
                        return Move.U2;
                    case DW:
                        return Move.UW;
                    case DW2:
                        return Move.UW2;
                    case DW_:
                        return Move.UW_;
                    case D_:
                        return Move.U_;
                    case E:
                        return Move.E_;
                    case E2:
                        return Move.E2;
                    case E_:
                        return Move.E;
                    case L:
                        return Move.R;
                    case L2:
                        return Move.R2;
                    case LW:
                        return Move.RW;
                    case LW2:
                        return Move.RW2;
                    case LW_:
                        return Move.RW_;
                    case L_:
                        return Move.R_;
                    case M:
                        return Move.M_;
                    case M2:
                        return Move.M2;
                    case M_:
                        return Move.M;
                    case R:
                        return Move.L;
                    case R2:
                        return Move.L2;
                    case RW:
                        return Move.LW;
                    case RW2:
                        return Move.LW2;
                    case RW_:
                        return Move.LW_;
                    case R_:
                        return Move.L_;
                    case U:
                        return Move.D;
                    case U2:
                        return Move.D2;
                    case UW:
                        return Move.DW;
                    case UW2:
                        return Move.DW2;
                    case UW_:
                        return Move.DW_;
                    case U_:
                        return Move.D_;
                    default:
                        return move;
                }
            case Z_:
                switch (move) {
                    case D:
                        return Move.R;
                    case D2:
                        return Move.R2;
                    case DW:
                        return Move.RW;
                    case DW2:
                        return Move.RW2;
                    case DW_:
                        return Move.RW_;
                    case D_:
                        return Move.R_;
                    case E:
                        return Move.M_;
                    case E2:
                        return Move.M2;
                    case E_:
                        return Move.M;
                    case L:
                        return Move.D;
                    case L2:
                        return Move.D2;
                    case LW:
                        return Move.DW;
                    case LW2:
                        return Move.DW2;
                    case LW_:
                        return Move.DW_;
                    case L_:
                        return Move.D_;
                    case M:
                        return Move.E;
                    case M2:
                        return Move.E2;
                    case M_:
                        return Move.E_;
                    case R:
                        return Move.U;
                    case R2:
                        return Move.U2;
                    case RW:
                        return Move.UW;
                    case RW2:
                        return Move.UW2;
                    case RW_:
                        return Move.UW_;
                    case R_:
                        return Move.U_;
                    case U:
                        return Move.L;
                    case U2:
                        return Move.L2;
                    case UW:
                        return Move.LW;
                    case UW2:
                        return Move.LW2;
                    case UW_:
                        return Move.LW_;
                    case U_:
                        return Move.L_;
                    default:
                        return move;
                }
            default:
                return move;
        }
    }

}
