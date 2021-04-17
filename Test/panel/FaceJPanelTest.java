package panel;

import bean.MineLable;
import listenner.Listener;
import main.MainFrame;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tools.LayBomb;
import tools.StaticTool;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class FaceJPanelTest {


    @Test
    public void test2(){//点击雷之后变成哭脸
        MainFrame mainFrame = new MainFrame();
        Listener listener = mainFrame.getBombJPanel().getListener();
        MineLable[][] mineLables = listener.getMineLable();
        LayBomb.lay_11(mineLables,9,9);
        listener.bombAction_1(9,9);
        assertEquals(mainFrame.getFaceJPanel().getLabelFace().getIcon(), StaticTool.faultFaceIcon);
    }

}
