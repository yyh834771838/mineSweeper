package tools;

import java.util.Random;

import bean.MineLable;

public class LayBomb {
	public static int returnCount = 0;
	public MineLable[][] labels = new MineLable[StaticTool.allrow][StaticTool.allcol];
	public void init(){
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j] = new MineLable(i, j);
				labels[i][j].setIcon(StaticTool.iconBlank);
			}
		};
	}
	public static void lay(MineLable[][] lable, int row, int col) {
		int count = 0;
		Random random = new Random();
		while (count < StaticTool.allcount) {
			int x = random.nextInt(StaticTool.allrow);
			int y = random.nextInt(StaticTool.allcol);
			if (lable[x][y].isMineTag() == false && !(x == row && y == col)) {
				lable[x][y].setMineTag(true);
				lable[x][y].setCounAround(9);
				if (StaticTool.isHole == true) {
					lable[x][y].setIcon(StaticTool.holeIcon);
				}
				count++;
			}
		}
		returnCount = count;
		computeBomb(lable);
	}

	public static void computeBomb(MineLable lable[][]) {

		for (int i = 0; i < lable.length; i++) {
			for (int j = 0; j < lable[i].length; j++) {
				if (lable[i][j].isMineTag() == false) {
					int count = 0;
					for (int x = Math.max(0, i - 1); x <= Math.min(
							StaticTool.allrow - 1, i + 1); x++) {
						for (int y = Math.max(0, j - 1); y <= Math.min(
								StaticTool.allcol - 1, j + 1); y++) {
							if (lable[x][y].isMineTag() == true) {
								count++;
							}
						}
					}
					lable[i][j].setCounAround(count);
				}

			}

		}

	}
}
