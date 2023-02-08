package com.sh.game.world;

import com.sh.communication.msg.AbstractMessage;
import com.sh.communication.util.AttributeUtil;
import com.sh.game.entity.User;
import com.sh.game.system.map.obj.Player;
import com.sh.game.util.Utils;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class Session {

    private Channel channel;

    private User user;

    private volatile Player player;

    private int sendDelay;

    private volatile boolean offline = false;

   // private Audience audience;

    public Session(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void registerUser(User user) {
        AttributeUtil.set(channel, SessionAttributeKey.UID, user.getId());
        this.user = user;
    }

    public boolean isUserRegistered() {
        return user != null;
    }

    public User getUser() {
        return user;
    }

    public void registerPlayer(Player player) {
        this.player = player;
        SessionManager.getInstance().registerPlayer(this);
    }

    public boolean isPlayerRegistered() {
        return player != null;
    }

    public Player getPlayer() {
        return player;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getSendDelay() {
        return sendDelay;
    }

    public void setSendDelay(int sendDelay) {
        this.sendDelay = sendDelay;
    }

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
    }

    public void sendMessage(AbstractMessage msg) {
        channel.writeAndFlush(msg);
    }

    public String getIP() {
        if (channel == null) {
            return "";
        }
        InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();
        if (address == null) {
            return "";
        }
        return address.getAddress().getHostAddress();
    }

    public String getIpAndPort() {
        if (channel == null) {
            return "";
        }
        InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();
        if (address == null) {
            return "";
        }
        return address.getAddress().getHostAddress() + ":" + address.getPort();
    }

    public void clearAttribute() {
        if (channel == null) {
            return;
        }
        AttributeUtil.set(channel, SessionAttributeKey.UID, null);
    }

    public void close() {
        log.info("close(), session={}, channel={}, trace={}", this, channel, Utils.getStackTrace());
        if (channel == null) {
            return;
        }
        clearAttribute();
        try {
            if (channel.isActive() || channel.isOpen()) {
                channel.close().sync();
            }
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }

    public void closeByHttp() {
        if (channel == null) {
            return;
        }
        try {
            channel.close().sync();
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }
}
