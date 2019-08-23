package core;

import core.interfaces.InputReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InputReaderImpl implements InputReader {
    private Scanner sc;
    private List<String> lines;

    public InputReaderImpl() {
        this.sc = new Scanner(System.in);
        this.lines = new ArrayList<>();
    }

    @Override
    public void readLines() {
        while (true) {
            String line = sc.nextLine();
            if ("Exit".equals(line)) {
                break;
            }
            lines.add(line);
        }
    }

    @Override
    public List<String> getLines() {
        return Collections.unmodifiableList(this.lines);
    }
}
