package space.jachen.system.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import space.jachen.model.system.SysRole;
import space.jachen.system.mapper.SysRoleMapper;

import java.util.List;

/**
 * @author JaChen
 * @date 2022/12/16 13:39
 */
@SpringBootTest
public class SysRoleMapperTest {

    @Autowired(required = false)
    private SysRoleMapper sysRoleMapper;

    @Test
    public void testSelectList() {
        System.out.println(("----- selectAll method test ------"));
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        List<SysRole> users = sysRoleMapper.selectList(null);
        for (SysRole user : users) {
            System.out.println("user = " + user);
        }
    }

}
