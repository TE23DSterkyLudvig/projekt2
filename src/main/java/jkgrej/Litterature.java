package jkgrej;

//This is the abstract parent class named Litterature, which has two extending subclasses, Books and Magazines,
// which implement the methods and variables present in Litterature. This class also contains toString,
// which makes it possible to showcase all elements as well as Setters/getters to change or get variables present in objects.
// It also contains compareTo, which makes it possible to sort lists using the collections.sort command.
// it also contains equals and hashcode, which makes hashset commands available.

import java.util.Objects;

public abstract class Litterature implements Comparable<Litterature>{
    
    protected String id;
    protected String title;
    protected boolean isAvailable;

    public Litterature(String id, String title, boolean isAvailable)
    {
        this.id= id;
        this.title = title;
        this.isAvailable = true;
    }


    public Litterature()
    {

    }


   


    //Jämför title av litterärar verk för att ordna dem i ordning senare med hjälp av collections.sort.
    @Override
    public int compareTo(Litterature other) {
        return this.title.compareTo(other.title);
    }


 /*Om jag eventuelltt kommer använda HashSet */
   

    public String getId() {
        return this.id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + (isAvailable ? 1231 : 1237);
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Litterature other = (Litterature) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (isAvailable != other.isAvailable)
            return false;
        return true;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString()
    {
        return"";
    }
}
