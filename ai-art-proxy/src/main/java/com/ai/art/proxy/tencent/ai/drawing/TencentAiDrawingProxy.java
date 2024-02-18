package com.ai.art.proxy.tencent.ai.drawing;

import com.ai.art.proxy.tencent.ai.model.request.drawing.TextToImageReqBO;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author wangBo
 * @version Id: TencentAiDrawingProxy.java, v 0.1 2024/2/6 15:54 wangBo Exp $$
 */
@Service
public class TencentAiDrawingProxy {

    @Value("${tencent.meId}")
    private String secretAiId;
    @Value("${tencent.meKey}")
    private String secretAiKey;
    // singleton client for connection reuse and better performance
    private static final OkHttpClient client = new OkHttpClient();

    /**
     * 请求腾讯的ai画图
     * @param reqBO 请求参数
     * @return 结果
     */
    public String textToImage(TextToImageReqBO reqBO) {
        try {
            String secretId = secretAiId;
            String secretKey = secretAiKey;
            String token = "";
            String service = "aiart";
            String version = "2022-12-29";
            String action = "TextToImage";
            Gson gson = new Gson();
            // 请求参数
            String body = gson.toJson(reqBO);
            // 请求的节点
            String region = "ap-guangzhou";
            return doRequest(secretId, secretKey, service, version, action, body, region, token);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static String doRequest(String secretId, String secretKey, String service,
                                   String version, String action, String body,
                                   String region, String token)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {

        Request request = buildRequest(secretId, secretKey, service, version, action, body, region, token);

        System.out.println(request.method() + " " + request.url());
        System.out.println(request.headers());
        System.out.println(body);

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static Request buildRequest(String secretId, String secretKey,
                                       String service, String version, String action,
                                       String body, String region, String token)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String host = "aiart.tencentcloudapi.com";
        String endpoint = "https://" + host;
        String contentType = "application/json; charset=utf-8";
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String auth = getAuth(secretId, secretKey, host, contentType, timestamp, body);
        return new Request.Builder().header("Host", host)
                .header("X-TC-Timestamp", timestamp)
                .header("X-TC-Version", version)
                .header("X-TC-Action", action)
                .header("X-TC-Region", region)
                .header("X-TC-Token", token)
                .header("X-TC-RequestClient", "SDK_JAVA_BAREBONE")
                .header("Authorization", auth)
                .url(endpoint)
                .post(RequestBody.create(MediaType.parse(contentType), body))
                .build();
    }

    private static String getAuth(String secretId, String secretKey,
                                  String host, String contentType,
                                  String timestamp, String body)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String canonicalUri = "/";
        String canonicalQueryString = "";
        String canonicalHeaders = "content-type:" + contentType + "\nhost:" + host + "\n";
        String signedHeaders = "content-type;host";

        String hashedRequestPayload = sha256Hex(body.getBytes(StandardCharsets.UTF_8));
        String canonicalRequest = "POST" + "\n" + canonicalUri + "\n" + canonicalQueryString + "\n" + canonicalHeaders + "\n" + signedHeaders + "\n" + hashedRequestPayload;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = sdf.format(new Date(Long.valueOf(timestamp + "000")));
        String service = host.split("\\.")[0];
        String credentialScope = date + "/" + service + "/" + "tc3_request";
        String hashedCanonicalRequest = sha256Hex(canonicalRequest.getBytes(StandardCharsets.UTF_8));
        String stringToSign = "TC3-HMAC-SHA256\n" + timestamp + "\n" + credentialScope + "\n" + hashedCanonicalRequest;

        byte[] secretDate = hmac256(("TC3" + secretKey).getBytes(StandardCharsets.UTF_8), date);
        byte[] secretService = hmac256(secretDate, service);
        byte[] secretSigning = hmac256(secretService, "tc3_request");
        String signature = printHexBinary(hmac256(secretSigning, stringToSign)).toLowerCase();
        return "TC3-HMAC-SHA256 " + "Credential=" + secretId + "/" + credentialScope + ", " + "SignedHeaders=" + signedHeaders + ", " + "Signature=" + signature;
    }

    public static String sha256Hex(byte[] b) throws NoSuchAlgorithmException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(b);
        return printHexBinary(d).toLowerCase();
    }

    private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

    public static String printHexBinary(byte[] data) {
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    public static byte[] hmac256(byte[] key, String msg) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
        mac.init(secretKeySpec);
        return mac.doFinal(msg.getBytes(StandardCharsets.UTF_8));
    }

}