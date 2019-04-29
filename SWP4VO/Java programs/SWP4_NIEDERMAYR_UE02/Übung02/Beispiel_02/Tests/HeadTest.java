package Beispiel_02.Tests;

import Beispiel_02.FileUtilities.Head;

public class HeadTest {

    public static void main(String[] args) {

    }

    //tests for Head
    public static void test01() throws Exception {
        Head h = new Head(3);
        h.readFile("Monika.txt");
    }

    public static void test02() throws Exception {
        Head h = new Head(-7);
        h.readFile("Monika.txt");
    }

    //test it with a line count that's bigger than the file
    public static void test03() throws Exception {
        Head h = new Head(99);
        h.readFile("Monika.txt");
    }

    public static void test04() throws Exception {
        Head h = new Head();
        h.readFile("Monika.txt");
    }

}
