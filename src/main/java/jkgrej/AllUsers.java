package jkgrej;

public class AllUsers 
{
    
    protected int id;

    public AllUsers(int id)
    {
        this.id = id;
    }

    public AllUsers()
    {

    }




    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        if (id != other.id)
            return false;
        return true;
    }



    public String toString()
    {
        return "";
    }

    

}
