package com.liu.springboot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.springboot.mapper.FileMapper;
import com.liu.springboot.pojo.Files;
import com.liu.springboot.pojo.dto.FileQueryDto;
import com.liu.springboot.service.FileService;
import com.liu.springboot.utils.Result;
import com.liu.springboot.utils.VerifyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 86183
* @description 针对表【sys_file】的数据库操作Service实现
* @createDate 2023-06-29 09:27:21
*/
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, Files>
    implements FileService{
    @Resource
    private FileMapper fileMapper;

    @Override
    public Result<IPage<Files>> findAll(FileQueryDto fileQueryDto) {
        if (VerifyUtil.verifyStr(fileQueryDto.getName())){
            fileQueryDto.setName("%" + fileQueryDto.getName() + "%");
        }
        if (VerifyUtil.verifyStr(fileQueryDto.getSongName())){
            fileQueryDto.setSongName("%" + fileQueryDto.getSongName() + "%");
        }
        if (!VerifyUtil.verifyNum(fileQueryDto.getClassifierId())){
            fileQueryDto.setClassifierId(0);
        }
        IPage<Files> allUser = fileMapper.findAllUser(new Page<>(fileQueryDto.getCurrent(), fileQueryDto.getPageSize()), fileQueryDto);
        return Result.success(allUser);
    }
}




