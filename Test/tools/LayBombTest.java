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

    //测试打开空白区域
    @Test
    public void expand(){
        MainFrame mainFrame = new MainFrame();
        Listener listener = mainFrame.getBombJPanel().getListener();
        MineLable[][] mineLables = listener.getMineLable();
        LayBomb.lay_mount(mineLables,9,9);
        listener.expand(1,0);//这里是测试打开 1行0列的一个空格 看看能否自动打开它下面和他右斜方的空格
        assertTrue(mineLables[2][0].isExpendTag());
        assertTrue(mineLables[2][1].isExpendTag());

    }
}
