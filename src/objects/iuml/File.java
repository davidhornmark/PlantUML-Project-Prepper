/**
 * 
 */
package objects.iuml;
import java.util.ArrayList;
/**
 * @author david
 *
 */

public class File {
	private ArrayList<Include> 		includes 	= null;
	private ArrayList<Relation> 	relations 	= null;
	private ArrayList<Entity> 		entities 	= null;
	
	public File() {
		this.includes = new ArrayList<Include>();
		this.relations = new ArrayList<Relation>();
		this.entities = new ArrayList<Entity>();
	}

	public ArrayList<Include> getIncludes() {
		return this.includes;
	}
	public ArrayList<Relation> getRelations() {
		return this.relations;
	}
	public ArrayList<Entity> getEntities() {
		return this.entities;
	}
	
	public boolean setIncludes(ArrayList<Include> new_includes) {
		this.includes.clear();
		return this.includes.addAll(new_includes);
	}
	public boolean setRelations(ArrayList<Relation> new_relations) {
		this.relations.clear();
		return this.relations.addAll(new_relations);
	}
	
	public boolean appendIncludes(String name, String path) {
		return this.includes.add(new Include(name, path));
	}
	
	public boolean appendRelations(String[] parties, String relation_token) {
		return this.relations.add(new Relation(parties, relation_token));
	}
	
	public boolean appendEntities(ArrayList<Member> members, String name) {
		return this.entities.add(new Entity(members, name));
	}
	
	
	public String toString() {
		String to_return = "";
		
		to_return += "Includes\n";
		for (Include temp : this.includes)
		{
			to_return += (temp.toString() + "\n");
		}
		to_return += "Relations\n";
		for (Relation temp : this.relations)
		{
			to_return += (temp.toString() + "\n");
		}
		to_return += "Entity\n";
		for (Entity temp : this.entities)
		{
			to_return += (temp.toString() + "\n");
		}
		return to_return;
	}
}
