package gov.nasa.rovers.rovercontroller.interpreter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class MoveCommandExtractorTest {

    @Test
    public void should_Extract_Move_Commands_From_Instruction() {
        // Given
        String command = "LMLMLM";
        MoveCommandExtractor moveCommandExtractor = new MoveCommandExtractor();

        // When
        MoveCommandInfo extract = moveCommandExtractor.extract(command);

        // Then
        assertThat(extract).isNotNull();
        assertThat(extract.getCommandTypeList()).hasSize(6);
    }

    @Test
    public void should_Throw_Exception_When_Instruction_Is_Invalid() {
        // Given
        String command = "ASD ASD";
        MoveCommandExtractor moveCommandExtractor = new MoveCommandExtractor();

        // When
        Throwable throwable = catchThrowable(() -> moveCommandExtractor.extract(command));

        // Then
        assertThat(throwable).isNotNull();
    }
}