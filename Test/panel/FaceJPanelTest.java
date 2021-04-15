package panel;

import listenner.Listener;
import main.MainFrame;
import org.junit.Test;
import tools.LayBomb;
import tools.StaticTool;

import static org.junit.Assert.assertEquals;

public class FaceJPanelTest {
    @Test
    public void test2(){//点击雷之后变成哭脸
        MainFrame mainFrame = new MainFrame();
        Listener listener = mainFrame.getBombJPanel().getListener();
        LayBomb.lay_11(listener.getMineLable(),9,9);
        listener.bombAction_1(9,9);
        assertEquals(mainFrame.getFaceJPanel().getLabelFace().getIcon(), StaticTool.faultFaceIcon);
    }
}
