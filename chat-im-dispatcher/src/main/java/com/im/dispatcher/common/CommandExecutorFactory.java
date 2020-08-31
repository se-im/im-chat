package com.im.dispatcher.common;


import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;


@Component
public class CommandExecutorFactory implements ApplicationContextAware
{
    public Map<Class, CommandExecutor> commandExecutorMap = Maps.newHashMap();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        // bean的名称， bean
        Map<String, CommandExecutor> beanMap = applicationContext.getBeansOfType(CommandExecutor.class);
        if (MapUtils.isNotEmpty(beanMap)) {
            beanMap.forEach((k, v) -> commandExecutorMap.put(getExecutorCommandType(v), v));
        }
    }

    private Class getExecutorCommandType(CommandExecutor executor) {
        ResolvableType resolvableType = ResolvableType.forClass(executor.getClass());
        Class<?> resolve = resolvableType.getInterfaces()[0].getGeneric(0).resolve();
        if (Objects.isNull(resolve)) {
            resolvableType = ResolvableType.forClass(AopUtils.getTargetClass(executor));
            resolve = resolvableType.getInterfaces()[0].getGeneric(0).resolve();
        }
        return resolve;
    }



    public CommandExecutor getCommandExecutor(Command cmd){
        return commandExecutorMap.get(cmd.getClass());
    }


//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
//    {
//        Map<String, CommandExecutor> beanMap = contextRefreshedEvent.getApplicationContext().getBeansOfType(CommandExecutor.class);
//        if (MapUtils.isNotEmpty(beanMap)) {
//            beanMap.forEach((k, v) -> commandExecutorMap.put(getExecutorCommandType(v), v));
//        }
//    }
}
