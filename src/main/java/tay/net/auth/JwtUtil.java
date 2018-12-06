package tay.net.auth;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

/**
 * 生成和解析JWT TOKEN
 */
public class JwtUtil {

    /**
     * 生成JWT Token
     *
     * @param signingKey
     * @param subject
     * @return
     */
    public static String generateToken(String signingKey, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, signingKey);

        return builder.compact();
    }

    /**
     * 解析JWT Token
     *
     * @param request
     * @param jwtTokenCookieName
     * @param signingKey
     * @return
     */
    public static String getSubject(HttpServletRequest request, String jwtTokenCookieName, String signingKey) {
        String token = CookieUtil.getValue(request, jwtTokenCookieName);
        return Optional.ofNullable(token).map(String::toString).orElse(null);
//        if (token == null) {
//            return null;
//        }
//        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
    }
}
