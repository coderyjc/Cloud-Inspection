/**
 * @Author: Yan Jingcun
 * @Date: 2021/11/9
 * @Description:
 * @Version: 1.0
 */

package com.stdu.inspection.controller;

import com.stdu.inspection.utils.FileUtil;
import com.stdu.inspection.utils.Msg;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

/**
 * 文件上传控制器
 * 用于图片上传
 */
@RestController
@RequestMapping("/upload")
public class FileController {

    /**
     * 上传损伤图片，接受图片，返回图片url和图片名称
     *
     * @param file    文件
     * @return
     */
    @PostMapping("/damage")
    public Msg fileUpload(@RequestParam("file") MultipartFile file) {
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
        String destDir = "C:/Users/Administrator/Pictures/webstatic";
        //通过UUID生成唯一文件名
        String filename = UUID.randomUUID().toString().replaceAll("-","") + "." + suffix;
        try {
            FileUtil.savePicture(file, destDir, filename);
            //图片的URL
            String url = "./" + filename;
            return Msg.success().add("success", 1).add("url", url);
        } catch (Exception e) {
            return Msg.fail();
        }

    }


}
