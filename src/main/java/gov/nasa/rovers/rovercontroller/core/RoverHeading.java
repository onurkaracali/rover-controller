package gov.nasa.rovers.rovercontroller.core;

public enum RoverHeading {
    EAST("E", 0),
    NORTH("N", 90),
    WEST("W", 180),
    SOUTH("S", 270);

    private String symbol;
    private int angle;

    RoverHeading(String symbol, int angle) {
        this.symbol = symbol;
        this.angle = angle;
    }

    public static RoverHeading bySymbol(final String symbol) {
        for (RoverHeading roverHeading : values()) {
            if (symbol.equals(roverHeading.symbol)) {
                return roverHeading;
            }
        }
        return null;
    }

    public static RoverHeading byAngle(final int angle) {
        for (RoverHeading roverHeading : values()) {
            if (roverHeading.angle == angle) {
                return roverHeading;
            }
        }
        return null;
    }

    public int getAngle() {
        return angle;
    }

    public String getSymbol() {
        return symbol;
    }
}
