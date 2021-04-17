package dialog;

import main.MainFrame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class AboutSweepingTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPanel() {
        MainFrame mainFrame = new MainFrame();
        JDialog help = new AboutSweeping(mainFrame);
        assertNotEquals(help, null);
    }
}