package codeTalk.LanguageService.Common;

import codeTalk.LanguageService.Common.Entities.BlockType;
import codeTalk.LanguageService.Common.Entities.ClassDefinition;
import codeTalk.LanguageService.Common.Entities.FunctionDefinition;
import codeTalk.LanguageService.Common.Entities.InterfaceDefinition;
import codeTalk.LanguageService.Common.Entities.PackageDeclaration;
import codeTalk.LanguageService.Common.Entities.UserDefinedType;

public class DefaultCodeVisitor implements ICodeVisitor{

	@Override
	public void Visit(ISyntaxEntity entity) {
        if (entity != null)
        {
            entity.AcceptVisitor(this);
        }
	}

	@Override
	public void VisitCodeFile(CodeFile codeFile) {
		VisitChildren(codeFile);
	}

	@Override
	public void VisitUserDefinedType(UserDefinedType udt) {
		VisitChildren(udt);	
	}

	@Override
	public void VisitClass(ClassDefinition userClass) {
		VisitChildren(userClass);		
	}

	@Override
	public void VisitFunction(FunctionDefinition functionDefinition) {
		 VisitChildren(functionDefinition);
	}

	@Override
	public void VisitBlockType(BlockType blockType) {
		// TODO Auto-generated method stub
		 VisitChildren(blockType);
	}	
	
	protected void VisitChildren(ISyntaxEntity entity)
    {
		 if (null != entity && null != entity.getChildren())
		 {
			 for (ISyntaxEntity child : entity.getChildren())
			 {
				 child.AcceptVisitor(this);
			 }
		 }
    }

	@Override
	public void VisitPackage(PackageDeclaration userClass) {
		// TODO Auto-generated method stub
		VisitChildren(userClass);
	}

	@Override
	public void VisitInterface(InterfaceDefinition functionDefinition) {
		// TODO Auto-generated method stub
		VisitChildren(functionDefinition);
	}

}
