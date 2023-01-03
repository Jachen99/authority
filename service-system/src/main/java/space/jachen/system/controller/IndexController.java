package space.jachen.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import space.jachen.common.helper.JwtHelper;
import space.jachen.common.result.Result;
import space.jachen.common.result.ResultCodeEnum;
import space.jachen.common.util.MD5;
import space.jachen.model.system.SysUser;
import space.jachen.model.vo.LoginVo;
import space.jachen.system.execption.MyException;
import space.jachen.system.service.SysUserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JaChen
 * @date 2022/12/19 10:12
 */

@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {


    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     * @return
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {

        SysUser sysUser = sysUserService.getByUserName(loginVo.getUsername());
        if (sysUser == null){
            throw new MyException(ResultCodeEnum.ACCOUNT_ERROR);
        }
        if (!MD5.encrypt(loginVo.getPassword()).equals(sysUser.getPassword())){
            throw new MyException(ResultCodeEnum.PASSWORD_ERROR);
        }
        if (sysUser.getStatus() == 0){
            throw new MyException(ResultCodeEnum.ACCOUNT_STOP);
        }
        Map<String, Object> map = new HashMap<>();

        map.put("token", JwtHelper.createToken(sysUser.getId(),sysUser.getUsername()));
        return Result.ok(map);
    }
    /**
     * 获取用户信息
     * @return
     */
    @ApiOperation(value = "获取用户信息")

    @GetMapping("/info")
    public Result info(HttpServletRequest request) {

        String token = request.getHeader("token");

        Long userId = JwtHelper.getUserId(token);

        Map<String,Object> map = sysUserService.getUserInfoByUserId(userId);

        return Result.ok(map);
    }
    /**
     * 退出
     * @return
     */
    @ApiOperation(value = "退出")

    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();
    }

}