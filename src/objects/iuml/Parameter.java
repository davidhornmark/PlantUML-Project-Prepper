package objects.iuml;

public class Parameter {

	private String type = null;
	private String name = null;
	
	public Parameter(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.type + " " + this.name;
	}
	
}
