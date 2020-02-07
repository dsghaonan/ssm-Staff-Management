package cn.ghn.sm.service;

import cn.ghn.sm.entity.Staff;

public interface SelfService {
    Staff login(String account, String password);

    void changePassword(Integer id, String password);
}
