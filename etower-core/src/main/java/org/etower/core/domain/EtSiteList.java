package org.etower.core.domain;

import java.util.ArrayList;
import java.util.List;

public class EtSiteList {
	
	private List<EtSite> etSites = new ArrayList<EtSite>();

	public List<EtSite> getEtSite() {
		return etSites;
	}

	public void setEtSite(List<EtSite> etSites) {
		etSites.clear();
		etSites.addAll(etSites);
	}
	
	public void addEtSite(List<EtSite> etSites) {
		this.etSites.addAll(etSites);
	}
	
	public void addEtSite(EtSite etSite) {
		etSites.add(etSite);
	}

}
