
import java.util.*;
public class TicTacToe{
	static ArrayList<Integer> playerpos;//array having player position
	static ArrayList<Integer> cpos;// array having computer position
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		do {
			playerpos = new ArrayList<>();
			cpos = new ArrayList<>();
			playTicTacToe();
			System.out.println("Do you wanna play again? [Y/N]");
			String playAgain = sc.next();
			if(playAgain.equals("N") || playAgain.equals("n") ){
				break;
			}
		}while(true);
	}
		public static void playTicTacToe(){
			//the main game board
			char[][] board = {{' ','|',' ','|',' '},
							  {'-','+','-','+','-'},
							  {' ','|',' ','|',' '},
							  {'-','+','-','+','-'},
							  {' ','|',' ','|',' '},
							  {'-','+','-','+','-'}};
			printboard(board);
			while(true) {
				//player plays..
				Scanner sc = new Scanner(System.in);
				System.out.println("enter the position to place x:[1-9]");
				int ppos = sc.nextInt();
				while(!validpos(ppos)){
					System.out.println("position taken or position invalid..try again");
					ppos = sc.nextInt();
				}
				placepiece(board,ppos,"player");
				//check
				String result = win();
				if(result.length()>0){
						printboard(board);
						System.out.println(result);
						break;
				}
				//cpu plays...
				Random r = new Random();//to generate random cpu moves
				int comppos = r.nextInt(9)+1;
				while(!validpos(comppos)){
					comppos = r.nextInt(9)+1;
				}
				placepiece(board,comppos,"cpu");
				printboard(board);
				//check
				result = win();
				if(result.length()>0){
						System.out.println(result);
						break;
				}
			}
		}
//verify the valid position
	public static boolean validpos(int pos){
		if(playerpos.contains(pos) || cpos.contains(pos) || (pos<1 && pos>9))
			return false;
		return true;
	}
	//printing the board..
	public static void printboard(char[][] board){
		for (char[] row : board) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
public static void placepiece(char[][] board,int pos,String user){
	char piece = ' ';
	if (user.equals("player")){
			piece = 'X';
			playerpos.add(pos);
	}else if(user.equals("cpu")){
		piece = '0';
		cpos.add(pos);
	}
	switch(pos){
		case 1:
			board[0][0] = piece;
			break;
		case 2:
			board[0][2] = piece;
			break;
		case 3:
			board[0][4] = piece;
			break;
		case 4:
			board[2][0] = piece;
			break;
		case 5:
			board[2][2] = piece;
			break;
		case 6:
			board[2][4] = piece;
			break;
		case 7:
			board[4][0] = piece;
			break;
		case 8:
			board[4][2] = piece;
			break;
		case 9:
			board[4][4] = piece;
			break;
		default:
			System.out.print("position invalid...Enter between 1 and 9");
			break;
		}
}
public static String win(){
	//valid conditions for winning
		List<Integer> row1 = Arrays.asList(1, 2, 3);
		List<Integer> row2 = Arrays.asList(4, 5, 6);
		List<Integer> row3 = Arrays.asList(7, 8, 9);
		List<Integer> col1 = Arrays.asList(1, 4, 7);
		List<Integer> col2 = Arrays.asList(2, 5, 8);
		List<Integer> col3 = Arrays.asList(3, 6, 9);
		List<Integer> diag1 = Arrays.asList(1, 5, 9);
		List<Integer> diag2 = Arrays.asList(7, 5, 3);
		
		List<List> wins = new ArrayList<List>();
		wins.add(row1);
		wins.add(row2);
		wins.add(row3);
		wins.add(col1);
		wins.add(col2);
		wins.add(col3);
		wins.add(diag1);
		wins.add(diag2);
		String res = "";
		for (List l : wins) {
			if (playerpos.containsAll(l)) {
				return "Congratulations, you won!";
			} else if (cpos.containsAll(l)) {
				return "computer won!!Better luck next time:( ";
			} else if (playerpos.size() + cpos.size() == 9) {
				res = "It's a tie!!";
			}
		}
		return res;
	}
}