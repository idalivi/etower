package org.etower.manager.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.etower.common.web.springmvc.RealPathResolver;
import org.etower.core.domain.EtSite;
import org.etower.core.domain.Resource;
import org.etower.repository.main.ResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class ResourceMng {
	
	private ResourceDao resourceDao;
	private RealPathResolver realPathResolver;

	@Autowired
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Autowired
	public void setRealPathResolver(RealPathResolver realPathResolver) {
		this.realPathResolver = realPathResolver;
	}

	// 获取所有资源（按优先级正序）
	public List<Resource> getList() {
		return (List<Resource>) resourceDao.findAll(new Sort(
				new Sort.Order(Sort.Direction.ASC, "priority")));
	}
	
	public List<Resource> findById(Long id) {
		return (List<Resource>) resourceDao.findById(id);
	}
	
	public List<Resource> findByParentId(Long id) {
		return (List<Resource>) resourceDao.findByParentId(id);
	}
	
	public Resource getById(Long id) {
		return resourceDao.findOne(id);
	}
	
	/**
	 * 复制模板资源文件
	 * @param from
	 * @param to
	 * @throws IOException
	 */
	public void copyTplAndRes(EtSite from, EtSite to) throws IOException {
		String fromSol = from.getTplSolution();
		String toSol = to.getTplSolution();
		
		File tplFrom = new File(realPathResolver.get(from.getTplPath()), fromSol);
		File tplTo = new File(realPathResolver.get(to.getTplPath()), toSol);
		FileUtils.copyDirectory(tplFrom, tplTo);
		File resFrom = new File(realPathResolver.get(from.getResPath()), fromSol);
		if (resFrom.exists()) {
			File resTo = new File(realPathResolver.get(to.getResPath()), toSol);
			FileUtils.copyDirectory(resFrom, resTo);
		}
	}
	
	/**
	 * 复制模板资源文件
	 * @param site
	 * @throws IOException
	 */
	public void delTplAndRes(EtSite site) throws IOException {
		File tpl = new File(realPathResolver.get(site.getTplPath()));
		File res = new File(realPathResolver.get(site.getResPath()));
		FileUtils.deleteDirectory(tpl);
		FileUtils.deleteDirectory(res);
	}
}
