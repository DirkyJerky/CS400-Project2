package frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

//TODO:  Extend from/replace with what the other application creators provide.
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

	
	// State info, 1 line:
	// this.menuName
	
	public void writeState(PrintWriter writer) {
		writer.println(this.menuName);
	}
	
	public static StreamSource readState(BufferedReader reader) throws IOException {
		String state = reader.readLine();
		
		for (StreamSource source : StreamSource.values()) {
			if (source.menuName.contentEquals(state)) {
				return source;
			}
		}
		
		throw new IOException("Invalid StreamSource in readState(): " + state);
	}
}
