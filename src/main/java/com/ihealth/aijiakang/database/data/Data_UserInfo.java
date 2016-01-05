package com.ihealth.aijiakang.database.data;

/**
 * 用户信息表
 * 
 * @author licheng
 */
public class Data_UserInfo {
	
    private String UserName;// 用户名（用户邮箱）
	private String PassWord;// 密码
	private int UserId;// UserId
	private long BirthDay;// 生日
	private String Email;// 分享email
	
	private int Gender;// 性别 0：男 1：女
	private int IsSporter;// 是否是运动员 1：是 2：不是 3：未知
	private String Name;// 昵称
	private int Height;// 身高（cm）
	private float Weight;// 体重（kg）
	
	private String Nation;// 国籍
	private String Language;// 语言
	private int UserCloud;// 是否同步云 0：不同步 1：同步
	private long TS;// 时间戳
	private String Logo;// 用户头像
	
	private long LogoTS;// 用户头像时间戳
	private int ActivityLevel;// 运动等级 1：久坐 2：活动中 3：活动大量
	private int IsRememberPassword;// 是否记住密码 0：不记住 1：记住
	private int AntoLogin;// 是否自动登录 0：不自动登录 1：自动登录
	private String LastTime; // 最后登录时间
	
	private String TimeZone;// 首次登录时区
	private int cloudSerialNumber;// 云账号序列号
	private String cloudUserMac;// AM 云绑定的用户mac地址
	private int bmr; // BMR

	@Override
	public String toString() {
		return "Data_TB_UserInfo [UserName=" + UserName + ", PassWord=" + PassWord + ", UserId=" + UserId + ", BirthDay=" + BirthDay + ", Email=" + Email + ", Gender=" + Gender
				+ ", IsSporter=" + IsSporter + ", Name=" + Name + ", Height=" + Height + ", Weight=" + Weight + ", Nation=" + Nation + ", Language=" + Language + ", UserCloud="
				+ UserCloud + ", TS=" + TS + ", Logo=" + Logo + ", LogoTS=" + LogoTS + ", ActivityLevel=" + ActivityLevel + ", IsRememberPassword=" + IsRememberPassword
				+ ", AntoLogin=" + AntoLogin + ", LastTime=" + LastTime + ", TimeZone=" + TimeZone + ", cloudSerialNumber=" + cloudSerialNumber + ", cloudUserMac=" + cloudUserMac
				+ ", bmr=" + bmr + "]";
	}
	public Data_UserInfo(String UserName, String PassWord, int UserId,
			long BirthDay, String Email, int Gender, int IsSporter,
			String Name, int Height, float Weight, String Nation,
			String Language, int UserCloud, long TS, String Logo,
			long LogoTS, int ActivityLevel, int IsRememberPassword,
			int AntoLogin, String LastTime, String TimeZone,
			int cloudSerialNumber, String cloudUserMac, int bmr) {
		super();
		this.UserName = UserName;
		this.PassWord = PassWord;
		this.UserId = UserId;
		this.BirthDay = BirthDay;
		this.Email = Email;
		this.Gender = Gender;
		this.IsSporter = IsSporter;
		this.Name = Name;
		this.Height = Height;
		this.Weight = Weight;
		this.Nation = Nation;
		this.Language = Language;
		this.UserCloud = UserCloud;
		this.TS = TS;
		this.Logo = Logo;
		this.LogoTS = LogoTS;
		this.ActivityLevel = ActivityLevel;
		this.IsRememberPassword = IsRememberPassword;
		this.AntoLogin = AntoLogin;
		this.LastTime = LastTime;
		this.TimeZone = TimeZone;
		this.cloudSerialNumber = cloudSerialNumber;
		this.cloudUserMac = cloudUserMac;
		this.bmr = bmr;
	}
	public Data_UserInfo() {
	    // TODO Auto-generated constructor stub
		UserName="";
		PassWord="";
		Email="";
		Name="";
		Nation="";
		Language="";
		Logo="";
		LastTime="";
		TimeZone="";
		cloudUserMac="";
		this.bmr = 1999;
		this.ActivityLevel = 1;
		this.IsRememberPassword = 1;
		this.AntoLogin = 1;
		
	}
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public long getBirthDay() {
		return BirthDay;
	}

	public void setBirthDay(long birthDay) {
		BirthDay = birthDay;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getGender() {
		return Gender;
	}

	public void setGender(int gender) {
		Gender = gender;
	}

	public int getIsSporter() {
		return IsSporter;
	}

	public void setIsSporter(int isSporter) {
		IsSporter = isSporter;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public float getWeight() {
		return Weight;
	}

	public void setWeight(float weight) {
		Weight = weight;
	}

	public String getNation() {
		return Nation;
	}

	public void setNation(String nation) {
		Nation = nation;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public int getUserCloud() {
		return UserCloud;
	}

	public void setUserCloud(int userCloud) {
		UserCloud = userCloud;
	}

	public long getTS() {
		return TS;
	}

	public void setTS(long tS) {
		TS = tS;
	}

	public String getLogo() {
		return Logo;
	}

	public void setLogo(String logo) {
		Logo = logo;
	}

	public long getLogoTS() {
		return LogoTS;
	}

	public void setLogoTS(long logoTS) {
		LogoTS = logoTS;
	}

	public int getActivityLevel() {
		return ActivityLevel;
	}

	public void setActivityLevel(int activityLevel) {
		ActivityLevel = activityLevel;
	}

	public int getIsRememberPassword() {
		return IsRememberPassword;
	}

	public void setIsRememberPassword(int isRememberPassword) {
		IsRememberPassword = isRememberPassword;
	}

	public int getAntoLogin() {
		return AntoLogin;
	}

	public void setAntoLogin(int antoLogin) {
		AntoLogin = antoLogin;
	}

	public String getLastTime() {
		return LastTime;
	}

	public void setLastTime(String lastTime) {
		LastTime = lastTime;
	}

	public String getTimeZone() {
		return TimeZone;
	}

	public void setTimeZone(String timeZone) {
		TimeZone = timeZone;
	}

	public int getCloudSerialNumber() {
		return cloudSerialNumber;
	}

	public void setCloudSerialNumber(int cloudSerialNumber) {
		this.cloudSerialNumber = cloudSerialNumber;
	}

	public String getCloudUserMac() {
		return cloudUserMac;
	}

	public void setCloudUserMac(String cloudUserMac) {
		this.cloudUserMac = cloudUserMac;
	}

	public int getBmr() {
		return bmr;
	}

	public void setBmr(int bmr) {
		this.bmr = bmr;
	}

}
