package TTTPD;

public class Board implements Cloneable
{
	private int[][] board;
	private int winner;
	
	public Board()
	{
		winner = 0;
		board = new int[3][3];
		
		//Initialize board to empty
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				board[i][j] = 0;
			}
		}
	}
	
	//Returns if the game is over
	public Boolean isComplete()
	{
		Boolean complete = false;
		
		if(isFull() || isWinner())
		{
			complete = true;
		}
		
		return complete;
	}
	
	//Returns if the board is full
	public Boolean isFull()
	{
		Boolean isFull = true;
		
		for (int i = 0; i < 3 && isFull; i++)
		{
			for (int j = 0; j < 3 && isFull; j++)
			{
				if(board[i][j] == 0)
				{
					isFull = false;
				}
			}
		}
		return isFull;
	}
	
	//Returns if the board has a winner
	public Boolean isWinner()
	{
		Boolean isWinner = false;
		int sum = 0;
		
		//Check rows for win
		for(int x = 0; x < 3 && !isWinner; x++)
		{
			sum = 0;
			for(int y = 0; y < 3 && !isWinner; y++)
			{
				sum += board[x][y];
				
			}
			if(sum == 3 || sum == -3)
			{
				isWinner = true;
			}
		}
		//Check columns for win
		for(int y = 0; y < 3 && !isWinner; y++)
		{
			sum = 0;
			for(int x = 0; x < 3 && !isWinner; x++)
			{
				sum += board[x][y];
				
			}
			if(sum == 3 || sum == -3)
			{
				isWinner = true;
			}
		}
		//Check first diagonal
		if(!isWinner)
		{
			sum = board[0][0] + board[1][1] + board[2][2];
			
			if(sum == 3 || sum == -3)
			{
				isWinner = true;
			}
		}
		//Check second diagonal
		if(!isWinner)
		{
			sum = board[0][2] + board[1][1] + board[2][0];
			
			if(sum == 3 || sum == -3)
			{
				isWinner = true;
			}
		}
		//Set winner based on results
		if(sum == 3)
		{
			winner = 1;
		}
		else if(sum == -3)
		{
			winner = -1;
		}
		return isWinner;
	}
	
	//Returns the value of winner
	public int getWinner()
	{
		return winner;
	}
	
	//Sets the value of winner
	public void setWinner(int w)
	{
		winner = w;
	}
	
	//Checks if the give location is open
	public Boolean isPosOpen(int x, int y)
	{
		Boolean isOpen = false;
		
		if(board[x][y] == 0)
		{
			isOpen = true;
		}
		return isOpen;
	}
	
	//Sets the value of a position on the board
	public void setPos(int x, int y, int player)
	{
		board[x][y] = player;
	}
	
	public int evaluate(int myMark)
	{
		int score;
		
		if(winner == myMark)
		{
			score = 1;
		}
		else
		{
			score = -1;
		}
		return score;
	}
	
	public Board clone()
	{
		Board newBoard = new Board();
		
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				newBoard.setPos(x, y, board[x][y]);
			}
		}
		return newBoard;
	}
	
	//Print the display for the board
	public void print(String mark1, String mark2)
	{
		int x;
		int y;
		
		System.out.println();
		for (x = 0; x < 2; x++)
		{
			for (y = 0; y < 2; y++)
			{
				System.out.print(getMark(x, y, mark1, mark2) + "|");
			}
			System.out.println(getMark(x, y, mark1, mark2));
			System.out.println("-----");
		}
		for (y = 0; y < 2; y++)
		{
			System.out.print(getMark(x, y, mark1, mark2) + "|");
		}
		System.out.println(getMark(x, y, mark1, mark2));
		System.out.println();
	}
	
	//Calculate which mark to use
	public String getMark(int x, int y, String mark1, String mark2)
	{
		String mark = " ";
		
		if(board[x][y] == 1)
		{
			mark = mark1;
		}
		else if(board[x][y] == -1)
		{
			mark = mark2;
		}
		
		return mark;
	}
}
