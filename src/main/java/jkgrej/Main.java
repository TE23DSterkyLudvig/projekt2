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
            System.out.println("Fel vid inläsning"+ e.getLocalizedMessage());
            return;
        }

        int status_books = all_book_response.getStatus();
        if( status_books != 200 || status_books != 204)
        {
            System.out.println("Statuskod: " + status_books + "serverfel");
        }


        String get_bookBody = all_book_response.getBody();

        try
        {
            Files.writeString(Paths.get("books.json"), get_bookBody);
            System.out.println("Data sparad till json");
        }
        catch(IOException e)
        {
            System.out.println("Filfel: " + e.getMessage());
        }

        Type bookType = new TypeToken<ArrayList<Books>>(){}.getType();
        ArrayList<books> bookList = gson.fromJson(get_bookBody, bookType);

        System.out.println("booklist created");
    }

       if(input == 2)
       {
            try 
            {
                all_magazineResponse = Unirest.get(baseUrl + "magazines").asString();
            } 
            catch (UnirestException e) 
            {
                System.out.println("Fel på inläsning: " + e.getLocalizedMessage());
                return;
            }

            int magazineStatus = all_magazineResponse.getStatus();
       }

       
    }
}