package com.bilibili.minio.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtils {

    /**
     * 计算上传文件的 MD5 值。
     *
     * @param file MultipartFile 类型的文件对象
     * @return 返回计算得到的 MD5 值
     * @throws IOException 文件读取异常
     * @throws NoSuchAlgorithmException MD5 算法异常
     */
    public static String generateFileMd5(MultipartFile file) throws IOException,NoSuchAlgorithmException{
        return generateMd5(file.getInputStream());
    }

    /**
     * 计算文件名的 MD5 值。
     *
     * @param fileName 文件名（字符串）
     * @return 返回计算得到的 MD5 值
     * @throws NoSuchAlgorithmException MD5 算法异常
     * @throws FileNotFoundException 文件未找到异常
     */
    public static String generateFileNameMd5(String fileName) throws NoSuchAlgorithmException, FileNotFoundException {
        return generateMd5(fileName.getBytes());
    }

    /**
     * 将字节数组转换为十六进制的字符串。
     *
     * @param digest 字节数组，通常是 MD5 算法计算的结果
     * @return 返回十六进制的字符串
     */
    private static String bytesToHex(byte[] digest) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    /**
     * 计算给定字节数组的 MD5 值。
     *
     * @param fileNameInput 输入的字节数组（例如文件名或文件内容）
     * @return 返回计算得到的 MD5 值
     * @throws NoSuchAlgorithmException MD5 算法异常
     */
    private static String generateMd5(byte[] fileNameInput) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(fileNameInput);
        return bytesToHex(messageDigest.digest());
    }

    /**
     * 计算给定输入流内容的 MD5 值。
     *
     * @param inputStream 输入流对象（例如文件输入流）
     * @return 返回计算得到的 MD5 值
     * @throws NoSuchAlgorithmException MD5 算法异常
     */
    private static String generateMd5(InputStream inputStream) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(buffer)) != -1) {
                messageDigest.update(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytesToHex(messageDigest.digest());
    }
}
