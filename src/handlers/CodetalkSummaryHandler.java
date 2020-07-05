package handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import codeTalk.Commands.GetFunctionsCommand;
import codeTalk.Commands.GetSummaryCommand;
import codeTalk.Commands.ICommand;

import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class CodetalkSummaryHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public CodetalkSummaryHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ICommand command = new GetSummaryCommand();
		command.Execute(event);

		// todo
		return null;
	}
	
}
