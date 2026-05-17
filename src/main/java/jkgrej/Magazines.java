package jkgrej;

// This class handles the actual magazine objects. It takes the variables from 
// Litterature (id, title, isAvailable) and adds the specific elementents such as the issuenumber, 
// the category, and its release year. As well as this, the class also contains toString, to write all elements,
// as well as Setter/getter to change or get varible from the books objects.

public class Magazines extends Litterature{

    private int issueNumber;
    private String category;
    private int publishedYear;

    public Magazines(int id, String title, boolean isAvailable, int issueNumber, String category, int publishedYear)
    {
        super(id, title, isAvailable);
        this.issueNumber = issueNumber;
        this.category = category;
        this.publishedYear = publishedYear;
    }

    public Magazines()
    {

    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + issueNumber;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + publishedYear;
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
        Magazines other = (Magazines) obj;
        if (issueNumber != other.issueNumber)
            return false;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (publishedYear != other.publishedYear)
            return false;
        return true;
    }

    public int getIssueNumber() {
        return this.issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPublishedYear() {
        return this.publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "Magazines [issueNumber=" + issueNumber + ", category=" + category + ", publishedYear=" + publishedYear
        + ", id=" + id + ", title=" + title + ", isAvailable=" + isAvailable + "]";
    }

    
    
}
