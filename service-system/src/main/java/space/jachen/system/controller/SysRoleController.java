package space.jachen.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import space.jachen.common.result.Result;
import space.jachen.model.system.SysRole;
import space.jachen.model.vo.AssginRoleVo;
import space.jachen.model.vo.SysRoleQueryVo;
import space.jachen.system.annotation.Log;
import space.jachen.system.enums.BusinessType;
import space.jachen.system.execption.MyException;
import space.jachen.system.service.SysRoleService;

import java.sql.Wrapper;
import java.util.List;
import java.util.Map;

/**
 * @author JaChen
 * @date 2022/12/16 14:12
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;


    @Log(title = "角色管理",businessType = BusinessType.ASSIGN)
    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId) {
        Map<String, Object> roleMap = sysRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }

    @Log(title = "角色管理",businessType = BusinessType.ASSIGN)
    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation(value = "获取全部角色列表")
    @GetMapping("/findAll")
    public Result<List<SysRole>> findAll() {

//        try {
//            int a = 10/0;
//        }catch(Exception e) {
//            throw new MyException(20001,"出现自定义异常");
//        }

        List<SysRole> roleList = sysRoleService.list();
        return Result.ok(roleList);
    }


    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation(value = "查询指定ID的角色")
    @GetMapping("/findOne/{id}")
    public Result<SysRole> findOne(@PathVariable Long id){

        SysRole role = sysRoleService.getById(id);

        return Result.ok(role);

    }


    @Log(title = "角色管理",businessType = BusinessType.INSERT)
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @ApiOperation(value = "添加角色")
    @PostMapping("/save")
    public Result<SysRole> save(@RequestBody SysRole sysRole){

        sysRoleService.save(sysRole);
        return Result.ok();

    }

    @Log(title = "角色管理",businessType = BusinessType.UPDATE)
    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @ApiOperation(value = "修改角色")
    @PutMapping("/update")
    public Result<SysRole> updateById(@RequestBody SysRole sysRole){

        sysRoleService.updateById(sysRole);

        return Result.ok();

    }


    @Log(title = "角色管理",businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation(value = "删除角色")
    @DeleteMapping("/remove/{id}")
    public Result<SysRole> deleteById(@PathVariable Long id){

        sysRoleService.removeById(id);

        return Result.ok();

    }


    @Log(title = "角色管理",businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation(value = "批量删除角色")
    @DeleteMapping("/batchRemove")
    public Result<SysRole> removeByIds(@RequestBody List<Long> ids){

        sysRoleService.removeByIds(ids);

        return Result.ok();
    }


    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation(value = "获取分页列表(mp方式)")
    @GetMapping("/v1/{page}/{limit}")
    public Result<IPage<SysRole>> index1(
            @ApiParam(name = "page",value = "当前页面",required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable Long limit,

            @ApiParam(name = "SysRoleQueryVo", value = "查询对象")SysRoleQueryVo queryVo
            ){
        // 分页参数
        IPage<SysRole> iPage = new Page<>(page,limit);
        // 查询参数
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        if (queryVo.getRoleName() != null){
            wrapper.like("role_name", queryVo.getRoleName());
        }
        IPage<SysRole> result = sysRoleService.page(iPage, wrapper);
        return Result.ok(result);

    }


    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation(value = "获取分页列表(xml方式)")
    @GetMapping("/v2/{page}/{limit}")
    public Result<IPage<SysRole>> index2(
            @ApiParam(name = "page",value = "当前页面",required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable Long limit,

            @ApiParam(name = "SysRoleQueryVo", value = "查询对象")SysRoleQueryVo queryVo
    ){
        Page<SysRole> sysRolePage = new Page<>(page, limit);
        IPage<SysRole> sysRoleIPage = sysRoleService.selectPage2(sysRolePage, queryVo);
        return Result.ok(sysRoleIPage);
    }


}
