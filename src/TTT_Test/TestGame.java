package TTT_Test;

import java.util.Scanner;

import TTTPD.Game;
import TTTPD.Player;
import TTTPD.Player.playerType;

public class TestGame
{
	public static void main(String args[])
	{
		//Initialize variables
		Scanner input = new Scanner(System.in);
		String response;
		Boolean valid;
		int playAgain = 1;
		Player player1 = null;
		Player player2 = null;
		
		//Start game
		System.out.println("Welcome to TIC-TAC-TOE\n");
		
		//Set up player 1
		valid = false;
		do
		{
			System.out.print("Is player 1 HUMAN or COMPUTER (H/C)? ");
			response = input.nextLine();
			response = response.toUpperCase();
			
			if(response.charAt(0) == 'H')
			{
				System.out.print("Enter Player 1's name: ");
				String p1Name = input.nextLine();
				
				player1 = new Player(p1Name, "X", playerType.HUMAN);
				
				valid = true;
			}
			else if(response.charAt(0) == 'C')
			{
				player1 = new Player("Computer", "X", playerType.COMPUTER);
				valid = true;
			}
			else
			{
				System.out.println("Invalid input, try again");
			}
			
		} while(!valid);
		
		//Set up player 2
		valid = false;
		do
		{
			System.out.print("Is player 2 HUMAN or COMPUTER (H/C)? ");
			response = input.nextLine();
			response = response.toUpperCase();
			
			if(response.charAt(0) == 'H')
			{
				System.out.print("Enter Player 2's name: ");
				String p2Name = input.nextLine();
				
				player2 = new Player(p2Name, "O", playerType.HUMAN);
				
				valid = true;
			}
			else if(response.charAt(0) == 'C')
			{
				player2 = new Player("Computer", "O", playerType.COMPUTER);
				valid = true;
			}
			else
			{
				System.out.println("Invalid input, try again");
			}
			
		} while(!valid);
		
		//Loop for multiple games
		while(playAgain == 1)
		{
			//Initialize game
			Game currentGame = new Game(player1, player2);
			player1.setGame(currentGame);
			player2.setGame(currentGame);
			int xPos, yPos;
			Player currentPlayer;
			
			//Take turns
			while(!currentGame.isComplete())
			{
				currentGame.printBoard();
				currentPlayer = currentGame.getCurrentPlayer();
				
				//If it's a human's turn, get their input
				if(currentPlayer.getType() == playerType.HUMAN)
				{
					Boolean moved = false;
					do
					{
						System.out.println(currentPlayer.getName() + "'s Turn");
						System.out.print("Enter x position: ");
						xPos = input.nextInt();
						System.out.print("Enter y position: ");
						yPos = input.nextInt();
						if(xPos >= 0 && xPos < 3 && yPos >= 0 && yPos < 3)
						{
							moved = currentGame.move(xPos, yPos);
						}
						if(!moved)
						{
							System.out.println("Invalid input, try again");
						}
					} while(!moved);
				}
				//Else, let the computer calculate its move
				else
				{
					if(currentPlayer == player1)
					{
						currentPlayer.move(1);
					}
					else
					{
						currentPlayer.move(-1);
					}
					
				}
				//Set up for next turn
				currentGame.switchPlayer();
			}
			currentGame.printBoard();
			
			//Display who won
			Player winner = currentGame.getWinner();
			
			if(winner != null)
			{
				System.out.println(winner.getName() + " Wins!");
			}
			else
			{
				System.out.println("Tie Game!");
			}
			
			System.out.print("Continue? (1 = Y, 0 = N): ");
			playAgain = input.nextInt();
		}
		input.close();
		
		System.out.println("\nGoodbye!");
	}
}