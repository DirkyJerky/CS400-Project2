package frontend;

public enum StreamSource {
	FILTERED("Filtered stream"),
	SAMPLE("1% Sample stream");
	
	final String menuName;
	
	StreamSource(String menuName) {
		this.menuName = menuName;
	}
	
	@Override
	public String toString() {
		return this.menuName;
	} 
}
