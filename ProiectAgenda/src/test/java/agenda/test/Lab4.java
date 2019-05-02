package agenda.test;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.model.repository.classes.RepositoryActivityFile;
import agenda.model.repository.classes.RepositoryContactFile;
import agenda.model.repository.interfaces.RepositoryActivity;
import agenda.model.repository.interfaces.RepositoryContact;

import agenda.startApp.MainClass;
import org.junit.Before;
import org.junit.Test;

import agenda.exceptions.InvalidFormatException;

public class Lab4 {

    private RepositoryActivity repAct;
    private RepositoryContact repCon;

    @Before
    public void setup() throws Exception {
        repCon = new RepositoryContactFile();
        repAct = new RepositoryActivityFile(repCon);

        for (Activity a : repAct.getActivities())
            repAct.removeActivity(a);
    }

    @Test
    public void testCase_A()
    {   Contact con= null;
        try {
            con = new Contact("name", "address1", "+4071122334455","bla");
        } catch (InvalidFormatException e) {
            assertTrue(false);
        }
        //int n = rep.count();
        repCon.addContact(con);
        for(Contact c : repCon.getContacts())
            if (c.equals(con))
            {
                assertTrue(true);
                break;
            }
        //assertTrue(n+1 == rep.count());
    }

    @Test
    public void testCase_B()
    {
        Activity act = null;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            act = new Activity("name1",
                    df.parse("03/20/2013 12:00"),
                    Duration.ofHours(1),
                    null,
                    "Lunch break","here");
            repAct.addActivity(act);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertTrue(1 == repAct.count());
    }


    @Test
    public void testCase_C() {
        Calendar c = Calendar.getInstance();
        c.set(2013, 3 - 1, 20, 12, 00);
        Date start = c.getTime();


        Activity act = new Activity("name1", start, Duration.ofHours(1),
                new LinkedList<Contact>(), "description2","here");

        repAct.addActivity(act);

        c.set(2013, 3 - 1, 20);

        List<Activity> result = repAct.activitiesOnDate("name1", c.getTime());
        assertTrue(result.size() == 1);
    }

    @Test
    public void testCase_P() {
        Contact con= null;
        try {
            con = new Contact("name", "address1", "+4071122334455","bla");
        } catch (InvalidFormatException e) {
            assertTrue(false);
        }
        repCon.addContact(con);
        Activity act = null;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            act = new Activity("name1",
                    df.parse("03/20/2013 12:00"),
                    Duration.ofHours(1),
                    repCon.getContacts(),
                    "Lunch break","here");
            repAct.addActivity(act);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.set(2013, 3 - 1, 20);
        List<Activity> result = repAct.activitiesOnDate("name1", c.getTime());
        assertTrue(result.size() == 1);
    }

    @Test
    public void testCase_P_A_B() {
        Contact con= null;
        try {
            con = new Contact("name", "address1", "+4071122334455","bla");
        } catch (InvalidFormatException e) {
            assertTrue(false);
        }
        repCon.addContact(con);
        Activity act = null;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            act = new Activity("name1",
                    df.parse("03/20/2013 12:00"),
                    Duration.ofHours(1),
                    repCon.getContacts(),
                    "Lunch break","here");
            repAct.addActivity(act);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertTrue(repAct.getActivities().size()==1);
    }


    @Test
    public void testCase_P_A() {
        Contact con= null;
        try {
            con = new Contact("name", "address1", "+4071122334455","bla");
        } catch (InvalidFormatException e) {
            assertTrue(false);
        }
        repCon.addContact(con);
        assertTrue(repCon.getContacts().contains(con));
    }

}
