import java.util.Scanner;

public class UserInput{
		
	private String[][] dimensions;	
	private Scanner keyB = new Scanner(System.in);
	private int height = 0, width = 0;
	
	public void makeDimensions(int height, int width){
		this.dimensions = new String[height][width];
	}

	public String[][] arrayMod(){
		System.out.println("Please input the height and dimensions respectively (No letters, or decimals)");
		while(true){
			boolean isGood = true;


			this.height = keyB.nextInt();
			this.width = keyB.nextInt();

			// checks if its an int

			if(isGood){

				System.out.println(this.height + " " + this.width);

				for(int i = 0; i  < height; i++){
					for(int j = 0; j < width;j++){
						this.dimensions[i][j] = Integer.toString(1);
					}
				}

				break;
			}
			else{
				System.out.println("Please try again");
				
			}
			
		}
		makeDimensions(height, width);
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
