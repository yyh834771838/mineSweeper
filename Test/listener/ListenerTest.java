package listener;


import bean.MineLable;
import listenner.Listener;
import main.MainFrame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tools.StaticTool;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

//  Ù–‘◊¢»Î
@RunWith(Parameterized.class)
public class ListenerTest {
    MainFrame mainFrame = new MainFrame();
    Listener listener = mainFrame.getBombJPanel().getListener();
    MineLable[][] mineLables = listener.getMineLable();
    @Parameterized.Parameter(0)
    public int i;
    @Parameterized.Parameter(1)
    public int j;

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new Object[][]{
                {0,2},{0,3},{0,5},{2,8},{4,7},
                {5,8},{6,5},{6,8},{7,0},{7,4}
        });
    }
    @Test
    public void testRightClick_1(){
        listener.rightClick_1(i,j);
        assertEquals(mineLables[i][j].getIcon(), StaticTool.flagIcon);
        assertEquals(mineLables[i][j].getRightClickCount(),1);
        assertTrue(mineLables[i][j].isFlagTag());
    }
    @Test
    public void testRightClick_2(){
        listener.rightClick_2(i,j);
        assertEquals(mineLables[i][j].getIcon(), StaticTool.askIcon);
        assertEquals(mineLables[i][j].getRightClickCount(),2);
        assertFalse(mineLables[i][j].isFlagTag());
    }

}
