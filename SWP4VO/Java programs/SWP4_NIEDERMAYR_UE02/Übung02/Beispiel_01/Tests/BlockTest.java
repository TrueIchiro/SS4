package Beispiel_01.Tests;

import Beispiel_01.Blocks.Block;
import Beispiel_01.Blocks.Implementations.ConsoleBlock;
import Beispiel_01.Blocks.Implementations.FileReaderBlock;
import Beispiel_01.Blocks.Implementations.SumBlock;

public class BlockTest {

    public static void main(String[] args) throws Exception {
        test05();
    }

    //always used
    public static void createBlocks(String filename) throws Exception {
        FileReaderBlock b = new FileReaderBlock();
        SumBlock s = new SumBlock();
        ConsoleBlock c = new ConsoleBlock();

        b.registerObserver(s);
        s.registerObserver(c);

        //if there was no filename
        if (filename.equals("")) {
            b.create_stream();
        } else {
            b.create_stream(filename);
        }
    }

    public static void test01() throws Exception {
        createBlocks("");
    }

    public static void test02() throws Exception {
        createBlocks("IntegerTest.txt");
    }

    public static void test03() throws Exception {
        createBlocks("IntegerTest2.txt");
    }

    public static void test04() throws Exception {
        createBlocks("DoubleTest.txt");
    }

    public static void test05() throws Exception {
        createBlocks("StringTest.txt");
    }

}
