package panel;

import bean.MineLable;
import com.sun.xml.internal.fastinfoset.tools.XML_SAX_StAX_FI;
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
import java.util.Stack;
import javax.swing.*;

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

    @Test
    public void showNumber() {
        MainFrame mainFrame = new MainFrame();
        FaceJPanel faceJPanel = mainFrame.getFaceJPanel();
        Listener listener = mainFrame.getBombJPanel().getListener();
        StaticTool.bombCount = 10;
        faceJPanel.setNumber(StaticTool.bombCount);
        assertEquals(StaticTool.bombCount, 10);
        listener.rightClick_1(0,0);
        assertEquals(StaticTool.bombCount, 9);
    }


    @Test
    public void countTime() throws InterruptedException {
        MainFrame mainFrame = new MainFrame();
        Timer timer = mainFrame.getTimer();
        assertEquals(0, StaticTool.timecount);
        timer.start();
        Thread.sleep(3500);
        timer.stop();
        assertEquals(3, StaticTool.timecount);
    }

    @Test
    public void getLabelFace() {
        MainFrame mainFrame = new MainFrame();
        FaceJPanel faceJPanel = mainFrame.getFaceJPanel();
        Listener listener = mainFrame.getBombJPanel().getListener();
        faceJPanel.getLabelFace().setIcon(StaticTool.clickIcon);
        assertEquals(StaticTool.clickIcon, faceJPanel.getLabelFace().getIcon());
        listener.bombAction_1(0,0);
        assertEquals(StaticTool.faultFaceIcon, faceJPanel.getLabelFace().getIcon());
    }
}
