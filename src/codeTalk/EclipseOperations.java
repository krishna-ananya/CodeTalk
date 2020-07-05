package codeTalk;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class EclipseOperations implements IEnvironmentOperations {

	private IDocument getActiveTextDocument() {
		final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();
		if (editor instanceof ITextEditor) {
			ITextEditor textEditor = (ITextEditor) editor;
			IDocumentProvider provider = textEditor.getDocumentProvider();
			IEditorInput input = editor.getEditorInput();
			IDocument document = provider.getDocument(input);

			return document;
		} else
			return null;
	}

	@Override
	public String GetActiveDocumentCode() {
		IDocument document = getActiveTextDocument();
		if (null != document) {
			return document.get();
		} else {
			return "";
		}
	}

	@Override
	public void GoToLocationInActiveDocument(int lineNumber, int columnNumber) {
		int start;
		try {
			final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.getActiveEditor();
			start = getActiveTextDocument().getLineOffset(lineNumber - 1);
			((ITextEditor) editor).selectAndReveal(start, 0);
			IWorkbenchPage page = editor.getSite().getPage();
			page.activate(editor);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public CursorPos GetCurrentCursorPosition() {
		int lineNumber = 0;
		final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();
		if (editor instanceof ITextEditor) {
			ITextEditor textEditor = (ITextEditor) editor;
			IDocumentProvider provider = textEditor.getDocumentProvider();
			IEditorInput input = editor.getEditorInput();
			IDocument document = provider.getDocument(input);
			ITextSelection textSelection = (ITextSelection) editor.getSite().getSelectionProvider().getSelection();
			int offset = textSelection.getOffset();
			try {
				lineNumber = document.getLineOfOffset(offset);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new CursorPos(lineNumber, 0);
	}

	@Override
	public String GetActiveDocumentPath() {
		int lineNumber = 0;
		final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();
		if (editor instanceof ITextEditor) {
			ITextEditor textEditor = (ITextEditor) editor;
			IDocumentProvider provider = textEditor.getDocumentProvider();
			IEditorInput input = editor.getEditorInput();
			IFile file = ((IFileEditorInput) input).getFile();
			String path = file.getFullPath().toFile().getName();
			return path;
		}
		return "";
	}
}
