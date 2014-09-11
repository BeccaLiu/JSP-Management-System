package cn.usermanage.dao;

import java.util.List;

import com.usermanage.vo.UserInfoVo;

public interface UserInfoDao {
	public void addUser(UserInfoVo userInfoVo);
	public void updateDate(UserInfoVo userInfoVo);//访问时间更新
	public void modUser(UserInfoVo userInfoVo);
	public void delUser(int id);
	public String check(String serialnum);
	public UserInfoVo findUserById(int id);
	public List<UserInfoVo> findUserList(String startDate,String endDate,String username,String serialNum,String Agent);//查询
	public UserInfoVo login(String serialnum);

}