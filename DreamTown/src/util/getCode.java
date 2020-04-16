package util;

public class getCode {

    public static String vcode()
     {
        String vcode = "";
        for (int i = 0; i < 6; i++) {
        vcode = vcode + (int)(Math.random() * 9);
    }
        return vcode;
    }
}
