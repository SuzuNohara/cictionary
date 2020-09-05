import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class main {
    public static void main(String args[]){
        List palabras = new ArrayList<String>();
        try{
            for(int i = 97; i <= 122; i++){ // 122
                char ous = (char) i;
                String semilla = ous + "";
                palabras.addAll(main.getData(semilla));
            }
            String result = "";
            for(int i = 0; i < palabras.size(); i++){
                result += palabras.get(i) + ",";
            }
            exportData(result);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static List<String> getData(String initial) throws ParserConfigurationException, SAXException, IOException {
        String result = consumir(initial);
        List temp = new ArrayList<String>();
        List data = new ArrayList<String>();
        if(result.length() > 0) {
            temp.addAll(Arrays.asList(result.split(",")));
        }
        if(temp.size() > 7){
            for(int i = 97; i <= 122; i++){ // 122
                char ous = (char) i;
                String semilla = ous + "";
                System.out.println("Iniciando rama :: " + initial + semilla + " -- " + result);
                data.addAll(main.getData(initial + semilla));
            }
        }else if(temp.size() >= 1){
            System.out.println("Final alcanzado (" + initial + "):: " + result);
            data.addAll(temp);
        }
        return data;
    }

    public static void exportData(String data){
        try{
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write(data);
            myWriter.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static String consumir(String semilla) throws IOException, ParserConfigurationException, SAXException
    {
        URL url= new URL("https://dle.rae.es/srv/keys?q=" + semilla);
        URLConnection urlConnection = url.openConnection();
        urlConnection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        InputStream input = new BufferedInputStream(urlConnection.getInputStream());
        String out = "";
        int i;
        while((i = input.read())!=-1) {
            out += (char)i;
        }
        return out.replace("\"", "").replace("[", "").replace("]", "").replace("\\u00e1", "\u00e1").replace("\\u00e9", "\u00e9").replace("\\u00ed", "\u00ed").replace("\\u00f3", "\u00f3").replace("\\u00fa", "\u00fa").replace("\\u00f1", "\u00f1");
    }
}
