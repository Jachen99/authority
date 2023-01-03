package space.jachen.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import space.jachen.model.system.SysOperLog;
import space.jachen.model.vo.SysOperLogQueryVo;

/**
 * @author JaChen
 * @date 2022/12/26 11:46
 */
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    IPage<SysOperLog> findPage(Page<SysOperLog> page, @Param("vo") SysOperLogQueryVo sysOperLogQueryVo);
}
