package bean;

import main.MainFrame;
import panel.FaceJPanel;

import static org.junit.Assert.*;

public class HeroBeanTest {
    HeroBean heroTest = new HeroBean(10,"yyh");
    @org.junit.Before
    public void setUp() throws Exception {
        //System.out.println("�����а�ĳɼ�����в���");
    }

    @org.junit.After
    public void tearDown() throws Exception {
        //System.out.println("���Խ���!");
    }

    @org.junit.Test
    public void getTime() {
        assertEquals(10,heroTest.getTime());
    }

    @org.junit.Test
    public void setTime() {
        heroTest.setTime(20);
        assertEquals(20,heroTest.getTime());
    }

    @org.junit.Test
    public void getName() {
        assertEquals("yyh",heroTest.getName());
    }

    @org.junit.Test
    public void setName() {
        heroTest.setName("yyhyyh");
        assertEquals("yyhyyh",heroTest.getName());
    }
}