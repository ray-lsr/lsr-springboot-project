package cn.lsr.boot.core.param;

/**
 * @Description: 系统参数工具类
 * @Author: lsr
 * @Date 2022年08月06日 16:39
 */
public class SystemParamManagerUtils {

    public static String getSystemId() {
        return System.getProperty(SystemParamManagerConstant.systemId);
    }
    public static String getApplicationName(){
        return System.getProperty("spring.application.name");
    }
    public static String getLogHome(){
        return System.getProperty(SystemParamManagerConstant.logHome);
    }
}
