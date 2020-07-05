package codeTalk.LanguageService.Common;

public class FileSpan {
	int StartLineNumber;
	int StartColumn;
	int EndLineNumber;
	int EndColumn;
	
	public FileSpan(int startLine, int startCol, int endLine, int endCol)
	{
		StartLineNumber = startLine;
		StartColumn = startCol;
		EndLineNumber = endLine;
		EndColumn = endCol;
	}
	
	public int getStartLineNumber() {
		return StartLineNumber;
	}
	
	public void setStartLineNumber(int startLineNumber) {
		StartLineNumber = startLineNumber;
	}
	
	public int getStartColumn() {
		return StartColumn;
	}
	
	public void setStartColumn(int startColumn) {
		StartColumn = startColumn;
	}
	
	public int getEndLineNumber() {
		return EndLineNumber;
	}
	
	public void setEndLineNumber(int endLineNumber) {
		EndLineNumber = endLineNumber;
	}
	
	public int getEndColumn() {
		return EndColumn;
	}
	
	public void setEndColumn(int endColumn) {
		EndColumn = endColumn;
	}
	
    public Boolean Encompasses(int lineNumber)
    {
        return lineNumber >= StartLineNumber && lineNumber <= EndLineNumber;
    }
}
