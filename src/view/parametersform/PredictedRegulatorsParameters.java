package view.parametersform;

import domainmodel.InputParameters;

public interface PredictedRegulatorsParameters {
	
	public int getRankThreshold();
	
	public float getAUCThreshold();
	
	public float getNESThreshold();
	
	public InputParameters deriveParameters();
	
	public float getMinOrthologous();
	
	public float getMaxMotifSimilarityFDR();
	
	public String getAttributeName();
}
