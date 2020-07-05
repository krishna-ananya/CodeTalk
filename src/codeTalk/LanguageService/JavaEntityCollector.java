package codeTalk.LanguageService;

import java.util.List;
import java.util.Optional;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import codeTalk.CodeTalk;
import codeTalk.LanguageService.Common.AbstractSyntaxEntity;
import codeTalk.LanguageService.Common.CodeFile;
import codeTalk.LanguageService.Common.FileSpan;
import codeTalk.LanguageService.Common.ISyntaxEntity;
import codeTalk.LanguageService.Common.Entities.BlockType;
import codeTalk.LanguageService.Common.Entities.FunctionDefinition;
import codeTalk.LanguageService.Common.Entities.UserDefinedType;

public class JavaEntityCollector extends VoidVisitorAdapter<Void> {

	private CodeFile m_currentCodeFile = null;
    private ISyntaxEntity m_currentParent = null;
    private CompilationUnit m_currentTree;
	
    public CodeFile getRoot() {  return m_currentCodeFile; } 
    
    @Override
    public void visit(final CompilationUnit node, final Void arg) {
        if (null == node)
        {
        	// throw exception
        }
        
        FileSpan span = new FileSpan(node.getBegin().get().line, node.getBegin().get().line
        		, node.getEnd().get().line, node.getEnd().get().line);
        
        m_currentCodeFile = new CodeFile(CodeTalk.eclipseOperations.GetActiveDocumentPath(), span, new Java());
        m_currentParent = m_currentCodeFile;
        m_currentCodeFile.CurrentCodeFile = m_currentCodeFile;
        super.visit(node, arg);
    }
    
	@Override
	public void visit(final MethodDeclaration node, final Void arg) {
		FunctionDefinition methodObj = JavaEntityCreationHelper.CreateMethod(node, m_currentParent, m_currentCodeFile);
        ISyntaxEntity oldParent = setCurrentParent(methodObj);
        super.visit(node, arg);
        m_currentParent = oldParent;
	}
	
	@Override
	public void visit(final ClassOrInterfaceDeclaration node, final Void arg) {
		UserDefinedType udt = null;
		if(node.isInterface()) 
		{
			udt = JavaEntityCreationHelper.CreateInterface(node, m_currentParent, m_currentCodeFile);
		}
		else 
		{
			udt = JavaEntityCreationHelper.CreateClass(node, m_currentParent, m_currentCodeFile);
		}
		ISyntaxEntity oldParent = setCurrentParent(udt);
        super.visit(node, arg);
        m_currentParent = oldParent;
	}
	
	@Override
	public void visit(final com.github.javaparser.ast.PackageDeclaration node, final Void arg) {
		codeTalk.LanguageService.Common.Entities.PackageDeclaration classObj 
			= JavaEntityCreationHelper.CreatePackage(node, m_currentParent, m_currentCodeFile);
		ISyntaxEntity oldParent = setCurrentParent(classObj);
        super.visit(node, arg);
        m_currentParent = oldParent;
	}
	
	private ISyntaxEntity setCurrentParent(ISyntaxEntity obj)
    {
        m_currentParent.AddChild(obj);
        obj.Parent = m_currentParent;
        ISyntaxEntity savedParent = m_currentParent;
        m_currentParent = obj;

        return savedParent;
    }


	@Override
	public void visit(final TryStmt node, final Void arg) {
		BlockType tryObj = JavaEntityCreationHelper.CreateTryBlock(node, m_currentParent, m_currentCodeFile);
		ISyntaxEntity oldParent = setCurrentParent(tryObj);
        super.visit(node, arg);
        m_currentParent = oldParent;
	}
	
	@Override
	public void visit(final CatchClause node, final Void arg) {
		BlockType catchObj = JavaEntityCreationHelper.CreateCatchBlock(node, m_currentParent, m_currentCodeFile);
		ISyntaxEntity oldParent = setCurrentParent(catchObj);
        super.visit(node, arg);
        m_currentParent = oldParent;
	}
	
	@Override
	public void visit(final IfStmt  node, final Void arg) {
		node.getCondition().accept(this, arg);
	    node.getElseStmt().ifPresent(l -> l.accept(this, arg));
	    node.getThenStmt().accept(this, arg);
		BlockType ifObj = JavaEntityCreationHelper.CreateIfBlock(node, m_currentParent, m_currentCodeFile);
		ISyntaxEntity oldParent = setCurrentParent(ifObj);
        super.visit(node, arg);
        m_currentParent = oldParent;
	}
	

	@Override
	public void visit(final ForStmt node, final Void arg) {
		BlockType forObj = JavaEntityCreationHelper.CreateForBlock(node, m_currentParent, m_currentCodeFile);
		ISyntaxEntity oldParent = setCurrentParent(forObj);
        super.visit(node, arg);
        m_currentParent = oldParent;
	}
	
	@Override
	public void visit(final ForeachStmt node, final Void arg) {
		BlockType forEachObj = JavaEntityCreationHelper.CreateForeachBlock(node, m_currentParent, m_currentCodeFile);
		ISyntaxEntity oldParent = setCurrentParent(forEachObj);
        super.visit(node, arg);
        m_currentParent = oldParent;
	}
	
	@Override
	public void visit(final WhileStmt node, final Void arg) {
		BlockType whileObj = JavaEntityCreationHelper.CreateWhileBlock(node, m_currentParent, m_currentCodeFile);
		ISyntaxEntity oldParent = setCurrentParent(whileObj);
        super.visit(node, arg);
        m_currentParent = oldParent;
	}
	
	
	
	 
	
/*	@Override
	public void visit(final ConstructorDeclaration n, final Void arg) {
		
	}
	
	@Override
	public void visit(final PackageDeclaration  n, final Void arg) {
		
	}
	
	@Override
	public void visit(final ClassOrInterfaceDeclaration n, final Void arg) {
		
	}

    @Override
    public void visit(final ConditionalExpr n, final Void arg) {
        
    }*/
}
