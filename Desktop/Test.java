public class Test{
	public static void main(String[] args){
		UserInput attempt = new UserInput();
		attempt.arrayMod();
		int height = attempt.getHeight(), width = attempt.getWidth();
		
		for(int i = 0; i  < height; i++){
			for(int j = 0; j < width;j++){
				System.out.print(attempt.getElement(i, j) + " ");
			}
		System.out.println();
		}
		
	}

}
