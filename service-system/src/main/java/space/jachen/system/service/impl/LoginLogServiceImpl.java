package space.jachen.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.jachen.model.system.SysLoginLog;
import space.jachen.system.mapper.SysLoginLogMapper;
import space.jachen.system.service.LoginLogService;

import javax.annotation.Resource;

/**
 * @author JaChen
 * @date 2022/12/26 11:18
 */
@Service
@Transactional
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public void recordLoginLog(String username, String ipAddr, Integer status, String msg) {
        //创建SysLoginLog对象
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUsername(username);
        sysLoginLog.setIpaddr(ipAddr);
        sysLoginLog.setStatus(status);
        sysLoginLog.setMsg(msg);
        sysLoginLogMapper.insert(sysLoginLog);
    }
}