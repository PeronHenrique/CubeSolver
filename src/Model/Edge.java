package Model;

public enum Edge {
    UB, UR, UF, UL,
    BL, BR, FR, FL,
    DB, DR, DF, DL;

    public static Edge getByIndex(int index) {
        for (Edge edge : Edge.values()) {
            if (edge.ordinal() == index)
                return edge;
        }

        return Edge.UB;
    }
}