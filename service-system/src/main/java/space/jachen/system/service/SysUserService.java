package space.jachen.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import space.jachen.model.system.SysUser;
import space.jachen.model.vo.SysUserQueryVo;

import java.util.List;
import java.util.Map;

/**
 * @author JaChen
 * @date 2022/12/22 23:34
 */
public interface SysUserService extends IService<SysUser> {

    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo adminQueryVo);

    void updateStatus(Long id, Integer status);


    SysUser getByUserName(String username);

    Map<String, Object> getUserInfoByUserId(Long userId);

    /**
     * 根据用户id获取用户按钮权限标识符
     * @param id
     * @return
     */
    List<String> getUserBtnPermsByUserId(Long id);
}