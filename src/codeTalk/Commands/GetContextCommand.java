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

public class GetContextCommand implements ICommand{

	@Override
	public void Execute(ExecutionEvent event) {
		List<ISyntaxEntity> contextHierarchy = new ArrayList<ISyntaxEntity>();
        CodeFile codeFile;		
        
        String path = CodeTalk.eclipseOperations.GetActiveDocumentPath();
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
        	// show message
        	return;
        }
        else
        {
        	IWorkbenchWindow window;
			try {
				Collections.reverse(contextHierarchy);
				window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
				CodeTalkElementListDialog dialog =
	        		    new CodeTalkElementListDialog(window.getShell(), new LabelProvider());
				dialog.setElements(contextHierarchy.toArray());
				dialog.setTitle("GetContextTitle");
				dialog.setBlockOnOpen(true);
				dialog.setHelpAvailable(false);
				dialog.setMultipleSelection(false);
				
				List<Object> initialSelection = new ArrayList<Object>();
				initialSelection.add(contextHierarchy.get(contextHierarchy.size() - 1));
				dialog.setInitialElementSelections(initialSelection);
				
				if (dialog.open() != Window.OK) {
					return;
				}
				
				int functionStartLineNumber = ((ISyntaxEntity)(dialog.getFirstResult())).getLocation().getStartLineNumber();
				CodeTalk.eclipseOperations.GoToLocationInActiveDocument(functionStartLineNumber, 0);
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
        }
	}
}
