package codeTalk.LanguageService;

import java.util.ArrayList;
import java.util.List;

import codeTalk.LanguageService.Common.DefaultCodeVisitor;
import codeTalk.LanguageService.Common.Entities.FunctionDefinition;

public class FunctionCollector extends DefaultCodeVisitor {
	private List<FunctionDefinition> m_functions = new ArrayList<FunctionDefinition>();
	
	public List<FunctionDefinition> getFunctions() {
		return m_functions;
	}

	@Override
	public void VisitFunction(FunctionDefinition functionDefinition)
	{
		if (functionDefinition == null)
        {
            // throw exception
        }
		
		m_functions.add(functionDefinition);
		super.VisitFunction(functionDefinition);
	}
}
