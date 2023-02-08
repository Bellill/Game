package com.sh.game.world;


import com.sh.world.AbstractHandler;
import com.sh.world.HandlerFilter;

/**
 * 这个类只是在Debug的时候会记录函数的执行时间-->World[236行]
 * 在AbstractHandler中只能放入放入一个Filter的应用
 */
public class MessageExeTimeStatisticsHandler implements HandlerFilter {

    @Override
    public boolean before(AbstractHandler handler) {
        //记录开始执行的时间
        handler.setStartTime(System.currentTimeMillis());
        return true;
    }

    @Override
    public boolean after(AbstractHandler handler) {return false;}
}
