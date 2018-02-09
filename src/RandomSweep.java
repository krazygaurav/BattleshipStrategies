//0000000
import java.util.concurrent.ThreadLocalRandom;
public class RandomSweep implements Strategy
{
	@Override
	public void search(int[][] grid) throws ArrayIndexOutOfBoundsException
	{
		int deadCounter = 0;
		boolean carrierFound = false, submarineFound = false;
		int cstartX = 0, cstartY = 0, cendX = 0, cendY = 0;
		int sstartX = 0, sstartY = 0, sendX = 0, sendY = 0;
		System.out.println("Strategy: Random Sweep");
		int min = 0;
		int max = 24;
		int randomX = 0;
		int randomY = 0;
		int subSym = 2;
		int carSym = 1;
		while(!submarineFound) 
		{
			randomX = ThreadLocalRandom.current().nextInt(min, max + 1);
			randomY = ThreadLocalRandom.current().nextInt(min, max + 1);
			if(grid[randomX][randomY] == 2)
			{	
				try 
				{
					if(grid[randomX][randomY-1] != subSym && grid[randomX][randomY+2] == subSym)
					{
						sstartX = randomX;
						sstartY = randomY;
						sendX = randomX;
						sendY = randomY+2;
						submarineFound = true;
						break;
					}
					if(grid[randomX][randomY-2] == subSym)
					{
						submarineFound = true;
						sstartX = randomX;
						sstartY = randomY-2;
						sendX = randomX;
						sendY = randomY;
						break;
					}
					if(grid[randomX][randomY-1] == subSym && grid[randomX][randomX+1] ==subSym)
					{
						submarineFound = true;
						sstartX = randomX;
						sstartY = randomY-1;
						sendX = randomX;
						sendY = randomY+1;
						break;
					}
					if(grid[randomX-1][randomY] != subSym && grid[randomX+2][randomY] == subSym)
					{
						sstartX = randomX;
						sstartY = randomY;
						sendX = randomX+2;
						sendY = randomY;
						submarineFound = true;
						break;

					}
					if(grid[randomX-2][randomY] == subSym)
					{
						submarineFound = true;
						sstartX = randomX-2;
						sstartY = randomY;
						sendX = randomX;
						sendY = randomY;
						break;
					}
					if(grid[randomX-1][randomY] == subSym && grid[randomX+1][randomX] == subSym)
					{
						submarineFound = true;
						sstartX = randomX-1;
						sstartY = randomY;
						sendX = randomX+1;
						sendY = randomY;
						break;
					}

				}
				catch (Exception ex) 
				{
					// catch the exception
				}
			}
			deadCounter++;
			
		}

		while(!carrierFound) 
		{
			randomX = ThreadLocalRandom.current().nextInt(min, max + 1);
			randomY = ThreadLocalRandom.current().nextInt(min, max + 1);
			
			// System.out.println(randomX + ": " + randomY);
			if(grid[randomX][randomY] == carSym)
			{
			//	System.out.println(randomX + ": " + randomY);
				carrierFound = true;
				for(int i = 0; i < max; i++)
				{
					if(grid[randomX][i] == carSym && grid[randomX][i+4] == carSym)
					{
						cstartX = randomX;
						cstartY = i;
						cendX = randomX;
						cendY = i+4;
						carrierFound = true;
						break;
					}
					if(grid[i][randomY] == carSym && grid[i+4][randomY] == carSym)
					{
						cstartX = i;
						cstartY = randomY;
						cendX = i+4;
						cendY = randomY;
						carrierFound = true;
					}
				}
			}
			deadCounter++;
			
		}
			
		
		System.out.println("Number of Cells searched: "+deadCounter);
		System.out.println("Carrier found: ("+cstartX+","+cstartY+") to ("+cendX+","+cendY+")"
				+ " Submarine found: ("+sstartX+","+sstartY+") to ("+sendX+","+sendY+")");
	}
	
}
