package space.jachen.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.jachen.model.system.SysOperLog;
import space.jachen.system.mapper.SysOperLogMapper;
import space.jachen.system.service.OperLogService;

import javax.annotation.Resource;

/**
 * @author JaChen
 * @date 2022/12/26 12:04
 */
@Service
@Transactional
public class OperLogServiceImpl implements OperLogService {

    @Resource
    private SysOperLogMapper sysOperLogMapper;

    @Async //异步调用该方法，会重开一个线程执行
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}