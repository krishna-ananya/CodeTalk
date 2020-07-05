package codeTalk;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

public class MyElementTreeSelectionDialog extends ElementTreeSelectionDialog {

	public MyElementTreeSelectionDialog(Shell parent, IBaseLabelProvider labelProvider,
			ITreeContentProvider contentProvider) {
		super(parent, labelProvider, contentProvider);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected TreeViewer getTreeViewer() {
		// TODO Auto-generated method stub
		super.getTreeViewer().expandAll();
		return super.getTreeViewer();
	}

	@Override
	protected TreeViewer createTreeViewer(Composite parent) {
		// TODO Auto-generated method stub
		TreeViewer treeViewer =  super.createTreeViewer(parent);
		treeViewer.expandAll();
		return treeViewer;
	}
	
}