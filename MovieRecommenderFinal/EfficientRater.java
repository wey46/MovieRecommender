import java.util.ArrayList;
import java.util.HashMap;


public class EfficientRater implements Rater {
	private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
    	myRatings.put(item, new Rating(item,rating));
    	//item is a movieID
    }

    public boolean hasRating(String item) {
        if (myRatings.containsKey(item)) return true;
        else return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        Rating r = myRatings.get(item);
        return r.getValue();
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        return new ArrayList<String>(myRatings.keySet());
    }
}
