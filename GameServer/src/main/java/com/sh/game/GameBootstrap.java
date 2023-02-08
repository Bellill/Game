package com.sh.game;

import com.sh.game.world.ApplicationContext;
import com.sh.game.world.ServerOption;
import com.sh.game.world.World;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author zsl
 * @Date 2023/2/8 15:33
 * @PackageName:com.sh.game
 * @ClassName: GameBootstrap
 * @Description: 启动类
 * @Version 1.0
 */

@Slf4j
public class GameBootstrap {
    public static void main(String[] args){
        log.info("server start...");
        //加启动文本日志
        World.writeStartLog("服务器启动");

        ServerOption serverOption = new ServerOption();
        //服务器配置
        serverOption.build(args[0]);
        ApplicationContext.getInstance().init(serverOption);
        GameServer server = ApplicationContext.getInstance().createServer();
        server.open();
    }
}
