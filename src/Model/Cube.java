package Model;

import java.util.Random;

public class Cube {
    private byte[] stickers;
    private byte[] centers;

    
    public Corner[] corners;
    public int[] cornerOrientation;
    public Edge[] edges;
    public int[] edgeOrientation;

    private Cube() {
        this.corners = new Corner[8];
        this.cornerOrientation = new int[8];
        this.edges = new Edge[12];
        this.edgeOrientation = new int[12];
    }

    public void move(Move move) {
        // if (move == Move.NONE)
        // return;
        // String name = move.name();

        // try {
        // final Method method = Cube.class.getDeclaredMethod(name, null);
        // method.invoke(this, null);
        // } catch (NoSuchMethodException e) {
        // e.printStackTrace();
        // } catch (SecurityException e) {
        // e.printStackTrace();
        // } catch (IllegalAccessException e) {
        // e.printStackTrace();
        // } catch (IllegalArgumentException e) {
        // e.printStackTrace();
        // } catch (InvocationTargetException e) {
        // e.printStackTrace();
        // }

        switch (move) {
            case U:
                this.U();
                break;
            case U2:
                this.U2();
                break;
            case U_:
                this.U_();
                break;
            case L:
                this.L();
                break;
            case L2:
                this.L2();
                break;
            case L_:
                this.L_();
                break;
            case F:
                this.F();
                break;
            case F2:
                this.F2();
                break;
            case F_:
                this.F_();
                break;
            case R:
                this.R();
                break;
            case R2:
                this.R2();
                break;
            case R_:
                this.R_();
                break;
            case B:
                this.B();
                break;
            case B2:
                this.B2();
                break;
            case B_:
                this.B_();
                break;
            case D:
                this.D();
                break;
            case D2:
                this.D2();
                break;
            case D_:
                this.D_();
                break;
            case E:
                this.E();
                break;
            case E2:
                this.E2();
                break;
            case E_:
                this.E_();
                break;
            case M:
                this.M();
                break;
            case M2:
                this.M2();
                break;
            case M_:
                this.M_();
                break;
            case S:
                this.S();
                break;
            case S2:
                this.S2();
                break;
            case S_:
                this.S_();
                break;
            case X:
                this.X();
                break;
            case X2:
                this.X2();
                break;
            case X_:
                this.X_();
                break;
            case Y:
                this.Y();
                break;
            case Y2:
                this.Y2();
                break;
            case Y_:
                this.Y_();
                break;
            case Z:
                this.Z();
                break;
            case Z2:
                this.Z2();
                break;
            case Z_:
                this.Z_();
                break;
            default:
            case NONE:
                break;
        }
        
        this.update();
    }

    /**
     * Get the color at an center piece of a face.
     * 
     * @param face The face.
     */
    public byte[] getCenterColors(Face face) {
        return new byte[] { this.centers[face.ordinal()] };
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
                return new byte[] { this.stickers[1], this.stickers[33] };

            case UR:
                return new byte[] { this.stickers[3], this.stickers[25] };

            case UF:
                return new byte[] { this.stickers[5], this.stickers[17] };

            case UL:
                return new byte[] { this.stickers[7], this.stickers[9] };

            case FR:
                return new byte[] { this.stickers[19], this.stickers[31] };

            case FL:
                return new byte[] { this.stickers[23], this.stickers[11] };

            case BL:
                return new byte[] { this.stickers[35], this.stickers[15] };

            case BR:
                return new byte[] { this.stickers[39], this.stickers[27] };

            case DF:
                return new byte[] { this.stickers[41], this.stickers[21] };

            case DL:
                return new byte[] { this.stickers[47], this.stickers[13] };

            case DB:
                return new byte[] { this.stickers[45], this.stickers[37] };

            case DR:
                return new byte[] { this.stickers[43], this.stickers[29] };
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
                return new byte[] { this.stickers[0], this.stickers[8], this.stickers[34] };

            case URB:
                return new byte[] { this.stickers[2], this.stickers[26], this.stickers[32] };

            case URF:
                return new byte[] { this.stickers[4], this.stickers[24], this.stickers[18] };

            case ULF:
                return new byte[] { this.stickers[6], this.stickers[10], this.stickers[16] };

            case DLF:
                return new byte[] { this.stickers[40], this.stickers[12], this.stickers[22] };

            case DLB:
                return new byte[] { this.stickers[46], this.stickers[14], this.stickers[36] };

            case DRB:
                return new byte[] { this.stickers[44], this.stickers[28], this.stickers[38] };

            case DRF:
                return new byte[] { this.stickers[42], this.stickers[30], this.stickers[20] };
        }
    }

    public boolean isSolved() {
        for (int i = 0; i < 48; i++) {
            if(this.stickers[i] != this.centers[i/8]) return false;
        }
        return true;
    }

    private byte[] getCubieFaces(byte[] cubieColors) {
        byte[] cubieFaces = new byte[cubieColors.length];

        for (int i = 0; i < cubieFaces.length; i++) {
            for (int j = 0; j < 6; j++) {
                if (cubieColors[i] == centers[j]) {
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
    public Corner getCornerIndex(Corner position) {
        byte[] cornerFaces = getCubieFaces(getCornerColors(position));

        // The colors range from 0 to 5, per RubiksCube.h.
        // Shifting 1 left by 0...5 gives 1, 2, 4, 8, 16, 32.
        // Adding these together gives a unique number for each corner cubie.

        int codex = (1 << cornerFaces[0]) | (1 << cornerFaces[1]) | (1 << cornerFaces[2]);

        switch (codex) {
            default:
            case 19:
                return Corner.ULB;
            case 25:
                return Corner.URB;
            case 13:
                return Corner.URF;
            case 7:
                return Corner.ULF;
            case 50:
                return Corner.DLB;
            case 56:
                return Corner.DRB;
            case 44:
                return Corner.DRF;
            case 38:
                return Corner.DLF;
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
    public int getCornerOrientation(Corner position) {
        byte[] corner = getCornerColors(position);

        if (corner[0] == centers[0] || corner[0] == centers[5])
            return 0;

        switch (position) {
            default:
            case ULB:
            case URF:
            case DLF:
            case DRB:
                return (corner[1] == centers[0] || corner[1] == centers[5]) ? 1 : 2;

            case URB:
            case ULF:
            case DLB:
            case DRF:
                return (corner[2] == centers[0] || corner[2] == centers[5]) ? 1 : 2;
        }
    }

    /**
     * Given two face colors, return a unique index for an edge cubie. The index
     * will be [0..11].
     * 
     * @param position An edge index.
     */
    public Edge getEdgeIndex(Edge position) {
        byte[] edgeFaces = getCubieFaces(getEdgeColors(position));

        // The colors range from 0 to 5, per RubiksCube.h.
        // Shifting 1 left by 0...5 gives 1, 2, 4, 8, 16, 32.
        // Adding these together gives a unique number for each edge cubie.
        int codex = (1 << edgeFaces[0]) | (1 << edgeFaces[1]);

        switch (codex) {
            default:
            case 17:
                return Edge.UB;
            case 9:
                return Edge.UR;
            case 5:
                return Edge.UF;
            case 3:
                return Edge.UL;
            case 18:
                return Edge.BL;
            case 24:
                return Edge.BR;
            case 12:
                return Edge.FR;
            case 6:
                return Edge.FL;
            case 48:
                return Edge.DB;
            case 40:
                return Edge.DR;
            case 36:
                return Edge.DF;
            case 34:
                return Edge.DL;
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
     *
     * @param position An edge.
     */
    public int getEdgeOrientation(Edge position) {
        // The first index in this edge is the up or down color for edges in
        // the up or down layer (M or S slice), or the front or back color for
        // edges in the middle layer (E slice).
        byte[] edgeColors = getEdgeColors(position);

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



    private void update() {
        for (int i = 0; i < 8; i++) {
            this.corners[i] = this.getCornerIndex(Corner.getByIndex(i));
            this.cornerOrientation[i] = this.getCornerOrientation(Corner.getByIndex(i));
        }

        for (int i = 0; i < 12; i++) {
            this.edges[i] = this.getEdgeIndex(Edge.getByIndex(i));
            this.edgeOrientation[i] = this.getEdgeOrientation(Edge.getByIndex(i));
        }
    }

    public int getCornerPosition(Corner index) {
        for (int i = 0; i < 8; i++) {
            if (this.corners[i] == index)
                return i;
        }

        return -1;
    }

    public int getEdgePosition(Edge index) {
        for (int i = 0; i < 12; i++) {
            if (this.edges[i] == index)
                return i;
        }

        return -1;
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
        this.centers = new byte[] { 5, 4, 3, 2, 1, 0, };
        this.stickers = new byte[] {
                5, 5, 5, 5, 5, 5, 5, 5,
                4, 4, 4, 4, 4, 4, 4, 4,
                3, 3, 3, 3, 3, 3, 3, 3,
                2, 2, 2, 2, 2, 2, 2, 2,
                1, 1, 1, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0, 0, 0,
        };
    }

    private void getCheckerBoardStickers() {
        this.centers = new byte[] { 0, 1, 2, 3, 4, 5, };
        this.stickers = new byte[] {
                0, 5, 0, 5, 0, 5, 0, 5,
                1, 3, 1, 3, 1, 3, 1, 3,
                2, 4, 2, 4, 2, 4, 2, 4,
                3, 1, 3, 1, 3, 1, 3, 1,
                4, 2, 4, 2, 4, 2, 4, 2,
                5, 0, 5, 0, 5, 0, 5, 0,
        };
    }

    private void getRandomStickers() {
        this.centers = new byte[] { 0, 1, 2, 3, 4, 5, };
        this.stickers = new byte[48];
        for (int i = 0; i < 48; i++) {
            this.stickers[i] = (byte) (new Random().nextInt(6));
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
        this.rotate90(array, d, c, b, a);
    }

    private void U() {
        this.rotate90(this.stickers, 0, 2, 4, 6);
        this.rotate90(this.stickers, 1, 3, 5, 7);
        this.rotate90(this.stickers, 32, 24, 16, 8);
        this.rotate90(this.stickers, 33, 25, 17, 9);
        this.rotate90(this.stickers, 34, 26, 18, 10);
    }

    private void U_() {
        this.rotate270(this.stickers, 0, 2, 4, 6);
        this.rotate270(this.stickers, 1, 3, 5, 7);
        this.rotate270(this.stickers, 32, 24, 16, 8);
        this.rotate270(this.stickers, 33, 25, 17, 9);
        this.rotate270(this.stickers, 34, 26, 18, 10);
    }

    private void U2() {
        this.rotate180(this.stickers, 0, 2, 4, 6);
        this.rotate180(this.stickers, 1, 3, 5, 7);
        this.rotate180(this.stickers, 32, 24, 16, 8);
        this.rotate180(this.stickers, 33, 25, 17, 9);
        this.rotate180(this.stickers, 34, 26, 18, 10);
    }

    private void D() {
        this.rotate90(this.stickers, 40, 42, 44, 46);
        this.rotate90(this.stickers, 41, 43, 45, 47);
        this.rotate90(this.stickers, 14, 22, 30, 38);
        this.rotate90(this.stickers, 13, 21, 29, 37);
        this.rotate90(this.stickers, 12, 20, 28, 36);
    }

    private void D_() {
        this.rotate270(this.stickers, 40, 42, 44, 46);
        this.rotate270(this.stickers, 41, 43, 45, 47);
        this.rotate270(this.stickers, 14, 22, 30, 38);
        this.rotate270(this.stickers, 13, 21, 29, 37);
        this.rotate270(this.stickers, 12, 20, 28, 36);
    }

    private void D2() {
        this.rotate180(this.stickers, 40, 42, 44, 46);
        this.rotate180(this.stickers, 41, 43, 45, 47);
        this.rotate180(this.stickers, 14, 22, 30, 38);
        this.rotate180(this.stickers, 13, 21, 29, 37);
        this.rotate180(this.stickers, 12, 20, 28, 36);
    }

    private void R() {
        this.rotate90(this.stickers, 24, 26, 28, 30);
        this.rotate90(this.stickers, 25, 27, 29, 31);
        this.rotate90(this.stickers, 38, 42, 18, 2);
        this.rotate90(this.stickers, 39, 43, 19, 3);
        this.rotate90(this.stickers, 32, 44, 20, 4);
    }

    private void R_() {
        this.rotate270(this.stickers, 24, 26, 28, 30);
        this.rotate270(this.stickers, 25, 27, 29, 31);
        this.rotate270(this.stickers, 38, 42, 18, 2);
        this.rotate270(this.stickers, 39, 43, 19, 3);
        this.rotate270(this.stickers, 32, 44, 20, 4);
    }

    private void R2() {
        this.rotate180(this.stickers, 24, 26, 28, 30);
        this.rotate180(this.stickers, 25, 27, 29, 31);
        this.rotate180(this.stickers, 38, 42, 18, 2);
        this.rotate180(this.stickers, 39, 43, 19, 3);
        this.rotate180(this.stickers, 32, 44, 20, 4);
    }

    private void L() {
        this.rotate90(this.stickers, 8, 10, 12, 14);
        this.rotate90(this.stickers, 9, 11, 13, 15);
        this.rotate90(this.stickers, 6, 22, 46, 34);
        this.rotate90(this.stickers, 7, 23, 47, 35);
        this.rotate90(this.stickers, 0, 16, 40, 36);
    }

    private void L_() {
        this.rotate270(this.stickers, 8, 10, 12, 14);
        this.rotate270(this.stickers, 9, 11, 13, 15);
        this.rotate270(this.stickers, 6, 22, 46, 34);
        this.rotate270(this.stickers, 7, 23, 47, 35);
        this.rotate270(this.stickers, 0, 16, 40, 36);
    }

    private void L2() {
        this.rotate180(this.stickers, 8, 10, 12, 14);
        this.rotate180(this.stickers, 9, 11, 13, 15);
        this.rotate180(this.stickers, 6, 22, 46, 34);
        this.rotate180(this.stickers, 7, 23, 47, 35);
        this.rotate180(this.stickers, 0, 16, 40, 36);
    }

    private void F() {
        this.rotate90(this.stickers, 16, 18, 20, 22);
        this.rotate90(this.stickers, 17, 19, 21, 23);
        this.rotate90(this.stickers, 4, 30, 40, 10);
        this.rotate90(this.stickers, 5, 31, 41, 11);
        this.rotate90(this.stickers, 42, 12, 6, 24);
    }

    private void F_() {
        this.rotate270(this.stickers, 16, 18, 20, 22);
        this.rotate270(this.stickers, 17, 19, 21, 23);
        this.rotate270(this.stickers, 4, 30, 40, 10);
        this.rotate270(this.stickers, 5, 31, 41, 11);
        this.rotate270(this.stickers, 42, 12, 6, 24);
    }

    private void F2() {
        this.rotate180(this.stickers, 16, 18, 20, 22);
        this.rotate180(this.stickers, 17, 19, 21, 23);
        this.rotate180(this.stickers, 4, 30, 40, 10);
        this.rotate180(this.stickers, 5, 31, 41, 11);
        this.rotate180(this.stickers, 42, 12, 6, 24);
    }

    private void B() {
        this.rotate90(this.stickers, 32, 34, 36, 38);
        this.rotate90(this.stickers, 33, 35, 37, 39);
        this.rotate90(this.stickers, 14, 44, 26, 0);
        this.rotate90(this.stickers, 15, 45, 27, 1);
        this.rotate90(this.stickers, 8, 46, 28, 2);
    }

    private void B_() {
        this.rotate270(this.stickers, 32, 34, 36, 38);
        this.rotate270(this.stickers, 33, 35, 37, 39);
        this.rotate270(this.stickers, 14, 44, 26, 0);
        this.rotate270(this.stickers, 15, 45, 27, 1);
        this.rotate270(this.stickers, 8, 46, 28, 2);
    }

    private void B2() {
        this.rotate180(this.stickers, 32, 34, 36, 38);
        this.rotate180(this.stickers, 33, 35, 37, 39);
        this.rotate180(this.stickers, 14, 44, 26, 0);
        this.rotate180(this.stickers, 15, 45, 27, 1);
        this.rotate180(this.stickers, 8, 46, 28, 2);
    }

    private void M() {
        this.rotate90(this.stickers, 17, 41, 37, 1);
        this.rotate90(this.stickers, 21, 45, 33, 5);
        this.rotate90(this.centers, 0, 2, 5, 4);
    }

    private void M_() {
        this.rotate270(this.stickers, 17, 41, 37, 1);
        this.rotate270(this.stickers, 21, 45, 33, 5);
        this.rotate270(this.centers, 0, 2, 5, 4);
    }

    private void M2() {
        this.rotate180(this.stickers, 17, 41, 37, 1);
        this.rotate180(this.stickers, 21, 45, 33, 5);
        this.rotate180(this.centers, 0, 2, 5, 4);
    }

    private void E() {
        this.rotate90(this.stickers, 11, 19, 27, 35);
        this.rotate90(this.stickers, 15, 23, 31, 39);
        this.rotate90(this.centers, 1, 2, 3, 4);
    }

    private void E_() {
        this.rotate270(this.stickers, 11, 19, 27, 35);
        this.rotate270(this.stickers, 15, 23, 31, 39);
        this.rotate270(this.centers, 1, 2, 3, 4);
    }

    private void E2() {
        this.rotate180(this.stickers, 11, 19, 27, 35);
        this.rotate180(this.stickers, 15, 23, 31, 39);
        this.rotate180(this.centers, 1, 2, 3, 4);
    }

    private void S() {
        this.rotate90(this.stickers, 3, 29, 47, 9);
        this.rotate90(this.stickers, 7, 25, 43, 13);
        this.rotate90(this.centers, 0, 3, 5, 1);
    }

    private void S_() {
        this.rotate270(this.stickers, 3, 29, 47, 9);
        this.rotate270(this.stickers, 7, 25, 43, 13);
        this.rotate270(this.centers, 0, 3, 5, 1);
    }

    private void S2() {
        this.rotate180(this.stickers, 3, 29, 47, 9);
        this.rotate180(this.stickers, 7, 25, 43, 13);
        this.rotate180(this.centers, 0, 3, 5, 1);
    }

    private void X() {
        this.R();
        this.M_();
        this.L_();
    }

    private void X_() {
        this.R_();
        this.M();
        this.L();
    }

    private void X2() {
        this.R2();
        this.M2();
        this.L2();
    }

    private void Y() {
        this.U();
        this.E_();
        this.D_();
    }

    private void Y_() {
        this.U_();
        this.E();
        this.D();
    }

    private void Y2() {
        this.U2();
        this.E2();
        this.D2();
    }

    private void Z() {
        this.F();
        this.S();
        this.B_();
    }

    private void Z_() {
        this.F_();
        this.S_();
        this.B();
    }

    private void Z2() {
        this.F2();
        this.S2();
        this.B2();
    }
}
