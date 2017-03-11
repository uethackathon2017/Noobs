public class demo
{
	public static void main(String[] args)
	{
		SQLite sqlite = new SQLite();
		sqlite.open();
		sqlite.create();
	}
}