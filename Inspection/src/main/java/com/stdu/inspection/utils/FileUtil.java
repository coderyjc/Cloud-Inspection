/**
 * @Author: Yan Jingcun
 * @Date: 2021/11/9
 * @Description:
 * @Version: 1.0
 */

package com.stdu.inspection.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件工具类
 * 用于写入文件
 */
public class FileUtil {


    /**
     * 保存图片到指定文件夹
     *
     * @param file    文件
     * @param destDir 指定路径，绝对路径
     */
    public static void savePicture(MultipartFile file, String destDir, String filename) {
        if (file != null) {
            //目标文件路径值
            String destPath = destDir + "/" + filename;
            File dest = new File(destPath);
            try {
                // 保存图片
                if (!dest.exists()) {
                    file.transferTo(dest);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
