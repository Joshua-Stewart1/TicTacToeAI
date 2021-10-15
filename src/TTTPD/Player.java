package TTTPD;

public class Player
{
	public enum playerType {HUMAN, COMPUTER};
	
	private String name;
	private String marker;
	private playerType type;
	private Game game;
	
	public Player(String name, String marker, playerType type)
	{
		this.name = name;
		this.marker = marker;
		this.type = type;
	}
	
	public void setGame(Game game)
	{
		this.game = game;
	}
	
	public String getName()
	{
		return name;
	}
	public String getMark()
	{
		return marker;
	}
	public playerType getType()
	{
		return type;
	}
	
	public void move(int mark)
	{
		GameTree tree = new GameTree(mark);
		tree.createTree(game.getCurrentBoard());
		tree.evaluateTree();
		game.move(tree.getNextMoveX(), tree.getNextMoveY());
	}
}
