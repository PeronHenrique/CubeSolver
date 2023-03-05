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

    
    public static final String[] OLL = {

    };

}
