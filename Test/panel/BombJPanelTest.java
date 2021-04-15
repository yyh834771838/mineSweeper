package panel;

import listenner.Listener;
import main.MainFrame;
import org.junit.Test;
import tools.LayBomb;
import tools.StaticTool;

import static org.junit.Assert.assertEquals;

public class BombJPanelTest {
    @Test
    public void test1(){//点击雷之后地图上显示红色的雷
        MainFrame mainFrame = new MainFrame();
        Listener listener = mainFrame.getBombJPanel().getListener();
        LayBomb.lay_11(listener.getMineLable(),9,9);
        listener.bombAction(9,9);
        assertEquals(1,1);
        assertEquals(listener.getMineLable()[1][1].getIcon(), StaticTool.blackBombIcon);
    }
}
