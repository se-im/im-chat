package com.im.dispatcher.common;


import com.mr.response.error.BusinessException;

public interface CommandExecutor<C extends Command>
{
    /**
     * 处理命令
     *
     * @param cmd
     * @return
     */
    void executor(C cmd) throws BusinessException;


    /**
     * 处理命令，返回一个值
     *
     * @param cmd
     * @return
     */
    Object executeWithResult(C cmd) throws BusinessException;
}
