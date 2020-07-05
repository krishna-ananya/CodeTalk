package codeTalk.LanguageService.Common.Entities;

import java.util.ArrayList;
import java.util.List;

import codeTalk.LanguageService.Common.CodeFile;
import codeTalk.LanguageService.Common.FileSpan;
import codeTalk.LanguageService.Common.ICodeVisitor;
import codeTalk.LanguageService.Common.ISyntaxEntity;

public abstract class InheritableUserDefinedType extends UserDefinedType{

	private List<String> m_baseClasses;
	
	protected InheritableUserDefinedType(String name, FileSpan loc, ISyntaxEntity parent, CodeFile currentCodeFile) {
		super(name, loc, parent, currentCodeFile);
		m_baseClasses = new ArrayList<String>();
	}

	public List<String> getBaseClasses() {
		return m_baseClasses;
	}
	

	@Override
    public String toString() {
        return getKind()+": "+getName() + " at line " + getLocation().getStartLineNumber();
    }
	
}
