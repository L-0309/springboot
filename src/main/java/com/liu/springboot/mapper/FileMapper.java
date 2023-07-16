package com.liu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.springboot.pojo.Files;
import com.liu.springboot.pojo.User;
import com.liu.springboot.pojo.dto.FileQueryDto;
import org.apache.ibatis.annotations.Param;

/**
* @author 86183
* @description 针对表【sys_file】的数据库操作Mapper
* @createDate 2023-06-29 09:27:21
* @Entity com.liu.springboot.pojo.File
*/
public interface FileMapper extends BaseMapper<Files> {
    /**
     *查询
     * @param page 分页
     * @param fileQueryDto 条件
     * @return {@link IPage}<{@link User}>
     */
    IPage<Files> findAllUser(Page<Files> page, @Param("fileDto") FileQueryDto fileQueryDto);
}




