package CodeTalkTests;

import static org.junit.Assert.*;

import org.junit.Test;

import codeTalk.LanguageService.Language;
import codeTalk.LanguageService.Common.CodeFile;
import codeTalk.LanguageService.Common.ISyntaxEntity;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ParseCodeFileTest {

	public static void PrintCodeFile(CodeFile codeFile)
	{
		System.out.println(codeFile.getKind() + " " + codeFile.getName());
		for (ISyntaxEntity child : codeFile.getChildren())
		{
			DFS(child);
		}
	}
	
	private static void DFS(ISyntaxEntity node) {
		System.out.println(node.getKind() + " " + node.getName());
		for (ISyntaxEntity child : node.getChildren())
		{
			DFS(child);
		}
	}

	@Test
	public void testCodeFile1() {
		String javaCode = "public class Dog {" + "\n"
				+ "String breed;" + "\n"
				+ "int age;" + "\n"
				+ "String color;" + "\n"
				+ "" + "\n"
				+ "void barking() {" + "\n"
				+ "}" + "\n"
				+ "" + "\n"
				+ "void hungry() {" + "\n"
				+ "}" + "\n"
				+ "" + "\n"
				+ "void sleeping() {" + "\n"
				+ "}" + "\n"
				+ "}" + "\n";

		Language langService = new Language();
		CodeFile codeFile = langService.Parse(javaCode, " ");
		PrintCodeFile(codeFile);
		System.out.println("Finished parsing");
	}
	
	@Test
	public void testCodeFile2() {
		String javaCode = "public class Dog {" + "\n"
				+ "String breed;" + "\n"
				+ "int age;" + "\n"
				+ "String color;" + "\n"
				+ "" + "\n"
				+ "void barking() {" + "\n"
				+ "}" + "\n"
				+ "" + "\n"
				+ "void hungry() {" + "\n"
				+ "}" + "\n"
				+ "" + "\n"
				+ "void sleeping() {" + "\n"
				+ "}" + "\n"
				+ "}" + "\n"
				+ "public class Cat {" + "\n"
				+ "String breed;" + "\n"
				+ "int age;" + "\n"
				+ "String color;" + "\n"
				+ "" + "\n"
				+ "void meow() {" + "\n"
				+ "}" + "\n"
				+ "" + "\n"
				+ "void hungry() {" + "\n"
				+ "}" + "\n"
				+ "" + "\n"
				+ "void sleeping() {" + "\n"
				+ "}" + "\n"
				+ "}" + "\n";

		Language langService = new Language();
		CodeFile codeFile = langService.Parse(javaCode, " ");
		PrintCodeFile(codeFile);
		System.out.println("\n\nFinished parsing");
	}

}
