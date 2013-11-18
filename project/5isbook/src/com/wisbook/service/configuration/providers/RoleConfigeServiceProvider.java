package com.wisbook.service.configuration.providers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.dao.user.RoleDao;
import com.wisbook.model.user.Role;
import com.wisbook.service.configuration.AbstractConfigeService;
import com.wisbook.service.configuration.XmlFileFormatException;
import com.wisbook.util.XPath;
/**
 * 配置系统的用户角色模块
 * @author volador
 *
 */
@Component("roleConfigeServiceProvider")
public class RoleConfigeServiceProvider extends AbstractConfigeService {
	
	private BaseDao<Role,Integer> roleDao;
	
	
	public BaseDao<Role, Integer> getRoleDao() {
		return roleDao;
	}

	@Resource(name="roleDao")
	public void setRoleDao(BaseDao<Role, Integer> roleDao) {
		this.roleDao = roleDao;
	}


	@Override
	protected void process() throws XmlFileFormatException {
		Node root = this.getRootNode();
		
		List<Node> roleList = XPath.selectNodes("/config/role",root);
		if(roleList != null && roleList.size() >0)
			for(Node node:roleList){
				String name = XPath.selectText("@name",node);
				String description = XPath.selectText("@description",node);
				
				if(this.assertNotNull(name) && ((RoleDao)this.roleDao).loadByName(name) == null){
					Role role = new Role();
					role.setName(name);
					role.setDisplayName(description);
					this.roleDao.saveEntity(role);
				}
			}
	}
	

}
