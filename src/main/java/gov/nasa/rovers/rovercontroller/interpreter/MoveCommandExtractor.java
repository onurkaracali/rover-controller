package gov.nasa.rovers.rovercontroller.interpreter;

import gov.nasa.rovers.rovercontroller.core.RoverCommandType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveCommandExtractor implements InstructionExtractor<MoveCommandInfo> {

    private final String POSITION_PATTERN = "([M|R|L])";

    @Override
    public MoveCommandInfo extract(String instruction) {
        Pattern pattern = Pattern.compile(POSITION_PATTERN);
        Matcher matcher = pattern.matcher(instruction);

        List<RoverCommandType> moveCommands = new ArrayList<>();
        while (matcher.find()) {
            String symbol = matcher.group();
            RoverCommandType commandType = RoverCommandType.bySymbol(symbol);
            moveCommands.add(commandType);
        }

        if (moveCommands.size() == 0) {
            throw new RuntimeException("Invalid instruction " + instruction);
        }

        return new MoveCommandInfo(moveCommands);
    }
}
