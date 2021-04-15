package tools;

import bean.MineLable;
import listenner.Listener;
import main.MainFrame;
import org.junit.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class LayBombTest {
    public static LayBomb layBomb = new LayBomb();

    @Test
    public void lay(){
        layBomb.init();
        layBomb.lay(layBomb.labels, StaticTool.allrow,StaticTool.allcol);
        assertEquals(10,LayBomb.returnCount);
    }
    @Test
    public void test1(){
        MainFrame mainFrame = new MainFrame();
        Listener listener = mainFrame.getBombJPanel().getListener();
        LayBomb.lay_11(listener.getMineLable(),9,9);
        listener.bombAction(9,9);
        assertEquals(1,1);
        assertEquals(listener.getMineLable()[1][1].getIcon(),StaticTool.blackBombIcon);
    }

}
