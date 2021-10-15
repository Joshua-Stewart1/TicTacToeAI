package TTTPD;

public class Game
{
	private Player player1;
	private Player player2;
	private Board currentBoard;
	private Player currentPlayer;
	private Player winner;
	
	public Game(Player p1, Player p2)
	{
		player1 = p1;
		player2 = p2;
		currentBoard = new Board();
		currentPlayer = player1;
		winner = null;
	}
	
	//Returns currentPlayer
	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}
	//Returns currentBoard
	public Board getCurrentBoard()
	{
		return currentBoard;
	}
	//Returns the winner of the game, or null on tie
	public Player getWinner()
	{
		if(currentBoard.getWinner() == 1)
		{
			winner = player1;
		}
		else if(currentBoard.getWinner() == -1)
		{
			winner = player2;
		}
		return winner;
	}
	
	//Returns if the game is over
	public Boolean isComplete()
	{
		Boolean complete = false;
		
		if(currentBoard.isComplete())
		{
			complete = true;
		}
		
		return complete;
	}
	
	//Checks if move is possible, and does it if so
	public Boolean move(int x, int y)
	{
		Boolean moved = false;
		
		if(currentBoard.isPosOpen(x, y))
		{
			moved = true;
			
			if(currentPlayer == player1)
			{
				currentBoard.setPos(x, y, 1);
			}
			else
			{
				currentBoard.setPos(x, y, -1);
			}
		}
		return moved;
	}
	
	//Switches the current player
	public void switchPlayer()
	{
		if(currentPlayer == player1)
		{
			currentPlayer = player2;
		}
		else
		{
			currentPlayer = player1;
		}
	}
	
	//Print the game's current state
	public void printBoard()
	{
		currentBoard.print(player1.getMark(), player2.getMark());
	}
}
