package gov.nasa.rovers.rovercontroller.interpreter;

import gov.nasa.rovers.rovercontroller.core.Position;
import gov.nasa.rovers.rovercontroller.core.Rover;
import gov.nasa.rovers.rovercontroller.core.RoverHeading;
import gov.nasa.rovers.rovercontroller.io.RoverInstructionFileReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoverInstructionInterpreterTest {

    @Mock
    RoverInstructionFileReader roverInstructionFileReader;

    @Test
    public void should_Run_Instructions_And_Do_Correct_Actions_With_1_Rover_Input() {
        // Given
        String fileName = "test.txt";
        List<String> instructions = Arrays.asList("55", "12N", "RM");
        RoverInstructionInterpreter roverInstructionInterpreter = new RoverInstructionInterpreter(roverInstructionFileReader);
        when(roverInstructionFileReader.readInstructionsFromFile(fileName)).thenReturn(instructions);

        // When
        roverInstructionInterpreter.runInstructions(fileName);

        // Then
        List<Rover> rovers = roverInstructionInterpreter.getRovers();

        assertThat(rovers).isNotNull();
        assertThat(rovers).hasSize(1);
        assertThat(rovers.get(0).getHeading()).isEqualTo(RoverHeading.EAST);
        assertThat(rovers.get(0).getPosition()).isEqualTo(new Position(2, 2));
    }

    @Test
    public void should_Run_Instructions_And_Do_Correct_Actions_With_2_Rover_Input() {
        // Given
        String fileName = "test.txt";
        List<String> instructions = Arrays.asList("55", "12N", "RM", "01N", "RMM");
        RoverInstructionInterpreter roverInstructionInterpreter = new RoverInstructionInterpreter(roverInstructionFileReader);
        when(roverInstructionFileReader.readInstructionsFromFile(fileName)).thenReturn(instructions);

        // When
        roverInstructionInterpreter.runInstructions(fileName);

        // Then
        List<Rover> rovers = roverInstructionInterpreter.getRovers();

        assertThat(rovers).isNotNull();
        assertThat(rovers).hasSize(2);
        assertThat(rovers.get(0).getHeading()).isEqualTo(RoverHeading.EAST);
        assertThat(rovers.get(0).getPosition()).isEqualTo(new Position(2, 2));
        assertThat(rovers.get(1).getHeading()).isEqualTo(RoverHeading.EAST);
        assertThat(rovers.get(1).getPosition()).isEqualTo(new Position(2, 1));
    }

    @Test
    public void should_Throw_Exception_When_Input_File_Is_Invalid() {
        // Given
        String fileName = "test.txt";
        List<String> instructions = Arrays.asList("55", "12N");
        RoverInstructionInterpreter roverInstructionInterpreter = new RoverInstructionInterpreter(roverInstructionFileReader);
        when(roverInstructionFileReader.readInstructionsFromFile(fileName)).thenReturn(instructions);

        // When
        Throwable throwable = catchThrowable(() ->  roverInstructionInterpreter.runInstructions(fileName));

        // Then
        assertThat(throwable).isNotNull();
    }
}