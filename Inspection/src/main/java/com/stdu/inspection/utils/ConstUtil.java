/**
 * @Author: Yan Jingcun
 * @Date: 2021/12/31
 * @Description:
 * @Version: 1.0
 */

package com.stdu.inspection.utils;

/**
 * ConstUtil类中放置本项目中所需的所有常量。
 */
public class ConstUtil {

    // 损伤已经在处理
    public static final int DAMAGE_REPAIRING = 2;

    // 损伤已修复
    public static final int DAMAGE_REPAIRED = 3;

    // 任务状态-任务已接收
    public static final int TASK_ACCEPT = 2;

    // 任务状态-任务已提交
    public static final int TASK_COMMIT = 3;

    // 任务状态-任务已(审核)完成
    public static final int TASK_COMPLETE = 4;

    /**
     * 项目的静态资源所在的根目录
     * 比如 图片文件夹 等
     */
//    private static final String BASE_ROOT = "/home/jancoyan/springbootApp/inspection/webstatic/";
    private static final String BASE_ROOT = "C:\\Users\\Administrator\\Pictures\\webstatic\\";

    /**
     * 损伤图片存放的文件夹
     */
    public static final String DAMAGE_PICTURE = BASE_ROOT + "damage_picture";

    /**
     * 修复完成用于验收的图片的文件夹
     */
    public static final String TASK_COMPLETE_PICTURE = BASE_ROOT + "complete";
}

