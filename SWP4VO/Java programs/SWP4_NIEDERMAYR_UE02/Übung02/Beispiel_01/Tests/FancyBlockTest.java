package Beispiel_01.Tests;

import Beispiel_01.Blocks.Implementations.*;

import java.nio.Buffer;

public class FancyBlockTest {

    public static void main(String[] args) throws Exception {
        test05();
    }

    //always used
    public static void createBlocks(String filename) throws Exception {
        FileReaderBlock b = new FileReaderBlock();
        BufferBlock bu = new BufferBlock(10);
        MedianBlock m = new MedianBlock(3);
        DifferentialBlock d = new DifferentialBlock();
        MinBlock min = new MinBlock();
        MaxBlock max = new MaxBlock();
        AvgBlock avg = new AvgBlock();
        ConsoleBlock c = new ConsoleBlock();
        FileWriterBlock f = new FileWriterBlock("signal.csv");

        //delete file signal.csv

        b.registerObserver(bu);
        bu.registerObserver(m);
        m.registerObserver(d);
        d.registerObserver(min);
        d.registerObserver(max);
        d.registerObserver(avg);
        d.registerObserver(f);
        min.registerObserver(c);
        max.registerObserver(c);
        avg.registerObserver(c);

        //if there was no filename
        if (filename.equals("")) {
            b.create_stream();
        } else {
            b.create_stream(filename);
        }
    }

    public static void test01() throws Exception {
        createBlocks("sample_data.csv");
    }

    public static void test02() throws Exception {
        FileReaderBlock f = new FileReaderBlock();
        ConsoleBlock c = new ConsoleBlock();

        f.registerObserver(c);

        f.create_stream("sample_data.csv");
    }

    public static void test03() throws Exception {
        FileReaderBlock f = new FileReaderBlock();
        BufferBlock b = new BufferBlock(2);
        ConsoleBlock c = new ConsoleBlock();

        f.registerObserver(b);
        b.registerObserver(c);

        f.create_stream("sample_data.csv");
    }

    public static void test04() throws Exception {
        FileReaderBlock f = new FileReaderBlock();
        BufferBlock b = new BufferBlock(6);
        MedianBlock m = new MedianBlock(3);
        ConsoleBlock c = new ConsoleBlock();

        f.registerObserver(b);
        b.registerObserver(m);
        m.registerObserver(c);

        f.create_stream("sample_data.csv");
    }

    public static void test05() throws Exception {
        FileReaderBlock f = new FileReaderBlock();
        BufferBlock b = new BufferBlock(6);
        MedianBlock m = new MedianBlock(3);
        DifferentialBlock d = new DifferentialBlock();
        ConsoleBlock c = new ConsoleBlock();

        f.registerObserver(b);
        b.registerObserver(m);
        m.registerObserver(d);
        d.registerObserver(c);

        f.create_stream("sample_data.csv");
    }

}
