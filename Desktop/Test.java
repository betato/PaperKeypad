public class Test{
	public static void main(String[] args){
		UserInput attempt = new UserInput();
		attempt.arrayMod();
		int height = attempt.getHeight(), width = attempt.getWidth();
		
		String[][] current = new String[height][width];
		
		for(int i = 0; i  < height; i++){
			for(int j = 0; j < width;j++){
				System.out.println(current[i][j]);
			}
		}
		
	}

}
