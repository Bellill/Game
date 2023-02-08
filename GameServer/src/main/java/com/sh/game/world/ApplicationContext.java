package com.sh.game.world;

import com.sh.game.GameServer;
import com.sh.game.util.IDUtil;
import com.sh.game.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @Author zsl
 * @Date 2023/2/8 16:42
 * @PackageName:com.sh.game.world
 * @ClassName: ApplicationContext
 * @Description:
 * @Version 1.0
 */
public class ApplicationContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContext.class);

    private static final ApplicationContext INSTANCE = new ApplicationContext();

    private int serverId;

    private int platformId;

    private int serverType;

    /**
     * 开服日期
     */
    private Date openTime;

    /**
     * 开服当天凌晨0点时间戳
     */
    private long openDayZeroTime;

    /**
     * 合服日期
     */
    private Date combineTime;

    /**
     * 合服日期当天凌晨0点时间戳
     */
    private long combineDayZeroTime;

    /**
     * 是否已经合服
     */
    private boolean combined = false;

    /**
     * 是否开启全服双倍经验
     */
    private int expDouble = 1;

    private ServerOption option;

    private GameServer server;

    /**
     * 服务器关闭逻辑是否已执行
     */
    private boolean closeLogicExecuted;

    /**
     * 推送消息中心url
     */
    private String pushCenterUrl;

    /**
     * 势力战中心url
     */
    private String battleCenterUrl;

//    private String goldenCenterUrl;

    private String pushCenterName;

    private String pushCenterPassword;

    private int cacheMax;


    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        return INSTANCE;
    }

    public void init(ServerOption option) {
        this.option = option;
        this.serverId = option.getServerId();
        this.platformId = option.getPlatformId();
        this.serverType = option.getServerType();
        this.openTime = option.getOpenTime();
        this.openDayZeroTime = TimeUtil.getZeroClockTime(this.openTime.getTime());
        this.pushCenterUrl = option.getPushCenterUrl();
        this.battleCenterUrl = option.getBattleCenterUrl();
        this.pushCenterName = option.getPushCenterName();
        this.pushCenterPassword = option.getPushCenterPassword();
        this.cacheMax = option.getCacheMax();
//        this.goldenCenterUrl = option.getGoldenCenterUrl();

        IDUtil.init(this.serverId, this.platformId);

        LOGGER.info("开服时间：{}", this.openTime);
        LOGGER.info("开服当天凌晨0点时间戳：{}", this.openDayZeroTime);
        LOGGER.info("开服距离开服当天凌晨：{}", (this.openTime.getTime() - this.openDayZeroTime));
        LOGGER.info("cacheMax: {}", cacheMax);

        this.combineTime = option.getCombineTime();
        if (this.combineTime != null) {
            this.combineDayZeroTime = TimeUtil.getZeroClockTime(option.getCombineTime().getTime());
            if (this.combineTime.getTime() <= this.openTime.getTime()) {
                throw new RuntimeException("开服与合服时间配置错误，合服时间早于或等于开服时间....");
            }
            this.combined = true;

            LOGGER.info("合服时间：{}", this.combineTime);
            LOGGER.info("合服当天凌晨0点时间戳：{}", this.combineDayZeroTime);
            LOGGER.info("合服距离开服当天凌晨：{}", (this.combineTime.getTime() - this.combineDayZeroTime));
        }

    }

    public GameServer createServer() {
        try {
            server = new GameServer(option);
            return server;
        } catch (Throwable e) {
            LOGGER.error("Failed to create server", e);
            System.exit(1);
            return null;
        }
    }
}
