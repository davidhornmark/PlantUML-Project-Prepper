package objects.iuml;

public class Member {
	public enum Visibility {
		PRIVATE,
		PUBLIC,
		PROTECTED
	}
	
	public enum MemberType {
		FIELD,
		METHOD,
	}
	
	private Visibility 	visibility 	= Visibility.PRIVATE;
	private MemberType 	memberType 	= MemberType.FIELD;
	private String 		type 		= null;
	private String 		name 		= null;
	private Parameter[]	parameters	= null;
	
	public Member(Visibility visibility, MemberType memberType, String type, String name, Parameter[] parameters) {
		this.visibility = visibility;
		this.memberType = memberType;
		this.type = type;
		this.name = name;
		this.parameters = parameters;
	}
	
	public Member(char visibility, MemberType memberType, String type, String name, String[] parameters) {
		switch (visibility) {
		case '-':
			this.visibility = Visibility.PRIVATE;
			break;
		case '+':
			this.visibility = Visibility.PUBLIC;
			break;
		default:
			this.visibility = Visibility.PROTECTED;
			break;
		}
		
		this.memberType = memberType;
		this.type = type;
		this.name = name;
		
		if (!parameters[0].equals(""))
		{
			this.parameters = new Parameter[parameters.length];
			for (int k = 0; k < parameters.length; k++) {
				String[] itemSplitStr = parameters[k].split(" ");
				String itemType = "";
				for (int j = 0; j < itemSplitStr.length - 1; j++) {
					itemType += itemSplitStr[j];
					if (j < (itemSplitStr.length - 2)) {
						itemType += " ";
					}
				}
				this.parameters[k] = new Parameter(itemType, itemSplitStr[itemSplitStr.length - 1]);
			}	
		}
	}
	
	public Member(char visibility, MemberType memberType, String type, String name) {
		switch (visibility) {
		case '-':
			this.visibility = Visibility.PRIVATE;
			break;
		case '+':
			this.visibility = Visibility.PUBLIC;
			break;
		default:
			this.visibility = Visibility.PROTECTED;
			break;
		}
		
		this.memberType = memberType;
		this.type = type;
		this.name = name;
	}
	
	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public MemberType getMemberType() {
		return memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Parameter[] getParameters() {
		return parameters;
	}

	public void setParameters(Parameter[] parameters) {
		this.parameters = parameters;
	}
	
	public String toString() {
		//String toReturn = "------- toString -------\n";
		String toReturn = "";
		switch (this.visibility) {
		case PRIVATE:
			toReturn += "private";
			break;
		case PUBLIC:
			toReturn += "public";
			break;
		default:
			toReturn += "protected";
			break;
		}
		toReturn += " ";
		toReturn += this.type;
		toReturn += " ";
		toReturn += this.name;
		if (this.memberType == MemberType.METHOD) {
			toReturn += "(";
			if (this.parameters != null) {
				for (int i = 0; i < this.parameters.length; i++) {
					toReturn += this.parameters[i].toString();
					if (i < (this.parameters.length - 1)) {
						toReturn += ", ";
					}	
				}
			}
			toReturn += ")";
		}
		//toReturn += "\n------------------------";
		
		return toReturn;
	}
	
}
