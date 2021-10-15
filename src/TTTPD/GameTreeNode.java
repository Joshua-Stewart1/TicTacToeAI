package TTTPD;

import java.util.ArrayList;

public class GameTreeNode
{
	private GameTreeNode parent;
	private ArrayList<GameTreeNode> children;
	private Board board;
	private int lastX, lastY;
	private int lastMark;
	private int score;
	
	public GameTreeNode(GameTreeNode p, Board b, int x, int y, int m)
	{
		parent = p;
		board = b;
		lastX = x;
		lastY = y;
		lastMark = m;
		children = new ArrayList<GameTreeNode>();
	}
	
	public Board getBoard()
	{
		return board;
	}
	public int getScore()
	{
		return score;
	}
	public int getLastX()
	{
		return lastX;
	}
	public int getLastY()
	{
		return lastY;
	}
	
	//Add children for the node
	public void createChildren(Board prevBoard)
	{
		GameTreeNode newChild;
		
		if(!(board.isComplete()))
		{
			for(int x = 0; x < 3; x++)
			{
				for(int y = 0; y < 3; y++)
				{
					if(board.isPosOpen(x, y))
					{
						newChild = new GameTreeNode(this, prevBoard.clone(), x, y, -lastMark);
						newChild.getBoard().setPos(x, y, -lastMark);
						children.add(newChild);
						newChild.createChildren(newChild.getBoard());
					}
				}
			}
		} 
	}
	
	//Determine the score of the node
	public int evaluate(int myMark)
	{
		if(children.size() == 0)
		{
			if(board.isWinner())
			{
				score = board.evaluate(myMark);
			}
			else
			{
				score = 0;
			}
		}
		else
		{
			int childScore;
			
			if(myMark == lastMark)
			{
				score = 2;
				
				for(GameTreeNode child : children)
				{
					childScore = child.evaluate(myMark);
					if(childScore < score)
					{
						score = childScore;
					}
				}
			}
			else
			{
				score = -2;
				
				for(GameTreeNode child : children)
				{
					childScore = child.evaluate(myMark);
					if(childScore > score)
					{
						score = childScore;
					}
				}
			}
		}
		return score;
	}
	
	//Get the x,y coordinates for the next move
	public int getNextMoveX()
	{
		int childScore;
		int nextX = -1;
		
		for(GameTreeNode child : children)
		{
			childScore = child.getScore();
			if(childScore == score)
			{
				nextX = child.getLastX();
			}
		}
		return nextX;
	}
	public int getNextMoveY()
	{
		int childScore;
		int nextY = -1;
		
		for(GameTreeNode child : children)
		{
			childScore = child.getScore();
			if(childScore == score)
			{
				nextY = child.getLastY();
			}
		}
		return nextY;
	}
}
