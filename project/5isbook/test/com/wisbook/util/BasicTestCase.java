package com.wisbook.util;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
/**
 * 测试基础类，提供事务回滚支持
 * @author volador
 *
 */
public abstract class BasicTestCase extends AbstractTransactionalDataSourceSpringContextTests{
    String filePathSufix = "classpath:"; // 配置文件地址的前缀  
    String appContextFile = filePathSufix + "beans.xml";// applicationContext.xml文件地址  
    
    /** 
     * 需要加载的配置文件地址列表 
     *  
     * @return 
     */  
    protected String[] getOtherConfigs(){
    	return new String[] {};
	}  
    /** 
     * 覆盖的获取配置文件地址的方法 
     */  
    @Override
    protected String[] getConfigLocations() {
        String[] otherConfigs = getOtherConfigs();  
        String[] configFiles = new String[otherConfigs.length + 1]; // 所有配置文件列表  
        configFiles[0] = appContextFile;  
        System.arraycopy(otherConfigs, 0, configFiles, 1, otherConfigs.length);  
        return configFiles;  
    }  
    /** 
     * 忽略的方法列表 
     *  
     * @return 
     */  
    protected String[] getIgnoredMethods(){
    	return new String[] {};  
    }
    /** 
     * 所有忽略方法列表中的方法都在测试时不执行 
     */  
    protected boolean isDisabledInThisEnvironment(String testMethodName) {  
        for (String methodName : getIgnoredMethods()) {  
            if (methodName.equals(testMethodName))  
                return true;  
        }  
        return false;  
    }  
}
