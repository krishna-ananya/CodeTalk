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
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import codeTalk.CodeTalk;
import codeTalk.MyElementTreeSelectionDialog;
import codeTalk.LanguageService.FunctionCollector;
import codeTalk.LanguageService.Language;
import codeTalk.LanguageService.Common.CodeFile;
import codeTalk.LanguageService.Common.CodeFileTree;
import codeTalk.LanguageService.Common.ICodeVisitor;
import codeTalk.LanguageService.Common.ISyntaxEntity;
import codeTalk.LanguageService.Common.Entities.FunctionDefinition;

public class GetSummaryCommand implements ICommand{

	@Override
	public void Execute(ExecutionEvent event) {
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
        	return;
        }
        
        CodeFileTree codeFileTree = new CodeFileTree(codeFile);
        
        if (false)
        {
        	return;
        }
        else
        {
        	IWorkbenchWindow window;
			try {
				window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
				MyElementTreeSelectionDialog treeDialog = new MyElementTreeSelectionDialog(window.getShell(), new LabelProvider(), codeFileTree);
				treeDialog.setInput(codeFileTree);
				treeDialog.setTitle("ElementTreeSelection Title");
				
				if (treeDialog.open() != Window.OK) {
					return;
				}
				
				int startLineNumber = ((ISyntaxEntity)(treeDialog.getFirstResult())).getLocation().getStartLineNumber();
				CodeTalk.eclipseOperations.GoToLocationInActiveDocument(startLineNumber, 0);
				
			} catch (ExecutionException e) {
				e.printStackTrace();
			}	
        }
	}
}
