package Tests.TestingClasses;

import static Algorithms.BruteSearch.search;

public class BruteSearchTest {

    public static void main(String[] args) {

        String text = "Fuckin try me mate";
        String pattern = "Fuck";

        //static, so no Object is needed
        System.out.println(search(text, pattern, 0));
    }

}
