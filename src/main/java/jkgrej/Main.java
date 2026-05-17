package jkgrej;

// A program where the user is allowed to read in all the books and magazines, as well as to print all the 
// diffrents types of litteratures. The user can also add new litterature objects. The user can also close the program.

import com.google.gson.*;

import java.io.IOException;
import java.nio.file.*;
import kong.unirest.*;
import java.util.Scanner;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
 import java.lang.reflect.Type;



public class Main {
    public static void main(String[] args) {


        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String baseUrl = "http://10.151.168.5:3115/";
        Scanner tb = new Scanner(System.in);
        int input;
        String inputString = "";
        ArrayList<Books> books = new ArrayList<>();
        ArrayList<Magazines> magazines = new ArrayList<>();
        ArrayList<Users> users = new ArrayList<>();
        ArrayList<Suspended> suspended = new ArrayList<>();

        Functions functions = new Functions(books, magazines,users, suspended, baseUrl,gson,tb);


        Type bookType = new TypeToken<ArrayList<Books>>(){}.getType();
        Type magazineType = new TypeToken<ArrayList<Magazines>>(){}.getType();
        Type userType = new TypeToken<ArrayList<Users>>(){}.getType();
        Type suspendedType =  new TypeToken<ArrayList<Suspended>>(){}.getType();

        int id;
        String title = "";
        boolean isAvailable = true;

        String author = "";
        String genre = "";
        int pages = 0;

        int issueNumber = 0;
        String category = "";
        int publishedYear = 0;

        String name = "";
        String email = "";

        int customer_id = 0;


        while (true) {
            
            System.out.println("----------------------------------------------");
            System.out.println("Welcome to the library System\nWhat would you like to do?");
       

            while(true)
        {
            try 
            { 
                System.out.println("1.read different object lists \n2.write diffrent objects \n3.add diffrent singular object \n4. read one object \n5.find litterature \n6.find users \n7.remove objects \n8.Sort objects \n9.Seperate users and suspended users \n10.closer program");
                input = tb.nextInt();
                tb.nextLine();
                
                if(input > 10 || input < 1)
                {
                    System.out.println("Not bigger than 10 or smaller than 1");
                    System.out.println("enter again");
                    tb.nextLine();
                    continue;
                }
                else
                {
                    break;
                }
            } 
            catch (Exception e) 
            {
                System.out.println("Error, no string: " + e.getMessage());
                tb.nextLine();
                continue;
            }
        }
        

            // The responses you get from the server.
            HttpResponse<String> all_book_response;
            HttpResponse<String> all_magazineResponse;

            // all different actions
            if(input == 1)
            {

                System.out.println("You chose to read in lists");

                int readInput;

                while(true){
                    try 
                    {
                        System.out.println("What would you like to enter 1 to 4 \n1.books \n2.magazines \n3.Users \n4.Suspended users");
                        readInput = tb.nextInt();
                        tb.nextLine();

                        if(readInput > 4 || readInput < 1)
                        {
                            System.out.println("Do not write a number bigger than 4 or lesser than 1");
                            continue;
                        }
                        else
                        {
                            break;
                        }
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("Do not enter words or other: " + e.getMessage());
                        tb.nextLine();
                        continue;
                    }
                }
                
                switch(readInput)
                {
                    case 1:
                        System.out.println("You chose to read all book");
                        functions.readAllBooks(bookType);
                        break;
                    case 2:
                        System.out.println("you chose to read all magazines ");
                        functions.readAllMagazines(magazineType);
                        break;
                    case 3:
                        System.out.println("You chose to read in all users");
                        functions.readAllUsers(userType);
                        break;
                    case 4:
                        System.out.println("You chose to read all suspended");
                        functions.readAllSuspended(suspendedType);
                        break;
                    default:
                        System.out.println("error");
                        break;
                }

        //Hämtar innehållet i Books
        /* 
                try
                {
                    all_book_response = Unirest.get(baseUrl+ "books").asString();
                }
                catch(UnirestException e)
                {
                    System.out.println("Error when reading"+ e.getLocalizedMessage());
                    return;
                }

                int status_books = all_book_response.getStatus();
                if( status_books != 200)
                {
                    System.out.println("Statuscode: " + status_books + "serverissue");
                }


                String get_bookBody = all_book_response.getBody();

                try
                {
                    Files.writeString(Paths.get("books.json"), get_bookBody);
                    System.out.println("Data saved to books.json");
                }
                catch(IOException e)
                {
                    System.out.println("Fileissue: " + e.getMessage());
                }

                
                books = gson.fromJson(get_bookBody, bookType);

                System.out.println("booklist created");
                */
            }

            else if(input == 2)
            {

                System.out.println("You chose to write lists");

                int readInput;

                while(true){
                    try 
                    {
                        System.out.println("What would you like to enter 1 to 4 \n1.books \n2.magazines \n3.Users \n4.Suspended users");
                        readInput = tb.nextInt();
                        tb.nextLine();

                        if(readInput > 4 || readInput < 1)
                        {
                            System.out.println("Do not write a number bigger than 4 or lesser than 1");
                            continue;
                        }
                        else
                        {
                            break;
                        }
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("Do not enter words or other: " + e.getMessage());
                        tb.nextLine();
                        continue;
                    }
                }
                
                switch(readInput)
                {
                    case 1:
                        System.out.println("You chose to write all books");
                        for (Books book : books) 
                        {
                            System.out.println(book.toString());
                        }
                        break;
                    case 2:
                        System.out.println("You chose to write all magazines");
                        for (Magazines magazine : magazines) 
                        {
                            System.out.println(magazine.toString());
                        }
                        break;
                    case 3:
                        System.out.println("You chose to write all users");
                        for (Users user : users) 
                        {
                            System.out.println(user.toString());
                        }
                        break;
                    case 4:
                        System.out.println("You chose to write all suspended users");
                        for (Suspended suspendeds : suspended) 
                        {
                            System.out.println(suspendeds.toString());
                        }
                        break;
                    default:
                        System.out.println("error");
                        break;



                //If the user wants to get all magazines.
                /* 
                try 
                {
                    all_magazineResponse = Unirest.get(baseUrl + "magazines").asString();
                } 
                catch (UnirestException e) 
                {
                    System.out.println("error when reading in: " + e.getLocalizedMessage());
                    return;
                }

                int magazineStatus = all_magazineResponse.getStatus();
                        
                if( magazineStatus != 200 )
                {
                    System.out.println("Statuscode: " + magazineStatus + "serverissue");
                }

                String magazineBody = all_magazineResponse.getBody();

                try 
                {
                    Files.writeString(Paths.get("magazine.json"), magazineBody);
                    System.out.println("Data saved in magazine.json");
                } 
                catch (IOException e) 
                {
                    System.out.println("file Error: " + e.getMessage());
                }

                magazineType = new TypeToken<ArrayList<Magazines>>(){}.getType();
                magazines = gson.fromJson(magazineBody, magazineType);

                System.out.println("magazine list created");
                */
                }
       }

            else if(input == 3)
            {
                // if the user wants to print all book
                System.out.println("You chose to add objects in lists");

                int readInput;

                while(true){
                    try 
                    {
                        System.out.println("What would you like to enter 1 to 4 \n1.books \n2.magazines \n3.Users \n4.Suspended users");
                        readInput = tb.nextInt();
                        tb.nextLine();

                        if(readInput > 4 || readInput < 1)
                        {
                            System.out.println("Do not write a number bigger than 4 or lesser than 1");
                            continue;
                        }
                        else
                        {
                            break;
                        }
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("Do not enter words or other: " + e.getMessage());
                        tb.nextLine();
                        continue;
                    }
                }
                
                HttpResponse<String> response = null;
                switch(readInput)
                {
                    case 1:
                        System.out.println("You chose to add a book");
                        functions.addBook(title, isAvailable, author, genre, pages,inputString);
                        break;
                    case 2:
                        System.out.println("you chose to add a magazine ");
                        functions.addMagazine(title, isAvailable, issueNumber, category, publishedYear, inputString);
                        break;
                    case 3:
                        System.out.println("You chose to add a user");
                        functions.addUser(name, email, response);;
                        break;
                    case 4:
                        System.out.println("You chose to add a suspended");
                        functions.addSuspended(customer_id, response);
                        break;
                    default:
                        System.out.println("error");
                        break;
                }
            }
        
            else if(input == 4)
            { 
                // if the user awants to print all magazines
                System.out.println("You chose to read in one object");

                int readInput;

                while(true){
                    try 
                    {
                        System.out.println("What would you like to enter 1 to 4 \n1.books \n2.magazines \n3.Users \n4.Suspended users");
                        readInput = tb.nextInt();
                        tb.nextLine();

                        if(readInput > 4 || readInput < 1)
                        {
                            System.out.println("Do not write a number bigger than 4 or lesser than 1");
                            continue;
                        }
                        else
                        {
                            break;
                        }
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("Do not enter words or other: " + e.getMessage());
                        tb.nextLine();
                        continue;
                    }
                }
                
                HttpResponse<String> response = null;
                int idChoice = 0;
                switch(readInput)
                {
                    case 1:
                        System.out.println("You chose to get a book");

                    
                        System.out.println("Which id would you like to read in");
                        idChoice = functions.chooseInt(idChoice, 99999, 1);
                        
                        Books book = functions.readOneBook(idChoice);
                        System.out.println(book.toString());
                        break;
                    case 2:
                        System.out.println("you chose to get a magazine ");
                                            
                        System.out.println("Which id would you like to read in");
                        idChoice = functions.chooseInt(idChoice, 99999, 1);
                        
                        Magazines magazine = functions.readOneMagazine(idChoice);
                        System.out.println(magazine.toString());
                        break;
                    case 3:
                        System.out.println("you chose to get a user ");
                                            
                        System.out.println("Which id would you like to read in");
                        idChoice = functions.chooseInt(idChoice, 99999, 1);
                        
                        Users user =  functions.readOneUser(idChoice);
                        System.out.println(user.toString());
                        break;
                    case 4:
                        System.out.println("you chose to get a suspended user ");
                                            
                        System.out.println("Which id would you like to read in");
                        idChoice = functions.chooseInt(idChoice, 99999, 1);
                        
                        Suspended suspend = functions.readOneSuspended(idChoice);
                        System.out.println(suspend.toString());
                        break;
                        
                    default:
                        System.out.println("error");
                        break;
                }
            }
      
            else if(input == 5)
            {

                System.out.println("you chose to find a litterature by title ");

                int choice = 0;

                while(true)
                {
                    try 
                    {
                        System.out.println("Would you like to find a \n1.book object \n2.magazine object \nWrite 1 or 2 ");
                        choice = tb.nextInt();
                        tb.nextLine();

                        if(choice > 2 || choice < 1)
                        {
                            System.out.println("Value can't be smaller than 1 or bigger than 2");
                            continue;
                        }
                        else
                        {
                            break;
                        }
                    } 
                    catch (Exception e) 
                    {
                        tb.nextLine();
                        System.out.println("No words or charachters, only numbers: " + e.getMessage());
                        continue;
                    }
                }


                switch(choice)
                {
                    case 1:
                        System.out.println("You chose to find a book object by title");
                        System.out.println("enter title");
                        title = functions.chooseString(title);

                        Books book = functions.findBooks(title);
                        System.out.println(book.toString());
                        break;
                    case 2:
                        System.out.println("You chose to find a magazine object by title");
                        System.out.println("enter title:");
                        title = functions.chooseString(title);

                        Magazines magazine = functions.findMagazines(title);
                        System.out.println(magazine.toString());
                        break;
                    default:
                        System.out.println("error");
                        break;
                }
                /* 
                // What happens when a book gets added
                System.out.println("You chose to add a book!");

                System.out.println("Define pages from 100 to 800");
                pages = functions.chooseInt(pages, 800 , 100);
                System.out.println("Define author");
                author = functions.chooseString(author);
                System.out.println("Define genre");
                genre = functions. chooseString(genre);
                System.out.println("Define title");
                title = functions.chooseString(title);
                isAvailable = functions.chooseBoolean(null, inputString);

                

               Books newBook = new Books(0, title, isAvailable, author, genre, pages);
               books.add(newBook);
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
                */
            }
        
            else if(input == 6)
            {
                System.out.println("you chose to find a user using an email");

                System.out.println("enter email");
                email = functions.chooseString(email);

                Users user = functions.findUsers(email);

                System.out.println(user.toString());

                /*
                // what happens when a magazine gets added
                System.out.println("Your chose to add a magazine.");

                System.out.println("Define title");
                title = functions.chooseString(title);
                
                isAvailable = functions.chooseBoolean(null, inputString);
                System.out.println("Define issuenumber from 1 to 1723");
                issueNumber = functions.chooseInt(issueNumber,1723,1);
                System.out.println("Define category");
                category = functions.chooseString(category);
                System.out.println("Define publishedyear from 1923 to 2026");
                publishedYear = functions.chooseInt(publishedYear, 2026, 1923);


                magazines.add(new Magazines(0, title, isAvailable, issueNumber, category, publishedYear));
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

                try
                {
                    postMagaziResponse = Unirest.post(baseUrl)
                        .header("Content-Type", "application/json")
                        .body(stringJsonMagazine)
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

               */
            }
        
            else if(input == 7)
            {
                System.out.println("you chose to remove objects from the server"); 

                int choice = 0;

                while(true){
                    try 
                    {
                        System.out.println("What would you like to remove 1 to 4 \n1.books \n2.magazines \n3.Users \n4.Suspended users");
                        choice = tb.nextInt();
                        tb.nextLine();

                        if(choice > 4 || choice < 1)
                        {
                            System.out.println("Do not write a number bigger than 4 or lesser than 1");
                            continue;
                        }
                        else
                        {
                            break;
                        }
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("Do not enter words or other: " + e.getMessage());
                        tb.nextLine();
                        continue;
                    }
                }
       
                switch(choice)
                {
                    case 1:
                        System.out.println("you chose to remove a book");
                        System.out.println("enter the title of the book");
                        title = functions.chooseString(title);

                        functions.removeBook(title);
                        System.out.println("book removed");
                        break;
                    case 2:
                        System.out.println("you chose to remove a magazine");
                        System.out.println("enter the title of the magazine");
                        title = functions.chooseString(title);

                        functions.removeMagazine(title);
                        System.out.println("magazine removed");
                        break;
                    case 3:
                        System.out.println("you chose to remove a user");
                        System.out.println("enter the email of the user");
                        email = functions.chooseString(email);

                        functions.removeUser(email);
                        System.out.println("user removed");
                        break;
                    case 4:
                        System.out.println("you chose to remove a suspended user");
                        System.out.println("enter the customer id of the user in numbers");
                        customer_id = functions.chooseInt(customer_id, 9999, 1);

                        functions.removeSuspended(customer_id);
                        System.out.println("Suspended user removed");
                        break;
                    default:
                        System.out.println("error");
                        break;
                }
        }

            else if(input == 8)
            {   
                System.out.println("you chose to sort the diffrent lists ");


                int choice = 0;

                while(true){
                    try 
                    {
                        System.out.println("What would you like to sort, enter 1 to 3 \n1.books \n2.magazines \n3.Users");
                        choice = tb.nextInt();
                        tb.nextLine();

                        if(choice > 3 || choice < 1)
                        {
                            System.out.println("Do not write a number bigger than 3 or lesser than 1");
                            continue;
                        }
                        else
                        {
                            break;
                        }
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("Do not enter words or other: " + e.getMessage());
                        tb.nextLine();
                        continue;
                    }
                }

                switch(choice)
                {
                    case 1: 
                        System.out.println("You chose to sort books after title");

                        functions.sortBooks();

                        System.out.println(books.toString());
                        break;
                    case 2:
                        System.out.println("You chose to sort magazines after title");

                        functions.sortMagazines();

                        System.out.println(magazines.toString());
                        break;
                    case 3:
                        System.out.println("you chose to sort users after name");

                        functions.sortUsers();

                        System.out.println(users.toString());
                        break;
                    default:
                        System.out.println("error");
                        break;
                }
            } 

            else if(input == 9)
            {
                System.out.println("you chose to seperate the suspended user from the users");

                functions.seperateUsers();

                System.out.println("seperation finished");
            }

            else if( input == 10)
            {
                System.out.println("Closing program");
                tb.close();
                Unirest.shutDown();
                System.exit(0);
            }
       
    }
        // makes it possible to check ints, string and booleans.

     /*    public static int chooseInt(int value, int maxValue, int minValue, Scanner tb)
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

        public static String chooseString(String value, Scanner tb)
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

        public static Boolean choosBoolean(Boolean isAvailable, String inputString,  Scanner tb)
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
                    */
        }
                 
}