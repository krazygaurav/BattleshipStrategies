
public class HorizontalSweep implements Strategy{
	@Override
	public void search(int[][] grid) {
		int deadCounter = 0;
		boolean carrierFound = false, submarineFound = false;
		int cstartX = 0, cstartY = 0, cendX = 0, cendY = 0;
		int sstartX = 0, sstartY = 0, sendX = 0, sendY = 0;
		System.out.println("Strategy: Horizontal Sweep");
		
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[i].length;j++){
				if(grid[i][j] == 1 && !carrierFound){
					carrierFound = true;
					//For Horizontal
					if(grid[i][j+4] == 1){
						cstartX = i; cstartY = j;
						cendX = i; cendY = j+4;
						j = j+4;
					}
					//For Vertical
					else if(grid[i+4][j] == 1){
						cstartX = i; cstartY = j;
						cendX = i+4; cendY = j;
					}
				}else if(grid[i][j] == 2 && !submarineFound){
					submarineFound = true;
					//For Horizontal
					if(grid[i][j+2] == 2){
						sstartX = i; sstartY = j;
						sendX = i; sendY = j+2;
						j = j+2;
					}
					//For Vertical
					else if(grid[i+2][j] == 2){
						sstartX = i; sstartY = j;
						sendX = i+2; sendY = j;
					}
				}else{
					if(carrierFound && submarineFound)
						break;
					deadCounter++;
				}
			}
		}
		System.out.println("Number of Cells searched: "+deadCounter);
		System.out.println("Carrier found: ("+cstartX+","+cstartY+") to ("+cendX+","+cendY+")"
				+ " Submarine found: ("+sstartX+","+sstartY+") to ("+sendX+","+sendY+")");
	}
}
