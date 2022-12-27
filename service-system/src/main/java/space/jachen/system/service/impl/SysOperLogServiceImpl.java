package space.jachen.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.jachen.model.system.SysOperLog;
import space.jachen.model.vo.SysOperLogQueryVo;
import space.jachen.system.mapper.SysOperLogMapper;
import space.jachen.system.service.SysOperLogService;

import javax.annotation.Resource;

/**
 * @author JaChen
 * @date 2022/12/26 11:51
 */
@Service
@Transactional
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {
    @Resource
    private SysOperLogMapper sysOperLogMapper;

    @Override
    public IPage<SysOperLog> findPage(Page<SysOperLog> pageParam, SysOperLogQueryVo sysOperLogQueryVo) {
        return sysOperLogMapper.findPage(pageParam, sysOperLogQueryVo);
    }
}
