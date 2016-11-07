package SimpleEnrollmentAndSaveDemo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    public static int byteToUnsignedInt(byte b) {
        return 0x00 << 24 | b & 0xff;
    }

    public static String extractDate(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group() : null;
    }
    
    public static int getIDFromResult(String verifiedIDString) {
        String[] array = verifiedIDString.split(":");
        return Integer.parseInt(array[1].toString().trim());
    }

    public static byte[] hexConversion(String input) {
        int convertedInteger = Integer.decode(input);
        //Sytem.out.println(input);
        byte[] bytes = new byte[1];
        //for (int i = 0; i < 4; i++) {
        bytes[0] = (byte) (convertedInteger);
        return bytes;
    }
    
    public static String byteToStringConversion(byte data) {
    	byte[] bytes = new byte[1];
    	bytes[0] = data;
    	String str = String.format("%02X ", data);// new String(bytes);
    	
    	//System.out.print(str + " -- ");
        return str;
    }

    public static String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
