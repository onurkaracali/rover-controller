package gov.nasa.rovers.rovercontroller.interpreter;

import java.util.Iterator;

public interface InstructionExtractor<T> {
    T extract(String instruction);
}
