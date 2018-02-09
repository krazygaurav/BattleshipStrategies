import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BattleshipSearch {
	int grid[][];

	public BattleshipSearch() {
		grid = new int[25][25];
	}

	public void resetGrid() {
		for (int row = 0; row < grid[0].length; row++) {
			for (int column = 0; column < grid[0].length; column++) {
				grid[row][column] = -1;
			}
		}
	}

	public List<String> readInput(String fileName) {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	/*
	 * Initialize Grid with Carrier and Submarine 1 -> Carrier 2 -> Submarine
	 */
	public void prepareGrid(String str) {
		resetGrid();
		str = str.substring(1, str.length() - 1);
		String[] parts = str.split("\\)\\(");
		for (int i = 0; i < parts.length; i++) {
			String coordinates[] = parts[i].split(",");
			int x = Integer.parseInt(coordinates[0]);
			int y = Integer.parseInt(coordinates[1]);
			if (i < 5) {
				grid[x][y] = 1;
			} else {
				grid[x][y] = 2;
			}
		}
	}

	// Showing the Grid to the User
	public void showGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == -1)
					System.out.print("~  ");
				else if (grid[i][j] == 1)
					System.out.print("C  ");
				else if (grid[i][j] == 2)
					System.out.print("S  ");
			}
			System.out.println();
		}
	}

	public void playGame() {
		Strategy strategy = new HorizontalSweep();
		strategy.search(grid);
		strategy = new RandomSweep();
		strategy.search(grid);
		strategy = new StrategicSweep();
		strategy.search(grid);
	}

	public static void main(String[] args) {
		BattleshipSearch battleship = new BattleshipSearch();
		int game_counter = 1;
		List<String> game_lines = battleship.readInput("input.txt");
		for (String str : game_lines) {
			battleship.prepareGrid(str);
//			battleship.showGrid();
			System.out.println("Game "+(game_counter++)+":");
			battleship.playGame();
		}
	}
}