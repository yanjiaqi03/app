package com.ihealth.aijiakang.database.data;

/**
 * 用户单位表
 * 
 * @author licheng
 */
public class Data_Unit {
	private String UserName; // 用户名（用户邮箱）
	private int BPUnit;// 血压单位 0：mmHg 1:kPa
	private long BPUnitTS;// 血压单位时间戳
	private int BGUnit;// 血糖单位 0：mg/dL 1:mmol/L
	private long BGUnitTS;// 血糖单位时间戳
	private int HeightUnit;// 身高单位 0：cm 1:feet
	private long HeightUnitTS;// 身高单位时间戳
	private int WeightUnit;// 体重单位 0：kg 1:lb 2:st
	private long WeightUnitTS;// 体重单位时间戳
	private int FoodWeightUnit;// 食物单位 0：oz 1:g
	private long FoodWeightUnitTS;// 食物单位时间戳

	private int LengthUnit;// 长度单位 0：miles 1：km
	private long LengthUnitTS;// 长度单位时间戳

	public Data_Unit(String UserName, int BPUnit, long BPUnitTS, int BGUnit,
			long BGUnitTS, int HeightUnit, long HeightUnitTS, int WeightUnit,
			long WeightUnitTS, int FoodWeightUnit, long FoodWeightUnitTS,
			int LengthUnit, long LengthUnitTS) {
		super();
		this.UserName = UserName;
		this.BPUnit = BPUnit;
		this.BPUnitTS = BPUnitTS;
		this.BGUnit = BGUnit;
		this.BGUnitTS = BGUnitTS;
		this.HeightUnit = HeightUnit;
		this.HeightUnitTS = HeightUnitTS;
		this.WeightUnit = WeightUnit;
		this.WeightUnitTS = WeightUnitTS;
		this.FoodWeightUnit = FoodWeightUnit;
		this.FoodWeightUnitTS = FoodWeightUnitTS;
		this.LengthUnit = LengthUnit;
		this.LengthUnitTS = LengthUnitTS;
	}

	public Data_Unit() {
		UserName=new String();
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getBPUnit() {
		return BPUnit;
	}

	public void setBPUnit(int bPUnit) {
		BPUnit = bPUnit;
	}

	public long getBPUnitTS() {
		return BPUnitTS;
	}

	public void setBPUnitTS(long bPUnitTS) {
		BPUnitTS = bPUnitTS;
	}

	public int getBGUnit() {
		return BGUnit;
	}

	public void setBGUnit(int bGUnit) {
		BGUnit = bGUnit;
	}

	public long getBGUnitTS() {
		return BGUnitTS;
	}

	public void setBGUnitTS(long bGUnitTS) {
		BGUnitTS = bGUnitTS;
	}

	public int getHeightUnit() {
		return HeightUnit;
	}

	public void setHeightUnit(int heightUnit) {
		HeightUnit = heightUnit;
	}

	public long getHeightUnitTS() {
		return HeightUnitTS;
	}

	public void setHeightUnitTS(long heightUnitTS) {
		HeightUnitTS = heightUnitTS;
	}

	public int getWeightUnit() {
		return WeightUnit;
	}

	public void setWeightUnit(int weightUnit) {
		WeightUnit = weightUnit;
	}

	public long getWeightUnitTS() {
		return WeightUnitTS;
	}

	public void setWeightUnitTS(long weightUnitTS) {
		WeightUnitTS = weightUnitTS;
	}

	public int getFoodWeightUnit() {
		return FoodWeightUnit;
	}

	public void setFoodWeightUnit(int foodWeightUnit) {
		FoodWeightUnit = foodWeightUnit;
	}

	public long getFoodWeightUnitTS() {
		return FoodWeightUnitTS;
	}

	public void setFoodWeightUnitTS(long foodWeightUnitTS) {
		FoodWeightUnitTS = foodWeightUnitTS;
	}

	public int getLengthUnit() {
		return LengthUnit;
	}

	public void setLengthUnit(int lengthUnit) {
		LengthUnit = lengthUnit;
	}

	public long getLengthUnitTS() {
		return LengthUnitTS;
	}

	public void setLengthUnitTS(long lengthUnitTS) {
		LengthUnitTS = lengthUnitTS;
	}

}
