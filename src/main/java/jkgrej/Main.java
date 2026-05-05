package jkgrej;
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
        ArrayList<Books> books = new ArrayList<>();
        ArrayList<Magazines> magazines = new ArrayList<>();

 
        String title = "";
        boolean isAvailable;

        String author = "";
        String genre = "";
        int pages = 0;

        int issueNumber = 0;
        String category = "";
        int publishedYear = 0;


        while (true) {
            
        
            System.out.println("Welcome to the library System\nWhat would you like to do?");
       

            while(true)
        {
            try 
            { 
                System.out.println("1.Get all books \n2.get all magazines \n3.Write all books \n4.Write all magazines \n5.Add book \n6.Add magazine \n7.close program");
                input = tb.nextInt();
                
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
                continue;
            }
        }
        


            HttpResponse<String> all_book_response;
            HttpResponse<String> all_magazineResponse;

            if(input == 1){
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
            if( status_books != 200 || status_books != 204)
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

            Type bookType = new TypeToken<ArrayList<Books>>(){}.getType();
            books = gson.fromJson(get_bookBody, bookType);

            System.out.println("booklist created");
    }

            else if(input == 2)
            {
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
                        
                if( magazineStatus != 200 || magazineStatus != 204)
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

                Type magazineType = new TypeToken<ArrayList<Magazines>>(){}.getType();
                magazines = gson.fromJson(magazineBody, magazineType);

                System.out.println("magazine list created");


       }

            else if(input == 3)
            {
                for (Books b : books) 
                {
                    System.out.println(b.toString());
                }
            }
        
            else if(input == 4)
            {
                for (Magazines m: magazines) 
                {
                    System.out.println(m.toString());
                }
            }
      
            else if(input == 5)
            {
                System.out.println("You chose to add a book!");

                pages = chooseInt(pages, 800 , 100, tb );
                author = chooseString(author, tb);
                genre = chooseString()



            }
        }
       

       
    }
        public static int chooseInt(int value, int maxValue, int minValue, Scanner tb)
        {
            while(true){
                try 
                {
                    System.out.println("Enter value!");  
                    value = tb.nextInt();

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

}