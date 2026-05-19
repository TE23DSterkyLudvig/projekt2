package jkgrej;
//Ludvig Sterky
// This class handles the actual user objects. It takes the basic stuff from 
// AllUsers (id) and adds the specifics like the user's name and email address. 
// As well as this, the class also implements the Comparable interface to sort users 
// alphabetically by name, contains a toString method to write all elements, 
// as well as Setters/getters to change or get variables from the user objects.

public class Users extends AllUsers implements Comparable<Users>
{
    
    private String name;
    private String email;

    public Users(String id, String name, String email)
    {
        super(id);
        this.name = name;
        this.email = email;
    }

    public Users()
    {

    }



    @Override
    public int compareTo(Users other) {
        return this.name.compareTo(other.name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users [id=" + id + ", name=" + name + ", email=" + email + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
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
        Users other = (Users) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }


    


    
    
}
