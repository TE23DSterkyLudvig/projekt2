package jkgrej;

// This class handles the actual book objects. It takes the basic stuff from 
// Litterature (id, title, isAvailable) and adds the specifics like who wrote it, 
// the genre, and how many pages it has. As well as this, the class also contains toString, to write all elements,
// as well as Setter/getter to change or get varible from the books objects.

public class Books extends Litterature {
    
    private String author;
    private String genre;
    private int pages;

    public Books(int id, String title, boolean isAvailable, String author, String genre, int pages)
    {
        super(id, title, isAvailable);
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        
    }

    public Books(){}

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Books [author=" + author + ", genre=" + genre + ", pages=" + pages + ", id=" + id + ", title=" + title
                + ", isAvailable=" + isAvailable + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + pages;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Books other = (Books) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        if (pages != other.pages)
            return false;
        return true;
    }

    
    
}
