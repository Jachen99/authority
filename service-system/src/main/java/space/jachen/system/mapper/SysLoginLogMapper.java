package space.jachen.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import space.jachen.model.system.SysLoginLog;
import space.jachen.model.vo.SysLoginLogQueryVo;

/**
 * @author JaChen
 * @date 2022/12/26 10:35
 */
@Repository
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {
    IPage<SysLoginLog> findPage(Page<SysLoginLog> page, @Param("vo") SysLoginLogQueryVo sysLoginLogQueryVo);
}
