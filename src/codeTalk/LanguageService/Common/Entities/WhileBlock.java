package codeTalk.LanguageService.Common.Entities;

import codeTalk.LanguageService.Common.CodeFile;
import codeTalk.LanguageService.Common.FileSpan;
import codeTalk.LanguageService.Common.ICodeVisitor;
import codeTalk.LanguageService.Common.ISyntaxEntity;
import codeTalk.LanguageService.Common.SyntaxEntityKind;

public class WhileBlock extends BlockType{

	public WhileBlock(String name, FileSpan loc, ISyntaxEntity parent, CodeFile currentCodeFile) {
		super(name, loc, parent, currentCodeFile);
		this.Kind = SyntaxEntityKind.While;
				
	}

	@Override
	public void AcceptVisitor(ICodeVisitor visitor) {
		if (null != visitor)
		{
			visitor.VisitBlockType(this);
		}
	}
	

	@Override
    public String toString() {
        return getKind()+": "+getName() + " at line " + getLocation().getStartLineNumber();
    }
}
