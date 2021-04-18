package panel;

import main.MainFrame;
import org.junit.Test;
import org.junit.runner.RunWith;
import tools.StaticTool;

import static org.junit.Assert.*;

public class FaceJPanelTest {
    MainFrame mainframe = new MainFrame();
    FaceJPanel FJ = new FaceJPanel(mainframe);
    @Test
    public void getLabelFace() {
        assertNotNull(FJ.getLabelFace());
    }

    @Test
    public void setTime() {
        FJ.setTime(999);
        assertEquals(FJ.getLabelTimeG().getIcon(),StaticTool.time[9]);
        assertEquals(FJ.getLabelTimeS().getIcon(),StaticTool.time[9]);
        assertEquals(FJ.getLabelTimeB().getIcon(),StaticTool.time[9]);
    }

    @Test
    public void setNumber() {
        FJ.setNumber(55);
        assertEquals(FJ.getLabelCountG().getIcon(),StaticTool.time[5]);
        assertEquals(FJ.getLabelCountS().getIcon(),StaticTool.time[5]);
        assertEquals(FJ.getLabelCountB().getIcon(),StaticTool.time[0]);
    }
}
