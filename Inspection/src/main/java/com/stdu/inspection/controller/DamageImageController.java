package com.stdu.inspection.controller;


import com.stdu.inspection.pojo.DamageImage;
import com.stdu.inspection.service.DamageImageService;
import com.stdu.inspection.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Msg listImage(
            @RequestParam("id") String damageId
    ){
        List<DamageImage> image = service.listImageByDamageId(damageId);
        return Msg.success().add("image", image);
    }



}

