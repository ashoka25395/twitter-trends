import java.io. * ;
import java.util. * ;
import java.util.regex. * ;

public class TwitterTrends {
  public static void main(String[] args) throws NumberFormatException,
  IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System. in ));
    Pattern pattern = Pattern.compile("(#\\w+)");
    Matcher matcher;

    Map < String,Integer > counter = new LinkedHashMap < String,Integer > ();
    String line = br.readLine();
    matcher = pattern.matcher(line);
    
    while (matcher.find()) {
      if (null != counter.get(matcher.group())) {
        counter.put(matcher.group(), (counter.get(matcher.group()) + 1));
      } else {
        counter.put(matcher.group(), new Integer(1));
      }
    }
    
    List < Map.Entry < String,Integer >> allEntries = new ArrayList < Map.Entry < String,Integer >> (counter.entrySet());
    
    Collections.sort(allEntries, new Comparator < Map.Entry < String, Integer >> () {
      public int compare(Map.Entry < String, Integer > first, Map.Entry < String, Integer > second) {
        if (second.getValue().equals(first.getValue()))
            return first.getKey().compareTo(second.getKey());
        else
        return second.getValue().compareTo(first.getValue());
      }
    });
    Map < String,Integer > sortedKeyValueMap = new LinkedHashMap < String,Integer > ();
    for (Map.Entry < String, Integer > entry: allEntries) {
      sortedKeyValueMap.put(entry.getKey(), entry.getValue());
    }
    Iterator it = sortedKeyValueMap.entrySet().iterator();
    int count = 1;
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      System.out.println(pair.getKey());
      it.remove();
      if (count == 10) break;
      count++;
    }
  }
}