package jkgrej;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.HashSet;
import java.util.List;
import java.util.Collections;

//Ludvig Sterky

// This class handles all the main operations and server communication for the system.
// It keeps track of the lists for books, magazines, users, and suspensions, working as
// the bridge between the console and the database. As well as this, the class contains
// methods to fetch data from the server, save copies to local JSON files, add new
// items, and remove records by their IDs. It also includes helper methods for sorting
// the lists, searching for specific items, and validating user input so the program
// doesn't crash on bad inputs.


public class Functions 
{
    private ArrayList<Books> books;
    private ArrayList<Magazines> magazines;
    private ArrayList<Users> users;
    private ArrayList<Suspended> suspended_users;

    private String baseUrl;
    private Gson gson;
    private Scanner tb;

    public Functions( ArrayList<Books> books, ArrayList<Magazines> magazines, ArrayList<Users> users,  ArrayList<Suspended> suspended_users, String baseUrl, Gson gson, Scanner tb)
    {
        this.books = books;
        this.magazines = magazines;
        this.users = users;
        this.suspended_users = suspended_users;
        this.gson = gson;
        this.tb = tb;
        this.baseUrl = baseUrl;
    }

    // readOne only gets on object and readAll gets all objects as a list.
    public void readAllBooks(Type bookType)

    {   
                HttpResponse<String> response;
                try
                {
                    response = Unirest.get(baseUrl+ "books").asString();
                }
                catch(UnirestException e)
                {
                    System.out.println("Error when reading"+ e.getLocalizedMessage());
                    return;
                }

                int status_books = response.getStatus();
                if( status_books != 200)
                {
                    System.out.println("Statuscode: " + status_books + "serverissue");
                }


                String get_bookBody = response.getBody();

                try
                {
                    Files.writeString(Paths.get("books.json"), get_bookBody);
                    System.out.println("Data saved to books.json");
                }
                catch(IOException e)
                {
                    System.out.println("Fileissue: " + e.getMessage());
                    

                
                }
                this.books = gson.fromJson(get_bookBody, bookType);
                System.out.println("booklist created");
}

    public Books readOneBook (int id)
    {
        HttpResponse<String> response = null;
        try
        {
            response = Unirest.get(baseUrl + "books/" + id).asString();
        } 
        catch (UnirestException e) 
        {
            System.out.println("error in reading in data" + e.getLocalizedMessage());
            System.exit(0);
        }
        
        int status = response.getStatus();
        if(status != 200)
        {
            System.out.println("error from server: status:" + status);
            System.exit(0);
        }

        String body = response.getBody();

        Books book = gson.fromJson(body, Books.class);
        return book;
        
    }
    


    public void readAllMagazines(Type magazineType)
    {
        HttpResponse<String> response = null;
        try
        {
            response = Unirest.get(baseUrl + "magazines").asString();
        }
        catch(UnirestException e)
        {
            System.out.println("Error when reading: " + e.getLocalizedMessage());
            return;
        }

        int status_magazines = response.getStatus();
        if(status_magazines != 200)
        {
            System.out.println("Statuscode: " + status_magazines + " serverissue");
        }

        String get_magazineBody = response.getBody();

        try
        {
            Files.writeString(Paths.get("magazines.json"), get_magazineBody);
            System.out.println("Data saved to magazines.json");
        }
        catch(IOException e)
        {
            System.out.println("Fileissue: " + e.getMessage());
        }

        // Flyttad utanför catch-blocket så att den faktiskt körs
        this.magazines = gson.fromJson(get_magazineBody, magazineType);
        System.out.println("magazinelist created");
    }

    
    public Magazines readOneMagazine( int id)
    {   
        HttpResponse<String> response = null;
        try 
        {
            response = Unirest.get(baseUrl + "magazines/" + id).asString();
        } 
        catch (UnirestException e) 
        {
            System.out.println("error in reading in data: " + e.getLocalizedMessage());
            System.exit(0);
        }
        
        int status = response.getStatus();
        if(status != 200)
        {
            System.out.println("error from server: status: " + status);
            System.exit(0);
        }

        String body = response.getBody();

        Magazines magazine = gson.fromJson(body, Magazines.class);
        return magazine;
    }


    public void readAllUsers(Type userType)
    {
        HttpResponse<String> response= null;
        try
        {
            response = Unirest.get(baseUrl + "users").asString();
        }
        catch(UnirestException e)
        {
            System.out.println("Error when reading: " + e.getLocalizedMessage());
            return;
        }

        int status_users = response.getStatus();
        if(status_users != 200)
        {
            System.out.println("Statuscode: " + status_users + " serverissue");
        }

        String get_userBody = response.getBody();

        try
        {
            Files.writeString(Paths.get("users.json"), get_userBody);
            System.out.println("Data saved to users.json");
        }
        catch(IOException e)
        {
            System.out.println("Fileissue: " + e.getMessage());
        }

        this.users = gson.fromJson(get_userBody, userType);
        System.out.println("userlist created");
    }


    public Users readOneUser( int id)
    {
        HttpResponse<String> response = null;
        try 
        {
            response = Unirest.get(baseUrl + "users/" + id).asString();
        } 
        catch (UnirestException e) 
        {
            System.out.println("error in reading in data: " + e.getLocalizedMessage());
            System.exit(0);
        }
        
        int status = response.getStatus();
        if(status != 200)
        {
            System.out.println("error from server: status: " + status);
            System.exit(0);
        }

        String body = response.getBody();

        Users user = gson.fromJson(body, Users.class);
        return user;
    }


    public void readAllSuspended(Type suspendedType)
    {
        HttpResponse<String> response = null;
        try
        {
            response = Unirest.get(baseUrl + "suspended").asString();
        }
        catch(UnirestException e)
        {
            System.out.println("Error when reading: " + e.getLocalizedMessage());
            return;
        }

        int status_suspended = response.getStatus();
        if(status_suspended != 200)
        {
            System.out.println("Statuscode: " + status_suspended + " serverissue");
        }

        String get_suspendedBody = response.getBody();

        try
        {
            Files.writeString(Paths.get("suspended.json"), get_suspendedBody);
            System.out.println("Data saved to suspended.json");
        }
        catch(IOException e)
        {
            System.out.println("Fileissue: " + e.getMessage());
        }

        this.suspended_users = gson.fromJson(get_suspendedBody, suspendedType);
        System.out.println("suspended list created");
    }


    public Suspended readOneSuspended( int id)
    {
        HttpResponse<String> response = null;
        try 
        {
            response = Unirest.get(baseUrl + "suspended/" + id).asString();
        } 
        catch (UnirestException e) 
        {
            System.out.println("error in reading in data: " + e.getLocalizedMessage());
            System.exit(0);
        }
        
        int status = response.getStatus();
        if(status != 200)
        {
            System.out.println("error from server: status: " + status);
            System.exit(0);
        }

        String body = response.getBody();

        Suspended suspended = gson.fromJson(body, Suspended.class);
        return suspended;
    }

    //adds new instances to the server
    public void addBook(String title, boolean isAvailable, String author,String genre,int pages, String inputString)
    {
                
                System.out.println("You chose to add a book!");

                System.out.println("Define pages from 100 to 800");
                pages = chooseInt(pages, 800 , 100);
                System.out.println("Define author");
                author = chooseString(author);
                System.out.println("Define genre");
                genre = chooseString(genre);
                System.out.println("Define title");
                title = chooseString(title);
                isAvailable = chooseBoolean(null, inputString);

                

               Books newBook = new Books("", title, isAvailable, author, genre, pages);
               books.add(newBook);

               //För att se till såa tt det kommer dubletter
               HashSet<Books> bookSet = new HashSet<>(books);

                this.books = new ArrayList<>(bookSet);

                System.out.println("added new book object.");

                String stringJsonBook  = gson.toJson(books);
        

                
                try 
                {  
                    Files.writeString(Paths.get("books.json" ), stringJsonBook);
                } 
                catch (IOException e) 
                {
                    System.out.println("Error reading in: " + e.getLocalizedMessage());
                }

                
                HttpResponse<String> postBookResponse;

                String jsonSingelObject = gson.toJson(newBook);

                try
                {
                    postBookResponse = Unirest.post(baseUrl +"books")
                        .header("Content-Type", "application/json")
                        .body(jsonSingelObject)
                        .asString();
                }
                catch(UnirestException e)
                {
                    System.out.println("Error in connection:" + e.getLocalizedMessage());
                    return;
                }

                int bookStatus = postBookResponse.getStatus();
                if(bookStatus != 200 && bookStatus != 201)
                {
                    System.out.println("Errorcode: " +  bookStatus);
                }
                System.out.println("Created book");
    }
    

    public void addMagazine(String title, boolean isAvailable,int issueNumber, String category, int publishedYear, String inputString)
    {
                System.out.println("Your chose to add a magazine.");

                System.out.println("Define title");
                title = chooseString(title);
                
                isAvailable = chooseBoolean(null, inputString);
                System.out.println("Define issuenumber from 1 to 1723");
                issueNumber = chooseInt(issueNumber,1723,1);
                System.out.println("Define category");
                category = chooseString(category);
                System.out.println("Define publishedyear from 1923 to 2026");
                publishedYear = chooseInt(publishedYear, 2026, 1923);


                Magazines newMagazine = new Magazines("", title, isAvailable, issueNumber, category, publishedYear);
                magazines.add(newMagazine);
                //no duplications
               HashSet<Magazines> magazineSet = new HashSet<>(magazines);

               this.magazines = new ArrayList<>(magazineSet);

                System.out.println("added magazine object.");

                String stringJsonMagazine = gson.toJson(magazines);

                try 
                {  
                    Files.writeString(Paths.get("magazine.json" ), stringJsonMagazine);
                } 
                catch (IOException e) 
                {
                    System.out.println("Error reading in: " + e.getMessage());
                }


                HttpResponse<String> postMagaziResponse;

                String singelMagazineObject = gson.toJson(newMagazine);

                try
                {
                    postMagaziResponse = Unirest.post(baseUrl)
                        .header("Content-Type", "application/json")
                        .body(singelMagazineObject)
                        .asString();
                }
                catch(UnirestException e)
                {
                    System.out.println("Error in connection:" + e.getLocalizedMessage());
                    return;
                }

                int magazineStatus = postMagaziResponse.getStatus();
                if(magazineStatus != 200 && magazineStatus != 201)
                {
                    System.out.println("Errorcode: " +  magazineStatus);
                }
                System.out.println("Created magazine");

    }


    public void addUser(String name, String email, HttpResponse<String> response) {
    System.out.println("You chose to add a user!");

    System.out.println("Define name");
    name = chooseString(name);
    
    System.out.println("Define email");
    email = chooseString(email);

    
    Users newUser = new Users("", name, email);
    users.add(newUser);
    
    //no duplications
    HashSet<Users> userSet = new HashSet<>(users);

    this.users = new ArrayList<>(userSet);

    System.out.println("Added new user object locally.");

    
    String stringJsonUsers = gson.toJson(users);
    try {  
        Files.writeString(Paths.get("users.json"), stringJsonUsers);
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getLocalizedMessage());
    }

    String jsonSingleUser = gson.toJson(newUser);
    try {
        response = Unirest.post(baseUrl + "users")
            .header("Content-Type", "application/json")
            .body(jsonSingleUser)
            .asString();
    } catch(UnirestException e) {
        System.out.println("Error in connection: " + e.getLocalizedMessage());
        return;
    }

    int userStatus = response.getStatus();
    if(userStatus != 200 && userStatus != 201) {
        System.out.println("Errorcode: " + userStatus);
    } else {
        System.out.println("Created user on server");
    }

}


    public void addSuspended(String customerId, HttpResponse<String> response) {
    System.out.println("You chose to suspend a customer!");

    System.out.println("Define customer ID to suspend from 1 to 999999");
    customerId = chooseString(customerId);

  
    Suspended newSuspended = new Suspended("",customerId);
    suspended_users.add(newSuspended);

    //no duplications
    HashSet<Suspended> suspendSet = new HashSet<>(suspended_users);

    this.suspended_users = new ArrayList<>(suspendSet);

    System.out.println("Added suspension locally.");

    // 2. Spara hela listan lokalt till suspended.json
    String stringJsonSuspended = gson.toJson(suspended_users);
    try {  
        Files.writeString(Paths.get("suspended.json"), stringJsonSuspended);
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getLocalizedMessage());
    }

    // 3. Gör om ENBART den nya avstängningen till JSON för servern
    String jsonSingleSuspended = gson.toJson(newSuspended);

    try {
        response = Unirest.post(baseUrl + "suspended") 
            .header("Content-Type", "application/json")
            .body(jsonSingleSuspended)
            .asString();
    } catch(UnirestException e) {
        System.out.println("Error in connection: " + e.getLocalizedMessage());
        return;
    }

    // 4. Kontrollera statuskod
    int suspendedStatus = response.getStatus();
    if(suspendedStatus != 200 && suspendedStatus != 201) {
        System.out.println("Errorcode: " + suspendedStatus);
    } else {
        System.out.println("Customer suspended on server");
    }
}

    //finds objects  by certain parameters
    public Users findUsers(String email)
    {
        for (Users user : users) 
        {
            if(user.getEmail().trim().equalsIgnoreCase(email) )
            {
                System.out.println(user.getId()+ " id found");
                
                return user;
            }   
        }
        System.out.println("No user with the email found");
        return null;
    }


    public Books findBooks(String title)
    {
        for (Books book: books) 
        {
            if(book.getTitle().trim().equalsIgnoreCase(title))
            {
                System.out.println("Found matching book with the title");
                return book;
            }
        }
        System.out.println("No matching book found");
        return null;
    }


    public Magazines findMagazines(String title)
    {
        for (Magazines magazine: magazines) 
        {
            if(magazine.getTitle().trim().equalsIgnoreCase(title))
            {
                System.out.println("Found matching magazine with the title");
                return magazine;
            }
        }
        System.out.println("No matching magazine found");
        return null;
    }

    // removes objects from server
    public void removeBook(String title)
    {
        String idRemove ="";
        int removeStatus = 0;

        for (Books book : books) 
        {
            if(book.getTitle().equalsIgnoreCase(title))
            {
                idRemove = book.getId();
                break;
            }
        }

        try
        {
            removeStatus = Unirest.delete(baseUrl +"books/" + idRemove)
                .asEmpty()
                .getStatus();
        }
        catch(UnirestException e)
        {
            System.out.println("error in connecting: "+ e.getLocalizedMessage());
            return;
        }

        if(removeStatus == 200)
        {
            System.out.println("Book with the id " + idRemove + " removed");
        }
        else if(removeStatus == 204)
        {
            System.out.println("Nothing to remove at the id " + idRemove);
        }
        else
        {
            System.out.println("Error present with the connection");
        }
    }   


    public void removeMagazine(String title)
    {
        String idRemove ="";
        int removeStatus = 0;

        for (Magazines magazine : magazines) 
        {
            if(magazine.getTitle().equalsIgnoreCase(title))
            {
                idRemove = magazine.getId();
                break;
            }
        }

        try
        {
            removeStatus = Unirest.delete(baseUrl +"magazines/"+ idRemove)
                .asEmpty()
                .getStatus();
        }
        catch(UnirestException e)
        {
            System.out.println("error in connecting: "+ e.getLocalizedMessage());
            return;
        }

        if(removeStatus == 200)
        {
            System.out.println("magazine with the id " + idRemove + " removed");
        }
        else if(removeStatus == 204)
        {
            System.out.println("Nothing to remove at the id " + idRemove);
        }
        else
        {
            System.out.println("Error present with the connection");
        }
    }   


    public void removeUser(String email)
    {
        String removeId = "";
        int removeStatus = 0;

        for (Users user : users) 
        {
            if(user.getEmail().equalsIgnoreCase(email))
            {
                removeId = user.getId();
                break;
            }
        }

        try 
        {
            removeStatus = Unirest.delete(baseUrl + "users/" + removeId)
                .asEmpty()
                .getStatus();
        } 
        catch (UnirestException e) 
        {
            System.out.println("Error in connection: " + e.getLocalizedMessage());
            return;
        }

        if(removeStatus == 200)
        {
            System.out.println("user with the id " + removeId + " removed");
        }
        else if(removeStatus == 204)
        {
            System.out.println("Nothing to remove at the id " + removeId);
        }
        else
        {
            System.out.println("Error present with the connection");
        }

    }


    public void removeSuspended(String customer_id)
    {
        String removeId = "";
        int removeStatus = 0;

        for (Suspended suspended : suspended_users) 
        {
            if(suspended.getCustomer_id() == customer_id)
            {
                removeId = suspended.getId();
                break;
            }
        }

        try 
        {
            removeStatus = Unirest.delete(baseUrl + "suspended/" + removeId)
                .asEmpty()
                .getStatus();
        } 
        catch (UnirestException e) 
        {
            System.out.println("Error in connection: " + e.getLocalizedMessage());
            return;
        }

        if(removeStatus == 200)
        {
            System.out.println("Suspended user with the id " + removeId + " removed");
        }
        else if(removeStatus == 204)
        {
            System.out.println("Nothing to remove at the id " + removeId);
        }
        else
        {
            System.out.println("Error present with the connection");
        }

    }

    //sorts the lists using comparable
    public void sortBooks()
    {
        Collections.sort(this.books);
        System.out.println("Booklist sorted");
    }

    public void sortMagazines()
    {
        Collections.sort(this.magazines);
        System.out.println("Magazinelist sorted");
    }

    public void sortUsers()
    {
        Collections.sort(this.users);
        System.out.println("Userlist sorted");
    }

    //tells us who cant borrow.
    public void seperateUsers()
    {

        ArrayList<String> userIds = new ArrayList<>();
        for (Users user : users) 
        {
            if(user.getId() != null)
            {
                userIds.add(user.getId().trim().toLowerCase());
            }
        }

        List<String> suspendedIds = new ArrayList<>();
        for (Suspended suspended : suspended_users) 
        {
            if (suspended.getCustomer_id() != null) 
            {
                suspendedIds.add(suspended.getCustomer_id().trim().toLowerCase());
            }
        }

        userIds.removeAll(suspendedIds);
        System.out.println("clenseing done, no duplications");


        /* 
            
        for (Users user : users) 
        {
            for (Suspended suspended : suspended_users) 
            {
                if(user.getId().trim().equalsIgnoreCase(suspended.getCustomer_id()))
                {
                    
                    System.out.println(user.toString()+ " cant borrow");
                }
                    
            }

            
        }
        */

    }
        



    public int chooseInt(int value, int maxValue, int minValue)
    {
        while(true){
                try 
                {
                    System.out.println("Enter value!");  
                    value = tb.nextInt();
                    tb.nextLine();

                    if(value > maxValue || value < minValue)
                    {
                        System.out.println("Value cant be bigger than "+ maxValue + " or smaller than " + minValue);
                        
                        continue;
                    }
                    else
                    {
                        return value;
                    }
                } 
                catch (Exception e) 
                {
                    System.out.println("Only numbers: " + e.getMessage());
                    tb.nextLine();
                    continue;
                }
            }
    }

    public String chooseString(String value)
        {
            

            while (true) 
            {
                try 
                {
                    System.out.println("Write your answer!");
                    value = tb.nextLine();
                    Integer.parseInt(value);
                
                } 
                catch (Exception e) 
                {
                    if(value == null || value.trim().isEmpty() )
                    {
                        System.out.println("Answer cant be empty or be empty space");
                        tb.nextLine();
                        continue;
                    }
                    else
                    {
                     return value;   
                    }   
                }

            }
        }

    public Boolean chooseBoolean(Boolean isAvailable, String inputString)
        {
                while (true) 
                {
                    try 
                    {
                    System.out.println("Define availability, yes or no");  
                    inputString = tb.nextLine();
                    
                    if (inputString.equalsIgnoreCase("yes")) 
                        {
                            return true;
                        }
                        else if(inputString.equalsIgnoreCase("no"))
                        {
                            return false;
                        }
                        else
                        {
                            continue;
                        }
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("error in choosing boolean: " + e.getMessage());
                    }
                }
        }

}
