package com.sh.game.world;

import io.netty.util.AttributeKey;

public interface SessionAttributeKey {

	/**
	 * 用户ID
	 */
	AttributeKey<Long> UID = AttributeKey.valueOf("UID");

	AttributeKey<String> LOGINNAME = AttributeKey.valueOf("loginName");

	AttributeKey<Integer> SERVERID = AttributeKey.valueOf("serverId");
}
