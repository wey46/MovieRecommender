
public class MinutesFilter implements Filter {
    private int minm,maxm;
    public MinutesFilter(int min,int max){
    	minm = min;
    	maxm = max;
    }
    @Override
    public boolean satisfies(String id){
    	int minutes = MovieDatabase.getMinutes(id);
    	return minutes>=minm && minutes<=maxm;
    }
    
}
