package gov.nasa.rovers.rovercontroller.interpreter;

import gov.nasa.rovers.rovercontroller.core.Position;
import gov.nasa.rovers.rovercontroller.core.RoverHeading;

public class DeployInfo {
    private Position position;
    private RoverHeading heading;

    public DeployInfo() {
    }

    public DeployInfo(Position position, RoverHeading heading) {
        this.position = position;
        this.heading = heading;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public RoverHeading getHeading() {
        return heading;
    }

    public void setHeading(RoverHeading heading) {
        this.heading = heading;
    }
}
