package agenda.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;

import agenda.model.base.Activity;
import agenda.model.repository.classes.RepositoryActivityMock;
import agenda.model.repository.interfaces.RepositoryActivity;

import org.junit.Before;
import org.junit.Test;

public class AddActivityTest {
	private Activity act;
	private RepositoryActivity rep;
	
	@Before
	public void setUp() throws Exception {
		rep = new RepositoryActivityMock();
	}
	
	@Test
	public void testCase1()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try {
			act = new Activity("name1", 
					df.parse("03/20/2013 12:00"), 
					Duration.ofHours(1),
					null,
					"Lunch break","here");
			rep.addActivity(act);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		assertTrue(1 == rep.count());
	}
	
	@Test
	public void testCase2()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try{
			for (Activity a : rep.getActivities())
				rep.removeActivity(a);
			
			act = new Activity("name1",
					df.parse("03/20/2013 12:00"),
					Duration.ofHours(1),
					null,
					"Lunch break","here");
			rep.addActivity(act);
			
			act = new Activity("name1",
					df.parse("03/21/2013 12:00"),
					Duration.ofHours(1),
					null,
					"Lunch break","here");
			rep.addActivity(act);
		}
		catch(Exception e){}	
		int c = rep.count();
		assertTrue( c == 2);
		for (Activity a : rep.getActivities())
			rep.removeActivity(a);
	}
	
	@Test
	public void testCase3()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try{
			for (Activity a : rep.getActivities())
				rep.removeActivity(a);
			
			act = new Activity("name1",
					df.parse("03/20/2013 12:00"), 
					Duration.ofHours(1),
					null,
					"Lunch break","here");
			rep.addActivity(act);
			
			act = new Activity("name1",
					df.parse("03/20/2013 12:30"),
					Duration.ofHours(1),
					null,
					"Lunch break","here");
			assertFalse(rep.addActivity(act));
		}
		catch(Exception e){}	
		assertTrue( 1 == rep.count());
		rep.saveActivities();
		for (Activity a : rep.getActivities())
			rep.removeActivity(a);
	}
	
	@Test
	public void testCase4()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try{
			for (Activity a : rep.getActivities())
				rep.removeActivity(a);
			
			act = new Activity("name1",
					df.parse("03/20/2013 12:00"),
					Duration.ofHours(1),
					null,
					"Lunch break","here");
			rep.addActivity(act);
			
			act = new Activity("name1",
					df.parse("03/20/2013 13:30"),
					Duration.ofHours(1),
					null,
					"Curs","here");
			rep.addActivity(act);
			
			act = new Activity("name1",
					df.parse("03/20/2013 13:30"),
					Duration.ofHours(1),
					null,
					"Lunch break","here");
			assertFalse(rep.addActivity(act));			
		}
		catch(Exception e){}	
		assertTrue( 2 == rep.count());
		for (Activity a : rep.getActivities())
			rep.removeActivity(a);
	}
	
	@Test
	public void testCase5()
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		try{
			for (Activity a : rep.getActivities())
				rep.removeActivity(a);
			
			act = new Activity("name1",
					df.parse("03/20/2013 12:00"),
					Duration.ofHours(1),
					null,
					"Lunch break","here");
			rep.addActivity(act);
			
			assertFalse(rep.addActivity(act));			
		}
		catch(Exception e){}	
		assertTrue( 1 == rep.count());
		for (Activity a : rep.getActivities())
			rep.removeActivity(a);
	}
}
