package panel;

import bean.MineLable;
import listenner.Listener;
import main.MainFrame;
import org.junit.Before;
import org.junit.Test;
import tools.LayBomb;
import tools.StaticTool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BombJPanelTest {
    MainFrame mainFrame = new MainFrame();
    Listener listener = mainFrame.getBombJPanel().getListener();
    MineLable[][] mineLables = listener.getMineLable();
    public int moban[][] = {
            {0,1,9,9,2,9,1,0,0},
            {0,1,2,2,2,1,1,1,1},
            {0,0,0,0,0,0,0,1,9},
            {0,0,0,0,0,0,1,2,2},
            {0,0,0,0,0,0,1,9,2},
            {0,0,0,0,1,1,2,3,9},
            {1,1,0,1,2,9,1,2,9},
            {9,1,0,1,9,2,1,1,1},
            {1,1,0,1,1,1,0,0,0}
    };
    @Before
    public void init(){
        LayBomb.lay_win(mineLables,9,9);
        listener.isWin_init();
    }
    @Test
    public void test1(){//点击雷之后地图上显示红色的雷
        LayBomb.lay_11(mineLables,9,9);
        listener.bombAction(9,9);
        assertEquals(1,1);
        assertEquals(listener.getMineLable()[1][1].getIcon(), StaticTool.blackBombIcon);
    }
    @Test
    public void isNum_right(){//点开后是否正确显示周围雷数
        for(int i =0;i<9;i++){
            for(int j=0;j<9;j++){
                assertEquals(mineLables[i][j].getCounAround(),moban[i][j]);
            }
        }

    }

}
