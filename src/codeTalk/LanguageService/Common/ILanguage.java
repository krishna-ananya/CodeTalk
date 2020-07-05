/**
 * 
 */
package codeTalk.LanguageService.Common;

/**
 * @author supartha
 *
 */
public interface ILanguage {
    CodeFile Compile(String programText, CompilationContext context);

	CodeFile Parse(String programText, String fileName);

    /*
    IEnumerable<CompileError> GetDiagnostics(string programText);

    string SpokenText(FunctionDefinition functionDefinition);

    string SpokenText(MemberProperty memberProperty);

    string SpokenText(FormalParameter formalParameter);

    string SpokenText(UserDefinedType udt);

    string SpokenText(NamespaceDefinition names);
    */
}
