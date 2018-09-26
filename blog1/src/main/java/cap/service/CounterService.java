package cap.service;

import cap.mapper.CounterMapper;
import cap.model.Counter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 刘贤熔
 * @version 1.0.0
 * @createTIme 2018/9/25 10:07
 **/
public class CounterService {
    @Resource
    CounterMapper counterMapper;

    public Counter getCounter() {
        return counterMapper.selectByPrimaryKey(1);
    }
}
