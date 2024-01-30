package com.shl.ohguohgutalk.util;

import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Slf4j
@Component
public class SHA256Util {
    // TODO : 키-스트레칭 (적용할지 말지 고민 -> sha 한걸 여러번) >> 후에 적용하기

    /**
     * 비밀번호 암호화 - SHA256
     * @param pwd 암호화 될 password
     */
    public String getSHA256HashedPwd(@NotEmpty String pwd)  {
        String hashedPwd = "";
        String salt = getSalt();

        try {
            hashedPwd = getHashedPwd("SHA-256", pwd, salt);
        } catch (NoSuchAlgorithmException e) {
            // NoSuchAlgorithmException : 특정 암호 알고리즘이 요구되었음에도 현재의 환경에서는 사용가능하지 않은 경우 발생
            log.error("NoSuchAlgorithmException 발생 - 대체 알고리즘(SHA-1) 사용 : {}", e);

            try {
                // 대체 알고리즘(SHA-1) 사용
                hashedPwd = getHashedPwd("SHA-1", pwd, salt);
            } catch (NoSuchAlgorithmException ex) {
                log.error("대체 알고리즘(SHA-1)도 사용 불가능 : {}", ex);
                // 회원가입에 실패하였습니다.
            }
        }

        return hashedPwd;
    }

    /**
     * 암호화가 적용된 비밀번호 반환 메소드
     * @param algorithm 암호화 알고리즘 종류
     * @param pwd 암호화 되기전 비밀번호
     * @param salt 솔트
     * @return 암호화가 적용된 비밀번호
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    @NotNull
    private static String getHashedPwd(String algorithm, String pwd, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        StringBuilder sb = new StringBuilder();
        md.update((pwd + salt).getBytes());
        for (byte b : md.digest()) {
            // 10진수의 문자열로 변경
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * 솔트(salt) 생성
     * @return 생성된 솔트
     */
    @NotNull
    private String getSalt() {
        // salt(솔트) 처리 > 임의의 문자열을 붙임
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[20];

        // 난수 생성
        sr.nextBytes(salt);

        // 10진수의 문자열로 변경
        StringBuilder sb = new StringBuilder();
        for(byte b : salt) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
