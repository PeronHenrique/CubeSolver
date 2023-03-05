package Solver;

public class Algs {

    public static final String[] cross = {
            // 0
            "U2 F2",
            "U R' F R",
            // 1
            "U F2",
            "R' F R",
            // 2
            "F2",
            "U' R' F R",
            // 3
            "U' F2",
            "L F' L'",
            // 4
            "D' L' D",
            "L2 F' L2",
            // 5
            "D R D'",
            "R2 F R2",
            // 6
            "D R' D'",
            "F",
            // 7
            "D' L D",
            "F'",
            // 8
            "B2 U2 F2",
            "B2 U R' F R",
            // 9
            "R2 U F2",
            "R F",
            // 10
            "",
            "F D' L D",
            // 11
            "L2 U' F2",
            "L' F'",
    };

    public static final String[] F2L = {
            // 0
            "U R U2 R' U R U' R'",
            "R U R'",
            "U' R U R' U' R U2 R'",

            "U2 F' U' F2 R' F' R",
            "U F' U2 F U' F R' F' R",
            "U' R U' R' U F' U' F",

            // 1
            "R U2 R' U' R U R'",
            "U' R U' R' U R U R'",
            "R' F R F'",

            "R U R' U R U R' U' F R' F' R",
            "R U' R' U2 F' U' F",
            "U' R U2 R' U F' U' F",

            // 2
            "U R U' R' U' R U' R' U R U' R'",
            "R' U2 R2 U R2 U R",
            "F' U F U2 R U R'",

            "F' U2 F U F' U' F",
            "F R' F' R",
            "U F' U F U' F' U' F",

            // 3
            "U2 R U R' U R U' R'",
            "U' R U R' U R U R'",
            "U' R U2 R' U' R U2 R'",

            "U' F' U2 F2 R' F' R",
            "U F' U' F U' F R' F' R",
            "F' U' F",
    };

    public static final String oll1 = "R U2 R2 F R F' U2 R' F R F'";
    public static final String oll2 = "r U r' U2 r U2 R' U2 R U' r'";
    public static final String oll3 = "M R U R' U r U2 r' U M'";
    public static final String oll4 = "M U' r U2 r' U' R U' R' M";
    public static final String oll5 = "l' U2 L U L' U l";
    public static final String oll6 = "r U2 R' U' R U' r'";
    public static final String oll7 = "r U R' U R U2 r'";
    public static final String oll8 = "l' U' L U' L' U2 l";
    public static final String oll9 = "R U R' U' R' F R2 U R' U' F'";
    public static final String oll10 = "R U R' U R' F R F' R U2 R'";
    public static final String oll11 = "r U R' U R' F R F' R U2 r'";
    public static final String oll12 = "M' R' U' R U' R' U2 R U' M";
    public static final String oll13 = "F U R U' R2 F' R U R U' R'";
    public static final String oll14 = "R' F R U R' F' R F U' F'";
    public static final String oll15 = "l' U' M U' L U l' U l";
    public static final String oll16 = "r U M U R' U' r U' r'";
    public static final String oll17 = "F R' F' R2 r' U R U' R' U' M'";
    public static final String oll18 = "r U R' U R U2 r2 U' R U' R' U2 r";
    public static final String oll19 = "M U R U R' U' M' R' F R F'";
    public static final String oll20 = "r U R' U' M2 U R U' R' U' M'";
    public static final String oll21 = "R U2 R' U' R U R' U' R U' R'";
    public static final String oll22 = "R U2 R2 U' R2 U' R2 U2 R";
    public static final String oll23 = "R2 D' R U2 R' D R U2 R";
    public static final String oll24 = "r U R' U' r' F R F'";
    public static final String oll25 = "F' r U R' U' r' F R";
    public static final String oll26 = "R U2 R' U' R U' R'";
    public static final String oll27 = "R U R' U R U2 R'";
    public static final String oll28 = "r U R' U' M U R U' R'";
    public static final String oll29 = "R U R' U' R U' R' F' U' F R U R'";
    public static final String oll30 = "F R' F R2 U' R' U' R U R' F2";
    public static final String oll31 = "R' U' F U R U' R' F' R";
    public static final String oll32 = "L U F' U' L' U L F L'";
    public static final String oll33 = "R U R' U' R' F R F'";
    public static final String oll34 = "R U R2 U' R' F R U R U' F'";
    public static final String oll35 = "R U2 R2 F R F' R U2 R'";
    public static final String oll36 = "L' U' L U' L' U L U L F' L' F";
    public static final String oll37 = "F R' F' R U R U' R'";
    public static final String oll38 = "R U R' U R U' R' U' R' F R F'";
    public static final String oll39 = "L F' L' U' L U F U' L'";
    public static final String oll40 = "R' F R U R' U' F' U R";
    public static final String oll41 = "R U R' U R U2 R' F R U R' U' F'";
    public static final String oll42 = "R' U' R U' R' U2 R F R U R' U' F'";
    public static final String oll43 = "F' U' L' U L F";
    public static final String oll44 = "F U R U' R' F'";
    public static final String oll45 = "F R U R' U' F'";
    public static final String oll46 = "R' U' R' F R F' U R";
    public static final String oll47 = "R' U' R' F R F' R' F R F' U R";
    public static final String oll48 = "F R U R' U' R U R' U' F'";
    public static final String oll49 = "r U' r2 U r2 U r2 U' r";
    public static final String oll50 = "r' U r2 U' r2 U' r2 U r'";
    public static final String oll51 = "F U R U' R' U R U' R' F'";
    public static final String oll52 = "R U R' U R U' B U' B' R'";
    public static final String oll53 = "l' U2 L U L' U' L U L' U l";
    public static final String oll54 = "r U2 R' U' R U R' U' R U' r'";
    public static final String oll55 = "R' F R U R U' R2 F' R2 U' R' U R U R'";
    public static final String oll56 = "r' U' r U' R' U R U' R' U R r' U r";
    public static final String oll57 = "R U R' U' M' U R U' r'";

    public static final String[] OLL = {
            "  " + oll1,
            "U' " + oll1,
            "U2 " + oll1,
            "U " + oll1,
            "  " + oll2,
            "U' " + oll2,
            "U2 " + oll2,
            "U " + oll2,
            "  " + oll3,
            "U' " + oll3,
            "U2 " + oll3,
            "U " + oll3,
            "  " + oll4,
            "U' " + oll4,
            "U2 " + oll4,
            "U " + oll4,
            "  " + oll5,
            "U' " + oll5,
            "U2 " + oll5,
            "U " + oll5,
            "  " + oll6,
            "U' " + oll6,
            "U2 " + oll6,
            "U " + oll6,
            "  " + oll7,
            "U' " + oll7,
            "U2 " + oll7,
            "U " + oll7,
            "  " + oll8,
            "U' " + oll8,
            "U2 " + oll8,
            "U " + oll8,
            "  " + oll9,
            "U' " + oll9,
            "U2 " + oll9,
            "U " + oll9,
            "  " + oll10,
            "U' " + oll10,
            "U2 " + oll10,
            "U " + oll10,
            "  " + oll11,
            "U' " + oll11,
            "U2 " + oll11,
            "U " + oll11,
            "  " + oll12,
            "U' " + oll12,
            "U2 " + oll12,
            "U " + oll12,
            "  " + oll13,
            "U' " + oll13,
            "U2 " + oll13,
            "U " + oll13,
            "  " + oll14,
            "U' " + oll14,
            "U2 " + oll14,
            "U " + oll14,
            "  " + oll15,
            "U' " + oll15,
            "U2 " + oll15,
            "U " + oll15,
            "  " + oll16,
            "U' " + oll16,
            "U2 " + oll16,
            "U " + oll16,
            "  " + oll17,
            "U' " + oll17,
            "U2 " + oll17,
            "U " + oll17,
            "  " + oll18,
            "U' " + oll18,
            "U2 " + oll18,
            "U " + oll18,
            "  " + oll19,
            "U' " + oll19,
            "U2 " + oll19,
            "U " + oll19,
            "  " + oll20,
            "U' " + oll20,
            "U2 " + oll20,
            "U " + oll20,
            "  " + oll21,
            "U' " + oll21,
            "U2 " + oll21,
            "U " + oll21,
            "  " + oll22,
            "U' " + oll22,
            "U2 " + oll22,
            "U " + oll22,
            "  " + oll23,
            "U' " + oll23,
            "U2 " + oll23,
            "U " + oll23,
            "  " + oll24,
            "U' " + oll24,
            "U2 " + oll24,
            "U " + oll24,
            "  " + oll25,
            "U' " + oll25,
            "U2 " + oll25,
            "U " + oll25,
            "  " + oll26,
            "U' " + oll26,
            "U2 " + oll26,
            "U " + oll26,
            "  " + oll27,
            "U' " + oll27,
            "U2 " + oll27,
            "U " + oll27,
            "  " + oll28,
            "U' " + oll28,
            "U2 " + oll28,
            "U " + oll28,
            "  " + oll29,
            "U' " + oll29,
            "U2 " + oll29,
            "U " + oll29,
            "  " + oll30,
            "U' " + oll30,
            "U2 " + oll30,
            "U " + oll30,
            "  " + oll31,
            "U' " + oll31,
            "U2 " + oll31,
            "U " + oll31,
            "  " + oll32,
            "U' " + oll32,
            "U2 " + oll32,
            "U " + oll32,
            "  " + oll33,
            "U' " + oll33,
            "U2 " + oll33,
            "U " + oll33,
            "  " + oll34,
            "U' " + oll34,
            "U2 " + oll34,
            "U " + oll34,
            "  " + oll35,
            "U' " + oll35,
            "U2 " + oll35,
            "U " + oll35,
            "  " + oll36,
            "U' " + oll36,
            "U2 " + oll36,
            "U " + oll36,
            "  " + oll37,
            "U' " + oll37,
            "U2 " + oll37,
            "U " + oll37,
            "  " + oll38,
            "U' " + oll38,
            "U2 " + oll38,
            "U " + oll38,
            "  " + oll39,
            "U' " + oll39,
            "U2 " + oll39,
            "U " + oll39,
            "  " + oll40,
            "U' " + oll40,
            "U2 " + oll40,
            "U " + oll40,
            "  " + oll41,
            "U' " + oll41,
            "U2 " + oll41,
            "U " + oll41,
            "  " + oll42,
            "U' " + oll42,
            "U2 " + oll42,
            "U " + oll42,
            "  " + oll43,
            "U' " + oll43,
            "U2 " + oll43,
            "U " + oll43,
            "  " + oll44,
            "U' " + oll44,
            "U2 " + oll44,
            "U " + oll44,
            "  " + oll45,
            "U' " + oll45,
            "U2 " + oll45,
            "U " + oll45,
            "  " + oll46,
            "U' " + oll46,
            "U2 " + oll46,
            "U " + oll46,
            "  " + oll47,
            "U' " + oll47,
            "U2 " + oll47,
            "U " + oll47,
            "  " + oll48,
            "U' " + oll48,
            "U2 " + oll48,
            "U " + oll48,
            "  " + oll49,
            "U' " + oll49,
            "U2 " + oll49,
            "U " + oll49,
            "  " + oll50,
            "U' " + oll50,
            "U2 " + oll50,
            "U " + oll50,
            "  " + oll51,
            "U' " + oll51,
            "U2 " + oll51,
            "U " + oll51,
            "  " + oll52,
            "U' " + oll52,
            "U2 " + oll52,
            "U " + oll52,
            "  " + oll53,
            "U' " + oll53,
            "U2 " + oll53,
            "U " + oll53,
            "  " + oll54,
            "U' " + oll54,
            "U2 " + oll54,
            "U " + oll54,
            "  " + oll55,
            "U' " + oll55,
            "U2 " + oll55,
            "U " + oll55,
            "  " + oll56,
            "U' " + oll56,
            "U2 " + oll56,
            "U " + oll56,
            "  " + oll57,
            "U' " + oll57,
            "U2 " + oll57,
            "U " + oll57,
    };


    
        // TODO: make PLL array
    public static final String[] PLL = {
        ""
    };

}
