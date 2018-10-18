package gov.nasa.rovers.rovercontroller.interpreter;

import gov.nasa.rovers.rovercontroller.core.Position;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class PositionExtractorTest {

    @Test
    public void should_Extract_Position_From_Instruction() {
        // Given
        String instruction = "12";
        PositionExtractor positionExtractor = new PositionExtractor();

        // When
        Position position = positionExtractor.extract(instruction);

        // Then
        assertThat(position).isEqualTo(new Position(1, 2));
    }

    @Test
    public void should_Throw_Exception_When_Instruction_Is_Invalid() {
        // Given
        String instruction = "1a";
        PositionExtractor positionExtractor = new PositionExtractor();

        // When
        Throwable throwable = catchThrowable(() -> positionExtractor.extract(instruction));

        // Then
        assertThat(throwable).isNotNull();
    }
}