package Beispiel_02.Tests;

import Beispiel_02.FileUtilities.LOC;

public class LOCTest {

    public static void main(String[] args) {

    }

    //tests for LOC
    public static void test09() throws Exception {
        LOC l = new LOC();

        l.readFile("Monika.txt");
    }

    public static void test10() throws Exception {
        LOC l = new LOC();

        l.readFile("IDoNotExist.txt");
    }

    public static void test11() throws Exception {
        LOC l = new LOC();

        l.readFile("IAmEmptyInside.txt");
    }

}
