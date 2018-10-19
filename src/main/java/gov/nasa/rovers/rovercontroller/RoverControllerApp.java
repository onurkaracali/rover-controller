package gov.nasa.rovers.rovercontroller;

import gov.nasa.rovers.rovercontroller.core.Rover;
import gov.nasa.rovers.rovercontroller.interpreter.RoverInstructionInterpreter;
import gov.nasa.rovers.rovercontroller.io.RoverInstructionFileReader;

import java.util.List;

public class RoverControllerApp {

    RoverInstructionFileReader roverInstructionFileReader;
    RoverInstructionInterpreter roverInstructionInterpreter;

    public RoverControllerApp() {
        roverInstructionFileReader = new RoverInstructionFileReader();
        roverInstructionInterpreter = new RoverInstructionInterpreter(roverInstructionFileReader);
    }

    public static void main(String[] args) {
        if (args.length <= 0) {
            System.out.println("Invalid argumants, An input file should be given!!");
            return;
        }

        String fileName = args[0];
        new RoverControllerApp().runInstructionsFileAndPrintRovers(fileName);
    }

    public void runInstructionsFileAndPrintRovers(String fileName) {
        try {
            roverInstructionInterpreter.runInstructions(fileName);
            List<Rover> rovers = roverInstructionInterpreter.getRovers();

            rovers.forEach(rover -> System.out.println("" + rover.getPosition().getX()
                    + rover.getPosition().getY()
                    + rover.getHeading().getSymbol()));
        } catch (Exception e) {
            System.out.println("Operation could not be done, " + e.getMessage());
        }
    }
}
