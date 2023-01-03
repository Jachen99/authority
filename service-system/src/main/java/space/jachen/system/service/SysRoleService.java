package space.jachen.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import space.jachen.model.system.SysRole;
import space.jachen.model.vo.AssginRoleVo;
import space.jachen.model.vo.SysRoleQueryVo;

import java.util.List;
import java.util.Map;

/**
 * @author JaChen
 * @date 2022/12/16 14:14
 */
public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> selectPage2(Page<SysRole> sysRolePage, SysRoleQueryVo queryVo);

    Map<String,Object> getRolesByUserId(Long userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
