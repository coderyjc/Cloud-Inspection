package com.stdu.inspection.service;

import com.stdu.inspection.pojo.DamageImage;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
