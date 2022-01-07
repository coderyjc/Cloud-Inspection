package com.stdu.inspection.service;

import com.stdu.inspection.pojo.DamageImage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jancoyan
 * @since 2021-12-30
 */
public interface DamageImageService extends IService<DamageImage> {


    /**
     * 将表中的updateid为postid的主键值都改成damageid
     * @param postId 上传者的id
     * @param damageId 损伤id
     * @return
     */
    int modifyDamageId(Integer postId, int damageId);

    /**
     * 由损伤的损伤id获取损伤对应的图片列表
     * @param damageId 损伤id
     * @return 图片列表
     */
    List<DamageImage> listImageByDamageId(String damageId);

    /**
     * 上传图片的时候删除图片
     * @param filename 图片名称
     * @return 成功
     */
    boolean removePicture(String filename);
}
