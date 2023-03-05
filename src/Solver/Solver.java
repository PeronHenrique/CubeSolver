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
    private final int scrambleSpeed = 1;
    private final int solveSpeed = 1;

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

        System.out.println("\nOLL:");
        solveOLL();
        
        System.out.println("\nPLL:");
        solvePLL();
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
        makeMoves(Algs.OLL[calculatOLLindex()], solveSpeed);
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
            case 0x6565:
                index = 0;
                break;
            case 0x5656:
                index = 1;
                break;
            // case 0x6565:
            // index = 2;
            // break;
            // case 0x5656:
            // index = 3;
            // break;

            case 0x6556:
                index = 4;
                break;
            case 0x5566:
                index = 5;
                break;
            case 0x5665:
                index = 6;
                break;
            case 0x6655:
                index = 7;
                break;

            case 0x4666:
                index = 8;
                break;
            case 0x6664:
                index = 9;
                break;
            case 0x6646:
                index = 10;
                break;
            case 0x6466:
                index = 11;
                break;

            case 0x5455:
                index = 12;
                break;
            case 0x4555:
                index = 13;
                break;
            case 0x5554:
                index = 14;
                break;
            case 0x5545:
                index = 15;
                break;

            case 0x2660:
                index = 16;
                break;
            case 0x6602:
                index = 17;
                break;
            case 0x6026:
                index = 18;
                break;
            case 0x0266:
                index = 19;
                break;

            case 0x5501:
                index = 20;
                break;
            case 0x5015:
                index = 21;
                break;
            case 0x0155:
                index = 22;
                break;
            case 0x1550:
                index = 23;
                break;

            case 0x0662:
                index = 24;
                break;
            case 0x6620:
                index = 25;
                break;
            case 0x6206:
                index = 26;
                break;
            case 0x2066:
                index = 27;
                break;

            case 0x5411:
                index = 28;
                break;
            case 0x4115:
                index = 29;
                break;
            case 0x1154:
                index = 30;
                break;
            case 0x1541:
                index = 31;
                break;

            case 0x1450:
                index = 32;
                break;
            case 0x4501:
                index = 33;
                break;
            case 0x5014:
                index = 34;
                break;
            case 0x0145:
                index = 35;
                break;

            case 0x2246:
                index = 36;
                break;
            case 0x2462:
                index = 37;
                break;
            case 0x4622:
                index = 38;
                break;
            case 0x6224:
                index = 39;
                break;

            case 0x2642:
                index = 40;
                break;
            case 0x6422:
                index = 41;
                break;
            case 0x4226:
                index = 42;
                break;
            case 0x2264:
                index = 43;
                break;

            case 0x5510:
                index = 44;
                break;
            case 0x5105:
                index = 45;
                break;
            case 0x1055:
                index = 46;
                break;
            case 0x0551:
                index = 47;
                break;

            case 0x0626:
                index = 48;
                break;
            case 0x6260:
                index = 49;
                break;
            case 0x2606:
                index = 50;
                break;
            case 0x6062:
                index = 51;
                break;

            case 0x1415:
                index = 52;
                break;
            case 0x4151:
                index = 53;
                break;
            case 0x1514:
                index = 54;
                break;
            case 0x5141:
                index = 55;
                break;

            case 0x2624:
                index = 56;
                break;
            case 0x6242:
                index = 57;
                break;
            case 0x2426:
                index = 58;
                break;
            case 0x4262:
                index = 59;
                break;

            case 0x1505:
                index = 60;
                break;
            case 0x5051:
                index = 61;
                break;
            case 0x0515:
                index = 62;
                break;
            case 0x5150:
                index = 63;
                break;

            case 0x5464:
                index = 64;
                break;
            case 0x4645:
                index = 65;
                break;
            case 0x6454:
                index = 66;
                break;
            case 0x4546:
                index = 67;
                break;

            case 0x5644:
                index = 68;
                break;
            case 0x6445:
                index = 69;
                break;
            case 0x4456:
                index = 70;
                break;
            case 0x4564:
                index = 71;
                break;

            case 0x6544:
                index = 72;
                break;
            case 0x5446:
                index = 73;
                break;
            case 0x4465:
                index = 74;
                break;
            case 0x4654:
                index = 75;
                break;

            case 0x4444:
                index = 76;
                break;
            // case 0x4444:
            // index = 77;
            // break;
            // case 0x4444:
            // index = 78;
            // break;
            // case 0x4444:
            // index = 79;
            // break;

            case 0x1212:
                index = 80;
                break;
            case 0x2121:
                index = 81;
                break;
            // case 0x1212:
            // index = 82;
            // break;
            // case 0x2121:
            // index = 83;
            // break;

            case 0x2211:
                index = 84;
                break;
            case 0x2112:
                index = 85;
                break;
            case 0x1122:
                index = 86;
                break;
            case 0x1221:
                index = 87;
                break;

            case 0x0012:
                index = 88;
                break;
            case 0x0120:
                index = 89;
                break;
            case 0x1200:
                index = 90;
                break;
            case 0x2001:
                index = 91;
                break;

            case 0x1002:
                index = 92;
                break;
            case 0x0021:
                index = 93;
                break;
            case 0x0210:
                index = 94;
                break;
            case 0x2100:
                index = 95;
                break;

            case 0x0201:
                index = 96;
                break;
            case 0x2010:
                index = 97;
                break;
            case 0x0102:
                index = 98;
                break;
            case 0x1020:
                index = 99;
                break;

            case 0x1101:
                index = 100;
                break;
            case 0x1011:
                index = 101;
                break;
            case 0x0111:
                index = 102;
                break;
            case 0x1110:
                index = 103;
                break;

            case 0x0222:
                index = 104;
                break;
            case 0x2220:
                index = 105;
                break;
            case 0x2202:
                index = 106;
                break;
            case 0x2022:
                index = 107;
                break;
            case 0x0440:
                index = 108;
                break;
            case 0x4400:
                index = 109;
                break;
            case 0x4004:
                index = 110;
                break;
            case 0x0044:
                index = 111;
                break;

            case 0x1442:
                index = 112;
                break;
            case 0x4421:
                index = 113;
                break;
            case 0x4214:
                index = 114;
                break;
            case 0x2144:
                index = 115;
                break;

            case 0x0461:
                index = 116;
                break;
            case 0x4610:
                index = 117;
                break;
            case 0x6104:
                index = 118;
                break;
            case 0x1046:
                index = 119;
                break;

            case 0x5402:
                index = 120;
                break;
            case 0x4025:
                index = 121;
                break;
            case 0x0254:
                index = 122;
                break;
            case 0x2540:
                index = 123;
                break;

            case 0x0650:
                index = 124;
                break;
            case 0x6500:
                index = 125;
                break;
            case 0x5006:
                index = 126;
                break;
            case 0x0065:
                index = 127;
                break;

            case 0x1406:
                index = 128;
                break;
            case 0x4061:
                index = 129;
                break;
            case 0x0614:
                index = 130;
                break;
            case 0x6140:
                index = 131;
                break;

            case 0x0425:
                index = 132;
                break;
            case 0x4250:
                index = 133;
                break;
            case 0x2504:
                index = 134;
                break;
            case 0x5042:
                index = 135;
                break;

            case 0x5024:
                index = 136;
                break;
            case 0x0245:
                index = 137;
                break;
            case 0x2450:
                index = 138;
                break;
            case 0x4502:
                index = 139;
                break;

            case 0x6410:
                index = 140;
                break;
            case 0x4106:
                index = 141;
                break;
            case 0x1064:
                index = 142;
                break;
            case 0x0641:
                index = 143;
                break;

            case 0x1460:
                index = 144;
                break;
            case 0x4601:
                index = 145;
                break;
            case 0x6014:
                index = 146;
                break;
            case 0x0146:
                index = 147;
                break;

            case 0x0542:
                index = 148;
                break;
            case 0x5420:
                index = 149;
                break;
            case 0x4205:
                index = 150;
                break;
            case 0x2054:
                index = 151;
                break;

            case 0x0506:
                index = 152;
                break;
            case 0x5060:
                index = 153;
                break;
            case 0x0605:
                index = 154;
                break;
            case 0x6050:
                index = 155;
                break;

            case 0x2414:
                index = 156;
                break;
            case 0x4142:
                index = 157;
                break;
            case 0x1424:
                index = 158;
                break;
            case 0x4241:
                index = 159;
                break;

            case 0x0452:
                index = 160;
                break;
            case 0x4520:
                index = 161;
                break;
            case 0x5204:
                index = 162;
                break;
            case 0x2045:
                index = 163;
                break;

            case 0x1244:
                index = 164;
                break;
            case 0x2441:
                index = 165;
                break;
            case 0x4412:
                index = 166;
                break;
            case 0x4124:
                index = 167;
                break;

            case 0x6401:
                index = 168;
                break;
            case 0x4016:
                index = 169;
                break;
            case 0x0164:
                index = 170;
                break;
            case 0x1640:
                index = 171;
                break;

            case 0x0560:
                index = 172;
                break;
            case 0x5600:
                index = 173;
                break;
            case 0x6005:
                index = 174;
                break;
            case 0x0056:
                index = 175;
                break;

            case 0x2405:
                index = 176;
                break;
            case 0x4052:
                index = 177;
                break;
            case 0x0524:
                index = 178;
                break;
            case 0x5240:
                index = 179;
                break;

            case 0x4160:
                index = 180;
                break;
            case 0x1604:
                index = 181;
                break;
            case 0x6041:
                index = 182;
                break;
            case 0x0416:
                index = 183;
                break;

            case 0x5522:
                index = 184;
                break;
            case 0x5225:
                index = 185;
                break;
            case 0x2255:
                index = 186;
                break;
            case 0x2552:
                index = 187;
                break;

            case 0x2651:
                index = 188;
                break;
            case 0x6512:
                index = 189;
                break;
            case 0x5126:
                index = 190;
                break;
            case 0x1265:
                index = 191;
                break;

            case 0x6611:
                index = 192;
                break;
            case 0x6116:
                index = 193;
                break;
            case 0x1166:
                index = 194;
                break;
            case 0x1661:
                index = 195;
                break;

            case 0x6215:
                index = 196;
                break;
            case 0x2156:
                index = 197;
                break;
            case 0x1562:
                index = 198;
                break;
            case 0x5621:
                index = 199;
                break;

            case 0x1526:
                index = 200;
                break;
            case 0x5261:
                index = 201;
                break;
            case 0x2615:
                index = 202;
                break;
            case 0x6152:
                index = 203;
                break;

            case 0x5162:
                index = 204;
                break;
            case 0x1625:
                index = 205;
                break;
            case 0x6251:
                index = 206;
                break;
            case 0x2516:
                index = 207;
                break;

            case 0x5612:
                index = 208;
                break;
            case 0x6125:
                index = 209;
                break;
            case 0x1256:
                index = 210;
                break;
            case 0x2561:
                index = 211;
                break;

            case 0x1652:
                index = 212;
                break;
            case 0x6521:
                index = 213;
                break;
            case 0x5216:
                index = 214;
                break;
            case 0x2165:
                index = 215;
                break;

            case 0x1616:
                index = 216;
                break;
            case 0x6161:
                index = 217;
                break;
            // case 0x1616:
            // index = 218;
            // break;
            // case 0x6161:
            // index = 219;
            // break;

            case 0x2525:
                index = 220;
                break;
            case 0x5252:
                index = 221;
                break;
            // case 0x2525:
            // index = 222;
            // break;
            // case 0x5252:
            // index = 223;
            // break;

            case 0x0404:
                index = 224;
                break;
            case 0x4040:
                index = 225;
                break;
            // case 0x0404:
            // index = 226;
            // break;
            // case 0x4040:
            // index = 227;
            // break;

        }

        System.out.println("\n" + (index/4 + 1));
        return index;
    }


    
    private void solvePLL() {
        makeMoves(Algs.PLL[calculatPLLindex()], solveSpeed);
    }


    private int calculatPLLindex() {
        int index = 0;
        int codex = 0;

        
        // TODO: calculate PLL index
        
        System.out.println("\n" + (index/4 + 1));
        return index;
    }
}
