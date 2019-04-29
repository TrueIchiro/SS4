package Beispiel_02.Tests;

import Beispiel_02.FileUtilities.TreeSize;

public class TreeSizeTest {

    public static void main(String[] args) {

    }

    //tests for TreeSize
    public static void test12() throws Exception {
        TreeSize tree = new TreeSize();

        tree.calculateTree("D:\\Studium\\Sommersemester 4\\SWP4VO\\Übungen\\Übung02");
    }

    public static void test13() throws Exception {
        TreeSize tree = new TreeSize();

        tree.calculateTree("D:\\IDoNotExist");
    }

    public static void test14() throws Exception {
        TreeSize tree = new TreeSize();

        tree.calculateTree("D:\\Studium");
    }

    public static void test15() throws Exception {
        TreeSize tree = new TreeSize();

        tree.calculateTree("D:\\Studium\\Sommersemester 4\\SWP4VO\\Übungen\\Übung02\\IAmEmpty");
    }

}
