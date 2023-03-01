package Model;

public enum Face {
    U, L, F, R, B, D;

    public static Face getByIndex(int index) {
        for (Face center : Face.values()) {
            if (center.ordinal() == index)
                return center;
        }

        return Face.U;
    }
}