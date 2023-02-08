package com.sh.game.back;

import com.sh.communication.NetworkConsumer;
import com.sh.communication.NetworkService;
import com.sh.communication.msg.AbstractMessage;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2016/9/18 14:54.
 *
 * @author 周锟
 */
public class BackServer implements NetworkConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackServer.class);

    private BackServerMessageAndHandlerPool msgPool;

    private NetworkService networkService;


    public BackServer(int port){}
    public void open() {
        if (networkService != null) {
            networkService.open();
        }
    }

    public void close() {
        if (networkService != null) {
            networkService.close();
        }
    }

    @Override
    public void consume(AbstractMessage abstractMessage, Channel channel) {}


    @Override
    public void connected(Channel channel) {
        LOGGER.info(channel + " connected to back server");
    }

    @Override
    public void disconnected(Channel channel) {}

    @Override
    public void exceptionOccurred(Channel channel, Throwable throwable) {
        LOGGER.error(channel + " exception occurred", throwable);
    }
}
