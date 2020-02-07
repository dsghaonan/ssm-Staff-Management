package cn.ghn.sm.dao;

import cn.ghn.sm.entity.Staff;
import org.springframework.stereotype.Repository;

@Repository("selfDao")
public interface SelfDao {

    Staff selectByAccount(String account);

}
