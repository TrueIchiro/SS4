package Beispiel_02.FileUtilities;

import java.io.File;
import java.text.DecimalFormat;

public class TreeSize {

    private String format = "|--";
    private String space = "   ";
    private String pattern = "###,###.###";
    private DecimalFormat decimalFormat = new DecimalFormat();

    private long tree(String path, int depth) {
        try {
            long size = 0;

            //create a new File with the path
            File folder = new File(path);
            //gets an array with the files in this path
            File[] files = folder.listFiles();

            //goes through the files in this directory
            for (int i = 0; i < files.length; i++) {
                //the current file is a directory
                if (files[i].isDirectory()) {
                    String newPath = files[i].toString();

                    size += tree(newPath, depth + 1);

                    //print the size for the directory
                    System.out.println(format + " Current size of "
                            + files[i].toString() + ": "
                            + decimalFormat.format(size)
                            + " Byte");
                } else { //get the size of one file
                    size += files[i].length();

                    for (int j = 0; j < depth; j++) {
                        System.out.print(space);
                    }

                    if (files[i].isFile()) {
                        //print the size for the current file
                        System.out.println(format + " Current size of "
                                + files[i].toString() + ": "
                                + decimalFormat.format(files[i].length())
                                + " Byte");
                    }
                }

            }

            return size;
        } catch (NullPointerException n) {
            System.out.println("The path is invalid!");
            return 0;
        }
    }

    public void calculateTree(String path) {
        System.out.println("Directory: " + path);

        long directorySize = tree(path, 0);

        System.out.println("Size of directory " + path + ": "
                + decimalFormat.format(directorySize) + " Byte");
    }

}
