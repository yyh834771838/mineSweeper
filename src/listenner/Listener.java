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
				+ InputEvent.BUTTON3_DOWN_MASK) { //左键和右键同时按下
			isDoublePress = true;//双击
			doublePress(row, col);

		} else if (e.getModifiers() == InputEvent.BUTTON1_MASK
				&& mineLable.isFlagTag() == false) {
			if (mineLable.isExpendTag() == false) {
				mineLable.setIcon(StaticTool.icon0); //点开没有空格的

			}
			mainFrame.getFaceJPanel().getLabelFace()
					.setIcon(StaticTool.clickIcon); //点击时表情变化
		} else if (e.getModifiers() == InputEvent.BUTTON3_MASK
				&& mineLable.isExpendTag() == false) {
			if (mineLable.getRightClickCount() == 0) {
				mineLable.setIcon(StaticTool.flagIcon);
				mineLable.setRightClickCount(1);
				mineLable.setFlagTag(true);
				StaticTool.bombCount--;
				mainFrame.getFaceJPanel().setNumber(StaticTool.bombCount);//右键时将雷数减一

			} else if (mineLable.getRightClickCount() == 1) {
				mineLable.setIcon(StaticTool.askIcon);
				mineLable.setRightClickCount(2);
				mineLable.setFlagTag(false);
				StaticTool.bombCount++;
				mainFrame.getFaceJPanel().setNumber(StaticTool.bombCount);//右键第二下将旗子变为问号

			} else {
				mineLable.setIcon(StaticTool.iconBlank);//第三下设定为初始状态
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
				backIcon(row, col);//没插旗 没打开
			} else {

				boolean isEquals = isEquals(row, col);
				if (isEquals) {
					doubleExpend(row, col);//已经插满了旗子 打开周围的未插旗的格子
				} else {
					backIcon(row, col);//还没插满一定数量的旗子

				}

			}
			mainFrame.getFaceJPanel().getLabelFace()
					.setIcon(StaticTool.smileIcon);

		} else if (e.getModifiers() == InputEvent.BUTTON1_MASK
				&& mineLable.isFlagTag() == false) {
			if (StaticTool.isStart == false) {
				LayBomb.lay(this.mineLable, row, col);

				StaticTool.isStart = true;//假如没开始
				mainFrame.getTimer().start();

			}


			if (mineLable.isMineTag() == true) {
				bombAction(row, col);
				mineLable.setIcon(StaticTool.bloodIcon); //设定红色的雷
				mainFrame.getFaceJPanel().getLabelFace()
						.setIcon(StaticTool.faultFaceIcon);//点到雷之后会显示哭脸
			} else {
				mainFrame.getFaceJPanel().getLabelFace()
						.setIcon(StaticTool.smileIcon);
				expand(row, col);
			}
		}
		isWin();
	}

	private void bombAction(int row, int col) {//点击到雷之后

		for (int i = 0; i < mineLable.length; i++) {
			for (int j = 0; j < mineLable[i].length; j++) {
				if (mineLable[i][j].isMineTag()) {
					if (mineLable[i][j].isFlagTag() == false) {
						mineLable[i][j].setIcon(StaticTool.blackBombIcon);
					}
				} else {
					if (mineLable[i][j].isFlagTag()) {
						mineLable[i][j].setIcon(StaticTool.errorBombIcon);
						//插旗子点击了之后会变成errorBombIcon
					}
				}
			}

		}

		mainFrame.getTimer().stop(); //时间停止

		for (int i = 0; i < mineLable.length; i++) {
			for (int j = 0; j < mineLable[i].length; j++) {
				mineLable[i][j].removeMouseListener(this);
				//不能再点击  点击失效
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
						expand(i, j); //深度优先搜索 不断扩展周围为没有雷的 并且显示图标

					}

				}

			} else {

				mineLable[x][y].setIcon(StaticTool.num[count]);//假如周围有雷 那么不扩展 并且设置扩展tag为true
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
					}//设定图标 假如点击第二下 那么问号 右键第三下恢复
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
					flagCount++;//判断周围的旗子数量是不是真实的周围数
				}
			}
		}
		if (count == flagCount) {
			return true;
		}
		return false;
	}

	private void doublePress(int i, int j) {// 点击未打开的方格。假如为旗子，那么变成问号
		for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.allrow - 1,
				i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(
					StaticTool.allcol - 1, j + 1); y++) {
				if (mineLable[x][y].isExpendTag() == false
						&& mineLable[x][y].isFlagTag() == false) {
					int rightClickCount = mineLable[x][y].getRightClickCount();
					if (rightClickCount == 1) { //已经右键点击过一次 那么就会周围的全部设置为问号
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
						bombAction(x, y);//是雷并且没有设定旗子

					}
				} else {

					if (mineLable[x][y].isFlagTag() == false) {
						expand(x, y);//不是雷 没有设定旗子  打开该雷区
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
					expendCount++; //是否已经被打开 就是显示数字的地方
				}

			}

		}
		if (needCount == expendCount) {
			for (int i = 0; i < mineLable.length; i++) {
				for (int j = 0; j < mineLable[i].length; j++) {
					if (mineLable[i][j].isMineTag()
							&& mineLable[i][j].isFlagTag() == false) {
						mineLable[i][j].setIcon(StaticTool.flagIcon); //假如没有设置旗子，那么自动设置旗子
						mineLable[i][j].setFlagTag(true);//并且将flagTag设置为true
					}

				}

			}

			mainFrame.getFaceJPanel().setNumber(0); //设置剩余雷数为0
			mainFrame.getTimer().stop();//胜利之后不能再点击雷盘
			for (int i = 0; i < mineLable.length; i++) {
				for (int j = 0; j < mineLable[i].length; j++) {
					mineLable[i][j].removeMouseListener(this);

				}
			}

			mainFrame.getFaceJPanel().getLabelFace()
					.setIcon(StaticTool.winFaceIcon); //表情变化 胜利图标
			int level = StaticTool.getLevel();
			if (level != 0) {
				if (level == 1) {
					String name = JOptionPane.showInputDialog(mainFrame,
							"好厉害！初级扫雷完成，请留下大名！");
					if (name != null) {
						StaticTool.treeSetC.add(new HeroBean(
								StaticTool.timecount, name));
					}
				} else if (level == 2) {
					String name = JOptionPane.showInputDialog(mainFrame,
							"好厉害！中级扫雷完成，请留下大名！");
					if (name != null) {
						StaticTool.treeSetZ.add(new HeroBean(
								StaticTool.timecount, name));
					}
				} else if (level == 3) {
					String name = JOptionPane.showInputDialog(mainFrame,
							"好厉害！高级扫雷完成，请留下大名！");
					if (name != null) {
						StaticTool.treeSetG.add(new HeroBean(
								StaticTool.timecount, name));
					}
				}

			}

		}

	}

}
