package wettkampf.listener;

import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;

public class DistanceListener implements FeatureListener {

	private int range;

	public DistanceListener() {
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
