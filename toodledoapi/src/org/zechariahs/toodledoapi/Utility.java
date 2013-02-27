package org.zechariahs.toodledoapi;

import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utility
{
     public static String hexadecimal(String input, String charsetName) throws UnsupportedEncodingException 
     {
         if (input == null) throw new NullPointerException();
         return asHex(input.getBytes(charsetName));
     }

     private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

     public static String asHex(byte[] buf)
     {
         char[] chars = new char[2 * buf.length];
         for (int i = 0; i < buf.length; ++i)
         {
             chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
             chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
         }
         return new String(chars);
     }
     
     public static String createMD5Hash(String md5) {
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(md5.getBytes());
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < array.length; ++i) {
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
             return sb.toString();
         } catch (java.security.NoSuchAlgorithmException e) {
         }
         return null;
     }
     
}
