package codeTalk;

public class CursorPos {
	private int lineNumber;
	private int columnNumber;
	
	public CursorPos(int l, int c)
	{
		this.lineNumber = l;
		this.columnNumber = c;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
}
