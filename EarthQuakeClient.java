import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe:quakeData)
        {
            if(qe.getMagnitude()>magMin)
            {
              answer.add(qe);  
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe: quakeData)
        {
            Location loc = qe.getLocation();
            //distance is in meters
            if(loc.distanceTo(from) < distMax)
            {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> filteredList= filterByMagnitude(list,5);
        System.out.println("read data for "+list.size()+" quakes");
        for(QuakeEntry qe: filteredList)
        {
            System.out.println(qe);
        }
        System.out.println("Found "+ filteredList.size()+" quakes that match the criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source= "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        
        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> filteredList = filterByDistanceFrom(list,1000000.0,city);
        for(QuakeEntry qe:filteredList)
        {
            Location loc = qe.getLocation();
            System.out.println(loc.distanceTo(city)+" "+qe.getInfo());
        }
        System.out.println("Found "+ filteredList.size()+" quakes that match the criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        
    }
    public ArrayList<QuakeEntry> filterByDepth (ArrayList<QuakeEntry> quakeData,double minDepth,double maxDepth)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData)
        {
            if(qe.getDepth()>minDepth && qe.getDepth()<maxDepth)
            {
              answer.add(qe);  
            }
        }
        return answer;
    }
    public void quakesOfDepth ()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source= "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> filteredList=filterByDepth(list,-10000,-8000);
        for(QuakeEntry qe: filteredList)
        {
            System.out.println(qe);
        }
        System.out.println("Found "+ filteredList.size()+" quakes that match the criteria");
    }
    public ArrayList<QuakeEntry> filterByPhrase (ArrayList<QuakeEntry> quakeData, String where,String phrase)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData)
        {
            String title= qe.getInfo();
            if(where.equals("start"))
            {
              if(title.startsWith(phrase)==true)
                {
                   answer.add(qe); 
                }
            }
            if(where.equals("end"))
            {
               if(title.endsWith(phrase)==true)
                {
                   answer.add(qe); 
                } 
            }
            if(where.equals("any"))
            {
                if(title.indexOf(phrase)!=-1)
                {
                   answer.add(qe); 
                }
            }
            
        }
        return answer;
    }
    public void quakesByPhrase ()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source= "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        String where= "any";
        String phrase="Creek";
        ArrayList<QuakeEntry> filteredList=filterByPhrase(list,where,phrase);
        for(QuakeEntry qe: filteredList)
        {
            System.out.println(qe);
        }
        if(where.equals("start"))
        {
            System.out.println("Found "+ filteredList.size()+" quakes that has " +phrase +" at the start of the title");
        }
        if(where.equals("end"))
        {
            System.out.println("Found "+ filteredList.size()+" quakes that has " +phrase +" at the end of the title");
        }
        if(where.equals("any")){
        System.out.println("Found "+ filteredList.size()+" quakes that has " +phrase +" somewhere in the title");
        }
        
    }
}
