package gov.nasa.rovers.rovercontroller.interpreter;

import gov.nasa.rovers.rovercontroller.core.Position;
import gov.nasa.rovers.rovercontroller.core.RoverHeading;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeployExtractor implements InstructionExtractor<DeployInfo> {

    private final String POSITION_PATTERN = "^([0-9])([0-9])([E|N|W|S])$";

    @Override
    public DeployInfo extract(String instruction) {
        Pattern pattern = Pattern.compile(POSITION_PATTERN);
        Matcher matcher = pattern.matcher(instruction);

        if(!matcher.find()) {
            throw new IllegalArgumentException("Illegal instruction " + instruction);
        }

        Integer xPosition = Integer.valueOf(matcher.group(1));
        Integer yPosition = Integer.valueOf(matcher.group(2));
        RoverHeading roverHeading = RoverHeading.bySymbol(matcher.group(3));

        return new DeployInfo(new Position(xPosition, yPosition), roverHeading);
    }
}
