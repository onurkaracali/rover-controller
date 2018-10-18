package gov.nasa.rovers.rovercontroller.interpreter;

import gov.nasa.rovers.rovercontroller.core.Position;
import gov.nasa.rovers.rovercontroller.core.RoverHeading;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class DeployExtractorTest {

    @Test
    public void should_Extract_Deploy_Info_When_Instruction_Is_Valid() {
        // Given
        String instruction = "12N";

        // When
        DeployExtractor deployExtractor = new DeployExtractor();
        DeployInfo deployInfo = deployExtractor.extract(instruction);

        // Then
        assertThat(deployInfo).isNotNull();
        assertThat(deployInfo.getPosition()).isEqualTo(new Position(1, 2));
        assertThat(deployInfo.getHeading()).isEqualTo(RoverHeading.NORTH);
    }

    @Test
    public void should_Throw_Exception_When_Instruction_Position_Is_Invalid() {
        // Given
        String instruction = "1aN";

        // When
        DeployExtractor deployExtractor = new DeployExtractor();
        Throwable throwable = catchThrowable(() -> deployExtractor.extract(instruction));

        // Then
        assertThat(throwable).isNotNull();
    }

    @Test
    public void should_Throw_Exception_When_Instruction_Heading_Is_Invalid() {
        // Given
        String instruction = "12X";

        // When
        DeployExtractor deployExtractor = new DeployExtractor();
        Throwable throwable = catchThrowable(() -> deployExtractor.extract(instruction));

        // Then
        assertThat(throwable).isNotNull();
    }
}