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


        Type bookType = new TypeToken<ArrayList<Books>>(){}.getType();
        Type magazineType = new TypeToken<ArrayList<Magazines>>(){}.getType();

        int id;
        String title = "";
        boolean isAvailable;

        String author = "";
        String genre = "";
        int pages = 0;

        int issueNumber = 0;
        String category = "";
        int publishedYear = 0;


        while (true) {
            
            System.out.println("----------------------------------------------");
            System.out.println("Welcome to the library System\nWhat would you like to do?");
       

            while(true)
        {
            try 
            { 
                System.out.println("1.Get all books \n2.get all magazines \n3.Write all books \n4.Write all magazines \n5.Add book \n6.Add magazine \n7.close program");
                input = tb.nextInt();
                tb.nextLine();
                
                if(input > 7 || input< 1)
                {
                    System.out.println("Not bigger than 7 or smaller than 1");
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
        //Hämtar innehållet i Books
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
            }

            else if(input == 2)
            {
                //If the user wants to get all magazines.
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


       }

            else if(input == 3)
            {
                // if the user wants to print all books
                for (Books b : books) 
                {
                    System.out.println(b.toString());
                }
            }
        
            else if(input == 4)
            { 
                // if the user awants to print all magazines
                for (Magazines m: magazines) 
                {
                    System.out.println(m.toString());
                }
            }
      
            else if(input == 5)
            {
                // What happens when a book gets added
                System.out.println("You chose to add a book!");

                System.out.println("Define pages from 100 to 800");
                pages = chooseInt(pages, 800 , 100, tb );
                System.out.println("Define author");
                author = chooseString(author, tb);
                System.out.println("Define genre");
                genre = chooseString(genre, tb);
                System.out.println("Define title");
                title = chooseString(title,tb);
                isAvailable = choosBoolean(null, inputString, tb);

                

                books.add(new Books(0, title, isAvailable, author, genre, pages));
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

                try
                {
                    postBookResponse = Unirest.post(baseUrl)
                        .header("Content-Type", "application/json")
                        .body(stringJsonBook)
                        .asString();
                }
                catch(UnirestException e)
                {
                    System.out.println("Error in connection:" + e.getLocalizedMessage());
                    return;
                }

                int magazineStatus = postBookResponse.getStatus();
                if(magazineStatus != 200 && magazineStatus != 201)
                {
                    System.out.println("Errorcode: " +  magazineStatus);
                }
                System.out.println("Created magazine");
            }
        
            else if(input == 6)
            {

                // what happens when a magazine gets added
                System.out.println("Your chose to add a magazine.");

                System.out.println("Define title");
                title = chooseString(title, tb);
                
                isAvailable = choosBoolean(null, inputString, tb);
                System.out.println("Define issuenumber from 1 to 1723");
                issueNumber = chooseInt(issueNumber,1723,1,tb);
                System.out.println("Define category");
                category = chooseString(category, tb);
                System.out.println("Define publishedyear from 1923 to 2026");
                publishedYear = chooseInt(publishedYear, 2026, 1923, tb);


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

               
            }
        
            else if(input == 7)
            {
                //closes program
                System.out.println("Closing program");
                tb.close();
                System.exit(0);
            }
        }
       

       
    }
        // makes it possible to check ints, string and booleans.

        public static int chooseInt(int value, int maxValue, int minValue, Scanner tb)
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
        }
}