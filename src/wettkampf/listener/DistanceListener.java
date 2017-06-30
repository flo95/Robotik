package wettkampf.listener;

import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import wettkampf.Model;

public class DistanceListener implements FeatureListener {

	private static Model model;
	private boolean throwBall;
	private int range;

	public DistanceListener() {
		model = Model.getInstance();
	}

	@Override
	public void featureDetected(Feature feature, FeatureDetector detector) {
		range = (int) feature.getRangeReading().getRange();
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

}
