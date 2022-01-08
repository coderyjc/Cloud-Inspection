package com.stdu.inspection.controller;


import com.stdu.inspection.pojo.DamageImage;
import com.stdu.inspection.service.DamageImageService;
import com.stdu.inspection.utils.ConstUtil;
import com.stdu.inspection.utils.FileUtil;
import com.stdu.inspection.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jancoyan
 * @since 2021-12-30
 */
@RestController
@RequestMapping("/damage_image")
public class DamageImageController {

    @Autowired
    DamageImageService service;


    /**
     * 根据损伤的id获取损伤的图片
     * @param damageId 损伤id
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Msg listImage(
            @RequestParam("id") String damageId
    ){
        List<DamageImage> image = service.listImageByDamageId(damageId);
        return Msg.success().add("image", image);
    }


    /**
     * 上传损伤图片
     * @param file 文件
     * @param id 上传的人的id
     * @return
     */
    @RequestMapping(value = "/put/{id}", method = RequestMethod.POST)
    public Msg uploadDamageImage(
            @RequestParam(value = "file") MultipartFile file,
            @PathVariable(value = "id") String id
    ){
        // 判空
        if (file == null) {
            return Msg.fail().add("msg", "请选择要上传的图片");
        }
        // 判断大小
        if (file.getSize() > 1024 * 1024 * 10) {
            return Msg.fail().add("msg", "文件大小不能大于10M");
        }
        //获取文件后缀
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            return Msg.fail().add("msg", "请选择jpg,jpeg,gif,png格式的图片");
        }
        // 目标地址
        String destDir = ConstUtil.DAMAGE_PICTURE;
        File file1 = new File(destDir);
        if(!file1.exists()) file1.mkdir();
        //通过UUID生成唯一文件名
        String filename = UUID.randomUUID().toString().replaceAll("-","") + "." + suffix;
        try {
            FileUtil.savePicture(file, destDir, filename);
            //暂时插入
            DamageImage damageImage = new DamageImage();
            damageImage.setInsertTime(new Date())
                    .setFilename(filename)
                    .setDamageId(null)
                    .setUpdateId(Integer.parseInt(id))
                    .insert();
            //图片的URL
            String url = "./static/webstatic/" + filename;
            return Msg.success()
                    .add("suc", true)
                    .add("url", url)
                    .add("filename", filename);
        } catch (Exception e) {
            return Msg.fail();
        }
    }


    /**
     * 上传图片的时候删除图片
     * @param filename
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public Msg deletePicture(
            @RequestParam(value = "filename") String filename
    ){
        boolean suc = service.removePicture(filename);
        if(suc){
            // 删除本地文件
            String path = ConstUtil.DAMAGE_PICTURE + "/" + filename;
            FileUtil.rm(path);
        }
        return Msg.success().add("suc", suc);
    }






}

