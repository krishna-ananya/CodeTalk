package codeTalk.Commands;

import java.util.ArrayList;
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
import codeTalk.LanguageService.FunctionCollector;
import codeTalk.LanguageService.Language;
import codeTalk.LanguageService.Common.CodeFile;
import codeTalk.LanguageService.Common.ICodeVisitor;
import codeTalk.LanguageService.Common.Entities.FunctionDefinition;

public class GetFunctionsCommand implements ICommand{

	@Override
	public void Execute(ExecutionEvent event) {
		List<FunctionDefinition> functions = new ArrayList<FunctionDefinition>();
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
        
       //Creating a function collector for getting all the functions
       ICodeVisitor functionCollector = new FunctionCollector();
       functionCollector.VisitCodeFile(codeFile);

       //Getting all the functions
       functions = ((FunctionCollector)functionCollector).getFunctions();
        
        if (0 == functions.size())
        {
        	// show message
        	return;
        }
        else
        {
        	IWorkbenchWindow window;
			try {
				window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
				ElementListSelectionDialog dialog =
	        		    new ElementListSelectionDialog(window.getShell(), new LabelProvider());
				dialog.setElements(functions.toArray());
				dialog.setTitle("List of functions");
				dialog.setBlockOnOpen(true);
				dialog.setHelpAvailable(false);
				dialog.setMultipleSelection(false);
				
				if (dialog.open() != Window.OK) {
					return;
				}
				
				int functionStartLineNumber = ((FunctionDefinition)(dialog.getFirstResult())).getLocation().getStartLineNumber();
				CodeTalk.eclipseOperations.GoToLocationInActiveDocument(functionStartLineNumber, 0);
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
        }
	}
}
