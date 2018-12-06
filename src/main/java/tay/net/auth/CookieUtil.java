package tay.net.auth;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    /**
     * 创建cookie
     *
     * @param httpServletResponse
     * @param name
     * @param value
     * @param secure
     * @param maxAge
     * @param domain
     */
    public static void create(HttpServletResponse httpServletResponse, String name, String value, Boolean secure,
                              Integer maxAge, String domain) {

        Cookie cookie = new Cookie(name, value);
        // 仅能在HTTPS链接中被浏览器传递到服务器
        cookie.setSecure(secure);
        // 使得JS脚本布尔诺那个读取cookies
        cookie.setHttpOnly(true);
        // 0:立即过期，1:永不过期
        cookie.setMaxAge(maxAge);
        // "/"：对所有路径可见
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }

    /**
     * 清除cookie
     *
     * @param httpServletResponse
     * @param name
     */
    public static void clear(HttpServletResponse httpServletResponse, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }

    /**
     * 获取cookie值
     *
     * @param httpServletRequest
     * @param name
     * @return
     */
    public static String getValue(HttpServletRequest httpServletRequest, String name) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
        return cookie != null ? cookie.getValue() : null;

    }
}
