/**
 * 
 */
package workers.translation;

import java.util.ArrayList;

import objects.code.CPPHeaderFile;
import objects.iuml.*;

/**
 * @author david
 *
 */
public class Translator {
	private objects.iuml.File umlFile = null;
	private objects.code.CPPHeaderFile translatedFile = null;
	public Translator(objects.iuml.File umlFile) {
		this.umlFile = umlFile;
		this.translatedFile = new CPPHeaderFile(umlFile.getEntities().get(0).getName());
		
		String language = "C++";
		this.translateIncludes(language);
		this.translateMembers(language);
	}
	
	private void translateIncludes(String language) {
		switch (language) {
		case "C++":
			this.translateIncludesCPP();
			break;
		default:
			break;
		}
	}
	
	private void translateMembers(String language) {
		switch (language) {
		case "C++":
			this.translateMembersCPP();
			break;
		default:
			break;
		}
	}
	
	private void translateIncludesCPP() {
		ArrayList<String> translatedincludes = new ArrayList<String>();
		for (Include item : this.umlFile.getIncludes()) {
			String includeString = "#include \""; 
			includeString += item.getPath();
			includeString += "\"";
			translatedincludes.add(includeString);
		}
		this.translatedFile.setIncludes(translatedincludes);
	}
	
	private void translateMembersCPP() {
		ArrayList<String> publicFields = new ArrayList<String>();
		ArrayList<String> privateFields = new ArrayList<String>();
		ArrayList<String> protectedFields = new ArrayList<String>();
		
		ArrayList<String> publicMethods = new ArrayList<String>();
		ArrayList<String> privateMethods = new ArrayList<String>();
		ArrayList<String> protectedMethods = new ArrayList<String>();
		
		for (Entity ent : this.umlFile.getEntities()) {
			for (Member item : ent.getMembers()) {
				String memberString = "";
				switch (item.getMemberType()) {
				case FIELD:
					memberString += item.getType();
					memberString += " ";
					memberString += item.getName();
					memberString += ";";
					switch (item.getVisibility()) {
					case PUBLIC:
						publicFields.add(memberString);
						break;
					case PRIVATE:
						privateFields.add(memberString);
						break;
					case PROTECTED:
						protectedFields.add(memberString);
						break;
					default:
						break;
					}	
					break;
				case METHOD:
					memberString += item.getType();
					memberString += " ";
					memberString += item.getName();
					memberString += "(";
					if (item.getParameters() != null) {
						int counter = 0;
						for (Parameter param : item.getParameters()) {
							memberString += param.getType();
							memberString += " ";
							memberString += item.getName();
							if (counter < item.getParameters().length - 1) {
								memberString += ", ";								
							}
							counter++;
						}						
					}
					memberString += ");";
					
					switch (item.getVisibility()) {
					case PUBLIC:
						publicMethods.add(memberString);
						break;
					case PRIVATE:
						privateMethods.add(memberString);
						break;
					case PROTECTED:
						protectedMethods.add(memberString);
						break;
					default:
						break;
					}	
					break;
				default:
					break;
				}
			}
		}
		this.translatedFile.setPrivateFields(privateFields);
		this.translatedFile.setPrivateMethods(privateMethods);
		
		this.translatedFile.setProtectedFields(protectedFields);
		this.translatedFile.setProtectedMethods(protectedMethods);
		
		this.translatedFile.setPublicFields(publicFields);
		this.translatedFile.setPublicMethods(publicMethods);
	}
	public String toString() {
		//return this.umlFile.toString();
		return this.translatedFile.toString();
	}
}
