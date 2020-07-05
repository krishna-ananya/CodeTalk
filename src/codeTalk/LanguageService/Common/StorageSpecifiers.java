/**
 * 
 */
package codeTalk.LanguageService.Common;

/**
 * @author supartha
 *
 */
public enum StorageSpecifiers {
	None (0), 
	Static (1), 
	Instance (2),
	Global (4), 
	Extern (8)
	;
	
	private final int specifierCode;

    private StorageSpecifiers(int specifierCode) {
        this.specifierCode = specifierCode;
    }
}
