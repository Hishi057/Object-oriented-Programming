package oop.ex4B2;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public Program parse(List<String> lines) {
        List<String[]> inputs = new ArrayList<String[]>();
        for(String l : lines){
            String[] line = l.split("\\s+");
            inputs.add(line);
        }

        return new Program(inputs);
    }
}
