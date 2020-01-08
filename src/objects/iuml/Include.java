/**
 * 
 */
package objects.iuml;

/**
 * @author david
 *
 */
public class Include {
	private String name;
	private String path;

	public Include(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return this.path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String toString() {
		return this.name + ", " + this.path;
	}
}

