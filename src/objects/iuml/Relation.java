/**
 * 
 */
package objects.iuml;

public class Relation {
	public enum RelationWay {
		ONEWAYLEFT,
		ONEWAYRIGHT,
		TWOWAY
	}
	public enum RelationType {
		AGGREGATION,
		COMPOSITION
	}
	
	private String[] parties = null;
	private RelationWay way = null;
	private RelationType type = null;

	public Relation(String[] parties, String relation_token) {
		this.parties = parties;
		switch (relation_token.charAt(0)) {
		case '*':
			this.type = RelationType.COMPOSITION;
			this.way = RelationWay.ONEWAYLEFT;
			break;
		case 'o':
			this.type = RelationType.AGGREGATION;
			this.way = RelationWay.ONEWAYLEFT;
			break;
		case '-':
			this.way = RelationWay.ONEWAYRIGHT;
			switch (relation_token.charAt(2)) {
			case '*':
				this.type = RelationType.COMPOSITION;
				break;
			case 'o':
				this.type = RelationType.AGGREGATION;
				break;
			}
			break;
		}
		
	}
	
	public Relation(String[] parties, RelationWay way, RelationType type) {
		this.parties = parties;
		this.way = way;
		this.type = type;
	}
	
	public void setParties(String[] new_parties) {
		this.parties = new_parties;
	}
	public String[] getParties() {
		return this.parties;
	}
	
	public void setWay(RelationWay new_way) {
		this.way = new_way;
	}
	public RelationWay getWay() {
		return this.way;
	}
	public String getWayAsString() {
	    String to_return = null;
		switch(this.way) {
	      case ONEWAYLEFT:
	        to_return = "One-way Left";
	        break;
	      case ONEWAYRIGHT:
	    	  to_return = "One-way Right";
	        break;
	      case TWOWAY:
	    	  to_return = "Two-way";
	        break;
	    }
		return to_return;
	}
	
	public void setType(RelationType new_type) {
		this.type = new_type;
	}
	public RelationType getType() {
		return this.type;
	}
	public String getTypeAsString() {
	    String to_return = null;
		switch(this.type) {
	      case AGGREGATION:
	        to_return = "Aggregation";
	        break;
	      case COMPOSITION:
	    	  to_return = "Composition";
	        break;
	    }
		return to_return;
	}
	
	public String toString() {
		return 	
				parties[0] + ", " + 
				this.parties[1] + ", " + 
				this.getWayAsString() + ", " + 
				this.getTypeAsString();
	}
}