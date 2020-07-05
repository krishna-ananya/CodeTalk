package codeTalk;

import codeTalk.LanguageService.Language;
import codeTalk.LanguageService.Common.CodeFile;

public class Driver {

	public static void main(String[] args) {
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
		System.out.println("Finished parsing");
	}
}
