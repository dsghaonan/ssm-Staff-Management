package cn.ghn.sm.service;

import cn.ghn.sm.entity.Log;

import java.util.List;

public interface LogService {

    void addSystemLog(Log log);

    void addLoginLog(Log log);

    void addOperationLog(Log log);

    List<Log> getSystemLog();

    List<Log> getLoginLog();

    List<Log> getOperationLog();

}
