## Rover Controller App

A demo app for controlling space rovers with an input file

### Requirements
Java 1.8 or higher and maven 3+

#### Usage

Build project using maven by typing

```mvn install```

### Input Format
<initial plateau boundary position (xy)>
<initial rover position and heading like 24N>
<move commands like RMLMRMMMRL>
  
#### Example Input File
77  
11N  
MMRMMMR

