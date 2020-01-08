package objects.iuml;

import java.util.ArrayList;

public class Entity {
	private ArrayList<Member> 	members 	= null;
	private String 				name 		= null;
	
	public Entity(ArrayList<Member> members, String name) {
		this.members = members;
		this.name = name;
	}

	public ArrayList<Member> getMembers() {
		return this.members;
	}

	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		String toReturn = this.name + "\n";
		if (this.members != null) {
			for (Member temp : this.members) {
				toReturn += temp.toString() + "\n";
			}
		}
		return toReturn;
	}
}