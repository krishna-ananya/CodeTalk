package codeTalk.LanguageService.Common;

import java.util.List;

public abstract class ISyntaxEntity {
	/**
	 * The name is simply the name of the entity without any 'decoration'. Like "ISyntaxEntity".
	 */
	String Name;
	
	/**
	 * Location in the file. 
	 */
	FileSpan Location;
	
	/**
	 * Any parent - for instance, parent of a function could be the containing class.
	 */
	public ISyntaxEntity Parent;
	
	/**
	 * The kind specifies the type of the entity. Useful for iterators to
	 *  hunt for specific types of entities.
	 */
	public SyntaxEntityKind Kind;

	/**
	 * The current code file object that contains this entity. 
	 * This object can be used to access language-specific 
	 * information through the Language object of the CodeFile 
	 * class. So, things like the names of the d'tor and c'tor
	 * and case-sensitivity of the language can all be found using
	 *  this pointer. 
	 */
	public CodeFile CurrentCodeFile;
	
	public String getName() {
		return Name;
	}

	public FileSpan getLocation() {
		return Location;
	}
	
	public SyntaxEntityKind getKind() {
		return Kind;
	}
	
	public ISyntaxEntity getParent() {
		return Parent;
	}

	public abstract List<ISyntaxEntity> getChildren();
	
	/**
	 * Add a child to the current entity. Implementation can choose to
	 * check for the type of the child, e.t.c
	 * @param child
	 */
	public abstract void AddChild(ISyntaxEntity child);
	
	/**
	 * Standard implementation of the visitor pattern. We are only offering
	 * the implementation where the visitor maintains STATE. i.e. there are
	 * no return values. 
	 * @param visitor
	 */
	abstract void AcceptVisitor(ICodeVisitor visitor);
	
    abstract String SpokenText();

    abstract String DisplayText();
}
