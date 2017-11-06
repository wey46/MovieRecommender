import java.util.ArrayList;
//import java.util.Collections;


public class MovieRunnerSimilarRatings {

	public static void main(String[] args) {
		printAverageRatingsByFilter();
		//printAverageRatings();//with similarity select
	}
	public static void printAverageRatings(){
    	//SecondRatings sr = new SecondRatings();
    	//ThirdRatings tr = new ThirdRatings("ratings.csv");
    	MovieDatabase.initialize("ratedmoviesfull.csv");
    	RaterDatabase.initialize("ratings.csv");
    	FourthRatings fr = new FourthRatings();
    	System.out.println("Movies: " + MovieDatabase.size() + "\t" + "Ratings: " + RaterDatabase.size());
        ArrayList<Rating> list = fr.getSimilarRatings("337", 10, 3);
        //Collections.sort(list,Collections.reverseOrder());
        //Collections.sort(list);
        for(int i=0;i<20;i++){
        	Rating r = list.get(i);
        	System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("Found "+list.size()+" movies");
    }
	
	public static void printAverageRatingsByFilter(){
		RaterDatabase.initialize("ratings.csv");
    	MovieDatabase.initialize("ratedmoviesfull.csv");
    	FourthRatings fr = new FourthRatings();
    	System.out.println("Movies: " + MovieDatabase.size() + "\t" + "Ratings: " + RaterDatabase.size());
        //ArrayList<Rating> list = tr.getAverageRatingsByFilter(1, new YearAfterFilter(2000));
        //ArrayList<Rating> list = tr.getAverageRatingsByFilter(1, new GenreFilter("Crime"));
        //ArrayList<Rating> list = tr.getAverageRatingsByFilter(1, new MinutesFilter(110,170));
        //ArrayList<Rating> list = tr.getAverageRatingsByFilter(1, new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));
    	AllFilters comb = new AllFilters();
    	comb.addFilter(new YearAfterFilter(1975));
    	//comb.addFilter(new GenreFilter("Drama"));
    	comb.addFilter(new MinutesFilter(70,200));
    	//comb.addFilter(new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
    	ArrayList<Rating> list = fr.getSimilarRatingsByFilter("314", 10, 5, comb);
        //Collections.sort(list,Collections.reverseOrder());
        //Collections.sort(list);
        /*for(Rating r:list){
        	//System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
        	//System.out.println("\t"+MovieDatabase.getGenres(r.getItem()));
        	System.out.println(r.getValue()+"\t"+MovieDatabase.getTitle(r.getItem())+"\t"+MovieDatabase.getMovie(r.getItem()));
        	System.out.println("\t"+MovieDatabase.getDirector(r.getItem())+"\t"+MovieDatabase.getMinutes(r.getItem()));
        }*/
        for(int i=0;i<20;i++){
        	Rating r = list.get(i);
        	System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("Found "+list.size()+" movies");
	}

}
