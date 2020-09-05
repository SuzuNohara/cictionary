import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;

public class textcomplete{
    public static void main(String args[]){
        try{
            FileWriter myWriter = new FileWriter("filename.txt");
            String data = "";
            for(int i = 97; i <= 122; i++){
                char ous = (char) i;
                data = consumir(data) + "\n";
                System.out.print(data);
            }
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