package Model;

import java.util.Random;

public class Cube {
    private byte[] stickers;
    private byte[] centers;

    private Cube() {
    }

    public void move(Move move) {
        switch (move) {
            case U: this.u(); break;
            case U2: this.u2(); break;
            case U_: this.u_(); break;
            case L: this.l(); break;
            case L2: this.l2(); break;
            case L_: this.l_(); break;
            case F: this.f(); break;
            case F2: this.f2(); break;
            case F_: this.f_(); break;
            case R: this.r(); break;
            case R2: this.r2(); break;
            case R_: this.r_(); break;
            case B: this.b(); break;
            case B2: this.b2(); break;
            case B_: this.b_(); break;
            case D: this.d(); break;
            case D2: this.d2(); break;
            case D_: this.d_(); break;
            default: case NONE: break;
        }
    }

    /**
     * Get the color at an center piece of a face.
     * 
     * @param face The face.
     */
    public byte[] getCenterColors(Face face) {
        return new byte[] { centers[face.ordinal()] };
    }

    /**
     * Get the colors at an edge. For the M and S slice, the Y (U/D)
     * color is first. For the E slice the Z (F/B) color is first.
     * 
     * @param edge The edge.
     */
    public byte[] getEdgeColors(Edge edge) {
        switch (edge) {
            default:
            case UB:
                return new byte[] { stickers[1], stickers[33] };

            case UR:
                return new byte[] { stickers[3], stickers[25] };

            case UF:
                return new byte[] { stickers[5], stickers[17] };

            case UL:
                return new byte[] { stickers[7], stickers[9] };

            case FR:
                return new byte[] { stickers[19], stickers[31] };

            case FL:
                return new byte[] { stickers[23], stickers[11] };

            case BL:
                return new byte[] { stickers[35], stickers[15] };

            case BR:
                return new byte[] { stickers[39], stickers[27] };

            case DF:
                return new byte[] { stickers[41], stickers[21] };

            case DL:
                return new byte[] { stickers[47], stickers[13] };

            case DB:
                return new byte[] { stickers[45], stickers[37] };

            case DR:
                return new byte[] { stickers[43], stickers[29] };
        }
    }

    /**
     * Get the colors at a corner in Y, X, Z order (e.g. up-left-back).
     * 
     * @param corner The corner.
     */
    public byte[] getCornerColors(Corner corner) {
        switch (corner) {
            default:
            case ULB:
                return new byte[] { stickers[0], stickers[8], stickers[34] };

            case URB:
                return new byte[] { stickers[2], stickers[26], stickers[32] };

            case URF:
                return new byte[] { stickers[4], stickers[24], stickers[18] };

            case ULF:
                return new byte[] { stickers[6], stickers[10], stickers[16] };

            case DLF:
                return new byte[] { stickers[40], stickers[12], stickers[22] };

            case DLB:
                return new byte[] { stickers[46], stickers[14], stickers[36] };

            case DRB:
                return new byte[] { stickers[44], stickers[28], stickers[38] };

            case DRF:
                return new byte[] { stickers[42], stickers[30], stickers[20] };
        }
    }

    private long getFace(Face side) {
        long face = 0;
        for (int i = 0; i < 8; i++)
            face = (face << 8) | stickers[side.ordinal() * 8 + i];
        return face;
    }

    public boolean isSolved() {
        return (this.getFace(Face.U) == 0x0000000000000000l) &&
                (this.getFace(Face.L) == 0x0101010101010101l) &&
                (this.getFace(Face.F) == 0x0202020202020202l) &&
                (this.getFace(Face.R) == 0x0303030303030303l) &&
                (this.getFace(Face.B) == 0x0404040404040404l) &&
                (this.getFace(Face.D) == 0x0505050505050505l);
    }


    
    private byte[] getCubieFaces(byte[] cubieColors) {
        byte[] cubieFaces = new byte[cubieColors.length];

        for (int i = 0; i < cubieFaces.length; i++) {
            for (int j = 0; j < centers.length; j++) {
                if(cubieColors[i] == centers[j]){
                    cubieFaces[i] = (byte) j;
                    break;
                }
            }
        } 

        return cubieFaces;
    }

    /**
     * Given three face colors, return a unique index for a corner cubie. The
     * index will be [0..7].
     * 
     * @param ind A corner index.
     */
    public int getCornerIndex(Corner corner) {
        byte[] cornerFaces = getCubieFaces(getCornerColors(corner));

        // The colors range from 0 to 5, per RubiksCube.h.
        // Shifting 1 left by 0...5 gives 1, 2, 4, 8, 16, 32.
        // Adding these together gives a unique number for each corner cubie. 

        int codex = (1 << cornerFaces[0]) | (1 << cornerFaces[1]) | (1 << cornerFaces[2]);

        switch (codex) {
            default: return 0;
            case 19: return 0;
            case 25: return 1;
            case 13: return 2;
            case 7: return 3;
            case 50: return 4;
            case 56: return 5;
            case 44: return 6;
            case 38: return 7;
        }
    }


    /**
     * Given a corner index, get the orientation of the piece currently occupying
     * the corner position.
     *
     * Orientation 0: Red or orange is on the top or bottom.
     * Orientation 1: The piece is rotated clockwise from its nearest up or down
     * face.
     * Orientation 2: The piece is rotated counterclockwise form its nearest up
     * or down face.
     */
    public int getCornerOrientation(Corner index) {
        byte[] corner = getCornerColors(index);

        if (corner[0] == centers[0] || corner[0] == centers[5])
            return 0;

        switch (index) {
            default:
            case ULB: case URF: case DLF: case DRB:
                return (corner[1] == centers[0] || corner[1] == centers[5]) ? 1 : 2;

            case URB: case ULF: case DLB: case DRF:
                return (corner[2] == centers[0] || corner[2] == centers[5]) ? 1 : 2;
        }
    }

    /**
     * Given two face colors, return a unique index for an edge cubie. The index
     * will be [0..11].
     * 
     * @param edge An edge index.
     */
    public int getEdgeIndex(Edge edge) {
        byte[] edgeFaces = getCubieFaces(getEdgeColors(edge));

        // The colors range from 0 to 5, per RubiksCube.h.
        // Shifting 1 left by 0...5 gives 1, 2, 4, 8, 16, 32.
        // Adding these together gives a unique number for each edge cubie.
        int codex = (1 << edgeFaces[0]) | (1 << edgeFaces[1]);

        switch (codex) {
            default: 
            case 17: return 0;
            case 9: return 1;
            case 5: return 2;
            case 3: return 3; 
            case 18: return 4;
            case 24: return 5;
            case 12: return 6;
            case 6: return 7;
            case 48: return 8;
            case 40: return 9;
            case 36: return 10;
            case 34: return 11;
        }
    }

    /**
     * Given an edge index, return the orientation of the edge cubie, 0, or 1.
     *
     * Note that edges cannot be flipped (disoriented) without F or B turns, so
     * U, D, L, and R keep the edges oriented.
     *
     * Orientation 0: Good.
     * Orientation 1: Bad (disoriented).
     *
     * See:
     * https://stackoverflow.com/questions/17305071/how-can-i-determine-optimally-if-an-edge-is-correctly-oriented-on-the-rubiks-cu
     * See: http://cube.crider.co.uk/zz.php?p=eoline#eo_detection
     *
     * @param edge An edge.
     */
    public int getEdgeOrientation(Edge edge) {
        // The first index in this edge is the up or down color for edges in
        // the up or down layer (M or S slice), or the front or back color for
        // edges in the middle layer (E slice).
        byte[] edgeColors = getEdgeColors(edge);

        // If the first sticker is the L or R color, it's bad.
        if (edgeColors[0] == centers[1] || edgeColors[0] == centers[3])
            return 1;

        // If the first sticker is the F or B color, then check the other sticker.
        // If the other sticker is the U or D color, it's bad.
        if (edgeColors[0] == centers[2] || edgeColors[0] == centers[4])
            if (edgeColors[1] == centers[0] || edgeColors[1] == centers[5])
                return 1;

        // Otherwise it's good.
        return 0;
    }

    public static Cube Solved() {
        Cube cube = new Cube();
        cube.getSolvedStickers();
        return cube;
    }

    public static Cube CheckerBoard() {
        Cube cube = new Cube();
        cube.getCheckerBoardStickers();
        return cube;
    }

    public static Cube Random() {
        Cube cube = new Cube();
        cube.getRandomStickers();
        return cube;
    }

    private void getSolvedStickers() {
        centers = new byte[] { 0, 1, 2, 3, 4, 5, };
        stickers = new byte[] {
                0, 0, 0, 0, 0, 0, 0, 0,
                1, 1, 1, 1, 1, 1, 1, 1,
                2, 2, 2, 2, 2, 2, 2, 2,
                3, 3, 3, 3, 3, 3, 3, 3,
                4, 4, 4, 4, 4, 4, 4, 4,
                5, 5, 5, 5, 5, 5, 5, 5,
        };
    }

    private void getCheckerBoardStickers() {
        centers = new byte[] { 0, 1, 2, 3, 4, 5, };
        stickers = new byte[] {
                0, 5, 0, 5, 0, 5, 0, 5,
                1, 3, 1, 3, 1, 3, 1, 3,
                2, 4, 2, 4, 2, 4, 2, 4,
                3, 1, 3, 1, 3, 1, 3, 1,
                4, 2, 4, 2, 4, 2, 4, 2,
                5, 0, 5, 0, 5, 0, 5, 0,
        };
    }

    private void getRandomStickers() {
        centers = new byte[] { 0, 1, 2, 3, 4, 5, };
        stickers = new byte[48];
        for (int i = 0; i < 48; i++) {
            stickers[i] = (byte) (new Random().nextInt(6));
        }
    }

    private void rotate90(byte[] array, int a, int b, int c, int d) {
        byte temp = array[d];
        array[d] = array[c];
        array[c] = array[b];
        array[b] = array[a];
        array[a] = temp;
    }

    private void rotate180(byte[] array, int a, int b, int c, int d) {
        byte temp = array[a];
        array[a] = array[c];
        array[c] = temp;

        temp = array[b];
        array[b] = array[d];
        array[d] = temp;
    }

    private void rotate270(byte[] array, int a, int b, int c, int d) {
        rotate90(array, d, c, b, a);
    }

    private void u() {
        rotate90(stickers, 0, 2, 4, 6);
        rotate90(stickers, 1, 3, 5, 7);
        rotate90(stickers, 32, 24, 16, 8);
        rotate90(stickers, 33, 25, 17, 9);
        rotate90(stickers, 34, 26, 18, 10);
    }

    private void u_() {
        rotate270(stickers, 0, 2, 4, 6);
        rotate270(stickers, 1, 3, 5, 7);
        rotate270(stickers, 32, 24, 16, 8);
        rotate270(stickers, 33, 25, 17, 9);
        rotate270(stickers, 34, 26, 18, 10);
    }

    private void u2() {
        rotate180(stickers, 0, 2, 4, 6);
        rotate180(stickers, 1, 3, 5, 7);
        rotate180(stickers, 32, 24, 16, 8);
        rotate180(stickers, 33, 25, 17, 9);
        rotate180(stickers, 34, 26, 18, 10);
    }

    private void d() {
        rotate90(stickers, 40, 42, 44, 46);
        rotate90(stickers, 41, 43, 45, 47);
        rotate90(stickers, 14, 22, 30, 38);
        rotate90(stickers, 13, 21, 29, 37);
        rotate90(stickers, 12, 20, 28, 36);
    }

    private void d_() {
        rotate270(stickers, 40, 42, 44, 46);
        rotate270(stickers, 41, 43, 45, 47);
        rotate270(stickers, 14, 22, 30, 38);
        rotate270(stickers, 13, 21, 29, 37);
        rotate270(stickers, 12, 20, 28, 36);
    }

    private void d2() {
        rotate180(stickers, 40, 42, 44, 46);
        rotate180(stickers, 41, 43, 45, 47);
        rotate180(stickers, 14, 22, 30, 38);
        rotate180(stickers, 13, 21, 29, 37);
        rotate180(stickers, 12, 20, 28, 36);
    }

    private void r() {
        rotate90(stickers, 24, 26, 28, 30);
        rotate90(stickers, 25, 27, 29, 31);
        rotate90(stickers, 38, 42, 18, 2);
        rotate90(stickers, 39, 43, 19, 3);
        rotate90(stickers, 32, 44, 20, 4);
    }

    private void r_() {
        rotate270(stickers, 24, 26, 28, 30);
        rotate270(stickers, 25, 27, 29, 31);
        rotate270(stickers, 38, 42, 18, 2);
        rotate270(stickers, 39, 43, 19, 3);
        rotate270(stickers, 32, 44, 20, 4);
    }

    private void r2() {
        rotate180(stickers, 24, 26, 28, 30);
        rotate180(stickers, 25, 27, 29, 31);
        rotate180(stickers, 38, 42, 18, 2);
        rotate180(stickers, 39, 43, 19, 3);
        rotate180(stickers, 32, 44, 20, 4);
    }

    private void l() {
        rotate90(stickers, 8, 10, 12, 14);
        rotate90(stickers, 9, 11, 13, 15);
        rotate90(stickers, 6, 22, 46, 34);
        rotate90(stickers, 7, 23, 47, 35);
        rotate90(stickers, 0, 16, 40, 36);
    }

    private void l_() {
        rotate270(stickers, 8, 10, 12, 14);
        rotate270(stickers, 9, 11, 13, 15);
        rotate270(stickers, 6, 22, 46, 34);
        rotate270(stickers, 7, 23, 47, 35);
        rotate270(stickers, 0, 16, 40, 36);
    }

    private void l2() {
        rotate180(stickers, 8, 10, 12, 14);
        rotate180(stickers, 9, 11, 13, 15);
        rotate180(stickers, 6, 22, 46, 34);
        rotate180(stickers, 7, 23, 47, 35);
        rotate180(stickers, 0, 16, 40, 36);
    }

    private void f() {
        rotate90(stickers, 16, 18, 20, 22);
        rotate90(stickers, 17, 19, 21, 23);
        rotate90(stickers, 4, 30, 40, 10);
        rotate90(stickers, 5, 31, 41, 11);
        rotate90(stickers, 42, 12, 6, 24);
    }

    private void f_() {
        rotate270(stickers, 16, 18, 20, 22);
        rotate270(stickers, 17, 19, 21, 23);
        rotate270(stickers, 4, 30, 40, 10);
        rotate270(stickers, 5, 31, 41, 11);
        rotate270(stickers, 42, 12, 6, 24);
    }

    private void f2() {
        rotate180(stickers, 16, 18, 20, 22);
        rotate180(stickers, 17, 19, 21, 23);
        rotate180(stickers, 4, 30, 40, 10);
        rotate180(stickers, 5, 31, 41, 11);
        rotate180(stickers, 42, 12, 6, 24);
    }

    private void b() {
        rotate90(stickers, 32, 34, 36, 38);
        rotate90(stickers, 33, 35, 37, 39);
        rotate90(stickers, 14, 44, 26, 0);
        rotate90(stickers, 15, 45, 27, 1);
        rotate90(stickers, 8, 46, 28, 2);
    }

    private void b_() {
        rotate270(stickers, 32, 34, 36, 38);
        rotate270(stickers, 33, 35, 37, 39);
        rotate270(stickers, 14, 44, 26, 0);
        rotate270(stickers, 15, 45, 27, 1);
        rotate270(stickers, 8, 46, 28, 2);
    }

    private void b2() {
        rotate180(stickers, 32, 34, 36, 38);
        rotate180(stickers, 33, 35, 37, 39);
        rotate180(stickers, 14, 44, 26, 0);
        rotate180(stickers, 15, 45, 27, 1);
        rotate180(stickers, 8, 46, 28, 2);
    }

}
