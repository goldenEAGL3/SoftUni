package InterfacesAndAbstractions.MilitaryElite;

import InterfacesAndAbstractions.MilitaryElite.Application.Engine;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Engine.run();
    }
}
