package objects.code;

import java.util.ArrayList;

public class CPPHeaderFile {
	private String className = null;
	private ArrayList<String> includes = null;
	
	ArrayList<String> ifndef = null;
	
	ArrayList<String> publicFields = null;
	ArrayList<String> privateFields = null;
	ArrayList<String> protectedFields = null;
	
	ArrayList<String> publicMethods = null;
	ArrayList<String> privateMethods = null;
	ArrayList<String> protectedMethods = null;
	
	public CPPHeaderFile(String className) {
		this.className = className;
		this.ifndef = new ArrayList<String>();
		
		this.ifndef.add("#ifndef " + className.toUpperCase() + "_H");
		this.ifndef.add("#define " + className.toUpperCase() + "_H");
		this.ifndef.add("#endif // !" + className.toUpperCase() + "_H");

		this.includes = new ArrayList<String>();
		
		this.publicFields = new ArrayList<String>();
		this.privateFields = new ArrayList<String>();
		this.protectedFields = new ArrayList<String>();
		
		this.publicMethods = new ArrayList<String>();
		this.privateMethods = new ArrayList<String>();
		this.protectedMethods = new ArrayList<String>();
	}

	
	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public ArrayList<String> getIncludes() {
		return includes;
	}


	public void setIncludes(ArrayList<String> includes) {
		this.includes = includes;
	}


	public ArrayList<String> getPublicFields() {
		return publicFields;
	}


	public void setPublicFields(ArrayList<String> publicFields) {
		this.publicFields = publicFields;
	}


	public ArrayList<String> getPrivateFields() {
		return privateFields;
	}


	public void setPrivateFields(ArrayList<String> privateFields) {
		this.privateFields = privateFields;
	}


	public ArrayList<String> getProtectedFields() {
		return protectedFields;
	}


	public void setProtectedFields(ArrayList<String> protectedFields) {
		this.protectedFields = protectedFields;
	}


	public ArrayList<String> getPublicMethods() {
		return publicMethods;
	}


	public void setPublicMethods(ArrayList<String> publicMethods) {
		this.publicMethods = publicMethods;
	}


	public ArrayList<String> getPrivateMethods() {
		return privateMethods;
	}


	public void setPrivateMethods(ArrayList<String> privateMethods) {
		this.privateMethods = privateMethods;
	}


	public ArrayList<String> getProtectedMethods() {
		return protectedMethods;
	}


	public void setProtectedMethods(ArrayList<String> protectedMethods) {
		this.protectedMethods = protectedMethods;
	}


	public String toString() {
		int counter = 0;
		String toReturn = "";
		toReturn += this.ifndef.get(counter++) + "\n";
		toReturn += this.ifndef.get(counter++) + "\n";
		for (String item : this.includes) {
			toReturn += item + "\n";
		}
		toReturn += "class " + this.className + "\n{\n";
		
		toReturn += "private:\n";
		for (String item : this.privateFields) {
			toReturn += "\t" + item + "\n";
		}
		if (this.privateFields.size() > 0) {
			toReturn += "\n";			
		}
		for (String item : this.privateMethods) {
			toReturn += "\t" + item + "\n";
		}
		
		toReturn += "protected:\n";
		for (String item : this.protectedFields) {
			toReturn += "\t" + item + "\n";
		}
		if (this.protectedFields.size() > 0) {
			toReturn += "\n";			
		}
		for (String item : this.protectedMethods) {
			toReturn += "\t" + item + "\n";
		}
		
		toReturn += "public:\n";
		for (String item : this.publicFields) {
			toReturn += "\t" + item + "\n";
		}
		if (this.publicFields.size() > 0) {
			toReturn += "\n";			
		}
		for (String item : this.publicMethods) {
			toReturn += "\t" + item + "\n";
		}
		toReturn += "}\n";
		toReturn += this.ifndef.get(counter++);
		return toReturn;
	}
}
