import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import com.srujith.beyond2048.GameBoard;


/**
 * @author ~Srujith~Kudikala~
 *
 */
public class main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		GameBoard gameBoard = new GameBoard();
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		gameBoard.printBoard();
		while (true) {
			
			System.out.println("A-left | D-right | W-up | S-down");
			String inputString=inputReader.readLine();
			if(inputString.isEmpty())continue;
			switch (inputString.charAt(0)) {
			case 'a':
				if(!gameBoard.swipeLeft()) continue;
				break;
			case 's':
				if(!gameBoard.swipeDown()) continue;
				break;
			case 'w':
				if(!gameBoard.swipeUp()) continue;
				break;
			case 'd':
				if(!gameBoard.swipeRight()) continue;
				break;
			default:
				break;
			}
			gameBoard.printBoard();
		}
	}

}
