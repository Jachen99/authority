package space.jachen.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.jachen.common.helper.MenuHelper;
import space.jachen.common.helper.RouterHelper;
import space.jachen.model.system.SysMenu;
import space.jachen.model.system.SysRole;
import space.jachen.model.system.SysUser;
import space.jachen.model.vo.RouterVo;
import space.jachen.model.vo.SysUserQueryVo;
import space.jachen.system.mapper.SysMenuMapper;
import space.jachen.system.mapper.SysUserMapper;
import space.jachen.system.service.SysUserService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author JaChen
 * @date 2022/12/22 23:34
 */
@Transactional
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void updateStatus(Long id, Integer status) {
        //创建SysUser对象
        SysUser sysUser = new SysUser();
        //设置id
        sysUser.setId(id);
        //设置用户状态
        sysUser.setStatus(status);
        //调用SysUserMapper中更新的方法
        sysUserMapper.updateById(sysUser);
    }

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo userQueryVo){

        return sysUserMapper.selectPage(pageParam, userQueryVo);
    }

    @Override
    public SysUser getByUserName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return sysUserMapper.selectOne(wrapper);
    }


    @Resource
    private SysMenuMapper sysMenuMapper;
    @Override
    public Map<String, Object> getUserInfoByUserId(Long userId) {

        // 1、获取用户信息
        SysUser sysUser = sysUserMapper.selectById(userId);
        // 2.1、调用根据id获取权限菜单的方法
        List<SysMenu> menuList = getUserMenusByUserId(userId);
        // 2.2、将UserMenuList转化为菜单树
        List<SysMenu> userMenuTree = MenuHelper.buildTree(menuList);
        // 2.3、将菜单树转化为路由
        List<RouterVo> routerVoList = RouterHelper.buildRouters(userMenuTree);
        // 3、获取用户的按钮权限标识符
        List<String> buttonPermissons = getUserBtnPermsByUserId(userId);


        Map<String,Object> map = new HashMap<>();
        map.put("name",sysUser.getName());

        map.put("roles",new HashSet<>());
        map.put("avatar",sysUser.getHeadUrl());
        map.put("routers", routerVoList);
        map.put("buttons", buttonPermissons);

        return map;
    }

    private List<SysMenu> getUserMenusByUserId(Long userId) {

        List<SysMenu> userMenuList = null;
        //判断该用户是否是系统管理员
        if(userId == 1L){
            //证明是系统管理员
            userMenuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("status",1).orderByAsc("sort_value"));
        }else{
            //根据用户id查询用户的权限菜单
            userMenuList = sysMenuMapper.selectMenuListByUserId(userId);
        }
        return userMenuList;
    }

    @Override
    public List<String> getUserBtnPermsByUserId(Long id) {
        //调用根据用户id获取权限菜单的方法
        List<SysMenu> userMenuList = getUserMenusByUserId(id);
        //创建一个保存用户按钮权限标识的List
        List<String> buttonPermissons = new ArrayList<>();
        //遍历权限菜单
        for (SysMenu sysMenu : userMenuList) {
            //将SysMenu的type值为2的perms的值放到buttonPermissons中
            if(sysMenu.getType() == 2){
                buttonPermissons.add(sysMenu.getPerms());
            }
        }
        return buttonPermissons;
    }
}