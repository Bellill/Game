package com.sh.game.entity;

import com.sh.common.persist.Persistable;

public class User implements Persistable {

	private boolean dirty;

	private long id;

	/**
	 * 服务器ID(表示在哪个数据库写入)
	 */
	private int serverId;

	/**
	 * 服务器ID(客户端显示)
	 * 这个字段在数据库对应的serverId,由于接口的serverId表示数据源，与这个冲突，不得已而为
	 */
	private int clientServerId;

	/**
	 * 平台ID
	 */
	private int platformId;

	/**
	 * 登录用户名
	 */
	private String loginName;

	/**
	 * 注册时间戳
	 */
	private long registerTime;

	/**
	 * 控制的角色
	 */
	private long controlRole;

	/**
	 * 未提取元宝数量
	 */
	private long money;

	/**
	 * GM等级
	 */
	private int gmLevel;

	/**
	 * 身份证号码
	 */
	private String cardId;

	/**
	 * 玩家登录的客户端
	 */
	private int client;

	/**
	* 设备的唯一标志
	*/
	private String identifier;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public int getClientServerId() {
		return clientServerId;
	}

	public void setClientServerId(int clientServerId) {
		this.clientServerId = clientServerId;
	}

	public int getPlatformId() {
		return platformId;
	}

	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}

	public long getControlRole() {
		return controlRole;
	}

	public void setControlRole(long controlRole) {
		this.controlRole = controlRole;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public int getGmLevel() {
		return gmLevel;
	}

	public void setGmLevel(int gmLevel) {
		this.gmLevel = gmLevel;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Override
	public String tableName() {
		return TableName.SYS_USER.name();
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		return "User[id=" + id + ", loginName=" + loginName + "]";
	}
}
