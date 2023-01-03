package space.jachen.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import space.jachen.model.system.SysMenu;

import java.util.List;

/**
 * @author JaChen
 * @date 2022/12/21 11:48
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectMenuListByUserId(Long userId);
}
