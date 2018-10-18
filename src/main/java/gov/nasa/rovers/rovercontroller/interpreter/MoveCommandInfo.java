package gov.nasa.rovers.rovercontroller.interpreter;

import gov.nasa.rovers.rovercontroller.core.RoverCommandType;

import java.util.List;

public class MoveCommandInfo {

    private List<RoverCommandType> commandTypeList;

    public MoveCommandInfo() {
    }

    public MoveCommandInfo(List<RoverCommandType> commandTypeList) {
        this.commandTypeList = commandTypeList;
    }

    public List<RoverCommandType> getCommandTypeList() {
        return commandTypeList;
    }

    public void setCommandTypeList(List<RoverCommandType> commandTypeList) {
        this.commandTypeList = commandTypeList;
    }
}
