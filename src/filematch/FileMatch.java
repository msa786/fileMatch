package filematch;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Md Shahnawaz Alam
 */
public class FileMatch {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        String hex = checksum("/home/hacker/NetBeansProjects/ken hum.mp3", md);
        String hex1 = checksum("/home/hacker/NetBeansProjects/ken hum.mp3", md);
        System.out.println(hex);
        
        if(hex.equals(hex1)){
            System.out.println("Its 100% same");
        }else{
            System.out.println("File did not match");
        }
        
        
    }

    private static String checksum(String filepath, MessageDigest md) throws IOException {

        // DigestInputStream is better, but you also can hash file like this.
        try (InputStream fis = new FileInputStream(filepath)) {
            byte[] buffer = new byte[1024];
            int nread;
            while ((nread = fis.read(buffer)) != -1) {
                md.update(buffer, 0, nread);
            }
        }

        // bytes to hex
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        return result.toString();

    }

}
