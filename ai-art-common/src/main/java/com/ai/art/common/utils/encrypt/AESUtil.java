package com.ai.art.common.utils.encrypt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import cn.hutool.core.exceptions.UtilException;
import org.apache.commons.lang3.StringUtils;


/**
 * @author wangBo
 * @version Id: AESUtil.java, v 0.1 2023/11/24 13:51 wangBo Exp $$
 */
public class AESUtil {

    private static final String KEY_ALGORITHM            = "AES";
    //默认的加密算发
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * AES 加密操作
     * @param content 被加密的文本
     * @param password 加密秘钥的key
     * @return 加密结果
     */
    public static String encrypt(String content, String password) {
        try {
            // 被加密参数判空
            if (StringUtils.isEmpty(content)) {
                return content;
            }
            // 加解密算法
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
            return content;
        }
    }

    /**
     * AES 解密操作
     * @param content 被解密的文本
     * @param password 秘钥的key
     * @return 解密结果
     */
    public static String decrypt(String content, String password) {

        try {
            // 被解密参数判空
            if (StringUtils.isEmpty(content)) {
                return content;
            }
            // 加解密算法
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
            // 执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
            return content;
        }
    }

    /**
     * 生成加解密秘钥
     */
    private static Key getSecretKey(String key) throws NoSuchAlgorithmException {
        // 返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            // AES 要求密钥长度为 128
            kg.init(128, random);
            // 生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new UtilException("GET_SECRET_KEY_ERROR");
        }
    }

}