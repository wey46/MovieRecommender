import java.util.Comparator;

public class compareRater implements Comparator<Rater> {
    public int compare(Rater a, Rater b){
		if(a.numRatings()>b.numRatings()) return 1;
		else if(a.numRatings()<b.numRatings()) return -1;
		else return 0;	
    }
}
