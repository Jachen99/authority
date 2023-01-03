package space.jachen.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.jachen.model.system.SysLoginLog;
import space.jachen.model.vo.SysLoginLogQueryVo;
import space.jachen.system.mapper.SysLoginLogMapper;
import space.jachen.system.service.SysLoginLogService;

import javax.annotation.Resource;

/**
 * @author JaChen
 * @date 2022/12/26 10:33
 */
@Service
@Transactional
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

    @Resource
    private SysLoginLogMapper sysLoginLogMapper;
    @Override
    public IPage<SysLoginLog> findPage(Page<SysLoginLog> pageParam, SysLoginLogQueryVo sysLoginLogQueryVo) {
        return sysLoginLogMapper.findPage(pageParam, sysLoginLogQueryVo);
    }
}
