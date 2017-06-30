package wettkampf;

public class Model {

	private static Model self;
	private int homeFieldColor;
	private int lineColor;
	private int fieldColor;
	private int enemyFieldColor;
	private final int distance = 20;
	private EnumTrashcanPosition position;
	private boolean isStart;

	public static Model getInstance() {
		if (self == null) {
			self = new Model();
		}
		return self;
	}

	public int getHomeFieldColor() {
		return homeFieldColor;
	}

	public void setHomeFieldColor(int homeFieldColor) {
		this.homeFieldColor = homeFieldColor;
	}

	public int getLineColor() {
		return lineColor;
	}

	public void setLineColor(int lineColor) {
		this.lineColor = lineColor;
	}

	public int getFieldColor() {
		return fieldColor;
	}

	public void setFieldColor(int fieldColor) {
		this.fieldColor = fieldColor;
	}

	public int getEnemyFieldColor() {
		return enemyFieldColor;
	}

	public void setEnemyFieldColor(int enemyFieldColor) {
		this.enemyFieldColor = enemyFieldColor;
	}

	public EnumTrashcanPosition getPosition() {
		return position;
	}

	public void setPosition(EnumTrashcanPosition position) {
		System.out.println("save position " + position.toString());
		this.position = position;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public int getDistance() {
		return distance;
	}

}
