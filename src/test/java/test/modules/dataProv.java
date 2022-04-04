package test.modules;

import org.testng.annotations.DataProvider;

public class dataProv {
    @DataProvider
    static public Object[][] dp() {
        return new Object[][] {
                new Object[] { "10", "4" },
                new Object[] { "8", "1" },
        };
    }

}
