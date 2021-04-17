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

    //���Դ򿪿հ�����
    @Test
    public void expand(){
        MainFrame mainFrame = new MainFrame();
        Listener listener = mainFrame.getBombJPanel().getListener();
        MineLable[][] mineLables = listener.getMineLable();
        LayBomb.lay_mount(mineLables,9,9);
        listener.expand(1,0);//�����ǲ��Դ� 1��0�е�һ���ո� �����ܷ��Զ��������������б���Ŀո�
        assertTrue(mineLables[2][0].isExpendTag());
        assertTrue(mineLables[2][1].isExpendTag());

    }
}
