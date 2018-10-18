package gov.nasa.rovers.rovercontroller.core;

public enum RoverCommandType {
    MOVE_FORWARD("M"),
    FLIP_RIGHT("R"),
    FLIP_LEFT("L");

    private String symbol;

    RoverCommandType(String symbol) {
        this.symbol = symbol;
    }

    public static RoverCommandType bySymbol(final String symbol) {
        for (RoverCommandType roverMode : values()) {
            if (symbol.equals(roverMode.symbol)) {
                return roverMode;
            }
        }
        return null;
    }
}
