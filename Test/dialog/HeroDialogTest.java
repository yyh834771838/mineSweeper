package dialog;

import main.MainFrame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import sun.applet.Main;

import static org.junit.Assert.*;

public class HeroDialogTest {
    @Before
    public void before(){
        System.out.println("测试排行榜模块");
    }
    @Test
    public void getPanel() {
        assertNotNull(new HeroDialog(1,new MainFrame()).getPanel());
    }
    @After
    public void after(){
        System.out.println("测试完成");
    }
}
