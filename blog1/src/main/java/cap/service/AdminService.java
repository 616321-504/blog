package cap.service;

import cap.mapper.AdminMapper;
import cap.model.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 刘贤熔
 * @version 1.0.0
 * @createTIme 2018/9/20 10:46
 **/
public class AdminService {
    @Resource
    private AdminMapper adminMapper;

    public Admin login(Admin admin) {
        return adminMapper.selectBySelective(admin);
    }
}
