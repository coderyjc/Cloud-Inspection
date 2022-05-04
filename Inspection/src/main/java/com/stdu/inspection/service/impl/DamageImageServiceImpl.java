package com.stdu.inspection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stdu.inspection.pojo.DamageImage;
import com.stdu.inspection.mapper.DamageImageMapper;
import com.stdu.inspection.service.DamageImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jancoyan
 * @since 2021-12-30
 */
@Service
public class DamageImageServiceImpl extends ServiceImpl<DamageImageMapper, DamageImage> implements DamageImageService {


    @Override
    public int modifyDamageId(Integer postId, int damageId) {
        // 将update_id为postId 的记录指定damageid
        QueryWrapper<DamageImage> wrapper = new QueryWrapper<>();
        wrapper.eq("update_id", postId);
        DamageImage damageImage = new DamageImage();
        damageImage.setDamageId(damageId);
        damageImage.setUpdateId(0);
        damageImage.update(wrapper);
        return 0;
    }

    @Override
    public List<DamageImage> listImageByDamageId(String damageId) {
        DamageImage image = new DamageImage();
        QueryWrapper<DamageImage> wrapper = new QueryWrapper<>();
        wrapper.eq("damage_id", damageId);
        return image.selectList(wrapper);
    }

    @Override
    public boolean removePicture(String filename) {
        QueryWrapper<DamageImage> wrapper = new QueryWrapper<>();
        wrapper.eq("filename", filename);
        return baseMapper.delete(wrapper) > 0;
    }
}
