package listenner;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import main.MainFrame;

import tools.LayBomb;
import tools.StaticTool;

import bean.HeroBean;
import bean.MineLable;

public class Listener implements MouseListener {
	MineLable[][] mineLable;
	MainFrame mainFrame;
	private boolean isDoublePress = false;

	public Listener(MineLable[][] mineLable, MainFrame mainFrame) {
		this.mineLable = mineLable;
		this.mainFrame = mainFrame;

	}
	public MineLable[][] getMineLable() {
		return mineLable;
	}
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		MineLable mineLable = (MineLable) e.getSource();

		int row = mineLable.getRowx();
		int col = mineLable.getColy();

		if (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK
				+ InputEvent.BUTTON3_DOWN_MASK) { //������Ҽ�ͬʱ����
			isDoublePress = true;//˫��
			doublePress(row, col);

		} else if (e.getModifiers() == InputEvent.BUTTON1_MASK
				&& mineLable.isFlagTag() == false) {
			if (mineLable.isExpendTag() == false) {
				mineLable.setIcon(StaticTool.icon0); //�㿪û�пո��
			}
			mainFrame.getFaceJPanel().getLabelFace()
					.setIcon(StaticTool.clickIcon); //���ʱ����仯
		} else if (e.getModifiers() == InputEvent.BUTTON3_MASK
				&& mineLable.isExpendTag() == false) {
			if (mineLable.getRightClickCount() == 0) {
				mineLable.setIcon(StaticTool.flagIcon);
				mineLable.setRightClickCount(1);
				mineLable.setFlagTag(true);
				StaticTool.bombCount--;
				mainFrame.getFaceJPanel().setNumber(StaticTool.bombCount);//�Ҽ�ʱ��������һ

			} else if (mineLable.getRightClickCount() == 1) {
				mineLable.setIcon(StaticTool.askIcon);
				mineLable.setRightClickCount(2);
				mineLable.setFlagTag(false);
				StaticTool.bombCount++;
				mainFrame.getFaceJPanel().setNumber(StaticTool.bombCount);//�Ҽ��ڶ��½����ӱ�Ϊ�ʺ�

			} else {
				mineLable.setIcon(StaticTool.iconBlank);//�������趨Ϊ��ʼ״̬
				mineLable.setRightClickCount(0);
			}

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		MineLable mineLable = (MineLable) e.getSource();
		int row = mineLable.getRowx();
		int col = mineLable.getColy();
		if (isDoublePress) {
			isDoublePress = false;
			if (mineLable.isExpendTag() == false
					&& mineLable.isFlagTag() == false) {
				backIcon(row, col);//û���� û��
			} else {

				boolean isEquals = isEquals(row, col);
				if (isEquals) {
					doubleExpend(row, col);//�Ѿ����������� ����Χ��δ����ĸ���
				} else {
					backIcon(row, col);//��û����һ������������

				}

			}
			mainFrame.getFaceJPanel().getLabelFace()
					.setIcon(StaticTool.smileIcon);

		} else if (e.getModifiers() == InputEvent.BUTTON1_MASK
				&& mineLable.isFlagTag() == false) {
			if (StaticTool.isStart == false) {
				LayBomb.lay(this.mineLable, row, col);

				StaticTool.isStart = true;//����û��ʼ
				mainFrame.getTimer().start();

			}


			if (mineLable.isMineTag() == true) {
				bombAction(row, col);
				mineLable.setIcon(StaticTool.bloodIcon); //�趨��ɫ����
				mainFrame.getFaceJPanel().getLabelFace()
						.setIcon(StaticTool.faultFaceIcon);//�㵽��֮�����ʾ����
			} else {
				mainFrame.getFaceJPanel().getLabelFace()
						.setIcon(StaticTool.smileIcon);
				expand(row, col);
			}
		}
		isWin();
	}

	public void bombAction_1(int row,int col){//?????�]??????????????????????????????
		bombAction(row,col);
		mainFrame.getFaceJPanel().getLabelFace().setIcon(StaticTool.faultFaceIcon);
	}

	public void bombAction(int row, int col) {//�������֮��

		for (int i = 0; i < mineLable.length; i++) {
			for (int j = 0; j < mineLable[i].length; j++) {
				if (mineLable[i][j].isMineTag()) {
					if (mineLable[i][j].isFlagTag() == false) {
						mineLable[i][j].setIcon(StaticTool.blackBombIcon);
					}
				} else {
					if (mineLable[i][j].isFlagTag()) {
						mineLable[i][j].setIcon(StaticTool.errorBombIcon);
						//�����ӵ����֮�����errorBombIcon
					}
				}
			}

		}

		mainFrame.getTimer().stop(); //ʱ��ֹͣ

		for (int i = 0; i < mineLable.length; i++) {
			for (int j = 0; j < mineLable[i].length; j++) {
				mineLable[i][j].removeMouseListener(this);
				//�����ٵ��  ���ʧЧ
			}
		}

	}

	private void expand(int x, int y) {

		int count = mineLable[x][y].getCounAround();

		if (mineLable[x][y].isExpendTag() == false
				&& mineLable[x][y].isFlagTag() == false) {

			if (count == 0) {
				mineLable[x][y].setIcon(StaticTool.num[count]);
				mineLable[x][y].setExpendTag(true);
				for (int i = Math.max(0, x - 1); i <= Math.min(
						mineLable.length - 1, x + 1); i++) {
					for (int j = Math.max(0, y - 1); j <= Math.min(
							mineLable[x].length - 1, y + 1); j++) {
						expand(i, j); //����������� ������չ��ΧΪû���׵� ������ʾͼ��

					}

				}

			} else {

				mineLable[x][y].setIcon(StaticTool.num[count]);//������Χ���� ��ô����չ ����������չtagΪtrue
				mineLable[x][y].setExpendTag(true);

			}

		}

	}

	private void backIcon(int i, int j) {
		for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.allrow - 1,
				i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(
					StaticTool.allcol - 1, j + 1); y++) {
				if (mineLable[x][y].isFlagTag() == false
						&& mineLable[x][y].isExpendTag() == false) {
					int rightClickCount = mineLable[x][y].getRightClickCount();
					if (rightClickCount == 2) {
						mineLable[x][y].setIcon(StaticTool.askIcon);
					} else {
						mineLable[x][y].setIcon(StaticTool.iconBlank);
					}//�趨ͼ�� �������ڶ��� ��ô�ʺ� �Ҽ������»ָ�
				}
			}
		}

	}

	private boolean isEquals(int i, int j) {
		int count = mineLable[i][j].getCounAround();
		int flagCount = 0;
		for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.allrow - 1,
				i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(
					StaticTool.allcol - 1, j + 1); y++) {
				if (mineLable[x][y].isFlagTag()) {
					flagCount++;//�ж���Χ�����������ǲ�����ʵ����Χ��
				}
			}
		}
		if (count == flagCount) {
			return true;
		}
		return false;
	}

	private void doublePress(int i, int j) {// ���δ�򿪵ķ��񡣼���Ϊ���ӣ���ô����ʺ�
		for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.allrow - 1,
				i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(
					StaticTool.allcol - 1, j + 1); y++) {
				if (mineLable[x][y].isExpendTag() == false
						&& mineLable[x][y].isFlagTag() == false) {
					int rightClickCount = mineLable[x][y].getRightClickCount();
					if (rightClickCount == 1) { //�Ѿ��Ҽ������һ�� ��ô�ͻ���Χ��ȫ������Ϊ�ʺ�
						mineLable[x][y].setIcon(StaticTool.askPressIcon);
					} else {
						mineLable[x][y].setIcon(StaticTool.icon0);

					}
				}
			}
		}
	}

	private void doubleExpend(int i, int j) {
		for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.allrow - 1,
				i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(
					StaticTool.allcol - 1, j + 1); y++) {
				if (mineLable[x][y].isMineTag()) {
					if (mineLable[x][y].isFlagTag() == false) {
						bombAction(x, y);//���ײ���û���趨����

					}
				} else {

					if (mineLable[x][y].isFlagTag() == false) {
						expand(x, y);//������ û���趨����  �򿪸�����
					}

				}

			}
		}

	}

	private void isWin() {

		int needCount = StaticTool.allrow * StaticTool.allcol
				- StaticTool.allcount;
		int expendCount = 0;
		for (int i = 0; i < mineLable.length; i++) {
			for (int j = 0; j < mineLable[i].length; j++) {
				if (mineLable[i][j].isExpendTag()) {
					expendCount++; //�Ƿ��Ѿ����� ������ʾ���ֵĵط�
				}

			}

		}
		if (needCount == expendCount) {
			for (int i = 0; i < mineLable.length; i++) {
				for (int j = 0; j < mineLable[i].length; j++) {
					if (mineLable[i][j].isMineTag()
							&& mineLable[i][j].isFlagTag() == false) {
						mineLable[i][j].setIcon(StaticTool.flagIcon); //����û���������ӣ���ô�Զ���������
						mineLable[i][j].setFlagTag(true);//���ҽ�flagTag����Ϊtrue
					}

				}

			}

			mainFrame.getFaceJPanel().setNumber(0); //����ʣ������Ϊ0
			mainFrame.getTimer().stop();//ʤ��֮�����ٵ������
			for (int i = 0; i < mineLable.length; i++) {
				for (int j = 0; j < mineLable[i].length; j++) {
					mineLable[i][j].removeMouseListener(this);

				}
			}

			mainFrame.getFaceJPanel().getLabelFace()
					.setIcon(StaticTool.winFaceIcon); //����仯 ʤ��ͼ��
			int level = StaticTool.getLevel();
			if (level != 0) {
				if (level == 1) {
					String name = JOptionPane.showInputDialog(mainFrame,
							"������������ɨ����ɣ������´�����");
					if (name != null) {
						StaticTool.treeSetC.add(new HeroBean(
								StaticTool.timecount, name));
					}
				} else if (level == 2) {
					String name = JOptionPane.showInputDialog(mainFrame,
							"���������м�ɨ����ɣ������´�����");
					if (name != null) {
						StaticTool.treeSetZ.add(new HeroBean(
								StaticTool.timecount, name));
					}
				} else if (level == 3) {
					String name = JOptionPane.showInputDialog(mainFrame,
							"���������߼�ɨ����ɣ������´�����");
					if (name != null) {
						StaticTool.treeSetG.add(new HeroBean(
								StaticTool.timecount, name));
					}
				}

			}

		}

	}

}
