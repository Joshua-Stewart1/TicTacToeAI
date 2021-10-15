package TTTPD;

public class GameTree
{
	private GameTreeNode root;
	private int myMark;
	
	public GameTree(int myMark)
	{
		this.myMark = myMark;
	}
	
	public void createTree(Board currentBoard)
	{
		root = new GameTreeNode(null, currentBoard.clone(), 0, 0, -myMark);
		
		root.createChildren(root.getBoard());
	}
	
	public void evaluateTree()
	{
		root.evaluate(myMark);
	}
	
	//Get the x,y coordinates to reach this board
	public int getNextMoveX()
	{
		return root.getNextMoveX();
	}
	public int getNextMoveY()
	{
		return root.getNextMoveY();
	}
}
