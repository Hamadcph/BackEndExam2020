/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTO.movieInfo;
import DTO.movieScore;
import com.google.gson.Gson;
import entities.MovieInfoPassthrough;
import entities.MoviePosterPassthrough;
import entities.TomatoRating;
import entities.imdbRating;
import entities.metaRating;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author Hamad
 */
public class fetchFacade {

//    public String getAllDataInParalelWithQueue() throws ProtocolException, IOException, InterruptedException, ExecutionException
//    {
//        ExecutorService workingJack = Executors.newCachedThreadPool();
//        List<Future<String>> futureList = new ArrayList();
//        for (int index = 1; index < 119; index++)
//        {
//            final int i = index;
//            Future<String> future = workingJack.submit(() -> getDnDData(i));
//            futureList.add(future);
//        }
//        StringBuilder sb = new StringBuilder("[");
//        for (Future<String> future : futureList)
//        {
//            sb.append(future.get() + ",");
//        }
//        sb.append("]");
//        return sb.toString();
//
//    }
    public movieInfo getMovieData(String title) throws MalformedURLException, ProtocolException, IOException
    {
        MovieInfoPassthrough m;
        MoviePosterPassthrough p;
        Gson g = new Gson();
        String fullUrl = "http://exam-1187.mydemos.dk/movieInfo/" + title; 
        URL url = new URL(fullUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        try (Scanner scan = new Scanner(con.getInputStream()))
        {
            String jsonStr = "";
            while (scan.hasNext())
            {
                jsonStr += scan.nextLine();
            }
            m = g.fromJson(jsonStr, MovieInfoPassthrough.class);
        }
        fullUrl = "http://exam-1187.mydemos.dk/moviePoster/" + title; 
        url = new URL(fullUrl);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        try (Scanner scan = new Scanner(con.getInputStream()))
        {
            String jsonStr = "";
            while (scan.hasNext())
            {
                jsonStr += scan.nextLine();
            }
            p = g.fromJson(jsonStr, MoviePosterPassthrough.class);
        }
        return new movieInfo(m, p);
        //poster info
    }
    
        public List<movieScore> getMovieScore(String title) throws MalformedURLException, ProtocolException, IOException
    {
        imdbRating a;
        TomatoRating b;
        metaRating c;
        Gson g = new Gson();
        String fullUrl = "http://exam-1187.mydemos.dk/imdbScore/" + title; 
        URL url = new URL(fullUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        try (Scanner scan = new Scanner(con.getInputStream()))
        {
            String jsonStr = "";
            while (scan.hasNext())
            {
                jsonStr += scan.nextLine();
            }
            a = g.fromJson(jsonStr, imdbRating.class);
        }
        fullUrl = "http://exam-1187.mydemos.dk/tomatoesScore/" + title; 
        url = new URL(fullUrl);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        try (Scanner scan = new Scanner(con.getInputStream()))
        {
            String jsonStr = "";
            while (scan.hasNext())
            {
                jsonStr += scan.nextLine();
                System.out.println(jsonStr);
            }
            b = g.fromJson(jsonStr, TomatoRating.class);
        }
        
        fullUrl = "http://exam-1187.mydemos.dk/metacriticScore/" + title; 
        url = new URL(fullUrl);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        try (Scanner scan = new Scanner(con.getInputStream()))
        {
            String jsonStr = "";
            while (scan.hasNext())
            {
                jsonStr += scan.nextLine();
            }
            c = g.fromJson(jsonStr, metaRating.class);
        }
        
        List<movieScore> scores = new ArrayList<>();
        scores.add(new movieScore(a));
        scores.add(new movieScore(b));
        scores.add(new movieScore(c));
        
        return scores;
        
    }
        

    
    
    
    
    
    

    
    public static void main(String[] args) throws ProtocolException, IOException, InterruptedException, ExecutionException {
        fetchFacade facade = new fetchFacade();
//        String satan = facade.getDnDData(1);
//        
//        System.out.println(facade.getDnDData(1));
        movieInfo result = facade.getMovieData("Grease");
        System.out.println(result.toString());
    }
    
    
    
}
