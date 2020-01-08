package workers.input;
import java.io.*;
import java.util.*;
import objects.iuml.*;

/**
 * @author David Hörnmark
 *
 */
public class Reader extends Thread {
	
	private String fileName = null;
	private objects.iuml.File umlFile = null;
	
	private String includePattern = "^![iI]+nclude .*";
	private String classPattern = "^[cC]+lass \\w+ \\{";
	private String relationPattern = "^[\"]*.*[\"]* [\\*\\-<|o]+-[\\*\\-o|>]+ [\"]*.*[\"]*";
	
	public Reader(String fileName) throws FileNotFoundException {
		this.fileName = fileName;
		this.umlFile = new objects.iuml.File();
	}
	private void handleInclude(String line) {
		String path = line.split(" ")[1];
		String temp[] = path.split("/");
		String name = temp[temp.length - 1].split("\\.")[0];
		this.umlFile.appendIncludes(name, path);
	}
	private void handleClass(String line, Scanner scan) {
		ArrayList<Member> members = new ArrayList<Member>();
		String[] temp  = line.split(" ");
		String entityName = "";
		for(String name : temp) {
			if (!name.equals("class") && !name.equals("Class") && !name.equals("{")) {
				entityName+=name;
			}
		}
		line = scan.nextLine();
		while (line.charAt(0) != '}')	{
			String[] memberSplitStr = null;
			memberSplitStr = line.split("  "); // IF TAB = 2 SPACES
			if (memberSplitStr.length <= 1) {
				memberSplitStr = line.split("    "); // IF TAB = 4 SPACES
			}
			
			String member = memberSplitStr[memberSplitStr.length - 1];			
			char visibility = member.charAt(0);
			Member mem = null;
			if (line.charAt(line.length()-1) == ')') {    				
				String memberName = member.split("\\(")[0];
				memberName = memberName.substring(1, memberName.length());
				String[] nameSplitStr = memberName.split(" ");
				memberName = nameSplitStr[nameSplitStr.length -1];
				
				String memberType = "";
				for (int i = 0; i < nameSplitStr.length - 1; i++) {
					memberType += nameSplitStr[i];
					if (i < (nameSplitStr.length - 2)) {
						memberType += " ";
					}							
				}
				
				String parametersSplitStr = member.split("\\(")[1];
				parametersSplitStr = parametersSplitStr.substring(0, parametersSplitStr.length() - 1);
				String[] memberParameters = parametersSplitStr.split(", ");
			
				mem = new Member(visibility, Member.MemberType.METHOD, memberType, memberName, memberParameters);
				members.add(mem);
			} else {
				String[] nameSplitStr = member.split(" ");
				String memberName = nameSplitStr[nameSplitStr.length - 1];
				String memberType = nameSplitStr[0];
				memberType = memberType.substring(1, memberType.length());
				mem = new Member(visibility, Member.MemberType.FIELD, memberType, memberName);
				members.add(mem);	
			}
			line = scan.nextLine();
		}
		if (line.charAt(0) == '}') {
			this.umlFile.appendEntities(members, entityName);
		}
	}
	private void handleRelation(String line) {
		String[] parties = line.split(" [\\*\\-<|o]+-[\\*\\-o|>]+ ");
		for (int i = 0; i < parties.length; i++) 
		{
			if (parties[i].matches("\".*\"")) 
			{
				parties[i] = parties[i].split("\"")[1];	    				
			}
		}
		String[] temp = line.split(" ");
		String relation_token = "";
		for (String token : temp) {
			if ((token.charAt(0) == '-' && (token.charAt(2) == 'o' || token.charAt(2) == '*')) ||
				(token.charAt(0) == 'o' || token.charAt(0) == '*') && token.charAt(2) == '-') {
				relation_token = token;
			}
		}
		this.umlFile.appendRelations(parties, relation_token);
	}
	private objects.iuml.File readFile(objects.iuml.File umlFile, Scanner scan) {
	    String line = null;
	    while (scan.hasNextLine()) {
	    	line = scan.nextLine();
	    	if (line.matches(includePattern)) {
	    		this.handleInclude(line);
	    	} else if (line.matches(classPattern)) {
	    		this.handleClass(line, scan);
	    	} else if (line.matches(relationPattern)) {
	    		this.handleRelation(line);
	    	}
	    }
	    return umlFile;
	}
	
	public void run() {
		try {
			this.umlFile = this.readFile(umlFile, new Scanner(new java.io.File(this.fileName)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public objects.iuml.File getUMLFile() {
		return this.umlFile;
	}
	
	public String toString() {
		return this.fileName + "\n" + this.umlFile.toString();
	}
}
