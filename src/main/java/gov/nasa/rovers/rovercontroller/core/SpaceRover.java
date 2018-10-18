package gov.nasa.rovers.rovercontroller.core;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class SpaceRover implements Rover {

    private static final int FULL_ANGLE = 360;
    private static final int FLIP_ANGLE = 90;

    private final Position plateauEdgePosition;
    private Position position;
    private RoverHeading heading;

    private Map<RoverHeading, Function<Position, Position>> positionCalculationMapByHeading;

    SpaceRover(Position plateauEdgePosition, Position position, RoverHeading heading) {
        this.plateauEdgePosition = plateauEdgePosition;
        this.position = position;
        this.heading = heading;

        initializePositionFuctionsMap();
    }

    private void initializePositionFuctionsMap() {
        positionCalculationMapByHeading = new HashMap<>();
        positionCalculationMapByHeading.put(RoverHeading.NORTH,
                position -> new Position(position.getX(), position.getY() + 1));
        positionCalculationMapByHeading.put(RoverHeading.EAST,
                position -> new Position(position.getX() + 1, position.getY()));
        positionCalculationMapByHeading.put(RoverHeading.SOUTH,
                position -> new Position(position.getX(), position.getY() - 1));
        positionCalculationMapByHeading.put(RoverHeading.WEST,
                position -> new Position(position.getX() - 1, position.getY()));

    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public RoverHeading getHeading() {
        return this.heading;
    }

    @Override
    public void move(RoverCommandType roverCommandType) {
        if (RoverCommandType.MOVE_FORWARD.equals(roverCommandType)) {
            doForwardMove();
        } else {
            this.heading = calculateNextheading(roverCommandType);
        }
    }

    private void doForwardMove() {
        Function<Position, Position> positionFunction = positionCalculationMapByHeading.get(heading);
        Position finalPosition = positionFunction.apply(position);

        if (!plateauEdgePosition.checkInsideBoundary(finalPosition)) {
            throw new RuntimeException("Invalid position: Rover left the plateau zone");
        }

        this.position = finalPosition;
    }

    private RoverHeading calculateNextheading(RoverCommandType moveType) {
        int finalAngle = calculateNextAngleByMoveType(moveType);
        return RoverHeading.byAngle(finalAngle);
    }

    private int calculateNextAngleByMoveType(RoverCommandType moveType) {
        int angle = heading.getAngle();
        if (RoverCommandType.FLIP_RIGHT.equals(moveType)) {
            angle -= FLIP_ANGLE;
            if (angle < 0) {
                angle += FULL_ANGLE;
            }
        } else if (RoverCommandType.FLIP_LEFT.equals(moveType)) {
            angle += FLIP_ANGLE;
        }
        return Math.abs(angle) % FULL_ANGLE;
    }

    @Override
    public String toString() {
        return "SpaceRover{" +
                "position=" + position +
                ", heading=" + heading +
                '}';
    }
}
