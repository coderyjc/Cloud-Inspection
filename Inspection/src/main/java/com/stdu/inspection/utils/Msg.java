/**
 * @Author: Yan Jingcun
 * @Date: 2021/9/14
 * @Description:
 * @Version: 1.0
 */

package com.stdu.inspection.utils;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回的类
 * @author Jancoyan
 */
@Data
public class Msg {
    /**
     * 状态码
     * 100-成功
     * 200-失败
     *
     * */
    private int code;
    //提示信息
    private String msg;

    //用户要返回给浏览器的数据
    private Map<String, Object> extend = new HashMap<String, Object>();

    /**
     * 用户信息过期
     * @return 结果
     */
    public static Msg expire(){
        Msg result = new Msg();
        result.setCode(601);
        result.setMsg("用户信息已过期");
        return result;
    }

    /**
     * 非法token
     * @return 结果
     */
    public static Msg illegalToken(){
        Msg result = new Msg();
        result.setCode(602);
        result.setMsg("登录信息无效");
        return result;
    }

    /**
     * 数据传输成功
     * @return 结果
     */
    public static Msg success(){
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("成功");
        return result;
    }

    /**
     * 用户未登录
     * @return 结果
     */
    public static Msg loginNeeded(){
        Msg result = new Msg();
        result.setCode(603);
        result.setMsg("用户未登录");
        return result;
    }

    /**
     * 数据传输失败
     * @return 结果
     */
    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("失败！");
        return result;
    }

    public Msg add(String key,Object value){
        this.getExtend().put(key, value);
        return this;
    }

}
