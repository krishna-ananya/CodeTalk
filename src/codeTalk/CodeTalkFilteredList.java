package codeTalk;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import org.eclipse.jface.viewers.ILabelProvider;

//import org.eclipse.jdt.internal.core.refactoring.util.Selection;
import org.eclipse.jdt.internal.ui.util.StringMatcher;


/**
 * A composite widget which holds a list of elements for user selection.
 * The elements are sorted alphabetically.
 * Optionally, the elements can be filtered and duplicate entries can
 * be hidden (folding).
 */
public class CodeTalkFilteredList extends Composite {

	private Table fList;
	private ILabelProvider fRenderer;
	private boolean fMatchEmtpyString= true;
	private boolean fIgnoreCase;
	private boolean fAllowDuplicates;
	private String fFilter= ""; //$NON-NLS-1$
	
	private Object[] fElements= new Object[0];
	private Label[] fLabels;

	private int[] fFoldedIndices;
	private int fFoldedCount;
	
	private int[] fFilteredIndices;
	private int fFilteredCount;

	private static class Label {
		public final String string;
		public final Image image;

		public Label(String string, Image image) {
			this.string= string;
			this.image= image;
		}
		
		public boolean equals(Label label) {
			if (label == null)
				return false;
				
			return			
				string.equals(label.string) &&
				image.equals(label.image);
		}
	}

	private static class LabelComparator implements Comparator {
		private boolean fIgnoreCase;
	
		LabelComparator(boolean ignoreCase) {
			fIgnoreCase= ignoreCase;
		}
	
		public int compare(Object left, Object right) {
			return 0;
		}
		
	}	
	
	/**
	 * Constructs a new instance of a filtered list.
	 * @param parent           the parent composite.
	 * @param style            the widget style.
	 * @param renderer         the label renderer.
	 * @param ignoreCase       specifies whether sorting and folding is case sensitive.
	 * @param allowDuplicates  specifies whether folding of duplicates is desired.
	 * @param matchEmptyString specifies whether empty filter strings should filter everything or nothing.
	 */
	public CodeTalkFilteredList(Composite parent, int style, ILabelProvider renderer,
		boolean ignoreCase, boolean allowDuplicates, boolean matchEmptyString)
	{
		super(parent, SWT.NONE);

		GridLayout layout= new GridLayout();
		layout.marginHeight= 0;
		layout.marginWidth= 0;
		setLayout(layout);
		
		fList= new Table(this, style);
		fList.setLayoutData(new GridData(GridData.FILL_BOTH));
		fList.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				fRenderer.dispose();
			}
		});
		
		fRenderer= renderer;
		fIgnoreCase= ignoreCase;		
	//	fSorter= new TwoArrayQuickSorter(new LabelComparator(ignoreCase));
		fAllowDuplicates= allowDuplicates;
		fMatchEmtpyString= matchEmptyString;
	}

	/**
	 * Sets the list of elements.
	 * @param elements the elements to be shown in the list.
	 */
	public void setElements(Object[] elements) {
		if (elements == null) {
			fElements= new Object[0];
		} else {
			// copy list for sorting
			fElements= new Object[elements.length];
			System.arraycopy(elements, 0, fElements, 0, elements.length);
		}
			
		int length= fElements.length;

		// fill labels			
		fLabels= new Label[length];
		for (int i= 0; i != length; i++)
			fLabels[i]= new Label(
				fRenderer.getText(fElements[i]),
				fRenderer.getImage(fElements[i]));

//		fSorter.sort(fLabels, fElements);
		
		fFoldedIndices= new int[length];
		fFoldedCount= fold();

		fFilteredIndices= new int[length];	
		fFilteredCount= filter();

		updateList();
	}

	/**
	 * Tests if the list (before folding and filtering) is empty.
	 * @return returns <code>true</code> if the list is empty, <code>false</code> otherwise.
	 */
	public boolean isEmpty() {
		return (fElements == null) || (fElements.length == 0);
	}

    /**
     * Adds a selection listener to the list.
     * @param listener the selection listener to be added.
     */
	public void addSelectionListener(SelectionListener listener) {
		fList.addSelectionListener(listener);
	}

    /**
     * Removes a selection listener from the list.
     * @param listener the selection listener to be removed.
     */
	public void removeSelectionListener(SelectionListener listener) {
		fList.removeSelectionListener(listener);
	}	

    /**
     * Sets the selection of the list.
     * @param selection an array of indices specifying the selection.
     */
	public void setSelection(int[] selection) {
		fList.setSelection(selection);
	}
	
	/**
	 * Returns the selection of the list.
	 * @return returns an array of indices specifying the current selection.
	 */
	public int[] getSelectionIndices() {
		return fList.getSelectionIndices();
	}
	
	/**
	 * Returns the selection of the list.
	 * This is a convenience function for <code>getSelectionIndices()</code>.
	 * @return returns the index of the selection, -1 for no selection.
	 */
	public int getSelectionIndex() {
		return fList.getSelectionIndex();		
	}
	
	/**
	 * Sets the selection of the list.
	 * @param elements the array of elements to be selected.
	 */
	public void setSelection(Object[] elements) {
		if ((elements == null) || (fElements == null))
			return;			
		
		// fill indices
		int[] indices= new int[elements.length];
		for (int i= 0; i != elements.length; i++) {
			int j;
			for (j= 0; j != fFilteredCount; j++) {
				int k = fFilteredIndices[j];
				int max= (k == fFoldedCount - 1)
					? fElements.length
					: fFoldedIndices[k + 1];
				
				int l;
				for (l= fFoldedIndices[k]; l != max; l++) {
					// found matching element?
					if (fElements[l].equals(elements[i])) {
						indices[i]= j;
						break;
					}					
				}
				
				if (l != max)
					break;
			}
			
			// not found
			if (j == fFilteredCount)
				indices[i] = 0;
		}
		
		fList.setSelection(indices);
	}
	
	/**
	 * Returns an array of the selected elements. The type of the elements
	 * returned in the list are the same as the ones passed with
	 * <code>setElements</code>. The array does not contain the rendered strings.
	 * @return returns the array of selected elements.
	 */
	public Object[] getSelection() {
		if (fList.isDisposed() || (fList.getSelectionCount() == 0))
			return new Object[0];

		int[] indices= fList.getSelectionIndices();
		Object[] elements= new Object[indices.length];
		
		for (int i= 0; i != indices.length; i++)
			elements[i]= fElements[fFoldedIndices[fFilteredIndices[indices[i]]]];
		
		return elements;		
	}

	/**
	 * Sets the filter pattern. Current only prefix filter patterns are supported.
	 * @param filter the filter pattern.
	 */
	public void setFilter(String filter) {
		fFilter= (filter == null) ? "" : filter; //$NON-NLS-1$

		fFilteredCount= filter();
		updateList();
	}
	
	/**
	 * Returns the filter pattern.
	 * @return returns the filter pattern.
	 */
	public String getFilter() {
		return fFilter;
	}

	/**
	 * Returns all elements which are folded together to one entry in the list.
	 * @param  index the index selecting the entry in the list.
	 * @return returns an array of elements folded together, <code>null</code> if index is out of range.
	 */
	public Object[] getFoldedElements(int index) {
		if ((index < 0) || (index >= fFilteredCount))
			return null;
		
		index= fFilteredIndices[index];

		int start= fFoldedIndices[index];			
		int count= (index == fFoldedCount - 1)
			? fElements.length - start
			: fFoldedIndices[index + 1] - start;
			
		Object[] elements= new Object[count];
		for (int i= 0; i != count; i++)
			elements[i]= fElements[start + i];
				
		return elements;
	}

    /*
     * Folds duplicate entries. Two elements are considered as a pair of
     * duplicates if they coiincide in the rendered string and image.
     * @return returns the number of elements after folding.
     */
	private int fold() {
		int length= fElements.length;
		
		if (fAllowDuplicates) {
			for (int i= 0; i != length; i++)			
				fFoldedIndices[i]= i; // identity mapping
				
			return length;			
		
		} else {
			int k= 0;
			Label last= null;
			for (int i= 0; i != length; i++) {
				Label current= fLabels[i];
				if (! current.equals(last)) {
					fFoldedIndices[k]= i;
					k++;
					last= current;
				}
			}
			return k;
		}
	}

	/*
	 * Filters the list with the filter pattern.
     * @return returns the number of elements after filtering.
	 */
	private int filter() {
		if (((fFilter == null) || (fFilter.length() == 0)) && !fMatchEmtpyString)
			return 0;
		
		StringMatcher matcher= new StringMatcher(fFilter + "*", fIgnoreCase, false); //$NON-NLS-1$

		int k= 0;
		for (int i= 0; i != fFoldedCount; i++) {
			int j = fFoldedIndices[i];
			if (matcher.match(fLabels[j].string))
				fFilteredIndices[k++]= i;
		}			
						
		return k;
	}	

	/*
	 * Updates the list widget.
	 */	 
	private void updateList() {
		if (fList.isDisposed())
			return;
			
		fList.setRedraw(false);
		
		// resize table
		int itemCount= fList.getItemCount();
		if (fFilteredCount < itemCount)
			fList.remove(0, itemCount - fFilteredCount - 1);
		else if (fFilteredCount > itemCount)
			for (int i= 0; i != fFilteredCount - itemCount; i++)
				new TableItem(fList, SWT.NONE);

		// fill table
		TableItem[] items= fList.getItems();
		for (int i= 0; i != fFilteredCount; i++) {
			TableItem item= items[i];
			Label label= fLabels[fFoldedIndices[fFilteredIndices[i]]];
			
			item.setText(label.string);
			item.setImage(label.image);
		}

		// select first item if any
		if (fList.getItemCount() > 0)
			fList.setSelection(0);
			
		fList.setRedraw(true);		
		fList.notifyListeners(SWT.Selection, new Event());
	}
	
}