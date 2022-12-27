package space.jachen.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import space.jachen.model.system.SysOperLog;
import space.jachen.model.vo.SysOperLogQueryVo;

/**
 * @author JaChen
 * @date 2022/12/26 11:49
 */
public interface SysOperLogService extends IService<SysOperLog> {
    IPage<SysOperLog> findPage(Page<SysOperLog> pageParam, SysOperLogQueryVo sysOperLogQueryVo);
}
