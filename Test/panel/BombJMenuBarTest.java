package panel;

import bean.MineLable;
import listenner.Listener;
import main.MainFrame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tools.StaticTool;

import static org.junit.Assert.*;

public class BombJMenuBarTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setMenuItemHole() {
        MainFrame mainFrame = new MainFrame();
        Listener listener = mainFrame.getBombJPanel().getListener();
        MineLable[][] mineLables = listener.getMineLable();
        StaticTool.isHole = true;
        for (int i = 0; i < StaticTool.allrow; i++) {
            for (int j = 0; j < StaticTool.allcol; j++) {
                if (mineLables[i][j].isMineTag()) {
                    assertEquals(StaticTool.holeIcon, listener.getMineLable()[i][j].getIcon());
                }
            }
        }
    }
}