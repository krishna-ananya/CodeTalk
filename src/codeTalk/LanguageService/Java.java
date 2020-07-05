package codeTalk.LanguageService;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import codeTalk.LanguageService.Common.*;

public class Java implements ILanguage{

	@Override
	public CodeFile Compile(String programText, CompilationContext context) {
		return null;
	}

	@Override
	public CodeFile Parse(String programText, String fileName)
	{
		CompilationUnit ast = JavaParser.parse(programText);
		Node root = ast.findRootNode();
		JavaEntityCollector collector = new JavaEntityCollector();
		collector.visit(ast, null);
		return collector.getRoot();
	}
}
