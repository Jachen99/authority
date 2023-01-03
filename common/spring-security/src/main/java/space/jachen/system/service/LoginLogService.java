package space.jachen.system.service;

/**
 * @author JaChen
 * @date 2022/12/26 11:10
 */
public interface LoginLogService {

    //记录日志
    void recordLoginLog(String username, String ipAddr , Integer status , String msg);

}
