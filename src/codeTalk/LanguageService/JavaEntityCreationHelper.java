package codeTalk.LanguageService;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

import codeTalk.LanguageService.Common.CodeFile;
import codeTalk.LanguageService.Common.FileSpan;
import codeTalk.LanguageService.Common.ISyntaxEntity;
import codeTalk.LanguageService.Common.Entities.BlockType;
import codeTalk.LanguageService.Common.Entities.CatchBlock;
import codeTalk.LanguageService.Common.Entities.ClassDefinition;
import codeTalk.LanguageService.Common.Entities.ForBlock;
import codeTalk.LanguageService.Common.Entities.ForeachBlock;
import codeTalk.LanguageService.Common.Entities.FunctionDefinition;
import codeTalk.LanguageService.Common.Entities.IfBlock;
import codeTalk.LanguageService.Common.Entities.InterfaceDefinition;
import codeTalk.LanguageService.Common.Entities.PackageDeclaration;
import codeTalk.LanguageService.Common.Entities.SwitchBlock;
import codeTalk.LanguageService.Common.Entities.TryBlock;
import codeTalk.LanguageService.Common.Entities.UserDefinedType;
import codeTalk.LanguageService.Common.Entities.WhileBlock;

public class JavaEntityCreationHelper {

	public static PackageDeclaration CreatePackage(com.github.javaparser.ast.PackageDeclaration node, ISyntaxEntity parent,
			CodeFile currentCodeFile) {
		FileSpan span = new FileSpan(node.getBegin().get().line, node.getBegin().get().line
        		, node.getEnd().get().line, node.getEnd().get().line);
		PackageDeclaration typeObj = new PackageDeclaration(node.getNameAsString(), span, 
				 parent, currentCodeFile);
		return typeObj;
	}
	
	public static FunctionDefinition CreateMethod(MethodDeclaration node, ISyntaxEntity parent, 
			CodeFile currentCodeFile) {
        FileSpan span = new FileSpan(node.getBegin().get().line, node.getBegin().get().line
        		, node.getEnd().get().line, node.getEnd().get().line);
		
		FunctionDefinition func = new FunctionDefinition(node.getNameAsString(), span, 
				parent, currentCodeFile);
		
		return func;
	}

	public static UserDefinedType CreateClass(ClassOrInterfaceDeclaration node, ISyntaxEntity parent,
			CodeFile currentCodeFile) {
		FileSpan span = new FileSpan(node.getBegin().get().line, node.getBegin().get().line
        		, node.getEnd().get().line, node.getEnd().get().line);
		 UserDefinedType typeObj = new ClassDefinition(node.getNameAsString(), span, 
				 parent, currentCodeFile);
		 
		return typeObj;
	}
	
	public static UserDefinedType CreateInterface(ClassOrInterfaceDeclaration node, ISyntaxEntity parent,
			CodeFile currentCodeFile) {
		FileSpan span = new FileSpan(node.getBegin().get().line, node.getBegin().get().line
        		, node.getEnd().get().line, node.getEnd().get().line);
		 UserDefinedType typeObj = new InterfaceDefinition(node.getNameAsString(), span, 
				 parent, currentCodeFile);
		 
		return typeObj;
	}
	
	public static BlockType CreateTryBlock(TryStmt node,ISyntaxEntity parent,CodeFile currentCodeFile) {
		FileSpan span = new FileSpan(node.getBegin().get().line, node.getBegin().get().line
        		, node.getEnd().get().line, node.getEnd().get().line);
		BlockType typeObj = new TryBlock("",span, 
				 parent, currentCodeFile);
		return typeObj;
	}
	

	
	public static BlockType CreateCatchBlock(CatchClause node,ISyntaxEntity parent,CodeFile currentCodeFile) {
		FileSpan span = new FileSpan(node.getBegin().get().line, node.getBegin().get().line
        		, node.getEnd().get().line, node.getEnd().get().line);
		BlockType typeObj = new CatchBlock("",span, 
				 parent, currentCodeFile);
		return typeObj;
	}

	public static BlockType CreateIfBlock(IfStmt node, 
			ISyntaxEntity parent, CodeFile currentCodeFile) {
		// TODO Auto-generated method stub
		FileSpan span = new FileSpan(node.getBegin().get().line, node.getBegin().get().line
        		, node.getEnd().get().line, node.getEnd().get().line);
		BlockType typeObj = new IfBlock("",span, parent, currentCodeFile);
		return typeObj;
	
	}

	public static BlockType CreateSwitchBlock(SwitchStmt node, 
			ISyntaxEntity parent, CodeFile currentCodeFile) {
		// TODO Auto-generated method stub
		FileSpan span = new FileSpan(node.getBegin().get().line, node.getBegin().get().line
        		, node.getEnd().get().line, node.getEnd().get().line);
		BlockType typeObj = new SwitchBlock("",span, parent, currentCodeFile);
		return typeObj;
	}
	public static BlockType CreateForBlock(ForStmt node, ISyntaxEntity parent, CodeFile currentCodeFile) {
		// TODO Auto-generated method stub

		FileSpan span = new FileSpan(node.getBegin().get().line, node.getBegin().get().line
        		, node.getEnd().get().line, node.getEnd().get().line);
		BlockType typeObj = new ForBlock("",span, parent, currentCodeFile);
		return typeObj;
	}

	public static BlockType CreateForeachBlock(ForeachStmt node, ISyntaxEntity parent,
			CodeFile currentCodeFile) {
		// TODO Auto-generated method stub

		FileSpan span = new FileSpan(node.getBegin().get().line, node.getBegin().get().line
        		, node.getEnd().get().line, node.getEnd().get().line);
		BlockType typeObj = new ForeachBlock("",span, parent, currentCodeFile);
		return typeObj;
	}

	public static BlockType CreateWhileBlock(WhileStmt node, ISyntaxEntity parent,
			CodeFile currentCodeFile) {
		// TODO Auto-generated method stub

		FileSpan span = new FileSpan(node.getBegin().get().line, node.getBegin().get().line
        		, node.getEnd().get().line, node.getEnd().get().line);
		BlockType typeObj = new WhileBlock("",span, parent, currentCodeFile);
		return typeObj;
	}

}
