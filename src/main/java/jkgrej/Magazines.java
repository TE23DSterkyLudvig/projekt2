package jkgrej;

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
