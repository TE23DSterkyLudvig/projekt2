package jkgrej;

public class Suspended extends AllUsers {

    private int customer_id;

    public Suspended(int id, int customer_id)
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
        int result = 1;
        result = prime * result + customer_id;
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
        Suspended other = (Suspended) obj;
        if (customer_id != other.customer_id)
            return false;
        return true;
    }


    public int getCustomer_id() {
        return customer_id;
    }


    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }




    
    
}
