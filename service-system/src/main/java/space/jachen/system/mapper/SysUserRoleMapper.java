package space.jachen.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import space.jachen.model.system.SysRole;
import space.jachen.model.system.SysUser;
import space.jachen.model.system.SysUserRole;

/**
 * @author JaChen
 * @date 2022/12/22 23:55
 */
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
}
