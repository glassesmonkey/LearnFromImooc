package org.imooc.dao;

import java.util.List;

import org.imooc.bean.Ad;

public interface AdDao {
    int insert(Ad ad);
    
    List<Ad> selectByPage(Ad ad);
    /**
     * 根据主键修改
     * @param ad 待修改的广告对象
     * @return 影响行数
     */
    int update(Ad ad);
    
    /**
     * 根据主键删除
     * @param id 主键
     * @return 影响行数
     */
    int delete(Long id);
    /**
     * 根据主键查询广告对象
     * @param id 主键值
     * @return 广告对象
     */
    Ad selectById(Long id);
}
