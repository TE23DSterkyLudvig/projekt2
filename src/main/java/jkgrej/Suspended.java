package jkgrej;

//Ludvig Sterky
// This class handles the suspended user objects. It takes the basic stuff from 
// AllUsers (id) and adds the specifics like the customer ID of the suspended user. 
// As well as this, the class also contains equals and hashCode methods for 
// comparing different suspension records, as well as Setters/getters to change 
// or get variables from the suspended objects.

public class Suspended extends AllUsers {

    private String customer_id;

    public Suspended(String id, String customer_id)
    {
        super(id);
        this.customer_id = customer_id;
    }


    public Suspended()
    {

    }

    


    


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
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
        Suspended other = (Suspended) obj;
        if (customer_id == null) {
            if (other.customer_id != null)
                return false;
        } else if (!customer_id.equals(other.customer_id))
            return false;
        return true;
    }


    public String getCustomer_id() {
        return customer_id;
    }


    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }




    
    
}
