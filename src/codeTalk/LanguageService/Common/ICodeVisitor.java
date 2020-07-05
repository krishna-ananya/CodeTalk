package codeTalk.LanguageService.Common;

import codeTalk.LanguageService.Common.Entities.BlockType;
import codeTalk.LanguageService.Common.Entities.ClassDefinition;
import codeTalk.LanguageService.Common.Entities.FunctionDefinition;
import codeTalk.LanguageService.Common.Entities.InterfaceDefinition;
import codeTalk.LanguageService.Common.Entities.PackageDeclaration;
import codeTalk.LanguageService.Common.Entities.UserDefinedType;

public interface ICodeVisitor {
	void Visit(ISyntaxEntity entity);

    void VisitCodeFile(CodeFile codeFile);
    
    void VisitUserDefinedType(UserDefinedType udt);
    
    void VisitPackage(PackageDeclaration userClass);
    
    void VisitClass(ClassDefinition userClass);

    void VisitFunction(FunctionDefinition functionDefinition);
    
    void VisitInterface(InterfaceDefinition functionDefinition);
    
    void VisitBlockType(BlockType blockType);
}
