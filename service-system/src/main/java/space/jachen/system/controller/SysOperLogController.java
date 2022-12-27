package space.jachen.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.jachen.common.result.Result;
import space.jachen.model.system.SysOperLog;
import space.jachen.model.vo.SysOperLogQueryVo;
import space.jachen.system.service.SysOperLogService;

import javax.annotation.Resource;

/**
 * @author JaChen
 * @date 2022/12/26 11:52
 */
@Api(tags = "操作日志管理")
@RestController
@RequestMapping(value="/admin/system/sysOperLog")
public class SysOperLogController {

    @Resource
    private SysOperLogService sysOperLogService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "sysOperLogVo", value = "查询对象", required = false)
            SysOperLogQueryVo sysOperLogQueryVo) {
        Page<SysOperLog> pageParam = new Page<>(page, limit);
        IPage<SysOperLog> pageModel = sysOperLogService.findPage(pageParam, sysOperLogQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/getById/{id}")
    public Result get(@PathVariable Long id) {
        SysOperLog sysOperLog = sysOperLogService.getById(id);
        return Result.ok(sysOperLog);
    }

}