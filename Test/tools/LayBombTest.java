package tools;

import bean.MineLable;
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
}
