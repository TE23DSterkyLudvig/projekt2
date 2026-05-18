package jkgrej;

// This class serves as the base class for all user types in the system. 
// It holds the basic stuff that every user shares (the id) so that subclasses 
// like Users or Suspended can inherit it and add their own specific details. 
// As well as this, the class also contains a toString method, equals and hashCode 
// for comparing different user objects, as well as Setters/getters to change 
// or get variables from the user objects.

public class AllUsers 
{
    
    protected String id;

    public AllUsers(String id)
    {
        this.id = id;
    }

    public AllUsers()
    {

    }




    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }





    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        AllUsers other = (AllUsers) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public String toString()
    {
        return "";
    }

    

}
