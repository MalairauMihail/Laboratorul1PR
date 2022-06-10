import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException{
        Main main = new Main();
        main.initialSocket("me.utm.md", 80);
    }
    public void initialSocket(String url, int port) throws IOException{
        System.out.println("Init socket from: "  +url +" with port " +port);
        Socket socket = new Socket(url, port);
        PrintWriter printWriter= new PrintWriter(socket.getOutputStream());
        printWriter.println("GET / HTTP/1.1");
        printWriter.println("Host: " +url);
        printWriter.println("");
        printWriter.flush();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String rsp;
        while((rsp = bufferedReader.readLine())!=null) {
            selectPhotos(rsp);
        }
        bufferedReader.close();
        printWriter.close();
        socket.close();

    }
    public void selectPhotos(String rsp){
        Pattern pattern = Pattern.compile("[^\\'\"=\\s]+\\.(jpe?g|png|gif)");
        String urlImages;
        String imageName;
        Matcher matcher;
        matcher = pattern.matcher(rsp);
        while(matcher.find()){
            imageName = matcher.group();
            if (imageName.startsWith("http")) {
                urlImages =  imageName;
            }
            else{
                urlImages = "http://me.utm.md/" + imageName;
            }
            System.out.println("Selectam o noua img " +urlImages);
            Multithreading multithreading = new Multithreading(urlImages, imageName);
            multithreading.run();

            }
        }
    }

