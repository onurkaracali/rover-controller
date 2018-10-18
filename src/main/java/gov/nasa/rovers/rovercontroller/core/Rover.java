package gov.nasa.rovers.rovercontroller.core;

public interface Rover {

    Position getPosition();

    RoverHeading getHeading();

    void move(RoverCommandType roverCommandType);
}
