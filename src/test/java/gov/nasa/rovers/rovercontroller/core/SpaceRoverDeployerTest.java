package gov.nasa.rovers.rovercontroller.core;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class SpaceRoverDeployerTest {

    @Test
    public void shouldDeployNewRover_When_InitialPosition_Is_Inside_Plateau_Zone() {
        // Given
        Position plateauEdge = new Position(5, 5);
        Position initialPosition = new Position(1, 2);
        RoverHeading roverHeading = RoverHeading.NORTH;

        // When
        SpaceRover deployedSpaceRover = SpaceRoverDeployer.newInstance()
                .plateauEdgePoint(plateauEdge)
                .withPosition(initialPosition)
                .withHeading(roverHeading)
                .deploy();

        // Then
        assertThat(deployedSpaceRover).isNotNull();
        assertThat(deployedSpaceRover.getPosition()).isEqualTo(initialPosition);
        assertThat(deployedSpaceRover.getHeading()).isEqualTo(roverHeading);
    }

    @Test
    public void shouldThrowException_When_DeployParameters_Is_Empty() {
        // When
        Throwable throwable = catchThrowable(() -> SpaceRoverDeployer.newInstance().deploy());

        // Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(RuntimeException.class);
        assertThat(throwable).hasMessage("All parameters are required to deploy a rover");
    }

    @Test
    public void shouldThrowException_When_InitialPosition_Is_Not_Inside_Plateau() {
        // Given
        Position plateauEdge = new Position(5, 5);
        Position initialPosition = new Position(6, 6);
        RoverHeading roverHeading = RoverHeading.NORTH;

        // When
        Throwable throwable = catchThrowable(() -> SpaceRoverDeployer.newInstance()
                .plateauEdgePoint(plateauEdge)
                .withPosition(initialPosition)
                .withHeading(roverHeading)
                .deploy());

        // Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(RuntimeException.class);
        assertThat(throwable).hasMessage("Given Initial rover position (6, 6) is not inside plateau zone");
    }
}
