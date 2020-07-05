package codeTalk.LanguageService.Common.Entities;

import codeTalk.LanguageService.Common.CodeFile;
import codeTalk.LanguageService.Common.FileSpan;
import codeTalk.LanguageService.Common.ISyntaxEntity;
import codeTalk.LanguageService.Common.SyntaxEntityKind;

public class ClassDefinition extends InheritableUserDefinedType
{
	public ClassDefinition(String name, FileSpan loc, ISyntaxEntity parent, CodeFile currentCodeFile) {
		super(name, loc, parent, currentCodeFile);
		this.Kind = SyntaxEntityKind.Class;
	}
	
	@Override
    public String toString() {
        return getKind()+": "+getName() + " at line " + getLocation().getStartLineNumber();
    }
}
