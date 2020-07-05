package codeTalk;

public interface IEnvironmentOperations
{
	/*
	 * Method to get the code text of the active document
	 */
    String GetActiveDocumentCode();

    /*
     * Method to go to the given location in the active document
     */
    void GoToLocationInActiveDocument(int lineNumber, int columnNumber);
    
    /*
     * Method to get the current cursor position
     */
    CursorPos GetCurrentCursorPosition();

    String GetActiveDocumentPath();
}
