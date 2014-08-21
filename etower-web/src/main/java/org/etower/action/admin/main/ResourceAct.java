package org.etower.action.admin.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.etower.core.domain.Resource;
import org.etower.manager.main.ResourceMng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResourceAct {
	
	private static final Logger log = LoggerFactory.getLogger(ResourceAct.class);
	
	@Autowired
	private ResourceMng resourceMng;
	
	@RequestMapping("/resource/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		List<Resource> list = new ArrayList<Resource>();
		List<Resource> rootRes = resourceMng.findByParentId((long) 0);
		
		for(Resource resour : rootRes) {
			int x = 1;
			List<Resource> firstRes = resourceMng.findByParentId(resour.getId());
			list.add(resour);
			for(Resource resouf : firstRes) {
				int y = 1;
				List<Resource> twoRes = resourceMng.findByParentId(resouf.getId());
				if(x < firstRes.size())
					resouf.setResouName("├─ "+resouf.getResouName());
				else {
					if(resourceMng.findByParentId(resouf.getId()).size() == 0)
						resouf.setResouName("&nbsp;&nbsp;&nbsp;&nbsp; └─ "+resouf.getResouName());
					else
						resouf.setResouName("└─ "+resouf.getResouName());
				}
				list.add(resouf);
				x++;
				for(Resource resout : twoRes) {
					int z = 1;
					List<Resource> threeRes = resourceMng.findByParentId(resout.getId());
					if(y < twoRes.size()) {
						if(resourceMng.findByParentId(resout.getId()).size() == 0)
							resout.setResouName("│&nbsp;&nbsp; ├─ "+resout.getResouName());
						else
							resout.setResouName("├─ "+resout.getResouName());
					} else
						resout.setResouName("│&nbsp;&nbsp; └─ "+resout.getResouName());
					list.add(resout);
					y++;
					for(Resource resoufo : threeRes) {
						if(z < threeRes.size())
							resoufo.setResouName("│&nbsp;&nbsp; ├─ "+resoufo.getResouName());
						else
							resoufo.setResouName("│&nbsp;&nbsp; └─ "+resoufo.getResouName());
						list.add(resoufo);
						z++;
					}
				}
			}
		}
		model.addAttribute("list", list);
		return "resource/list";
	}
	
	@RequestMapping("/resource/v_add.do")
	public String add(ModelMap model, Long id) {
		Resource parent = null;
		if(id != null) {
			parent = resourceMng.getById(id);
			model.addAttribute("parent", parent);
		}
		return "resource/add";
	}
	
	@RequestMapping("/resource/v_edit.do")
	public String edit(ModelMap model, Long id) {
		// 资源
		Resource resource = resourceMng.getById(id);
		// 父资源
		Resource parent = null;
		if(id != null) {
			parent = resourceMng.getById(id);
			model.addAttribute("parent", parent);
		}
		List<Resource> topList = resourceMng.getList();
		List<Resource> resourceList = Resource.getListForSelect(topList, null, resource);
		model.addAttribute("resourceList", resourceList);
		return "resource/edit";
	}

}
