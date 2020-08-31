package com.im.dispatcher.common;


public interface CommandExecutor<C extends Command>
{
    /**
     * 处理命令
     *
     * @param cmd
     * @return
     */
    void executor(C cmd);


    /**
     * 处理命令，返回一个值
     *
     * @param cmd
     * @return
     */
    Object executeWithResult(C cmd);
}
