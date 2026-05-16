package jkgrej;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;



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

    public void readAllBooks(HttpResponse<String> response, Type bookType)

    {
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
                    this.books = gson.fromJson(get_bookBody, bookType);

                System.out.println("booklist created");
                }
    }

    public Books readOneBook (HttpResponse<String> response, int id)
    {
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
    


    public void readAllMagazines(HttpResponse<String> response, Type magazineType)
    {
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

    public Magazines readOneMagazine(HttpResponse<String> response, int id)
    {
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


    public void readAllUsers(HttpResponse<String> response, Type userType)
    {
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

    public Users readOneUser(HttpResponse<String> response, int id)
    {
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


    public void readAllSuspended(HttpResponse<String> response, Type suspendedType)
    {
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

    public Suspended readOneSuspended(HttpResponse<String> response, int id)
    {
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
                        tb.nextLine();
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

    public Boolean choosBoolean(Boolean isAvailable, String inputString)
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
