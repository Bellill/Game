package com.sh.game;

import com.sh.communication.NetworkService;
import com.sh.communication.NetworkServiceBuilder;
import com.sh.game.back.BackServer;
import com.sh.game.util.MsgExeTimeUtil;
import com.sh.game.util.PackageCountUtil;
import com.sh.game.world.MessageAndHandlerPool;
import com.sh.game.world.ServerOption;
import com.sh.game.world.World;

public class GameServer {

	NetworkService netWork;

	World world;

	BackServer backServer;

	public GameServer(ServerOption option) throws Exception {

		int bossLoopGroupCount = 4;
		int workerLoopGroupCount = Runtime.getRuntime().availableProcessors() < 8 ? 8
				: Runtime.getRuntime().availableProcessors();

		MessageAndHandlerPool pool = new MessageAndHandlerPool();

		// 创建游戏世界
		world = new World(option, pool);

		NetworkServiceBuilder builder = new NetworkServiceBuilder();
		builder.setMsgPool(pool);
		builder.setBossLoopGroupCount(bossLoopGroupCount);
		builder.setWorkerLoopGroupCount(workerLoopGroupCount);
		builder.setPort(option.getGameServerPort());
		builder.setConsumer(world);
		if (option.isDebug()) {
			builder.extraHandler(new PackageCountUtil.PackageCountHandler());
			PackageCountUtil.open(true);
		}
		MsgExeTimeUtil.setOpen(option.isFilterMsg());

		// 创建网络服务
		netWork = builder.createService();

		backServer = new BackServer(option.getGmServerPort());
	}

	public void open() {
		// 开启游戏世界
		world.open();

		// 开启网络服务
		netWork.open();

		backServer.open();
	}


}
