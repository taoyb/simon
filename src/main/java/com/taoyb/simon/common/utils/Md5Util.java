package com.taoyb.simon.common.utils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by taoyb on 2016-12-08.
 */
public class Md5Util {
    private static final String HEX_NUMS_STR = "0123456789ABCDEF";
    //private static final Integer SALT_LENGTH = 0; 随机个数
    public static final String STR_PWD = "fgfjol;aweurU*(8P$EHNFK>SFNFJrr435IOFJPJ(*)EUKLEJ#ur98r04puio4U#*()%PU&@#*(%PU:E#@?J:";
    /**
     * 将16进制字符串转换成字节数组
     *
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
        }
        return result;
    }
    /**
     * 将指定byte数组转换成16进制字符串
     *
     * @param b
     * @return
     */
    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }
    /**
     * 验证口令是否合法
     *
     * @param password
     * @param passwordInDb
     * @param SALT_LENGTH  随机个数
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static boolean validPassword(String password, String passwordInDb, Integer SALT_LENGTH)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //将16进制字符串格式口令转换成字节数组
        byte[] pwdInDb = hexStringToByte(passwordInDb);
        //声明盐变量
        byte[] salt = new byte[SALT_LENGTH];
        //将盐从数据库中保存的口令字节数组中提取出来
        System.arraycopy(pwdInDb, 0, salt, 0, SALT_LENGTH);
        //创建消息摘要对象
        MessageDigest md = MessageDigest.getInstance("MD5");
        //将盐数据传入消息摘要对象
        md.update(salt);
        //将口令的数据传给消息摘要对象
        md.update(password.getBytes("UTF-8"));
        //生成输入口令的消息摘要
        byte[] digest = md.digest();
        //声明一个保存数据库中口令消息摘要的变量
        byte[] digestInDb = new byte[pwdInDb.length - SALT_LENGTH];
        //取得数据库中口令的消息摘要
        System.arraycopy(pwdInDb, SALT_LENGTH, digestInDb, 0, digestInDb.length);
        //比较根据输入口令生成的消息摘要和数据库中消息摘要是否相同
        if (Arrays.equals(digest, digestInDb)) {
            //口令正确返回口令匹配消息
            return true;
        } else {
            //口令不正确返回口令不匹配消息
            return false;
        }
    }
    public static String getEncryptedPwd(String password){
        return isRandom(password,0);
    }
    public static String getEncryptedPwd(String password,Integer SALT_LENGTH){
        return isRandom(password,SALT_LENGTH);
    }
    /**
     * 获得加密后的16进制形式口令
     *
     * @param password
     * @param SALT_LENGTH 随机个数
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private static String isRandom(String password,Integer SALT_LENGTH) {
        //声明加密后的口令数组变量
        byte[] pwd = null;
        //随机数生成器
        SecureRandom random = new SecureRandom();
        //声明盐数组变量
        byte[] salt = new byte[SALT_LENGTH];
        //将随机数放入盐变量中
        random.nextBytes(salt);
        //声明消息摘要对象
        MessageDigest md = null;
        //创建消息摘要
        try {
            md = MessageDigest.getInstance("MD5");
            //将盐数据传入消息摘要对象
            md.update(salt);
            //将口令的数据传给消息摘要对象
            md.update(password.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //获得消息摘要的字节数组
        byte[] digest = md.digest();
        //因为要在口令的字节数组中存放盐，所以加上盐的字节长度
        pwd = new byte[digest.length + SALT_LENGTH];
        //将盐的字节拷贝到生成的加密口令字节数组的前12个字节，以便在验证口令时取出盐
        System.arraycopy(salt, 0, pwd, 0, SALT_LENGTH);
        //将消息摘要拷贝到加密口令字节数组从第13个字节开始的字节
        System.arraycopy(digest, 0, pwd, SALT_LENGTH, digest.length);
        //将字节数组格式加密后的口令转化为16进制字符串格式的口令
        return byteToHexString(pwd);
    }
    private static Map users = new HashMap();
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //String pwd = getEncryptedPwd("123fdsafda");
        //System.out.println(pwd);
        String userName = "zyg";
        String password = "123fddgsreyjfhg&*^T^GJKLHUI";
        registerUser(userName,password,0);

        userName = "changong";
        password = "456";
        registerUser(userName,password,0);

        String loginUserId = "zyg";
        String pwd = "123fddgsreyjfhg&*^T^GJKLHUI";
        try {
            if(loginValid(loginUserId,pwd,0)){
                System.out.println("欢迎登陆！！！");
            }else{
                System.out.println("口令错误，请重新输入！！！");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    /**
     * 注册用户
     *
     * @param userName
     * @param password
     */
    public static void registerUser(String userName, String password, Integer SALT_LENGTH) {
        String encryptedPwd = null;
        encryptedPwd = getEncryptedPwd(password, SALT_LENGTH);
        System.out.println(encryptedPwd);
        users.put(userName, encryptedPwd);
    }
    /**
     * 验证登陆
     * @param userName
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static boolean loginValid(String userName, String password, Integer SALT_LENGTH)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String pwdInDb = (String) users.get(userName);
        if (null != pwdInDb) { // 该用户存在
            return validPassword(password, pwdInDb, SALT_LENGTH);
        } else {
            System.out.println("不存在该用户！！！");
            return false;
        }
    }
}
