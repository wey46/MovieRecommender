
public class DirectorsFilter implements Filter {
	private String directors;
    public DirectorsFilter(String s){
    	directors = s;
    }
    @Override
    public boolean satisfies(String id){
    	String[] dir = directors.split(",");
    	String s = MovieDatabase.getDirector(id);
    	for(int i=0;i<dir.length;i++){
    		if(s.contains(dir[i])) return true;
    	}
    	return false;
    }
}
