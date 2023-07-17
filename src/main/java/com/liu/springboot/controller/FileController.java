package com.liu.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.liu.springboot.exception.ServiceException;
import com.liu.springboot.pojo.Files;
import com.liu.springboot.pojo.dto.FileMusicDto;
import com.liu.springboot.pojo.dto.FileQueryDto;
import com.liu.springboot.pojo.dto.FilesPictureDto;
import com.liu.springboot.service.FileService;
import com.liu.springboot.utils.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: spring-vue
 * @CreateTime: 2023-06-28  14:22
 * @Description:
 * @Author: LiuHaoYu
 */
@RestController
@RequestMapping("/files")
public class FileController {

    @Resource
    private FileService fileService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 上传接口
     * @return {@link Result}<{@link ?}>
     */
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        //文件名
        String originalFilename = file.getOriginalFilename();
        //文件类型
        String type = FileUtil.extName(originalFilename);
        //文件大小
        long size = file.getSize();
        //返回路径
        File uploadFile = new File(Constants.UPLOAD_PATH + originalFilename);
        //获取文件的md5
        String md5;
        //获取返回地址url
        String url = Constants.IP + originalFilename;
        //Files对象
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size / 1024);

        //判断参数是否传来
        if (VerifyUtil.verifyStr(originalFilename) && VerifyUtil.verifyStr(type)) {
            //查询是否有此数据
            Files files = filesNyName(originalFilename, type);
            if (files != null) {
                if (uploadFile.exists() && !uploadFile.isDirectory()) {
                    //此处表示文件已存在,返回数据库中的url
                    return Result.success("文件已存在,已切换存在文件", files.getUrl());
                } else {
                    //此处表示数据库中有上传的数据，但磁盘中没有,上传磁盘
                    file.transferTo(uploadFile);
                    //返回地址
                    return Result.success("检测到磁盘中没有此文件，已上传磁盘", files.getUrl());
                }
            } else {
                //获取所有文件类型
                Constants constants = new ConstantsImpl();
                List<String> pictureType = constants.pictureType();
                List<String> musicType = constants.musicType();
                List<String> videoType = constants.videoType();
                if (!uploadFile.exists() && !uploadFile.isDirectory()) {
                    //磁盘中没有
                    file.transferTo(uploadFile);
                }
                //获取磁盘文件的MD5
                md5 = SecureUtil.md5(uploadFile);
                //获取返回地址
                saveFile.setUrl(url);
                saveFile.setMd5(md5);
                //判断文件类型
                assert type != null;
                pictureType.forEach(s -> {
                    if (type.equals(s)) {
                        saveFile.setClassifierId(1);
                    }
                });
                musicType.forEach(s -> {
                    if (type.equals(s)) {
                        MusicUtil mp3Info = Mp3Util.getMp3Info(uploadFile);
                        saveFile.setSingName(mp3Info.getSingName());
                        saveFile.setSongName(mp3Info.getSongName());
                        saveFile.setSongLength(mp3Info.getSongLength());
                        saveFile.setClassifierId(2);
                        flushRedis(Constants.FILES_MUSIC_KEY);
                    }
                });
                videoType.forEach(s -> {
                    if (type.equals(s)) {
                        saveFile.setClassifierId(3);
                    }
                });
                //执行添加
                fileService.save(saveFile);
                flushRedis(Constants.FILES_ITEMS_KEY);
                //返回访问地址
                return Result.success(url);
            }
        } else {
            return Result.error(Constants.CODE_400, "参数错误");
        }
    }

    @GetMapping("/find")
    public Result<?> findPage(FileQueryDto fileQueryDto) {
        return fileService.findAll(fileQueryDto);
    }

    @GetMapping("/items")
//    @Cacheable(value = "files", key = "targetClass + methodName")
    public Result<?> itemsAll() {
        String jsonStr = stringRedisTemplate.opsForValue().get(Constants.FILES_ITEMS_KEY);
        List<Files> files ;
        if (StrUtil.isBlank(jsonStr)) {
            //取出来的json是空的
            QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_delete",false);
            //从数据库取出数据
            files = fileService.list(queryWrapper);
            //再去缓存到redis
            stringRedisTemplate.opsForValue().set(Constants.FILES_ITEMS_KEY, JSONUtil.toJsonStr(files));
        }else {
            //从redis缓存中获取数据
            files = JSONUtil.toBean(jsonStr, new TypeReference<List<Files>>() {
            }, true);
        }
        return Result.success(files);
    }

    /**
     * 获取图片接口
     * @return Result<?>
     */
    @GetMapping("/items/picture")
    public Result<?> itemsPicture() {
        String jsonStr = stringRedisTemplate.opsForValue().get(Constants.FILES_PICTURE_KEY);
        List<FilesPictureDto> pictureDto;
        if (StrUtil.isBlank(jsonStr)) {
            //取出来的json是空的
            QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_delete",false);
            List<Files> list = fileService.list(queryWrapper);
            pictureDto = new ConstantsImpl().filesPictureDto(list);
            //再去缓存到redis
            stringRedisTemplate.opsForValue().set(Constants.FILES_PICTURE_KEY, JSONUtil.toJsonStr(pictureDto));
        }else {
            //从redis缓存中获取数据
            pictureDto = JSONUtil.toBean(jsonStr, new TypeReference<List<FilesPictureDto>>() {
            }, true);
        }
        return Result.success(pictureDto);
    }


    /**
     * 获取音乐数据的接口
     * @return R
     */
    @GetMapping("/items/music")
    public Result<?> itemsMusic() {
        String jsonStr = stringRedisTemplate.opsForValue().get(Constants.FILES_MUSIC_KEY);
        List<FileMusicDto> musicDtoList;
        if (StrUtil.isBlank(jsonStr)) {
            QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_delete",false);
            List<Files> list = fileService.list(queryWrapper);
            musicDtoList = new ConstantsImpl().fileMusicDto(list);
            stringRedisTemplate.opsForValue().set(Constants.FILES_MUSIC_KEY, JSONUtil.toJsonStr(musicDtoList));
        }else {
            musicDtoList = JSONUtil.toBean(jsonStr, new TypeReference<List<FileMusicDto>>() {
            }, true);
        }
        return Result.success(musicDtoList);
    }

    @GetMapping("/detail/{id}")
    public Result<?> detailById(@PathVariable Integer id) {
        return Result.success(fileService.getById(id));
    }

    /**
     * 清除一条缓存
     */
//    @CacheEvict(value = "files", key = "targetClass + methodName")
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        if (!VerifyUtil.verifyNum(id)) {
            return Result.error(Constants.CODE_400, "未收到参数");
        }
        Files files = fileService.getById(id);
        files.setIsDelete(true);
        flushRedis(Constants.FILES_ITEMS_KEY);
        return fileService.updateById(files) ? Result.success() : Result.error(Constants.CODE_0, "删除失败");
    }

    @PostMapping("/del/batch")
    public Result<?> delete(@RequestBody List<Integer> ids) {
        if (ids.size() == 0) {
            return Result.error(Constants.CODE_400, "未收到参数");
        }
        List<Files> files = fileService.listByIds(ids);
        //Lambda遍历
        files.forEach(file -> file.setIsDelete(true));
        if (files.size() > 0) {
            flushRedis(Constants.FILES_ITEMS_KEY);
            return fileService.updateBatchById(files) ? Result.success() : Result.error(Constants.CODE_0, "删除失败");
        } else {
            throw new ServiceException(Constants.CODE_0, "未找到信息");
        }
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody Files files) {
        try {
            fileService.updateById(files);
            flushRedis(Constants.FILES_ITEMS_KEY);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "禁用失败");
        }
    }


    /**
     * 批量启用接口
     *
     * @param enables 启用的id
     * @return {@link Result}<{@link ?}>
     */
    @PostMapping("/enable")
    public Result<?> enable(@RequestBody List<Integer> enables) {
        if (enables.size() > 0) {
            List<Files> files = fileService.listByIds(enables);
            files.forEach(file -> file.setEnable(true));
            flushRedis(Constants.FILES_ITEMS_KEY);
            return fileService.updateBatchById(files) ? Result.success() : Result.error();
        } else {
            return Result.error(Constants.CODE_0, "你还没有选择");
        }
    }

    /**
     * 批量禁用接口
     *
     * @param disables 禁用的id
     * @return {@link Result}<{@link ?}>
     */
    @PostMapping("/disable")
    public Result<?> disable(@RequestBody List<Integer> disables) {
        if (disables.size() > 0) {
            List<Files> files = fileService.listByIds(disables);
            files.forEach(file -> file.setEnable(false));
            flushRedis(Constants.FILES_ITEMS_KEY);
            return fileService.updateBatchById(files) ? Result.success() : Result.error();
        } else {
            return Result.error(Constants.CODE_0, "你还没有选择");
        }
    }

    public Files filesNyName(String name, String type) {
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name).eq("type", type).eq("is_delete", false);
        try {
            return fileService.getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException("500", "系统错误");
        }
    }

    /**
     * 设置缓存
     * @param key 缓存的key
     * @param value 重新查询数据库中的数据，转成json数据
     */
    private void setCache(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 删除缓存
     * @param key 缓存的key
     */
    private void flushRedis(String key) {
        stringRedisTemplate.delete(key);
    }
}
