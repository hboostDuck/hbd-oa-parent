package com.orbit.auth.controller;

import com.orbit.auth.service.SysUserService;
import com.orbit.common.config.execption.BizException;
import com.orbit.common.jwt.JwtHelper;
import com.orbit.common.result.Result;
import com.orbit.model.system.SysUser;
import com.orbit.vo.system.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.orbit.common.utils.MD5;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        SysUser sysUser = sysUserService.getByUsername(loginVo.getUsername());
        if(null == sysUser) {
            throw new BizException(201,"用户不存在");
        }
        if(!MD5.encrypt(loginVo.getPassword()).equals(loginVo.getPassword())) {
            throw new BizException(201,"密码错误");
        }
        if(sysUser.getStatus().intValue() == 0) {
            throw new BizException(201,"用户被禁用");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtHelper.createToken(sysUser.getId(), sysUser.getUsername()));
        return Result.ok(map);
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        String username = JwtHelper.getUsername(request.getHeader("token"));
        Map<String, Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }
}
