package Beispiel_02.Tests;

import Beispiel_02.FileUtilities.Tail;

public class TailTest {

    public static void main(String[] args) {

    }

    //tests for Tail
    public static void test05() throws Exception {
        Tail t = new Tail(3);
        t.readFile("Monika.txt");
    }

    public static void test06() throws Exception {
        Tail t = new Tail(-77);
        t.readFile("Monika.txt");
    }

    //test it with a line count that's bigger than the file
    public static void test07() throws Exception {
        Tail t = new Tail(1337);
        t.readFile("Monika.txt");
    }

    public static void test08() throws Exception {
        Tail t = new Tail();
        t.readFile("Monika.txt");
    }

}
