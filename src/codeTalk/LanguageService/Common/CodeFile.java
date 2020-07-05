package codeTalk.LanguageService.Common;

import java.util.ArrayList;
import java.util.List;

public class CodeFile extends AbstractSyntaxEntity {

	public ILanguage Language;

	public CodeFile() {
		
	}
	public CodeFile(String name, FileSpan location, ILanguage language) 
	{
		super(name, location, null, null);
		CurrentCodeFile = this;
		Language = language;
		this.Kind = SyntaxEntityKind.CodeFile;
	}

	@Override
    public String toString() {
		return getKind() + ": " + getName();
    }
	
	public List<ISyntaxEntity> GetContextAtLine(int lineNumber)
	{
		List<ISyntaxEntity> entities = new ArrayList<ISyntaxEntity>();
		collect(this, lineNumber, entities);
		entities.add(this);
		return entities;
	}

	@Override
	public void AcceptVisitor(ICodeVisitor visitor) {
		if (null != visitor)
		{
			visitor.VisitCodeFile(this);
		}
	}

	private void collect(ISyntaxEntity node, int lineNumber, List<ISyntaxEntity> entities)
	{
		for (ISyntaxEntity child : node.getChildren())
		{
			if (child.Location.Encompasses(lineNumber) )
			{
				collect(child, lineNumber, entities);
				entities.add(child);
			}
		}
	}
	
	@Override
	public String SpokenText()
	{
		return super.SpokenText();
	}

	@Override
	public String DisplayText()
	{
		return super.DisplayText();
	}
}
