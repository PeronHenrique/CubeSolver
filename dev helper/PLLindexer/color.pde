public enum COLOR {
    WHITE(255, 255, 255),
    YELLOW(255, 255, 0),
    BLUE(0, 0, 255),
    GREEN(0, 255, 0),
    RED(255, 0, 0),
    ORANGE(255, 125, 0),
    BLACK(0, 0, 0);

    private final int c;

    private COLOR(int r, int g, int b) {
        c = (0xFF << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF) << 0);
    }

    private COLOR(int r, int g, int b, int a) {
        c = ((a & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8) | ((b & 0xFF) << 0);
    }

    public int getColor() {
      return c;
    }
        
}
