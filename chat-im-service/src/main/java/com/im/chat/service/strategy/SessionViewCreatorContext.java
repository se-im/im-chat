package com.im.chat.service.strategy;

import com.im.chat.annotation.SessionViewStrategyAnnotation;
import com.im.chat.enums.CvsTypeEnum;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SessionViewCreatorContext implements ApplicationListener<ContextRefreshedEvent>
{
    private Map<CvsTypeEnum, SessionViewCreatorStrategy> sessionViewCreatorStrategyMap = new HashMap<>();


    public SessionViewCreatorStrategy getStrategy(CvsTypeEnum cvsTypeEnum)
    {
        return sessionViewCreatorStrategyMap.get(cvsTypeEnum);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        Map<String, SessionViewCreatorStrategy> beansOfType = contextRefreshedEvent.getApplicationContext().getBeansOfType(SessionViewCreatorStrategy.class);

        for(Map.Entry<String, SessionViewCreatorStrategy> entry: beansOfType.entrySet())
        {
            SessionViewCreatorStrategy strategy = entry.getValue();
            SessionViewStrategyAnnotation annotation = strategy.getClass().getAnnotation(SessionViewStrategyAnnotation.class);
            CvsTypeEnum cvsTypeEnum = annotation.cvsType();
            sessionViewCreatorStrategyMap.put(cvsTypeEnum, strategy);


        }
    }
}
