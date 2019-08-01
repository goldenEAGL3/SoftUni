package Inheritance.hero;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Knight knight1 = new Knight("silencer", 79);
        DarkKnight knight = new DarkKnight("respeta", 80);
        BladeKnight knight2 = new BladeKnight("proba", 81);

        Elf elf1 = new Elf("Miro", 2);
        MuseElf elf = new MuseElf("goldenEAGLE", 80);

        Wizard wizard = new Wizard("gosho", 25);
        DarkWizard darkWizard = new DarkWizard("pesho", 24);
        SoulMaster master = new SoulMaster("noobmaster69", 69);

        System.out.println(knight1.toString());
        System.out.println(knight.toString());
        System.out.println(knight2.toString());

        System.out.println(elf.toString());
        System.out.println(elf1.toString());

        System.out.println(wizard.toString());
        System.out.println(darkWizard.toString());
        System.out.println(master.toString());
    }
}
