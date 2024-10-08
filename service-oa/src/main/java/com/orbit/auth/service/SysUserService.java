package com.orbit.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orbit.model.system.SysUser;
import com.orbit.vo.system.RouterVo;

import java.util.List;
import java.util.Map;

public interface SysUserService extends IService<SysUser> {
    void updateStatus(Long id, Integer status);

    SysUser getByUsername(String username);

    /**
     * 根据用户名获取用户登录信息
     * @param username
     * @return
     */
    Map<String, Object> getUserInfo(String username);

    Map<String, Object> getCurrentUser();
}
