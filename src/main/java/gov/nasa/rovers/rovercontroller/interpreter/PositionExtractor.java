package gov.nasa.rovers.rovercontroller.interpreter;

import gov.nasa.rovers.rovercontroller.core.Position;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PositionExtractor implements InstructionExtractor<Position> {
    private final String POSITION_PATTERN = "^([0-9])([0-9])$";

    @Override
    public Position extract(String instruction) {
        Pattern pattern = Pattern.compile(POSITION_PATTERN);
        Matcher matcher = pattern.matcher(instruction);

        if(!matcher.find()) {
            throw new IllegalArgumentException("Illegal instruction " + instruction);
        }

        return new Position(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
    }
}
