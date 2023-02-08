package com.sh.game.system;

/**
 * @Author zsl
 * @Date 2023/2/8 16:34
 * @PackageName:com.sh.game.system
 * @ClassName: Const
 * @Description: 常量
 * @Version 1.0
 */
public class Const {
    public static final long SYSTEM_ROLE_ID = 0L;

    /**
     * 服务器类型
     */
    public static final class SERVER_TYPE {
        /**
         * 合服时共享服玩家标志
         */
        public static final int MERGE_SHARE = -1;
        /**
         * 无效的服务器类型
         */
        public static final int NONE = MERGE_SHARE;
        /**
         * 游戏服
         */
        public static final int GAME_SERVER = 1;
        /**
         * 3v3地图服
         */
        public static final int _3v3_MAP_SERVER = 2;
        /**
         * 共享服务器(苍月岛)
         */
        public static final int SHARE_SERVER = 3;
        /**
         * 中心服
         */
        public static final int CENTRE_SERVER = 4;
        /**
         * 共享服务器2(神龙帝国)
         */
        public static final int SHARE_SERVER2 = 5;
        /**
         * 天梯中心服
         */
        public static final int LADDER_CENTER = 6;
        /**
         * 国战中心服
         */
        public static final int COUNTRY_BATTLE_CENTER = 7;
        /**
         * 聊天中心服
         */
        public static final int CHAT_CENTER_SERVER = 8;

        /**
         * 圣域战 中心服
         */
        public static final int FANTASY_BATTLE_CENTER = 9;
        /**
         * 魔境战 中心服
         */
        public static final int COPY_WORLD_BATTLE_CENTER = 10;
        /**
         * 共享服务器3(魔境)
         */
        public static final int SHARE_SERVER3 = 11;
        /**
         * 荣耀皇城 中心服
         */
        public static final int GLORY_CITY = 12;
    }
    /**
     * 默认缓存大小
     */
    public static final int DefaultCacheSize = 200000;

    /**
     * 不会用到的服务器ID
     */
    public static final int UNUSE_SERVERID = -1;

    /**
     * user role 状态
     */
    public static final class USER_ROLE {
        /**
         * 正常状态
         */
        public static final int NORMAL = 0;
        /**
         * 已删除
         */
        public static final int DELETED = 1;
    }
}
