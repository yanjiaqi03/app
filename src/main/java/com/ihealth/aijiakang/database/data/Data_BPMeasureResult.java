package com.ihealth.aijiakang.database.data;

public class Data_BPMeasureResult {

	private String bpMeasureID;
	private String iHealthCloud;
	private float sys;// 高压 （HP）
	private float dia;// 低压（LP）
	private int pulse; // 心率（HR）
	
	private int bpLevel; // 血压等级（BPL）
	private int isIHB; // 是否心率不齐（IsArr）
	private String wavelet; // 小波（WL）
	private int measureType; // 0.单次测量 1. 手动数据 2.批量上传（MeasureType）
	private long bpMeasureDate; // 测量时间（MeasureTime）
	
	private String bpNote; // 备注（Note）
	private String deviceType;  // 测试来源（MechineType）
	private String bpmDeviceID;  // 下位机设备编号（MechineDeviceID）
	private int wHO; // 血压等级（BPL）
	private int changeType;   // 是否删除 1.存储 2.删除（ChangeType）
	
	private long lastChangeTime;  // 最后一次修改时间（LastChangeTime）
	private String bpDataID;   // 数据的唯一编号（PhoneDataID）
	private long dataCreatTime;   //数据的创建时间（只读）（PhoneCreateTime）
	private double lat;    // 纬度（Lat）
	private double lon;   // 经度（Lon）
	
	private float timeZone;  // 时区（TimeZone）
	private int bpMood; // 心情（Mood）
	private String temp; //温度
	private String weather; // 天气码
	private String humidity;  //湿度
	
	private String visibility; //可见度（最新需求改为大气压！！！！！！所以此字段存的是大气压！！！！）
	private int bpActivity;  //运动（Activity）
	private int UsedUserID; //获取用户id(以防血压计添加筛选)
	private long NoteChangeTime;  // Note最后一次修改时间（云上字段为NoteTS）
	private String Care_Json; //当前BPID下得Care(Json形式)
	
	private String Content_Json; //当前BPID下得Content(Json形式)
	private int takePill;//是否服药： -1没有选择;0没服;1服.
	private String personalized;//个性化解读
	private int isDisplay;//下载数据判读是否未显示 1:已显示；0:未显示
	private long timezoneTS;//云端变化的时间戳
	
	public Data_BPMeasureResult() {
		super();
		// TODO Auto-generated constructor stub
		iHealthCloud = new String();
		wavelet = new String();
		bpNote = new String();
		bpmDeviceID = new String();
		temp = new String();
		weather = new String();
		humidity = new String();
		visibility = new String();
		bpMeasureID = new String();
		//add 20140822
		Care_Json = new String();
		Content_Json = new String();
		personalized = new String();
		takePill = -1;
		this.isDisplay = 1;
	}

	public Data_BPMeasureResult(String bpMeasureID, String iHealthCloud,
			float sys, float dia, int pulse, int bpLevel, int isIHB,
			String wavelet, int measureType, long bpMeasureDate, String bpNote,
			String deviceType, String bpmDeviceID, int wHO, int changeType,
			long lastChangeTime, String bpDataID, long dataCreatTime,
			double lat, double lon, float timeZone, int bpMood, String temp,
			String weather, String humidity, String visibility, int bpActivity , long NoteChangeTime, int UsedUserID,
			int takePill, String personalized, int isDisplay) {
		super();
		this.bpMeasureID = bpMeasureID;
		this.iHealthCloud = iHealthCloud;
		this.sys = sys;
		this.dia = dia;
		this.pulse = pulse;
		this.bpLevel = bpLevel;
		this.isIHB = isIHB;
		this.wavelet = wavelet;
		this.measureType = measureType;
		this.bpMeasureDate = bpMeasureDate;
		this.bpNote = bpNote;
		this.deviceType = deviceType;
		this.bpmDeviceID = bpmDeviceID;
		this.wHO = wHO;
		this.changeType = changeType;
		this.lastChangeTime = lastChangeTime;
		this.bpDataID = bpDataID;
		this.dataCreatTime = dataCreatTime;
		this.lat = lat;
		this.lon = lon;
		this.timeZone = timeZone;
		this.bpMood = bpMood;
		this.temp = temp;
		this.weather = weather;
		this.humidity = humidity;
		this.visibility = visibility;
		this.bpActivity = bpActivity;
		this.UsedUserID = UsedUserID;
		this.NoteChangeTime = NoteChangeTime;
		this.takePill = takePill;
		this.personalized = personalized;
		this.isDisplay = isDisplay;
	}
    @Override
	public String toString() {
		return "Data_TB_BPMeasureResult [bpMeasureID=" + bpMeasureID
				+ ", iHealthCloud=" + iHealthCloud + ", sys=" + sys + ", dia="
				+ dia + ", pulse=" + pulse + ", bpLevel=" + bpLevel
				+ ", isIHB=" + isIHB + ", wavelet=" + wavelet
				+ ", measureType=" + measureType + ", bpMeasureDate="
				+ bpMeasureDate + ", bpNote=" + bpNote + ", deviceType="
				+ deviceType + ", bpmDeviceID=" + bpmDeviceID + ", wHO=" + wHO
				+ ", changeType=" + changeType + ", lastChangeTime="
				+ lastChangeTime + ", bpDataID=" + bpDataID
				+ ", dataCreatTime=" + dataCreatTime + ", lat=" + lat
				+ ", lon=" + lon + ", timeZone=" + timeZone + ", bpMood="
				+ bpMood + ", temp=" + temp + ", weather=" + weather
				+ ", humidity=" + humidity + ", visibility=" + visibility
				+ ", bpActivity=" + bpActivity + ", UsedUserID=" + UsedUserID +", NoteChangeTime="
				+ NoteChangeTime + ", takePill = "+ takePill + ", personalized = "+ personalized +", isDisplay = "+ isDisplay + "]";
	}
	/*
	 * NoteTS
	 */
	public long getNoteChangeTime() {
		return NoteChangeTime;
	}

	public void setNoteChangeTime(long noteChangeTime) {
		NoteChangeTime = noteChangeTime;
	}

	/*
	 * 用户ID
	 */
	public int getUsedUserID() {
		return UsedUserID;
	}

	public void setUsedUserID(int usedUserID) {
		UsedUserID = usedUserID;
	}

	/*
	 * 主键
	 */
	public String getBpMeasureID() {
		return bpMeasureID;
	}
	/*
	 * 主键
	 */
	public void setBpMeasureID(String bpMeasureID) {
		this.bpMeasureID = bpMeasureID;
	}
	/*
	 * 云账号
	 */
	public String getiHealthCloud(){
		return iHealthCloud;
	}
	/*
	 * 云账号
	 */
	public void setiHealthCloud(String iHealthCloud){
		this.iHealthCloud = iHealthCloud;
	}
	/*
	 * 高压
	 */
	public float getSys(){
		return sys;
	}
	/*
	 * 高压
	 */
	public void setSys(float sys){
		this.sys = sys;
	}
	/*
	 * 低压
	 */
	public float getDia(){
		return dia;
	}
	/*
	 * 低压
	 */
	public void setDia(float dia){
		this.dia = dia;
	}
	/*
	 *心率 
	 */
	public int getPulse(){
		return pulse;
	}
	/*
	 *心率 
	 */
	public void setPulse(int pulse){
		this.pulse = pulse;
	}
	/*
	 * 血压等级
	 */
	public int getBpLevel(){
		return bpLevel;
	}
	/*
	 * 血压等级
	 */
	public void setBpLevel(int bpLevel){
		this.bpLevel = bpLevel;
	}
	/*
	 * 是否心率不齐
	 */
	public int getIsIHB(){
		return isIHB;
	}
	/*
	 * 是否心律不齐
	 */
	public void setIsIHB(int isIHB){
		this.isIHB = isIHB;
	}
	/*
	 * 小波
	 */
	public String getWavelet(){
		return wavelet;
	}
	/*
	 * 小波
	 */
	public void setWavelet(String wavelet){
		this.wavelet = wavelet;
	}
	/*
	 * 测量方式
	 */
	public int getMeasureType(){
		return measureType;
	}
	/*
	 * 测量方式
	 */
	public void setMeasureType(int measureType){
		this.measureType = measureType;
	}
	/*
	 * 测量来源
	 */
	public long getBpMeasureDate(){
		return bpMeasureDate;
	}
	/*
	 * 测量来源
	 */
	public void setBpMeasureDate(long bpMeasureDate){
		this.bpMeasureDate = bpMeasureDate;
	}
	/*
	 * 备注
	 */
	public String getBpNote(){
		return bpNote;
	}
	/*
	 * 备注
	 */
	public void setBpNote(String bpNote){
		this.bpNote = bpNote;
	}
	/*
	 * 测试来源
	 */
	public String getDeviceType(){
		return deviceType;
	}
	/*
	 * 测试来源
	 */
	public void setDeviceType(String deviceType){
		this.deviceType = deviceType;
	}
	/*
	 * 下位机设备编号
	 */
	public String getBpmDeviceID(){
		return bpmDeviceID;
	}
	/*
	 * 下位机设备编号
	 */
	public void setBpmDeviceID(String bpmDeviceID){
		this.bpmDeviceID = bpmDeviceID;
	}
	/*
	 * wHO
	 */
	public int getwHO(){
		return wHO;
	}
	/*
	 * wHO
	 */
	public void setwHO(int wHO){
		this.wHO = wHO;
	} 
	/*
	 * 是否删除 1.存储 2.删除
	 */
	public int getChangeType(){
		return changeType;
	}
	/*
	 * 是否删除 1.存储 2.删除
	 */
	public void setChangeType(int changeType){
		this.changeType = changeType;
	}
	/*
	 * 最后一次修改时间
	 */
	public long getLastChangeTime(){
		return lastChangeTime;
	}
	/*
	 * 最后一次修改时间
	 */
	public void setLastChangeTime(long lastChangeTime){
		this.lastChangeTime = lastChangeTime;
	}
	/*
	 * 数据的唯一编号
	 */
	public String getBpDataID(){
		return bpDataID;
	}

	/*
	 * 数据的唯一编号
	 */
	public void setBpDataID(String bpDataID){
		this.bpDataID = bpDataID;
	}
	/*
	 * 数据的创建时间（只读）
	 */
	public long getDataCreatTime(){
		return dataCreatTime;
	}
	/*
	 * 数据的创建时间（只读）
	 */
	public void setDataCreatTime(long dataCreatTime){
		this.dataCreatTime = dataCreatTime;
	}

	/*
	 * 纬度
	 */
	public double getLat(){
		return lat;
	}
	/*
	 * 纬度
	 */
	public void setLat(double lat){
		this.lat = lat;
	}

	/*
	 * 经度
	 */
	public double getLon(){
		return lon;
	}
	/*
	 * 经度
	 */
	public void setLon(double lon){
		this.lon = lon;
	}
	/*
	 * 时区
	 */
	public float getTimeZone(){
		return timeZone;
	}
	/*
	 * 时区
	 */
	public void setTimeZone(float timeZone){
		this.timeZone = timeZone;
	}
	/*
	 * 心情
	 */
	public int getBpMood(){
		return bpMood;
	}
	/*
	 * 心情
	 */
	public void setBpMood(int bpMood){
		this.bpMood = bpMood;
	}
	/*
	 * 温度
	 */
	public String getTemp(){
		return temp;
	}
	/*
	 * 温度
	 */
	public void setTemp(String temp){
		this.temp = temp; 
	}
	/*
	 * 天气码
	 */
	public String getWeather(){
		return weather;
	}
	/*
	 * 天气码
	 */
	public void setWeather(String weather){
		this.weather = weather;
	}
	/*
	 * 湿度
	 */
	public String getHumidity(){
		return humidity;
	}
	/*
	 * 湿度
	 */
	public void setHumidity(String humidity){
		this.humidity = humidity;
	}
	/*
	 * 可见度
	 */
	public String getVisibility(){
		return visibility;
	}
	/*
	 * 可见度
	 */
	public void setVisibility(String visibility){
		this.visibility = visibility;
	}
	/*
	 * 运动
	 */
	public int getBpActivity(){
		return bpActivity;
	}
	/*
	 * 运动
	 */
	public void setBpActivity(int bpActivity){
		this.bpActivity = bpActivity;
	}

	public String getCare_Json() {
		return Care_Json;
	}

	public void setCare_Json(String care_Json) {
		Care_Json = care_Json;
	}

	public String getContent_Json() {
		return Content_Json;
	}

	public void setContent_Json(String content_Json) {
		Content_Json = content_Json;
	}

    public int getTakePill() {
        return takePill;
    }

    public void setTakePill(int takePill) {
        this.takePill = takePill;
    }

    public String getPersonalized() {
        return personalized;
    }

    public void setPersonalized(String personalized) {
        this.personalized = personalized;
    }

    public int getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(int isDisplay) {
        this.isDisplay = isDisplay;
    }

	public long getTimezoneTS() {
		return timezoneTS;
	}

	public void setTimezoneTS(long timezoneTS) {
		this.timezoneTS = timezoneTS;
	}
    
}
