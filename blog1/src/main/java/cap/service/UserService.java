package cap.service;

/**
 * @author 刘贤熔
 * @version 1.0.0
 * @createTIme 2018/9/25 10:09
 **/


import cap.mapper.UserMapper;
import cap.model.User;
import cap.util.PageControl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User login(User user) {
        return userMapper.login(user);
    }

    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public int getIdByUserName(String username) {
        try {
            return userMapper.selectByUsername(username).getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public User getByEmail(String email) {
        try {
            return userMapper.selectByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int register(User user) {
        int res = userMapper.insertSelective(user);
        return res;
    }

    public int setIsAppliedById(int id) {
        User u = userMapper.selectByPrimaryKey(id);
        u.setIsApplied(true);
        userMapper.updateByPrimaryKeySelective(u);
        return 0;
    }

    public User getByIdPwd(int userId, String password) {
        User u = new User();
        u.setId(userId);
        u.setPassword(password);
        return userMapper.selectBySelective(u).get(0);
    }

    public int updatePwdById(int userId, String password) {
        User u = userMapper.selectByPrimaryKey(userId);
        u.setPassword(password);
        return userMapper.updateByPrimaryKeySelective(u);
    }

    public int setIsProfile(int userId) {
        User u = userMapper.selectByPrimaryKey(userId);
        u.setIsProfile(true);
        return userMapper.updateByPrimaryKeySelective(u);
    }

    public PageControl getUserOfPage(String curPageStr) {
        int total = userMapper.count();
        PageControl pc = new PageControl(curPageStr, total);
        List<User> uList = userMapper.getUserByPage((pc.getCurPage() - 1) * pc.getPageSize(), pc.getPageSize());
        pc.setList(uList);
        return pc;
    }

    public int deleteUser(int uId) {
        User u = userMapper.selectByPrimaryKey(uId);
        u.setIsDelete(true);
        return userMapper.updateByPrimaryKeySelective(u);
    }

    public int activeUser(int uId) {
        User u = userMapper.selectByPrimaryKey(uId);
        u.setIsDelete(false);
        return userMapper.updateByPrimaryKeySelective(u);
    }

    public List<User> getAllUser() {
        return userMapper.selectAll();
    }

    public List<User> getActiveUser(int num) {
        return userMapper.getActiveUser(num);
    }

    public User selectByUserName(String name) {
        User user = userMapper.selectByUsername(name);
        return user;
    }

    public User selectByEmail(String email) {
        User user = userMapper.selectByEmail(email);
        return user;
    }

    public int getTotalCount() {
        return userMapper.count();
    }
}
