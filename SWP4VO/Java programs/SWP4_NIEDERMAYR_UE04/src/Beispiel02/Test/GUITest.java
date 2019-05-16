package Beispiel02.Test;

import Beispiel02.Functionalities.GUI.PseudoGUI;

public class GUITest {

    public static void main(String[] args) throws Exception {
        test01();
    }

    //is needed for everything
    public static void test01() throws Exception {
        PseudoGUI gui = new PseudoGUI();
        gui.startGUI();
    }

}
