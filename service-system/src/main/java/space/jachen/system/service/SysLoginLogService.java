package space.jachen.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import space.jachen.model.system.SysLoginLog;
import space.jachen.model.vo.SysLoginLogQueryVo;

/**
 * @author JaChen
 * @date 2022/12/26 10:31
 */
public interface SysLoginLogService extends IService<SysLoginLog> {
    /**
     * 记录日志
     * @param pageParam
     * @param sysLoginLogQueryVo
     * @return
     */
    IPage<SysLoginLog> findPage(Page<SysLoginLog> pageParam, SysLoginLogQueryVo sysLoginLogQueryVo);
}
