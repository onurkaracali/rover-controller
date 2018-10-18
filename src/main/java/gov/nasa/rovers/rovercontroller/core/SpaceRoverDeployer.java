package gov.nasa.rovers.rovercontroller.core;

public class SpaceRoverDeployer {

    private Position plateauEdge = null;
    private Position roverInitialPosition = null;
    private RoverHeading roverHeading = null;

    private SpaceRoverDeployer() {}

    public static SpaceRoverDeployer newInstance() {
        return new SpaceRoverDeployer();
    }

    public SpaceRoverDeployer plateauEdgePoint(Position plateauEdge) {
        this.plateauEdge = plateauEdge;
        return this;
    }

    public SpaceRoverDeployer withPosition(Position roverInitialPosition) {
        this.roverInitialPosition = roverInitialPosition;
        return this;
    }

    public SpaceRoverDeployer withHeading(RoverHeading roverHeading) {
        this.roverHeading = roverHeading;
        return this;
    }

    public SpaceRover deploy() {
        if (plateauEdge == null
                || roverInitialPosition == null
                || roverHeading == null) {
            throw new RuntimeException("All parameters are required to deploy a rover");
        }

        if (!plateauEdge.checkInsideBoundary(roverInitialPosition)) {
            throw new RuntimeException(
                    String.format("Given Initial rover position (%d, %d) is not inside plateau zone",
                            roverInitialPosition.getX(),
                            roverInitialPosition.getY()));
        }

        return new SpaceRover(plateauEdge, roverInitialPosition, roverHeading);
    }
}
