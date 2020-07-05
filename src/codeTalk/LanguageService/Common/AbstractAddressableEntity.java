package codeTalk.LanguageService.Common;

public abstract class AbstractAddressableEntity extends AbstractSyntaxEntity{

	protected AbstractAddressableEntity(String name, FileSpan loc, ISyntaxEntity parent, CodeFile currentCodeFile)
	{
		super(name, loc, parent, currentCodeFile);
		init();
	}
	
	private void init()
	{

	}
}
