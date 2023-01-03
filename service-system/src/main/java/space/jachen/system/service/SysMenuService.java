package space.jachen.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import space.jachen.model.system.SysMenu;
import space.jachen.model.vo.AssginMenuVo;

import java.util.List;

/**
 * @author JaChen
 * @date 2022/12/21 11:49
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 菜单树形数据
     * @return
     */
    List<SysMenu> findNodes();

    /**
     * 根据角色获取授权权限数据
     * @return
     */
    List<SysMenu> findSysMenuByRoleId(Long roleId);

    /**
     * 保存角色权限
     * @param  assginMenuVo
     */
    void doAssign(AssginMenuVo assginMenuVo);

}