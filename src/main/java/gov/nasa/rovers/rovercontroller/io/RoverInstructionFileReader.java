package gov.nasa.rovers.rovercontroller.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class RoverInstructionFileReader {

    public List<String> readInstructionsFromFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println("Could not read instruction file.");
        }
        return Collections.emptyList();
    }
}
