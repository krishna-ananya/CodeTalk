package codeTalk.LanguageService.Common;

public enum AccessSpecifiers {
	Public (1), 
	Private(2), 
	Internal(4), 
	Package(8), 
	Protected(16), 
	None(0)
	;
	
	private final int accessSpecifierCode;

    private AccessSpecifiers(int accessSpecifierCode) {
        this.accessSpecifierCode = accessSpecifierCode;
    }
}
