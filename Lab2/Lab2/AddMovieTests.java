import Model.Movie;
import Repository.RepositoryMovie;

import static org.junit.Assert.*;

import Service.ServiceMovie;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AddMovieTests {

    private RepositoryMovie rep;
    private ServiceMovie service;
    private List<String> actors;
    private int year=1990;
    private List<String> keywords;
    private String category = "Drama";

    @Before
    public void setUp() throws Exception {
        rep = new RepositoryMovie();
        service = new ServiceMovie(rep);
        actors=new ArrayList<String>();
        keywords= new ArrayList<String>();
        keywords.add("film");
        actors.add("cineva");
    }

    @Test
    public void testCase1() {
        try {
            service.addMovie("Movie", "Director", year, actors, category, keywords);
        } catch (Exception e) {
            assertTrue(false);
            e.printStackTrace();
        }
        assertTrue(true);
    }

    @Test
    public void testCase3() {
        try {
            service.addMovie("", "Director", year, actors, category, keywords);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(String.valueOf(e.getMessage()).contains("Title is either empty"));
        }

    }



    @Test
    public void testCase4()
    {  try {
        service.addMovie("Movie", "", year, actors, category, keywords);
        fail();
    } catch (Exception e) {
        assertTrue(String.valueOf(e.getMessage()).contains("Director's name is either empty"));
    }
    }

    @Test
    public void testCase6()
    {  try {
        service.addMovie("M", "Director", year, actors, category, keywords);
        assertTrue(true);
    } catch (Exception e) {
        fail();
    }
    }

    @Test
    public void testCase11()
    {  try {
        service.addMovie("M", "D", year, actors, category, keywords);
        assertTrue(true);
    } catch (Exception e) {
        fail();
    }
    }


    @Test
    public void testCase14()
    {
        String name= "";
        for (int i=0;i<256;i++)
            name+='a';
        try {
            service.addMovie("Movie", name, year, actors, category, keywords);
            fail();
        } catch (Exception e) {
            assertTrue(String.valueOf(e.getMessage()).contains("Director's name is either empty"));

        }
    }
    @Test
    public void testCase9()
    {
        String name= "";
        for (int i=0;i<256;i++)
            name+='a';
        try {
            service.addMovie(name, "Direcotr", year, actors, category, keywords);
            fail();
        } catch (Exception e) {
            assertTrue(String.valueOf(e.getMessage()).contains("Title is either empty"));

        }
    }
    @Test
    public void testCase7()
    {
        String name= "";
        for (int i=0;i<255;i++)
            name+='a';
        try {
            service.addMovie(name, "Direcotr", year, actors, category, keywords);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testCase8()
    {
        String name= "";
        for (int i=0;i<254;i++)
            name+='a';
        try {
            service.addMovie(name, "Direcotr", year, actors, category, keywords);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testCase12()
    {
        String name= "";
        for (int i=0;i<255;i++)
            name+='a';
        try {
            service.addMovie("title", name, year, actors, category, keywords);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testCase13()
    {
        String name= "";
        for (int i=0;i<254;i++)
            name+='a';
        try {
            service.addMovie("title", name, year, actors, category, keywords);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }
}
