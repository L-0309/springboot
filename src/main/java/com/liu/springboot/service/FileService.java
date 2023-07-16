package com.liu.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.springboot.pojo.Files;
import com.liu.springboot.pojo.dto.FileQueryDto;
import com.liu.springboot.utils.Result;

/**
* @author 86183
* @description 针对表【sys_file】的数据库操作Service
* @createDate 2023-06-29 09:27:21
*/
public interface FileService extends IService<Files> {
    /**
     * 查询接口
     * @param fileQueryDto 参数
     * @return {@link Result}<{@link ?}>
     */
    Result<IPage<Files>> findAll(FileQueryDto fileQueryDto);
}
