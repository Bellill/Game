package com.sh.game.world;

import com.rpg.log.LogService;
import com.sh.common.config.ConfigDataManager;
import com.sh.common.jdbc.ConnectionPool;
import com.sh.common.jdbc.DruidConnectionPool;
import com.sh.common.jdbc.JdbcTemplate;
import com.sh.communication.NetworkConsumer;
import com.sh.communication.msg.AbstractMessage;
import com.sh.game.util.TimeUtil;
import com.sh.game.util.Utils;
import com.sh.script.ScriptEngine;
import com.sh.world.HandlerPool;
import com.sh.world.executor.DumpStackThread;
import com.sh.world.executor.QueueMonitor;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import static com.sh.game.util.TimeUtil.DEFAULT_FORMAT;

/**
 * @Author zsl
 * @Date 2023/2/8 16:12
 * @PackageName:com.sh.game.world
 * @ClassName: World
 * @Description:
 * @Version 1.0
 */
public class World implements NetworkConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(World.class);
    private boolean open = false;

    public World(ServerOption option, HandlerPool pool) throws Exception {}

    public void open() {
        this.open = true;
    }

    public static void writeStartLog(String log) {
        PrintWriter pw = null;
        try {
            File file = new File("GameBootstrap_Start.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            StringBuilder builder = new StringBuilder();
            long now = System.currentTimeMillis();
            String timeString = TimeUtil.getTimeString(now, DEFAULT_FORMAT);
            builder.append(log + "：now=" + now + " :" + timeString);
            pw.println(builder);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.flush();
                pw.close();
            }
        }
    }


    @Override
    public void consume(AbstractMessage abstractMessage, Channel channel) {

    }

    @Override
    public void connected(Channel channel) {

    }

    @Override
    public void disconnected(Channel channel) {

    }

    @Override
    public void exceptionOccurred(Channel channel, Throwable throwable) {

    }
}
