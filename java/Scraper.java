import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraper {

    public static List<Data> ListAll(){

        try {

            Document document = Jsoup.connect("https://ms.wikipedia.org/wiki/Malaysia").get();

            Elements table = ((org.jsoup.nodes.Document) document).getElementsByClass("wikitable");

            Elements s = table.get(1).getElementsByClass("wikitable");

            Elements column1 = s.select("th");

            Elements column2 = s.select("td");

            List<Data> result = new ArrayList<>();

            for (int i = 0; i < column1.size(); i++) {

                result.add(new Data("" + column1.get(i).text(), "" + column2.get(i).text()));
            }

            System.out.println("extracting the data . . . . .");
            Thread.sleep(4000);

            for (Data data : result) {
                System.out.println(data.getCol1() + "     :       " + data.getCol2());
            }

            return result;
        }

        catch(InterruptedException e){
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("data extracted");
            return null;
        }
    }
