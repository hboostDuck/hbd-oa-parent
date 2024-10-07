package com.orbit.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orbit.model.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    @Select("        select distinct\n" +
            "            m.id,m.parent_id,m.name,m.type,m.path,m.component,m.perms,m.icon,m.sort_value,m.status,m.create_time,m.update_time,m.is_deleted\n" +
            "        from sys_menu m\n" +
            "        inner join sys_role_menu rm on rm.menu_id = m.id\n" +
            "        inner join sys_user_role ur on ur.role_id = rm.role_id\n" +
            "        where ur.user_id=#{userId}\n" +
            "          and m.status = 1\n" +
            "          and rm.is_deleted = 0\n" +
            "          and ur.is_deleted = 0\n" +
            "          and m.is_deleted = 0")
    List<SysMenu> findListByUserId(@Param("userId") Long userId);
}
