package com.sh.game.system.map.obj;

import com.sh.game.system.map.Grid;
import com.sh.game.system.map.Point;

public interface IMapObject {

	long getId();

	void setId(long id);

	int getConfigId();

	void setConfigId(int configId);

	int getType();

	int getMapId();

	void setMapId(int mapId);

	int getLine();

	void setLine(int line);

	boolean isVisible();

	void setVisible(boolean visible);

	Point getPoint();

	void setPoint(Grid point);

	String getName();

	void setName(String name);

}
