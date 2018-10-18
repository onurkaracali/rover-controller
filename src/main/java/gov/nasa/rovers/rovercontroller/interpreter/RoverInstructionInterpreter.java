package gov.nasa.rovers.rovercontroller.interpreter;

import gov.nasa.rovers.rovercontroller.core.*;
import gov.nasa.rovers.rovercontroller.io.RoverInstructionFileReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoverInstructionInterpreter {

    private List<String> instructions;
    private Position plateauBoundary;
    private List<Rover> rovers = new ArrayList<>();
    private PositionExtractor positionExtractor = new PositionExtractor();
    private DeployExtractor deployExtractor = new DeployExtractor();
    private MoveCommandExtractor moveCommandExtractor = new MoveCommandExtractor();

    private final RoverInstructionFileReader roverInstructionFileReader;

    public RoverInstructionInterpreter(RoverInstructionFileReader roverInstructionFileReader) {
        this.roverInstructionFileReader = roverInstructionFileReader;
    }

    public void runInstructions(String fileName) {
        instructions = readInstructions(fileName);
        if(instructions.isEmpty()) {
            throw new IllegalArgumentException("Instructions can not be empty");
        }

        Iterator<String> iterator = instructions.iterator();

        plateauBoundary = positionExtractor.extract(iterator.next());
        while (iterator.hasNext()) {
            deployAndManageRovers(iterator);
        }
    }

    private List<String> readInstructions(String fileName) {
        return roverInstructionFileReader.readInstructionsFromFile(fileName);
    }

    private void deployAndManageRovers(Iterator<String> iterator) {
        String deployInstruction = iterator.next();

        if (!iterator.hasNext()) {
            throw new RuntimeException("Intructions file is invalid!");
        }
        String moveInstruction = iterator.next();

        SpaceRover rover = runInstructionAndDeployARover(deployInstruction);
        List<RoverCommandType> roverCommandTypes = extractMoveCommands(moveInstruction);

        roverCommandTypes.forEach(rover::move);

        rovers.add(rover);
    }

    private SpaceRover runInstructionAndDeployARover(String deployInstruction) {
        DeployInfo deployInfo = deployExtractor.extract(deployInstruction);

        return SpaceRoverDeployer.newInstance()
                .plateauEdgePoint(plateauBoundary)
                .withHeading(deployInfo.getHeading())
                .withPosition(deployInfo.getPosition())
                .deploy();
    }

    private List<RoverCommandType> extractMoveCommands(String moveCommandInstruction) {
        MoveCommandInfo moveCommandInfo = moveCommandExtractor.extract(moveCommandInstruction);
        return moveCommandInfo.getCommandTypeList();
    }

    public List<Rover> getRovers() {
        return rovers;
    }
}
