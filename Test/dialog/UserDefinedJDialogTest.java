package dialog;

import listenner.Listener;
import listenner.UserDefinedListener;
import main.MainFrame;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

public class UserDefinedJDialogTest {
    MainFrame mainframe = new MainFrame();
    UserDefinedJDialog UDJD = new UserDefinedJDialog(mainframe);
    @Test
    public void getPanel() {
        assertNotNull(UDJD.getPanel());
    }

    @Test
    public void getjLabelMessage() {
        assertEquals("���������֣����ܳ�����λ",UDJD.getjLabelMessage().getText());
    }

    @Test
    public void getjTextFieldHigh() {
        UDJD.getjTextFieldHigh().setText("10");
        assertEquals("10",UDJD.getjTextFieldHigh().getText());
    }

    @Test
    public void getjTextFieldWide() {
        UDJD.getjTextFieldWide().setText("20");
        assertEquals("20",UDJD.getjTextFieldWide().getText());
    }

    @Test
    public void getjTextFieldBomb() {
        UDJD.getjTextFieldBomb().setText("30");
        assertEquals("30",UDJD.getjTextFieldBomb().getText());
    }

    @Test
    public void getButtonSure() {
        UserDefinedListener definedListener = new UserDefinedListener(UDJD, mainframe);
        UDJD.getButtonSure().addActionListener(definedListener);
        assertEquals("���������֣����ܳ�����λ",UDJD.getjLabelMessage().getText());
    }

    @Test
    public void getButtonCancer() {
        UserDefinedListener definedListener = new UserDefinedListener(UDJD, mainframe);
        UDJD.getButtonSure().addActionListener(definedListener);
        assertEquals("�����������ΧӦ��9-30֮��",UDJD.getjLabelMessage().getText());
    }
}
