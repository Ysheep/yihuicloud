/**
 * 
 */
package com.qzdsoft;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.qzdsoft.eshop.domain.SysConfig;
import com.qzdsoft.eshop.service.sys.SysConfigService;
import com.qzdsoft.eshop.vo.sys.SysConfigInfo;

/**
 * 服务启动执行
 *
 * <p>detailed comment
 * @author pc-20170420 2017年10月21日
 * @see
 * @since 1.0
 */
@Component
@Order(1)
public class StartupRunner implements CommandLineRunner
{

    private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);
    @Autowired
    private SysConfigService sysConfigService;
    /**
     * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
     */
    @Override
    public void run(String... arg0) throws Exception
    {
        logger.info("开始加载配置。。。。。。。。。");
        List<SysConfig> configs = sysConfigService.selectAll();
        Map<String,String> configsMap = new HashMap<>();
        for(SysConfig c:configs) {
            configsMap.put(c.getCode(), c.getValue());
        }
        SysConfigInfo.setConfigs(configsMap);
        logger.info("加载配置结束。。。。。。。。。");
    }

}
