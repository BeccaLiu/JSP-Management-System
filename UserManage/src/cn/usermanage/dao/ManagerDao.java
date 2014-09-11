package cn.usermanage.dao;

import com.usermanage.vo.ManagerVo;


public interface ManagerDao {
	public ManagerVo login(String userName,String password);
	public ManagerVo passchange(String managerName,String pass) ;

}