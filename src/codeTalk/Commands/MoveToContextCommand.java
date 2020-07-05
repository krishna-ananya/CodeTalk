package codeTalk.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import codeTalk.CodeTalk;
import codeTalk.CodeTalkElementListDialog;
import codeTalk.LanguageService.FunctionCollector;
import codeTalk.LanguageService.Language;
import codeTalk.LanguageService.Common.CodeFile;
import codeTalk.LanguageService.Common.ICodeVisitor;
import codeTalk.LanguageService.Common.ISyntaxEntity;
import codeTalk.LanguageService.Common.Entities.FunctionDefinition;

public class MoveToContextCommand implements ICommand{

	@Override
	public void Execute(ExecutionEvent event) {
		List<ISyntaxEntity> contextHierarchy = new ArrayList<ISyntaxEntity>();
        CodeFile codeFile;		
        
        String path = ""; //TalkCodePackage.vsOperations.GetActiveDocumentPath();
        String codeText = CodeTalk.eclipseOperations.GetActiveDocumentCode();
        
        //Creating a language service
        Language lService = new Language();
        
        try
        {
        	codeFile = lService.Parse(codeText, path);
        }
        catch(Exception ex)
        {
        	// show error
        	return;
        }
        
        int currentLineNumber = 0;
        currentLineNumber = CodeTalk.eclipseOperations.GetCurrentCursorPosition().getLineNumber();
        
        contextHierarchy = codeFile.GetContextAtLine(currentLineNumber);
       
        if (0 == contextHierarchy.size())
        {
        	return;
        }
        else
        {
        	IWorkbenchWindow window;
			try {
				int contextLineNumber = contextHierarchy.get(0).getLocation().getStartLineNumber();
				CodeTalk.eclipseOperations.GoToLocationInActiveDocument(contextLineNumber, 0);
			} catch (Exception e) {
				e.printStackTrace();
			}	
        }
	}
}
