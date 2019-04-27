
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (Ahrar Hayat) 
 * @version (27/4/2019)
 */
import java.util.*;
public class LargestQuakes {
    public void findLargestQuakes ()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
       /* for(QuakeEntry qe : list)
        {
            System.out.println(qe);
        }
        */
        System.out.println("read data for "+list.size());
        /* int maxIndex= indexOfLargest(list);
        System.out.println("The index is "+maxIndex+" and the magnitude is "+list.get(maxIndex).getMagnitude());
        */
       ArrayList<QuakeEntry> largestQuakes = getLargest(list,5);
       for(QuakeEntry qe: largestQuakes)
       {
           System.out.println(qe);
       }
    }
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData)
    {
        ArrayList<QuakeEntry> list = quakeData;
        int maxIndex=0;
        double maxMag=0;
        for(int k=0;k<list.size();k++)
            {
                QuakeEntry qe = list.get(k);
                if(maxMag==0)
                {
                    maxMag=qe.getMagnitude();
                    maxIndex=k;
                }
                if(qe.getMagnitude()>maxMag)
                {
                    maxMag=qe.getMagnitude();
                    maxIndex=k;
                }
            }
        
        return maxIndex;
    }
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany)
    {
        ArrayList<QuakeEntry> list = quakeData;
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        for(int j=0;j<howMany;j++)
        {
            if(list.size()<howMany)
            {
                howMany=list.size();
            }
            int maxIndex= indexOfLargest(list);
            ret.add(list.get(maxIndex));
            list.remove(list.get(maxIndex));
        }
        return ret;
    }
}
