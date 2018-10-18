package gov.nasa.rovers.rovercontroller.io;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RoverInstructionFileReaderTest {

    @Test
    public void should_New_Instance_Created_When_File_Exists() throws IOException {
        // Given
        String fileName = "src/test/resources/input/simple_input_file.txt";

        // When
        RoverInstructionFileReader roverInstructionReader = new RoverInstructionFileReader();

        // Then
        assertThat(roverInstructionReader).isNotNull();
    }

    @Test
    public void should_Return_All_Instructions_When_File_Exists_And_Has_Lines() throws IOException {
        // Given
        String fileName = "src/test/resources/input/simple_input_file.txt";

        // When
        RoverInstructionFileReader roverInstructionReader = new RoverInstructionFileReader();
        List<String> allInstructions = roverInstructionReader.readInstructionsFromFile(fileName);
        // Then
        assertThat(roverInstructionReader).isNotNull();
        assertThat(allInstructions).isNotNull();
        assertThat(allInstructions).hasSize(3);
    }

    @Test
    public void should_Return_Empty_List_When_File_Has_Not_Any_Line() throws IOException {
        // Given
        String fileName = "src/test/resources/input/empty_file.txt";

        // When
        RoverInstructionFileReader roverInstructionReader = new RoverInstructionFileReader();
        List<String> allInstructions = roverInstructionReader.readInstructionsFromFile(fileName);

        // Then
        assertThat(roverInstructionReader).isNotNull();
        assertThat(allInstructions).isEmpty();
    }
}