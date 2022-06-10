import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Multithreading extends Thread{
    private String urlOfImages;
    private String imageName;

    Multithreading(String urlOfImages, String imageName){
        this.urlOfImages = urlOfImages;
        this.imageName = imageName;
    }
    @Override
    public void run(){
        savePhoto(urlOfImages, imageName);
    }
    public void savePhoto(String urlOfImages, String imageName) {
        try{
            System.out.println(urlOfImages);
            URL url = new URL(urlOfImages);

            String[] split =  imageName.split("/");
            System.out.println("Save images" + split[split.length -1]);
            String path = "C:\\imaginiPR\\" + split[split.length-1];
            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream(path);
            byte[] bytes = new byte[2048];
            int length;
            while((length = inputStream.read(bytes)) !=-1) {
                outputStream.write(bytes, 0, length);
            }
            inputStream.close();
            outputStream.close();

    } catch (Exception e){

}}}

