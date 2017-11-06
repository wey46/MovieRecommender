import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FourthRatings {
    public double getAverageByID(String Mvid, int minRt){
		double totalRatings = 0.0;
		int totalRaters = 0;
		for (Rater rtr : RaterDatabase.getRaters()){
			if(rtr.hasRating(Mvid)){
				totalRaters ++;
				totalRatings += rtr.getRating(Mvid);
			}
		}
		if (totalRaters<minRt){
			//System.out.println("no enough ratings: " + totalRaters);
			return 0.0; //return 0.0 for not enough ratings
		} else {
			return totalRatings/totalRaters; //else return average
		}
    }
    
    private double getAverageByIDSelect(String Mvid,int minRt, List<Rating> list){
		double totalRatings = 0.0;
		int totalRaters = 0;
		for (int i=0;i<list.size();i++){
			Rater rtr = RaterDatabase.getRater(list.get(i).getItem());
			if(rtr.hasRating(Mvid)){
				totalRaters ++;
				totalRatings += rtr.getRating(Mvid)*list.get(i).getValue();
			}
		}
		if (totalRaters<minRt){
			//System.out.println("no enough ratings: " + totalRaters);
			return 0.0; //return 0.0 for not enough ratings
		} else {
			return totalRatings/totalRaters; //else return average
		}
		
    }

    public ArrayList<Rating> getAverageRatings(int minRtr){
	    ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> ans = new ArrayList<Rating>();
	    for (String movieid : movies){
    	    double avgrating = getAverageByID(movieid,minRtr);
    	    if(avgrating != 0.0){
    	    	Rating newRt = new Rating(movieid,avgrating);
    		    ans.add(newRt);
    	    }
        }
	    return ans;
    }
    
    /*private ArrayList<Rating> getAverageRatingsSelect(int minRt, ArrayList<Rater> list){
	    ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> ans = new ArrayList<Rating>();
	    for (String movieid : movies){
    	    double avgrating = getAverageByIDSelect(movieid, minRt, list);
    	    if(avgrating != 0.0){
    	    	Rating newRt = new Rating(movieid,avgrating);
    		    ans.add(newRt);
    	    }
        }
	    return ans;
    }*/

    public ArrayList<Rating> getAverageRatingsByFilter(int minRtr, Filter criteria){
	    ArrayList<String> movies = MovieDatabase.filterBy(criteria);
	    ArrayList<Rating> ans = new ArrayList<Rating>();
	    for (String movieid : movies){
    	    double avgrating = getAverageByID(movieid,minRtr);
    	    if(avgrating != 0.0){
    		    Rating newRt = new Rating(movieid,avgrating);
    		    ans.add(newRt);
    	    }
        }
	    return ans;
    }
    
    private double dotProduct(Rater me, Rater r){
    	double ans = 0;
    	for(String movieid : me.getItemsRated()){
    		if(r.hasRating(movieid)){
    			double dotp = (me.getRating(movieid)-5)*(r.getRating(movieid)-5);
    			ans += dotp;
    		}
    	}
    	return ans;
    }
    
    private ArrayList<Rating> getSimilarities(String uid){
    	Rater me = RaterDatabase.getRater(uid);
    	ArrayList<Rating> list = new ArrayList<Rating>();
    	for(Rater rtr:RaterDatabase.getRaters()){
    		if(me==rtr) continue;
    		else{
    			if(dotProduct(me,rtr)>0){
        			Rating rt = new Rating(rtr.getID(),dotProduct(me,rtr));
        			list.add(rt);
        		}
    		}	
    	}
    	Collections.sort(list,Collections.reverseOrder());
    	return list;
    	// each Rating object the item field is a rater’s ID, and the value field is the dot product comparison
    }
    
    public ArrayList<Rating> getSimilarRatings(String raterid, int numOfSimilar, int minRaters){
    	ArrayList<Rating> ans = new ArrayList<Rating>();
    	//ArrayList<Rating> similarRt = getSimilarities(raterid);
    	List<Rating> topSimilar = getSimilarities(raterid).subList(0, numOfSimilar);
    	//weighted average ratings using only the top numSimilarRaters with positive rating
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	for(String movieid:movies){
    		//multiply their similarity rating by the rating they gave that movie
    		double weightedRating = getAverageByIDSelect(movieid,minRaters,topSimilar);
    		Rating rt = new Rating(movieid,weightedRating);
    		ans.add(rt);
    	}
    	//This method returns an ArrayList of Ratings for movies and their calculated weighted ratings, in sorted order.
    	Collections.sort(ans,Collections.reverseOrder());
    	return ans;
    }
    
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String raterid, int numOfSimilar, int minRaters,Filter criteria){
    	ArrayList<Rating> ans = new ArrayList<Rating>();
    	List<Rating> topSimilar = getSimilarities(raterid).subList(0, numOfSimilar);
    	ArrayList<String> movies = MovieDatabase.filterBy(criteria);
    	for(String movieid:movies){
    		double weightedRating = getAverageByIDSelect(movieid,minRaters,topSimilar);
    		Rating rt = new Rating(movieid,weightedRating);
    		ans.add(rt);
    	}
    	Collections.sort(ans,Collections.reverseOrder());
    	return ans;
    }
    
    
}
