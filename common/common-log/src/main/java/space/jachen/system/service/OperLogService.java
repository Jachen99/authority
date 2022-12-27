package space.jachen.system.service;

import space.jachen.model.system.SysOperLog;

/**
 *
 * 保存操作日志的接口
 *
 * @author JaChen
 * @date 2022/12/26 12:01
 */
public interface OperLogService {

    /**
     * 保存操作日志
     * @param sysOperLog
     */
    void saveSysOperLog(SysOperLog sysOperLog);

}
