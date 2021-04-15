package main;

import bean.MineLable;
import listenner.Listener;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tools.LayBomb;
import tools.StaticTool;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

// ����ע��
@RunWith(Parameterized.class)
public class MainFrameTest {
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
    @Before
    public void init(){
        LayBomb.lay_win(mineLables,9,9);
        listener.isWin_init();
    }
    @Test
    public void test_win(){//������ע���ж�ͼ���Ƿ��б仯
        assertEquals(mineLables[i][j].getIcon(),StaticTool.flagIcon);//�ж��Ƿ�����������
        assertTrue(mineLables[i][j].isFlagTag());//�ж�flagTag���1
    }

    @Test
    public void test_win_icon(){
        assertEquals(mainFrame.getFaceJPanel().getLabelFace().getIcon(),StaticTool.winFaceIcon);//ʤ��֮��ͼ��ת��
        assertFalse(mainFrame.getTimer().isRunning());//ʤ��֮�����ٵ������
    }
}
