/**
 * 
 */
package com.srujith.beyond2048;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;


/**
 * @author ~Srujith~Kudikala~
 *
 */
public class GameBoard {
	
	/**
	 * 
	 */
	public GameBoard() {
		super();
		Arrays.stream(board).forEach(
				row->{
						for(int i=0; i<row.length; i++) {
							row[i]=0;
						}
					}
				);
		random=new Random(new Date().getTime());
		placeRandom();
	}
	public boolean swipeRight() {
		boolean moveComplete=false;
		for (int y=0; y<GAMESIZE; y++) {
			int toIndex=-1;
			for (int x=GAMESIZE-1; x>=0; x--) {
				if(toIndex==-1&&board[x][y]==0) {
					toIndex=x;
				}else if(toIndex!=-1&&board[x][y]!=0) {
					//check for merge condition
					if(toIndex<GAMESIZE-1&&board[toIndex+1][y]==board[x][y]) {
						//merge
						board[toIndex+1][y]*=2;
						board[x][y]=0;
						moveComplete=true;
					}else {
						//just  move
						board[toIndex][y]=board[x][y];
						board[x][y]=0;
						toIndex--;
						moveComplete=true;
					}
				}else if(x<GAMESIZE-1&&board[x][y]!=0&&board[x+1][y]==board[x][y]) {
					//merge
					board[x+1][y]*=2;
					board[x][y]=0;
					toIndex=x;
					moveComplete=true;
				}
			}
		}
		if(moveComplete)placeRandom();
		return moveComplete;
	}
	
	public boolean swipeLeft() {
		boolean moveComplete=false;
		for (int y=0; y<GAMESIZE; y++) {
			int toIndex=-1;
			for (int x=0; x<GAMESIZE; x++) {
				if(toIndex==-1&&board[x][y]==0) {
					toIndex=x;
				}else if(toIndex!=-1&&board[x][y]!=0) {
					//check for merge condition
					if(toIndex>0&&board[toIndex-1][y]==board[x][y]) {
						//merge
						board[toIndex-1][y]*=2;
						board[x][y]=0;
						moveComplete=true;
					}else {
						//just  move
						board[toIndex][y]=board[x][y];
						board[x][y]=0;
						toIndex++;
						moveComplete=true;
					}
				}else if(x>0&&board[x][y]!=0&&board[x-1][y]==board[x][y]) {
					//merge
					board[x-1][y]*=2;
					board[x][y]=0;
					toIndex=x;
					moveComplete=true;
				}
			}
		}
		if(moveComplete)placeRandom();
		return moveComplete;
	}
	public boolean swipeUp() {
		boolean moveComplete=false;
		for (int x=0; x<GAMESIZE; x++) {
			int toIndex=-1;
			for (int y=0; y<GAMESIZE; y++) {
				if(toIndex==-1&&board[x][y]==0) {
					toIndex=y;
				}else if(toIndex!=-1&&board[x][y]!=0) {
					//check for merge condition
					if(toIndex>0&&board[x][toIndex-1]==board[x][y]) {
						//merge
						board[x][toIndex-1]*=2;
						board[x][y]=0;
						moveComplete=true;
					}else {
						//just  move
						board[x][toIndex]=board[x][y];
						board[x][y]=0;
						toIndex++;
						moveComplete=true;
					}
				}else if(y>0&&board[x][y]!=0&&board[x][y-1]==board[x][y]) {
					//merge
					board[x][y-1]*=2;
					board[x][y]=0;
					toIndex=y;
					moveComplete=true;
				}
			}
		}
		if(moveComplete)placeRandom();
		return moveComplete;
	}
	public boolean swipeDown() {
		boolean moveComplete=false;
		for (int x=0; x<GAMESIZE; x++) {
			int toIndex=-1;
			for (int y=GAMESIZE-1; y>=0; y--) {
				if(toIndex==-1&&board[x][y]==0) {
					toIndex=y;
				}else if(toIndex!=-1&&board[x][y]!=0) {
					//check for merge condition
					if(toIndex<GAMESIZE-1&&board[x][toIndex+1]==board[x][y]) {
						//merge
						board[x][toIndex+1]*=2;
						board[x][y]=0;
						moveComplete=true;
					}else {
						//just  move
						board[x][toIndex]=board[x][y];
						board[x][y]=0;
						toIndex--;
						moveComplete=true;
					}
				}else if(y<GAMESIZE-1&&board[x][y]!=0&&board[x][y+1]==board[x][y]) {
					//merge
					board[x][y+1]*=2;
					board[x][y]=0;
					toIndex=y;
					moveComplete=true;
				}
			}
		}
		if(moveComplete)placeRandom();
		return moveComplete;
	}
	private void placeRandom() {
		int exponent=0;
		do {
			exponent=(int)random.nextGaussian();
		}while(exponent<0);
		
		int randomCount=random.nextInt(GAMESIZE*GAMESIZE);
		for (int y=0; y<GAMESIZE; y++) {
			for (int x=0; x<GAMESIZE; x++) {
				if(board[x][y]==0) {
					if(randomCount==0) {
						board[x][y]=1<<(exponent+1);
						return;
					}else {
						randomCount--;
					}
				}
			}
			if(y==GAMESIZE-1) {
				y=-1;
			}
		}
		
	}
	Random random;
	static final int GAMESIZE=4;
	int board[][]= new int[GAMESIZE][GAMESIZE];//[x][y]
	public void printBoard() {
		//search for highest number of digits
		int maxNumDigits=0;
		for (int x=0; x<GAMESIZE; x++) {
			for (int y=0; y<GAMESIZE; y++) {
				while(board[x][y]/((int)Math.pow(10, maxNumDigits))>9) {
					maxNumDigits++;
				}
			}
		}
		//print row by row
		for (int y=0; y<GAMESIZE; y++) {
			if(y==0) {
				System.out.print(" |");
				for (int x=0; x<GAMESIZE; x++) {
					System.out.print(String.format("%-" + Integer.toString(maxNumDigits+1) + "s|", x));
				}
				System.out.println("X");
			}
			System.out.print(Integer.toString(y)+"|");
			for (int x=0; x<GAMESIZE; x++) {
				System.out.print(String.format("%-" + Integer.toString(maxNumDigits+1) + "s|", Integer.toString(board[x][y])));
			}
			System.out.println();
		}
		System.out.println("Y");
	}
}
