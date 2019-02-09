import java.util.Scanner;

public class UserInput{
		
	private String[][] dimensions;	
	private Scanner keyB = new Scanner(System.in);
	private int height = 0, width = 0;
	
	public void makeDimensions(int height, int width){
		this.dimensions = new String[height][width];
	}

	public String[][] arrayMod(){
		boolean flag = true;
		String
		System.out.println("Please input the height and dimensions respectively (No letters, or decimals)");
		while(flag == true){
			boolean isGood = true;


			this.height = keyB.nextInt();
			this.width = keyB.nextInt();

			makeDimensions(height, width);

			// checks if its an int
			String[][] current = new String[this.height][this.width];
			if(isGood){

				for(int i = 0; i  < height; i++){
					for(int j = 0; j < width;j++){
						current[i][j] = Integer.toString(j);
					}
				}
				System.out.println();
				this.dimensions = current;
				break;
			}
			else{
				System.out.println("Please try again");
				
			}
			flag = false;
		}
		return this.dimensions;
	}
	public String[][] getArray(){
		return this.dimensions;
	}
	public String getElement(int one, int two){
		return this.dimensions[one][two];
	}
	public int getHeight(){
		return this.height;
	}
	public int getWidth(){
		return this.width;
	}


}
