package space.jachen.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import space.jachen.model.system.SysRole;
import space.jachen.model.vo.SysRoleQueryVo;

/**
 * @author JaChen
 * @date 2022/12/16 13:37
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    IPage<SysRole> selectPage2(Page<SysRole> page,@Param("vo") SysRoleQueryVo queryVo);

}
