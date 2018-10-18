package gov.nasa.rovers.rovercontroller.core;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class SpaceRoverTest {

    @Test
    public void should_Final_Position_Is_Correct_After_Move_Forward_When_Heading_Is_East() {
        // Given
        Position plateauPosition = new Position(5, 5);
        Position initialPosition = new Position(1, 2);
        RoverHeading roverHeading = RoverHeading.EAST;

        SpaceRover spaceRover = new SpaceRover(plateauPosition, initialPosition, roverHeading);

        // When
        spaceRover.move(RoverCommandType.MOVE_FORWARD);

        // Then
        assertThat(spaceRover.getPosition()).isEqualTo(new Position(2, 2));
        assertThat(spaceRover.getHeading()).isEqualTo(RoverHeading.EAST);
    }

    @Test
    public void should_Final_Position_Is_Correct_After_Move_Forward_When_Heading_Is_North() {
        // Given
        Position plateauPosition = new Position(5, 5);
        Position initialPosition = new Position(1, 3);
        RoverHeading roverHeading = RoverHeading.NORTH;

        SpaceRover spaceRover = new SpaceRover(plateauPosition, initialPosition, roverHeading);

        // When
        spaceRover.move(RoverCommandType.MOVE_FORWARD);

        // Then
        assertThat(spaceRover.getPosition()).isEqualTo(new Position(1, 4));
        assertThat(spaceRover.getHeading()).isEqualTo(RoverHeading.NORTH);
    }

    @Test
    public void should_Final_Position_Is_Correct_After_Move_Forward_When_Heading_Is_South() {
        // Given
        Position plateauPosition = new Position(5, 5);
        Position initialPosition = new Position(1, 2);
        RoverHeading roverHeading = RoverHeading.SOUTH;

        SpaceRover spaceRover = new SpaceRover(plateauPosition, initialPosition, roverHeading);

        // When
        spaceRover.move(RoverCommandType.MOVE_FORWARD);

        // Then
        assertThat(spaceRover.getPosition()).isEqualTo(new Position(1, 1));
        assertThat(spaceRover.getHeading()).isEqualTo(RoverHeading.SOUTH);
    }

    @Test
    public void should_Final_Position_Is_Correct_After_Move_Forward_When_Heading_Is_West() {
        // Given
        Position plateauPosition = new Position(5, 5);
        Position initialPosition = new Position(2, 2);
        RoverHeading roverHeading = RoverHeading.WEST;

        SpaceRover spaceRover = new SpaceRover(plateauPosition, initialPosition, roverHeading);

        // When
        spaceRover.move(RoverCommandType.MOVE_FORWARD);

        // Then
        assertThat(spaceRover.getPosition()).isEqualTo(new Position(1, 2));
        assertThat(spaceRover.getHeading()).isEqualTo(RoverHeading.WEST);
    }

    @Test
    public void should_Final_Position_Is_Correct_After_Flip_Right() {
        // Given
        Position plateauPosition = new Position(5, 5);
        Position initialPosition = new Position(1, 3);
        RoverHeading roverHeading = RoverHeading.NORTH;
        SpaceRover spaceRover = new SpaceRover(plateauPosition, initialPosition, roverHeading);

        // When
        spaceRover.move(RoverCommandType.FLIP_RIGHT);

        // Then
        assertThat(spaceRover.getPosition()).isEqualTo(new Position(1, 3));
        assertThat(spaceRover.getHeading()).isEqualTo(RoverHeading.EAST);
    }

    @Test
    public void should_Final_Position_Is_Correct_After_Flip_Left() {
        // Given
        Position plateauPosition = new Position(5, 5);
        Position initialPosition = new Position(1, 3);
        RoverHeading roverHeading = RoverHeading.NORTH;
        SpaceRover spaceRover = new SpaceRover(plateauPosition, initialPosition, roverHeading);

        // When
        spaceRover.move(RoverCommandType.FLIP_LEFT);

        // Then
        assertThat(spaceRover.getPosition()).isEqualTo(new Position(1, 3));
        assertThat(spaceRover.getHeading()).isEqualTo(RoverHeading.WEST);
    }

    @Test
    public void should_Flip_Circular_When_Heading_Is_East_And_Move_Flip_Right() {
        // Given
        Position plateauPosition = new Position(5, 5);
        Position initialPosition = new Position(1, 3);
        RoverHeading roverHeading = RoverHeading.EAST;
        SpaceRover spaceRover = new SpaceRover(plateauPosition, initialPosition, roverHeading);

        // When
        spaceRover.move(RoverCommandType.FLIP_RIGHT);

        // Then
        assertThat(spaceRover.getPosition()).isEqualTo(new Position(1, 3));
        assertThat(spaceRover.getHeading()).isEqualTo(RoverHeading.SOUTH);
    }

    @Test
    public void should_Flip_Circular_When_Heading_Is_South_And_Move_Flip_Left() {
        // Given
        Position plateauPosition = new Position(5, 5);
        Position initialPosition = new Position(1, 3);
        RoverHeading roverHeading = RoverHeading.SOUTH;
        SpaceRover spaceRover = new SpaceRover(plateauPosition, initialPosition, roverHeading);

        // When
        spaceRover.move(RoverCommandType.FLIP_LEFT);

        // Then
        assertThat(spaceRover.getPosition()).isEqualTo(new Position(1, 3));
        assertThat(spaceRover.getHeading()).isEqualTo(RoverHeading.EAST);
    }



    @Test
    public void should_Throw_Exception_When_Final_Position_Is_Not_Correct() {
        // Given
        Position plateauPosition = new Position(5, 5);
        Position initialPosition = new Position(1, 5);
        RoverHeading roverHeading = RoverHeading.NORTH;
        SpaceRover spaceRover = new SpaceRover(plateauPosition, initialPosition, roverHeading);

        // When
        Throwable throwable = catchThrowable(() -> spaceRover.move(RoverCommandType.MOVE_FORWARD));

        // Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(RuntimeException.class);
    }
}