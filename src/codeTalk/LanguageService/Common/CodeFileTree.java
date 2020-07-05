package codeTalk.LanguageService.Common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;

public class CodeFileTree implements ITreeContentProvider {
	
	CodeFile codeFile; 

	public CodeFileTree(CodeFile codeFile) {
		this.codeFile = codeFile;
	}
	
	@Override
	public Object[] getChildren(Object arg0) {
		// TODO Auto-generated method stub
		ISyntaxEntity entity = (ISyntaxEntity)arg0;
		return entity.getChildren().toArray();
	}

	@Override
	public Object[] getElements(Object arg0) {
		
		ISyntaxEntity entity = ((CodeFileTree)(arg0)).codeFile;
		ISyntaxEntity[] entityArray= {entity};
		return entityArray; 
		//return GetAllElements(entity).toArray();
	}


	private List<ISyntaxEntity> GetAllElements(ISyntaxEntity entity)
	{
		List<ISyntaxEntity> entities = new ArrayList<ISyntaxEntity>();
		return DFS(entity, entities);
	}
	
	private List<ISyntaxEntity> DFS(ISyntaxEntity node, List<ISyntaxEntity> entities) {
		entities.add(node);
		for (ISyntaxEntity child : node.getChildren())
		{
			DFS(child, entities);
		}
		
		return entities;
	}
	
	@Override
	public Object getParent(Object arg0) {
		// TODO Auto-generated method stub

		ISyntaxEntity entity = (ISyntaxEntity)arg0;
		return entity.getParent();
	}

	@Override
	public boolean hasChildren(Object arg0) {
		// TODO Auto-generated method stub
		ISyntaxEntity entity = (ISyntaxEntity)arg0;
		return entity.getChildren().size()>0;
	}
}
