/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import entity.VerifyToken;

/**
 *
 * @author Admin
 */
public class TokenFunction {

    public String generateToken(String email) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                + "lmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(50);
        for (int i = 0; i < 50; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString() + '.' + encodeBase64(email);
    }

    public String encodeBase64(String plainText) {
        return Base64.getEncoder().encodeToString(plainText.getBytes());
    }

    public String decodeBase64(String cipherText) {
        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        return new String(decodedBytes);
    }

    public int checkToken(String token, String email) throws Exception {

//        System.out.println(email);
        TokenDAO dao = new TokenDAO();
        if (dao.getTokenByEmail(email) != null) {
            VerifyToken verifyToken = dao.getTokenByEmail(email);
            Date current_Time = new Date();
            Date create_Time = verifyToken.getCreate_Time();
            long getDiff = current_Time.getTime() - create_Time.getTime();
            long getDaysDiff = TimeUnit.MILLISECONDS.toDays(getDiff);
            if (verifyToken.getToken().equals(token)) {
                
                if (getDaysDiff <= 1) {
                    System.out.println("oke");
                    return 1; // Verify Sucess
                } else {
                    return 2; // Token Expired
                }
            } else {
                return 3; // Token Wrong
            }
        } else {
            return 4; //Email unregistered
        }
    }

//    public static void main(String[] args) throws Exception {
//        String regexPass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
//        System.out.println("aaZZa44@".matches(regexPass));
////        boolean flag = checkToken("kX91lOTJfyhYksyOYIkIfzReSfJQ7E48ARAXGVVs2GN71z5f3Q.dGVzdG1haWxAZ21haWwuY29t");
////        System.out.println(flag);
////        String token = "wS6XYVqc3w6ycJT8JsfdjuVpglHTVdNXyIrPnwE4wRRAQydZWY.dGVzdGdtYWlsQGdtYWlsLmNvbQ==";
//
//    }
}
