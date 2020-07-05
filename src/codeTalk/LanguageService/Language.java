package codeTalk.LanguageService;

import codeTalk.LanguageService.Common.CodeFile;
import codeTalk.LanguageService.Common.ILanguage;

public class Language {
	private ILanguage m_language;
	
	 public CodeFile Parse(String programText, String fileName)
     {
		try
		{
			m_language = new Java();
			CodeFile codeFile = m_language.Parse(programText, fileName); 
			return codeFile;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
			//throw new CodeTalkLanguageServiceException(e);
		}
     }
}
