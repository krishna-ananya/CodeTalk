package codeTalk.LanguageService.Common.Entities;

import codeTalk.LanguageService.Common.AbstractAddressableEntity;
import codeTalk.LanguageService.Common.CodeFile;
import codeTalk.LanguageService.Common.FileSpan;
import codeTalk.LanguageService.Common.ICodeVisitor;
import codeTalk.LanguageService.Common.ISyntaxEntity;

public abstract class BlockType extends AbstractAddressableEntity{

	protected BlockType(String name, FileSpan loc, ISyntaxEntity parent, CodeFile currentCodeFile) {
		super(name, loc, parent, currentCodeFile);
	}

	@Override
	public void AcceptVisitor(ICodeVisitor visitor) {
		if (null != visitor)
		{
			visitor.VisitBlockType(this);
		}
	}
}
