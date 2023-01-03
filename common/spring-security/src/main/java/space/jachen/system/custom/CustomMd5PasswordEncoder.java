package space.jachen.system.custom;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import space.jachen.common.util.MD5;

/**
 *
 * TODO:自定义密码组件 密码处理
 *
 * @author JaChen
 * @date 2022/12/23 22:25
 */
@Component
public class CustomMd5PasswordEncoder implements PasswordEncoder {

    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}