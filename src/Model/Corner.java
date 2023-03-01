package Model;

public enum Corner {
    ULB, URB, URF, ULF,
    DLB, DRB, DRF, DLF;


    public static Corner getByIndex(int index) {
        for (Corner corner : Corner.values()) {
            if (corner.ordinal() == index)
                return corner;
        }

        return Corner.ULB;
    }
}