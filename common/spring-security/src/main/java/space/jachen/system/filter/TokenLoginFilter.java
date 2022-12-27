package space.jachen.system.filter;

/**
 * @author JaChen
 * @date 2022/12/23 22:45
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import space.jachen.common.helper.JwtHelper;
import space.jachen.common.result.Result;
import space.jachen.common.result.ResultCodeEnum;
import space.jachen.common.util.IpUtil;
import space.jachen.common.util.ResponseUtil;
import space.jachen.model.vo.LoginVo;
import space.jachen.system.custom.CustomUser;
import space.jachen.system.service.LoginLogService;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 * </p>
 *
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {


    @Resource
    private RedisTemplate redisTemplate;

    private LoginLogService loginLogService;

    public TokenLoginFilter(AuthenticationManager authenticationManager,
                            RedisTemplate redisTemplate , LoginLogService loginLogService) {
        //设置认证管理器
        this.setAuthenticationManager(authenticationManager);
        //设置登录的地址和请求方式
        this.setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher("/admin/system/index/login", "POST"));
        this.redisTemplate = redisTemplate;
        this.loginLogService = loginLogService;
    }

    /**
     * 登录认证
     * @param req
     * @param res
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            LoginVo loginVo = new ObjectMapper().readValue(req.getInputStream(), LoginVo.class);

            Authentication authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 登录成功 认证成功调用的方法
     * @param request
     * @param response
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        // 1、通过Authentication获取认证的对象
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        // 2、通过JwtHelper生成token
        String token = JwtHelper.createToken(customUser.getSysUser().getId(), customUser.getSysUser().getUsername());
        // 3、获取用户的权限
        Collection<GrantedAuthority> authorities = customUser.getAuthorities();
        // 4、将权限保存到Redis中
        redisTemplate.boundValueOps(customUser.getUsername()).set(authorities);

        // 5、保存登录日志
        loginLogService.recordLoginLog(customUser.getUsername(), IpUtil.getIpAddress(request),1,"登录成功");

        // 创建一个Map
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        // 6、通过ResponseUtil工具类响应到前端
        ResponseUtil.out(response, Result.ok(map));
    }

    /**
     * 登录失败
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        if (e.getCause() instanceof RuntimeException){
            ResponseUtil.out(response,Result.build(null,204,e.getMessage()));
        }else {
            ResponseUtil.out(response,Result.build(null, ResultCodeEnum.LOGIN_MOBLE_ERROR));
        }
    }
}