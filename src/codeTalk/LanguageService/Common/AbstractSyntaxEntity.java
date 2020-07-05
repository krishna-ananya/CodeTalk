package codeTalk.LanguageService.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractSyntaxEntity extends ISyntaxEntity{
	private List<ISyntaxEntity> m_children;
	
	protected AbstractSyntaxEntity()
	{
		m_children = new ArrayList<ISyntaxEntity>();
	}

	private AbstractSyntaxEntity(String name, FileSpan location)
	{
		//Anonymous functions are AbstractSyntaxEntities but don't have names. So cannot
		//block empty/null name here.
		m_children = new ArrayList<ISyntaxEntity>();
		this.Name = name;
		this.Location = location;
	}

	private AbstractSyntaxEntity(String name, FileSpan location, ISyntaxEntity parent)
	{
		this(name, location);
		this.Parent = parent; //OK if the parent is null.
	}

	protected AbstractSyntaxEntity(String name, FileSpan location, ISyntaxEntity parent, CodeFile currentCodeFile)
	{
		this(name, location, parent);
		this.CurrentCodeFile = currentCodeFile;
	}

	public abstract void AcceptVisitor(ICodeVisitor visitor);

	public void AddChild(ISyntaxEntity child)
	{
		m_children.add(child);
		Collections.sort(m_children, new Comparator<ISyntaxEntity>() {
			@Override
			public int compare(ISyntaxEntity c1, ISyntaxEntity c2) {
				Integer line1 = c1.Location.StartLineNumber;
				Integer line2 = c2.Location.StartLineNumber;
				return line1.compareTo(line2);
			}
		});
	}

	public String SpokenText()
	{
		return this.Kind + " " + this.Name + " at line " + this.Location.StartLineNumber;
	}

	public String DisplayText()
	{
		return this.Name + " at line " + this.Location.StartLineNumber;
	}

	// Getters/setters
	
	public CodeFile getCurrentCodeFile() {
		return CurrentCodeFile;
	}
	
	protected void setCurrentCodeFile(CodeFile currentCodeFile) {
		CurrentCodeFile = currentCodeFile;
	}

	public List<ISyntaxEntity> getChildren() {
		return m_children;
	}

	public String getName() {
		return Name;
	}

	protected void setName(String name) {
		Name = name;
	}

	public FileSpan getLocation() {
		return Location;
	}

	protected void setLocation(FileSpan location) {
		Location = location;
	}

	public ISyntaxEntity getParent() {
		return Parent;
	}

	protected void setParent(ISyntaxEntity parent) {
		Parent = parent;
	}
	
	public SyntaxEntityKind getKind() {
		return Kind;
	}
}
