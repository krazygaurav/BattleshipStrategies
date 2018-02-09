//505050505050
public class StrategicSweep implements Strategy {
	@Override
	public void search(int[][] grid) {
		int deadCounter = 0;
		boolean carrierFound = false, submarineFound = false;
		int cstartX = 0, cstartY = 0, cendX = 0, cendY = 0;
		int sstartX = 0, sstartY = 0, sendX = 0, sendY = 0;
		int randomX = 0, randomY = 0, max = 24;
		int subSym = 2;
		int carSym = 1;
		System.out.println("Strategy: Strategic Sweep");
		for (int i = 1; i < grid.length; i += 2) {

			for (int j = 1; j < grid[i].length; j += 3) {
				if ((grid[i - 1][j - 1] == 1 || grid[i + 1][j + 1] == 1 || grid[i - 1][j + 1] == 1
						|| grid[i + 1][j - 1] == 1 || grid[i][j] == 1) && !carrierFound) {

					carrierFound = true;
					if (grid[i - 1][j - 1] == 1) {
						randomX = i - 1;
						randomY = j - 1;
					} else if (grid[i + 1][j + 1] == 1) {
						randomX = i + 1;
						randomY = j + 1;
					} else if (grid[i - 1][j + 1] == 1) {
						randomX = i - 1;
						randomY = j + 1;
					} else if (grid[i + 1][j - 1] == 1) {
						randomX = i + 1;
						randomY = j - 1;
					} else if (grid[i][j] == 1) {
						randomX = i;
						randomY = j;
					}

					// System.out.println(randomX + ": " + randomY);
					if (grid[randomX][randomY] == carSym) {
						// System.out.println(randomX + ": " + randomY);
						carrierFound = true;
						for (int p = 0; p < max; p++) {
							if (grid[randomX][p] == carSym && grid[randomX][p + 4] == carSym) {
								cstartX = randomX;
								cstartY = p;
								cendX = randomX;
								cendY = p + 4;
								carrierFound = true;
								break;
							}
							if (grid[p][randomY] == carSym && grid[p + 4][randomY] == carSym) {
								cstartX = p;
								cstartY = randomY;
								cendX = p + 4;
								cendY = randomY;
								carrierFound = true;
							}
						}
					}
					// search in
					// horizontal
					// direction

				} else if ((grid[i - 1][j - 1] == subSym || grid[i + 1][j + 1] == subSym || grid[i - 1][j + 1] == subSym
						|| grid[i + 1][j - 1] == subSym || grid[i][j] == subSym) && !submarineFound) {
					if (grid[i - 1][j - 1] == subSym) {
						randomX = i - 1;
						randomY = j - 1;
					} else if (grid[i + 1][j + 1] == subSym) {
						randomX = i + 1;
						randomY = j + 1;
					} else if (grid[i - 1][j + 1] == subSym) {
						randomX = i - 1;
						randomY = j + 1;
					} else if (grid[i + 1][j - 1] == subSym) {
						randomX = i + 1;
						randomY = j - 1;
					} else {
						randomX = i;
						randomY = j;
					}
					submarineFound = true;
					try {
						if (grid[randomX][randomY - 1] != subSym && grid[randomX][randomY + 2] == subSym) {
							sstartX = randomX;
							sstartY = randomY;
							sendX = randomX;
							sendY = randomY + 2;
							submarineFound = true;
							break;
						}
						if (grid[randomX][randomY - 2] == subSym) {
							submarineFound = true;
							sstartX = randomX;
							sstartY = randomY - 2;
							sendX = randomX;
							sendY = randomY;
							break;
						}
						if (grid[randomX][randomY - 1] == subSym && grid[randomX][randomX + 1] == subSym) {
							submarineFound = true;
							sstartX = randomX;
							sstartY = randomY - 1;
							sendX = randomX;
							sendY = randomY + 1;

							break;
						}
						if (grid[randomX - 1][randomY] != subSym && grid[randomX + 2][randomY] == subSym) {
							sstartX = randomX;
							sstartY = randomY;
							sendX = randomX + 2;
							sendY = randomY;
							submarineFound = true;
							break;

						}
						if (grid[randomX - 2][randomY] == subSym) {
							submarineFound = true;
							sstartX = randomX - 2;
							sstartY = randomY;
							sendX = randomX;
							sendY = randomY;

							break;
						}
						if (grid[randomX - 1][randomY] == subSym && grid[randomX + 1][randomX] == subSym) {
							submarineFound = true;
							sstartX = randomX - 1;
							sstartY = randomY;
							sendX = randomX + 1;
							sendY = randomY;
							break;
						}
						// deadCounter++;

					} catch (Exception ex) {
						// catch the exception
					}
					// search
					// in
					// horizontal
					// direction
					// deadCounter++;

					if (carrierFound && submarineFound) {
						break;
					}

				}
				deadCounter++;
			}
		}
		System.out.println("Number of Cells searched: " + deadCounter);
		System.out.println("Carrier found: (" + cstartX + "," + cstartY + ") to (" + cendX + "," + cendY + ")"
				+ " Submarine found: (" + sstartX + "," + sstartY + ") to (" + sendX + "," + sendY + ")");

	}
}