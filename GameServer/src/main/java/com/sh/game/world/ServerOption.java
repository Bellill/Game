package com.sh.game.world;

import com.sh.game.system.Const;
import com.sh.game.system.Const.SERVER_TYPE;
import com.sh.game.util.Utils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/**
 * 游戏选项
 */
@Slf4j
public class ServerOption {
    /**
     * 服务器ID
     * 最大支持16383（14位）
     */
    private int serverId;

    /**
     * 平台ID
     * 最大支持511（9位）
     */
    private int platformId;

    /**
     * 服务器类型1：游戏服，2：日志服，100：版署服
     */
    private int serverType = 1;
    /**
     * 登录验证Key
     */
    private String authKey;

    /**
     * 游戏服务器端口
     */
    private int gameServerPort;

    /**
     * 游戏服务器地址
     */
    private String gameServerDomain;

    /**
     * 共享服开关
     */
    private boolean shareOnOff;

    /**
     * 共享服务器Id
     */
    private int shareServerId;

    /**
     * 共享服地址
     */
    private String shareServerDomain;

    /**
     * 共享服端口
     */
    private int shareServerPort;

    /**
     * 共享服2开关
     */
    private boolean shareOnOff2;

    /**
     * 共享服务器2Id
     */
    private int shareServerId2;

    /**
     * 共享服地址2
     */
    private String shareServerDomain2;

    /**
     * 共享服端口2
     */
    private int shareServerPort2;

    /**
     * GM服务器端口
     */
    private int gmServerPort;

    /**
     * 配置文件路径
     */
    private String configDataPah;
    /**
     * 开服日期
     */
    private Date openTime;
    /**
     * 合服日期
     */
    private Date combineTime;
    /**
     * 是否开启聊天信息推送
     */
    private boolean openChatChangePush;

    /**
     * 是否开启角色信息变更推送
     */
    private boolean openRoleInfoChangePush;
    /**
     * config配置地址
     */
    private String configPath;
    /**
     * 游戏服数据库连接池配置文件
     */
    private String gameDbConfigPath;

    /**
     * 游戏服日志写入连接池配置文件
     */
    private String gameLogDbConfigPath;

    /**
     * spring boot配置文件
     */
    private String springConfigFile;

    private boolean debug;

    /**
     * 消息过滤，查看执行时间
     */
    private boolean filterMsg;

    private boolean newId;

    /**
     * 共享服连接游戏服务器的个数
     */
    private int share2gameCount;

    /**
     * 所有连接的游戏服务器ID
     */
    private int[] gameServerIds;

    /**
     * 共享服2连接游戏服务器的个数
     */
    private int share2gameCount2;

    /**
     * 共享服2所有连接的游戏服务器ID
     */
    private int[] gameServerIds2;

    /**
     * 游戏服务器路径格式
     */
    private String gameDBConfigPathFormat;

    /**
     * 日志数据库路径格式
     */
    private String gameLogDbConfigPathFormat;

    /**
     * 游戏服务器路径格式2
     */
    private String gameDBConfigPathFormat2;

    /**
     * 日志数据库路径格式2
     */
    private String gameLogDbConfigPathFormat2;

    /**
     * 推送消息中心服网址
     */
    private String pushCenterUrl;

    /**
     * 势力战中心url
     */
    private String battleCenterUrl;

    private String goldenCenterUrl;

    private String pushCenterName;

    private String pushCenterPassword;

    /**
     * 缓存大小
     */
    private int cacheMax;

    /**
     *共享服3连接游戏服务器的个数
     */

    private int share3gameCount;
    /**
     * 共享服3所有连接的游戏服务器ID
     */
    private int[] gameServerIds3;
    /**
     * 游戏服务器路径格式3
     */
    private String gameDBConfigPathFormat3;

    /**
     * 日志数据库路径格式3
     */
    private String gameLogDbConfigPathFormat3;

    @Override
    public String toString() {
        return "ServerOption{" +
                "serverId=" + serverId +
                ", serverType=" + serverType +
                ", authKey='" + authKey + '\'' +
                ", gameServerPort=" + gameServerPort +
                ", gameServerDomain='" + gameServerDomain + '\'' +
                ", shareOnOff=" + shareOnOff +
                ", shareServerId=" + shareServerId +
                ", shareServerDomain='" + shareServerDomain + '\'' +
                ", shareServerPort=" + shareServerPort +
                ", shareOnOff2=" + shareOnOff2 +
                ", shareServerId2=" + shareServerId2 +
                ", shareServerDomain2='" + shareServerDomain2 + '\'' +
                ", shareServerPort2=" + shareServerPort2 +
                ", gmServerPort=" + gmServerPort +
                ", configDataPah='" + configDataPah + '\'' +
                ", openTime=" + openTime +
                ", combineTime=" + combineTime +
                ", openChatChangePush=" + openChatChangePush +
                ", openRoleInfoChangePush=" + openRoleInfoChangePush +
                ", configPath='" + configPath + '\'' +
                ", gameDbConfigPath='" + gameDbConfigPath + '\'' +
                ", gameLogDbConfigPath='" + gameLogDbConfigPath + '\'' +
                ", springConfigFile='" + springConfigFile + '\'' +
                ", debug=" + debug +
                ", filterMsg=" + filterMsg +
                ", newId=" + newId +
                ", share2gameCount=" + share2gameCount +
                ", gameServerIds=" + Arrays.toString(gameServerIds) +
                ", share2gameCount2=" + share2gameCount2 +
                ", gameServerIds2=" + Arrays.toString(gameServerIds2) +
                ", gameDBConfigPathFormat='" + gameDBConfigPathFormat + '\'' +
                ", gameLogDbConfigPathFormat='" + gameLogDbConfigPathFormat + '\'' +
                ", gameDBConfigPathFormat2='" + gameDBConfigPathFormat2 + '\'' +
                ", gameLogDbConfigPathFormat2='" + gameLogDbConfigPathFormat2 + '\'' +
                ", pushCenterUrl='" + pushCenterUrl + '\'' +
                ", cacheMax='" + cacheMax + '\'' +
                '}';
    }

    /**
     * 中心匹配服地址
     */
    private String centerServerDomain;
    /**
     * 中心匹配服端口
     */
    private int centerServerPort;
    /**
     * 天梯赛中心服地址
     */
    private String ladderCenterDomain;
    /**
     * 天梯赛中心服端口
     */
    private int ladderCenterPort;

    /**
     * 国战中心服地址
     */
    private String battleCenterServerDomain;
    /**
     * 国战中心服端口
     */
    private int battleCenterServerPort;

    /**
     * 聊天中心服地址
     */
    private String chatCenterServerDomain;
    /**
     * 聊天中心服端口
     */
    private int chatCenterServerPort;

    /**
     * 圣域战中心服ip
     */
    private String fantasyCenterServerDomain;

    /**
     * 圣域战中心服端口
     */
    private int fantasyCenterServerPort;
    /**
     * 魔境战场中心服ip
     */
    private String copyWorldCenterServerDomain;

    /**
     * 魔境战场中心服端口
     */
    private int copyWorldCenterServerPort;

    /**
     * 荣耀皇城中心服ip
     */
    private String gloryCityCenterServerDomain;

    /**
     * 荣耀皇城中心服端口
     */
    private int gloryCityCenterServerPort;

    public void build(String configFile) {
        this.configPath = configFile;
        this.printVersionContent();
        InputStream in = null;
        try {
            in = new FileInputStream(configFile);
            Properties pro = new Properties();
            pro.load(in);

            this.serverId = Integer.parseInt(pro.getProperty("serverId"));
            this.serverType = Integer.parseInt(pro.getProperty("serverType"));
            this.authKey = pro.getProperty("authKey");
            this.gameServerPort = Integer.parseInt(pro.getProperty("gameServerPort"));
            this.gameServerDomain = pro.getProperty("gameServerDomain");
            this.gmServerPort = Integer.parseInt(pro.getProperty("gmServerPort"));
            this.configDataPah = pro.getProperty("configDataPah");

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.openTime = format.parse(pro.getProperty("openTime"));
            if (pro.getProperty("combineTime") != null) {
                this.combineTime = format.parse(pro.getProperty("combineTime"));
            }

            this.openChatChangePush = Boolean.parseBoolean(pro.getProperty("openChatChangePush"));
            this.openRoleInfoChangePush = Boolean.parseBoolean(pro.getProperty("openRoleInfoChangePush"));
            this.gameDbConfigPath = pro.getProperty("gameDbConfigPath");
            this.gameLogDbConfigPath = pro.getProperty("gameLogDbConfigPath");
            this.springConfigFile = pro.getProperty("springConfigFile");
            this.debug = Boolean.parseBoolean(pro.getProperty("debug"));
            this.filterMsg = Boolean.parseBoolean(pro.getProperty("filterMsg"));
            this.newId = Boolean.parseBoolean(pro.getProperty("newId"));

            if (serverType == SERVER_TYPE.GAME_SERVER) {
                try {
                    this.shareOnOff = Boolean.parseBoolean(pro.getProperty("shareOnOff"));
                } catch (Exception e) {
                    log.info("shareOnOff 没配置，默认关闭!");
                    this.shareOnOff = false;
                }
                if (this.shareOnOff) {
                    this.shareServerId = Integer.parseInt(pro.getProperty("shareServerId"));
                    try {
                        this.shareServerDomain = pro.getProperty("shareServerDomain");
                        this.shareServerPort = Integer.parseInt(pro.getProperty("shareServerPort"));
                    } catch (Exception e) {
                        this.shareServerDomain = "";
                        this.shareServerPort = 0;
                    }
                }
                try {
                    this.shareOnOff2 = Boolean.parseBoolean(pro.getProperty("shareOnOff2"));
                } catch (Exception e) {
                    log.info("shareOnOff2 没配置，默认关闭!");
                    this.shareOnOff2 = false;
                }
                if (this.shareOnOff2) {
                    this.shareServerId2 = Integer.parseInt(pro.getProperty("shareServerId2"));
                    try {
                        this.shareServerDomain2 = pro.getProperty("shareServerDomain2");
                        this.shareServerPort2 = Integer.parseInt(pro.getProperty("shareServerPort2"));
                    } catch (Exception e) {
                        this.shareServerDomain2 = "";
                        this.shareServerPort2 = 0;
                    }
                }
            }

            if (serverType == SERVER_TYPE.SHARE_SERVER) {
                this.share2gameCount = Integer.parseInt(pro.getProperty("share2gameCount"));
                this.gameServerIds = new int[this.share2gameCount + 1];
                for (int i = 1; i <= this.share2gameCount; i++) {
                    String str = String.format("gameServerId%d", i);
                    this.gameServerIds[i] = Integer.parseInt(pro.getProperty(str));
                }
                this.gameDBConfigPathFormat = pro.getProperty("gameDBConfigPathFormat");
                this.gameLogDbConfigPathFormat = pro.getProperty("gameLogDbConfigPathFormat");
            }

            if (serverType == SERVER_TYPE.SHARE_SERVER2) {
                this.share2gameCount2 = Integer.parseInt(pro.getProperty("share2gameCount2"));
                this.gameServerIds2 = new int[this.share2gameCount2 + 1];
                for (int i = 1; i <= this.share2gameCount2; i++) {
                    String str = String.format("gameServer2Id%d", i);
                    this.gameServerIds2[i] = Integer.parseInt(pro.getProperty(str));
                }
                this.gameDBConfigPathFormat2 = pro.getProperty("gameDBConfigPathFormat2");
                this.gameLogDbConfigPathFormat2 = pro.getProperty("gameLogDbConfigPathFormat2");
            }
            if (serverType == SERVER_TYPE.SHARE_SERVER3) {
                this.share3gameCount = Integer.parseInt(pro.getProperty("share3gameCount"));
                this.gameServerIds3 = new int[this.share3gameCount + 1];
                for (int i = 1; i <= this.share3gameCount; i++) {
                    String str = String.format("gameServer3Id%d", i);
                    this.gameServerIds3[i] = Integer.parseInt(pro.getProperty(str));
                }
                this.gameDBConfigPathFormat3 = pro.getProperty("gameDBConfigPathFormat3");
                this.gameLogDbConfigPathFormat3 = pro.getProperty("gameLogDbConfigPathFormat3");
            }
            this.centerServerDomain = pro.getProperty("centerServerDomain");
            this.centerServerPort = Utils.parseToInt(pro.getProperty("centerServerPort"));

            this.ladderCenterDomain = pro.getProperty("ladderCenterDomain");
            this.ladderCenterPort = Utils.parseToInt(pro.getProperty("ladderCenterPort"));

            this.battleCenterServerDomain = pro.getProperty("battleCenterServerDomain");
            this.battleCenterServerPort = Utils.parseToInt(pro.getProperty("battleCenterServerPort"));

            this.chatCenterServerDomain = pro.getProperty("chatCenterServerDomain");
            this.chatCenterServerPort = Utils.parseToInt(pro.getProperty("chatCenterServerPort"));

            this.pushCenterUrl = pro.getProperty("pushCenterUrl");
            this.battleCenterUrl = pro.getProperty("battleCenterUrl");
            this.goldenCenterUrl = pro.getProperty("goldenCenterUrl");
            this.pushCenterName = pro.getProperty("pushCenterName");
            this.pushCenterPassword = pro.getProperty("pushCenterPassword");

            this.fantasyCenterServerDomain = pro.getProperty("fantasyCenterServerDomain");
            this.fantasyCenterServerPort = Utils.parseToInt(pro.getProperty("fantasyCenterServerPort"));
            this.copyWorldCenterServerDomain = pro.getProperty("copyWorldCenterServerDomain");
            this.copyWorldCenterServerPort = Utils.parseToInt(pro.getProperty("copyWorldCenterServerPort"));
            this.gloryCityCenterServerDomain = pro.getProperty("gloryCityCenterServerDomain");
            this.gloryCityCenterServerPort = Utils.parseToInt(pro.getProperty("gloryCityCenterServerPort"));
            String cacheMaxStr = pro.getProperty("cacheMax");
            if (cacheMaxStr != null) {
                this.cacheMax = Integer.parseInt(cacheMaxStr);
            }

            log.info("serverOption = {}", toString());
        } catch (Exception e) {
            throw new RuntimeException("服务器初始配置文件读取错误，启动失败......", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //打印version文件内容
    public void printVersionContent() {
        String configPath = "..//version"; //System.getProperty("user.dir")+"\\version";;
        File file = new File(configPath);
        if (!file.exists()) {
            log.info("version file not exists");
            return;
        }
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String s;
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator()).append(s);
            }
            if (result.toString().length() < 1) {
                log.info("printVersionContent() Content is null!");
            } else {
                log.info("printVersionContent() Content is :" + result.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean reBuild() {
        log.info("reBuild() configPath={}", configPath);
        if (configPath == null || configPath.isEmpty()) {
            log.info("reBuild() configPath is null!");
            return false;
        }
        build(configPath);
        return true;
    }

    public String getChatCenterServerDomain() {
        return chatCenterServerDomain;
    }

    public void setChatCenterServerDomain(String chatCenterServerDomain) {
        this.chatCenterServerDomain = chatCenterServerDomain;
    }

    public int getChatCenterServerPort() {
        return chatCenterServerPort;
    }

    public void setChatCenterServerPort(int chatCenterServerPort) {
        this.chatCenterServerPort = chatCenterServerPort;
    }

    public String getBattleCenterUrl() {
        return battleCenterUrl;
    }

    public void setBattleCenterUrl(String battleCenterUrl) {
        this.battleCenterUrl = battleCenterUrl;
    }

    public String getGoldenCenterUrl() {
        return goldenCenterUrl;
    }

    public void setGoldenCenterUrl(String goldenCenterUrl) {
        this.goldenCenterUrl = goldenCenterUrl;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public int getServerType() {
        return serverType;
    }

    public void setServerType(int serverType) {
        this.serverType = serverType;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public int getGameServerPort() {
        return gameServerPort;
    }

    public void setGameServerPort(int gameServerPort) {
        this.gameServerPort = gameServerPort;
    }

    public String getGameServerDomain() {
        return gameServerDomain;
    }

    public void setGameServerDomain(String gameServerDomain) {
        this.gameServerDomain = gameServerDomain;
    }

    public boolean isShareOnOff() {
        return shareOnOff;
    }

    public void setShareOnOff(boolean shareOnOff) {
        this.shareOnOff = shareOnOff;
    }

    public int getShareServerId() {
        return shareServerId;
    }

    public void setShareServerId(int shareServerId) {
        this.shareServerId = shareServerId;
    }

    public String getShareServerDomain() {
        return shareServerDomain;
    }

    public void setShareServerDomain(String shareServerDomain) {
        this.shareServerDomain = shareServerDomain;
    }

    public int getShareServerPort() {
        return shareServerPort;
    }

    public void setShareServerPort(int shareServerPort) {
        this.shareServerPort = shareServerPort;
    }

    public int getGmServerPort() {
        return gmServerPort;
    }

    public void setGmServerPort(int gmServerPort) {
        this.gmServerPort = gmServerPort;
    }

    public boolean isShareOnOff2() {
        return shareOnOff2;
    }

    public void setShareOnOff2(boolean shareOnOff2) {
        this.shareOnOff2 = shareOnOff2;
    }

    public int getShareServerId2() {
        return shareServerId2;
    }

    public void setShareServerId2(int shareServerId2) {
        this.shareServerId2 = shareServerId2;
    }

    public String getShareServerDomain2() {
        return shareServerDomain2;
    }

    public void setShareServerDomain2(String shareServerDomain2) {
        this.shareServerDomain2 = shareServerDomain2;
    }

    public int getShareServerPort2() {
        return shareServerPort2;
    }

    public void setShareServerPort2(int shareServerPort2) {
        this.shareServerPort2 = shareServerPort2;
    }

    public String getConfigDataPah() {
        return configDataPah;
    }

    public void setConfigDataPah(String configDataPah) {
        this.configDataPah = configDataPah;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCombineTime() {
        return combineTime;
    }

    public void setCombineTime(Date combineTime) {
        this.combineTime = combineTime;
    }

    public boolean isOpenChatChangePush() {
        return openChatChangePush;
    }

    public void setOpenChatChangePush(boolean openChatChangePush) {
        this.openChatChangePush = openChatChangePush;
    }

    public boolean isOpenRoleInfoChangePush() {
        return openRoleInfoChangePush;
    }

    public void setOpenRoleInfoChangePush(boolean openRoleInfoChangePush) {
        this.openRoleInfoChangePush = openRoleInfoChangePush;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public String getGameDbConfigPath() {
        return gameDbConfigPath;
    }

    public void setGameDbConfigPath(String gameDbConfigPath) {
        this.gameDbConfigPath = gameDbConfigPath;
    }

    public String getGameLogDbConfigPath() {
        return gameLogDbConfigPath;
    }

    public void setGameLogDbConfigPath(String gameLogDbConfigPath) {
        this.gameLogDbConfigPath = gameLogDbConfigPath;
    }

    public String getSpringConfigFile() {
        return springConfigFile;
    }

    public void setSpringConfigFile(String springConfigFile) {
        this.springConfigFile = springConfigFile;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isFilterMsg() {
        return filterMsg;
    }

    public boolean isNewId() {
        return newId;
    }

    public void setNewId(boolean newId) {
        this.newId = newId;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public int getShare2gameCount() {
        return share2gameCount;
    }

    public void setShare2gameCount(int share2gameCount) {
        this.share2gameCount = share2gameCount;
    }

    public int[] getGameServerIds() {
        return gameServerIds;
    }

    public void setGameServerIds(int[] gameServerIds) {
        this.gameServerIds = gameServerIds;
    }

    public boolean inGameServerIds(int serverId) {
        for (int i = 1; i <= this.getShare2gameCount(); i++) {
            if (serverId == this.gameServerIds[i]) {
                return true;
            }
        }
        return false;
    }

    public int getShare2gameCount2() {
        return share2gameCount2;
    }

    public void setShare2gameCount2(int share2gameCount2) {
        this.share2gameCount2 = share2gameCount2;
    }

    public int[] getGameServerIds2() {
        return gameServerIds2;
    }

    public void setGameServerIds2(int[] gameServerIds2) {
        this.gameServerIds2 = gameServerIds2;
    }

    public boolean inGameServerIds2(int serverId) {
        for (int i = 1; i <= this.getShare2gameCount2(); i++) {
            if (serverId == this.gameServerIds2[i]) {
                return true;
            }
        }
        return false;
    }
    public boolean inGameServerIds3(int serverId) {
        for (int i = 1; i <= this.getShare3gameCount(); i++) {
            if (serverId == this.gameServerIds3[i]) {
                return true;
            }
        }
        return false;
    }
    public String getGameDBConfigPathFormat() {
        return gameDBConfigPathFormat;
    }

    public void setGameDBConfigPathFormat(String gameDBConfigPathFormat) {
        this.gameDBConfigPathFormat = gameDBConfigPathFormat;
    }

    public String getGameLogDbConfigPathFormat() {
        return gameLogDbConfigPathFormat;
    }

    public void setGameLogDbConfigPathFormat(String gameLogDbConfigPathFormat) {
        this.gameLogDbConfigPathFormat = gameLogDbConfigPathFormat;
    }

    public String getGameDBConfigPathFormat2() {
        return gameDBConfigPathFormat2;
    }

    public void setGameDBConfigPathFormat2(String gameDBConfigPathFormat2) {
        this.gameDBConfigPathFormat2 = gameDBConfigPathFormat2;
    }

    public String getGameLogDbConfigPathFormat2() {
        return gameLogDbConfigPathFormat2;
    }

    public void setGameLogDbConfigPathFormat2(String gameLogDbConfigPathFormat2) {
        this.gameLogDbConfigPathFormat2 = gameLogDbConfigPathFormat2;
    }

    public String getCenterServerDomain() {
        return centerServerDomain;
    }

    public void setCenterServerDomain(String centerServerDomain) {
        this.centerServerDomain = centerServerDomain;
    }

    public int getCenterServerPort() {
        return centerServerPort;
    }

    public void setCenterServerPort(int centerServerPort) {
        this.centerServerPort = centerServerPort;
    }

    public String getLadderCenterDomain() {
        return ladderCenterDomain;
    }

    public void setLadderCenterDomain(String ladderCenterDomain) {
        this.ladderCenterDomain = ladderCenterDomain;
    }

    public int getLadderCenterPort() {
        return ladderCenterPort;
    }

    public void setLadderCenterPort(int ladderCenterPort) {
        this.ladderCenterPort = ladderCenterPort;
    }

    public String getPushCenterUrl() {
        return pushCenterUrl;
    }

    public void setPushCenterUrl(String pushCenterUrl) {
        this.pushCenterUrl = pushCenterUrl;
    }

    public String getPushCenterName() {
        return pushCenterName;
    }

    public void setPushCenterName(String pushCenterName) {
        this.pushCenterName = pushCenterName;
    }

    public String getPushCenterPassword() {
        return pushCenterPassword;
    }

    public void setPushCenterPassword(String pushCenterPassword) {
        this.pushCenterPassword = pushCenterPassword;
    }

    public int getCacheMax() {
        if (cacheMax < 20000 || cacheMax > Const.DefaultCacheSize) {
            cacheMax = Const.DefaultCacheSize;
        }
        return cacheMax;
    }

    public String getBattleCenterServerDomain() {
        return battleCenterServerDomain;
    }

    public void setBattleCenterServerDomain(String battleCenterServerDomain) {
        this.battleCenterServerDomain = battleCenterServerDomain;
    }

    public int getBattleCenterServerPort() {
        return battleCenterServerPort;
    }

    public void setBattleCenterServerPort(int battleCenterServerPort) {
        this.battleCenterServerPort = battleCenterServerPort;
    }

    public String getFantasyCenterServerDomain() {
        return this.fantasyCenterServerDomain;
    }

    public int getFantasyCenterServerPort() {
        return this.fantasyCenterServerPort;
    }

    public int getShare3gameCount() {
        return share3gameCount;
    }

    public int[] getGameServerIds3() {
        return gameServerIds3;
    }

    public String getGameDBConfigPathFormat3() {
        return gameDBConfigPathFormat3;
    }

    public String getGameLogDbConfigPathFormat3() {
        return gameLogDbConfigPathFormat3;
    }

    public String getCopyWorldCenterServerDomain() {
        return copyWorldCenterServerDomain;
    }

    public void setCopyWorldCenterServerDomain(String copyWorldCenterServerDomain) {
        this.copyWorldCenterServerDomain = copyWorldCenterServerDomain;
    }

    public int getCopyWorldCenterServerPort() {
        return copyWorldCenterServerPort;
    }

    public void setCopyWorldCenterServerPort(int copyWorldCenterServerPort) {
        this.copyWorldCenterServerPort = copyWorldCenterServerPort;
    }

    public String getGloryCityCenterServerDomain() {
        return gloryCityCenterServerDomain;
    }

    public void setGloryCityCenterServerDomain(String gloryCityCenterServerDomain) {
        this.gloryCityCenterServerDomain = gloryCityCenterServerDomain;
    }

    public int getGloryCityCenterServerPort() {
        return gloryCityCenterServerPort;
    }

    public void setGloryCityCenterServerPort(int gloryCityCenterServerPort) {
        this.gloryCityCenterServerPort = gloryCityCenterServerPort;
    }
}
