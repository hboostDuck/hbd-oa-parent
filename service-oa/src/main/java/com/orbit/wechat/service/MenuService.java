package com.orbit.wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orbit.model.wechat.Menu;
import com.orbit.vo.wechat.MenuVo;

import java.util.List;

public interface MenuService extends IService<Menu> {

    List<MenuVo> findMenuInfo();

    void syncMenu();

    void removeMenu();
}
