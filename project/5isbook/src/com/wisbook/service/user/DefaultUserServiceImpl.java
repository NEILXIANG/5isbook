package com.wisbook.service.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource; 

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Component;

import com.wisbook.dao.base.BaseDao;
import com.wisbook.dao.user.AddressDao;
import com.wisbook.dao.user.LoginInfoDao;
import com.wisbook.dao.user.RoleDao;
import com.wisbook.model.user.Address;
import com.wisbook.model.user.LoginInfo;
import com.wisbook.model.user.Role;
import com.wisbook.model.user.User;
import com.wisbook.model.user.UserInfo;
import com.wisbook.util.AccountFormat;
import com.wisbook.util.PageBean;

/**
 * user service的默认实现
 * 
 * @author volador
 * 
 */
@Component("userService")
public class DefaultUserServiceImpl implements UserService {

	private BaseDao<User, String> userDao;
	private BaseDao<LoginInfo, Integer> loginInfoDao;
	private BaseDao<Role, Integer> roleDao;
	private AccountFormat format;
	private BaseDao<Address,Integer> addressDao;
	
	
	
	@Resource(name="addressDao")
	public void setAddressDao(BaseDao<Address, Integer> addressDao) {
		this.addressDao = addressDao;
	}

	@Resource(name="emailFormat")
	public void setFormat(AccountFormat format) {
		this.format = format;
	}

	@Resource(name = "roleDao")
	public void setRoleDao(BaseDao<Role, Integer> roleDao) {
		this.roleDao = roleDao;
	}

	@Resource(name = "loginInfoDao")
	public void setLoginInfoDao(BaseDao<LoginInfo, Integer> loginInfoDao) {
		this.loginInfoDao = loginInfoDao;
	}

	@Resource(name = "userDao")
	public void setUserDao(BaseDao<User, String> userDao) {
		this.userDao = userDao;
	}

	@Override
	public PageBean<User> getUsersByRole(String roleName, int pageIndex,
			int pageSize) {
		String HQL = "from User u where u.role.name = '" + roleName + " '";
		return this.userDao.fetchEntityByPageHQL(HQL, pageIndex, pageSize);
	}

	@Override
	public User getUserByAccount(String currentAccount, String accessAccount)
			throws AccessErrorException {
		User currentUser = this.userDao.getEnitytById(currentAccount);
		User accessUser = this.userDao.getEnitytById(accessAccount);
		// 跨权就抛错,可以看自己的，不能看同级的
		if(accessUser.getAccount().equals(currentUser.getAccount()))
			return accessUser;
		if (accessUser.getRole().getName().length() >= currentUser.getRole()
				.getName().length())
			throw new AccessErrorException("step over role!");
		return accessUser;
	}

	@Override
	public PageBean<LoginInfo> getLoginInfosByAccount(String account,
			int pageIndex, int pageSize) {
		String HQL = "from LoginInfo li where li.user.account = " + account;
		return this.loginInfoDao.fetchEntityByPageHQL(HQL, pageIndex, pageSize);
	}

	@Override
	public UserInfo getUserInfoByAccount(String account) {
		User u = this.userDao.getEnitytById(account);
		UserInfo ui = null;
		if (u != null)
			ui = u.getUserInfo();
		return ui;
	}

	@Override
	public void tiggleEnabled(String currentAccount, String accessAccount)
			throws AccessErrorException {
		User accessUser = this.userDao.getEnitytById(accessAccount);
		User currentUser = this.userDao.getEnitytById(currentAccount);
		if (accessUser.getRole().getName().length() >= currentUser.getRole()
				.getName().length())
			throw new AccessErrorException("step over role!");
		if (accessUser.isEnabled())
			accessUser.setEnabled(false);
		else
			accessUser.setEnabled(true);
		this.userDao.updateEntity(accessUser);
	}

	@Override
	public void registryAdmin(String currentAccount, String account,String password)
			throws AccessErrorException {
		User currentUser = this.userDao.getEnitytById(currentAccount);
		if (!currentUser.getRole().getName().equals("ROLE_SUPER_ADMIN"))
			throw new AccessErrorException("step over role!");
		Role adminRole = ((RoleDao) this.roleDao).loadByName("ROLE_ADMIN");
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		user.setEnabled(true);
		user.setRole(adminRole);
		this.userDao.saveEntity(user);
	}

	@Override
	public boolean changePassword(String currentAccount, String oldPass,
			String newPass) {
		User user = this.userDao.getEnitytById(currentAccount);
		if (!user.getPassword().equals(oldPass))
			return false;
		user.setPassword(newPass);
		this.userDao.updateEntity(user);
		return true;
	}

	@Override
	public boolean accountExist(String account) {
		boolean flag = true;
		if(this.format.testFormat(account)){
			User user = this.userDao.getEnitytById(account);
			try{
				user.getPassword();
			}catch(ObjectNotFoundException e){
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public boolean registryUser(String account,String password) {
		boolean flag = false;
		if(this.format.testFormat(account) && !this.accountExist(account)){
			User user = new User();
			user.setAccount(account);
			user.setPassword(password);
			user.setEnabled(true);
			Role userRole = ((RoleDao)this.roleDao).loadByName("ROLE_USER");
			user.setRole(userRole);
			this.userDao.saveEntity(user);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean addAddress(String currentAccount, String name,String addr, String longTel,String shortTel) {
		User user = this.userDao.getEnitytById(currentAccount);
		Address address = new Address();
		address.setAddress(addr);
		address.setRealName(name);
		address.setLongTel(longTel);
		address.setShortTel(shortTel);
		address.setUser(user);
		user.getAddresses().add(address);
		this.userDao.updateEntity(user);
		return true;
	}

	@Override
	public boolean deleteAddress(String currentAccount, int addressId) {
		boolean flag = false;
		Address address = this.addressDao.getEnitytById(addressId);
		if(address.getUser().getAccount().equals(currentAccount)){
			this.addressDao.deleteEntity(address);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean modifyAddress(String currentAccount, int addressId,String name, String addr, String longTel,String shortTel) {
		boolean flag = true;
		Address address = this.addressDao.getEnitytById(addressId);
		try{
			address.getRealName();
		}catch(ObjectNotFoundException e){ //对象不存在
			flag = false;
		}
		if(flag && address.getUser().getAccount().equals(currentAccount)){
			address.setRealName(name);
			address.setAddress(addr);
			address.setLongTel(longTel);
			address.setShortTel(shortTel);
			this.addressDao.updateEntity(address);
		}
		return flag;
	}

	@Override
	public LoginInfo addLoginMessage(String account, String ipAddress) {
		User user = this.userDao.getEnitytById(account);
		LoginInfo lf = new LoginInfo();
		lf.setUser(user);
		lf.setLoginTime(new Date());
		lf.setIp(ipAddress);
		this.loginInfoDao.saveEntity(lf);
		return lf;
	}

	@Override
	public LoginInfo getTopLoginInfo(String account) {
		return ((LoginInfoDao)this.loginInfoDao).getTopRecord(account);
	}

	@Override
	public List<Address> getAllAddress(String currentAccount) {
		return ((AddressDao)this.addressDao).getAllAddress(currentAccount);
	}
	
	@Override
	public Address getAddress(String currentAccount, int id) {
		Address addr = this.addressDao.getEnitytById(id);
		String account = null;
		try{
			account = addr.getUser().getAccount();
		}catch(ObjectNotFoundException e){
			return null;
		}
		return account.equals(currentAccount)?addr:null;
	}
	
	@Override
	public boolean updateUser(User user) {
		this.userDao.updateEntity(user);
		return true;
	}
}
