
public class GenreFilter implements Filter {
    private String genre;
    public GenreFilter(String s){
    	genre = s;
    }
    
    @Override
    public boolean satisfies(String id){
    	return MovieDatabase.getGenres(id).contains(genre);
    }
    
}
