package com.kingdomsofargus.kingdoms.player;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class User {

	private Player player;
	private boolean lastOnGround = false;
	private boolean beforeOnGround = false;
	private boolean threeGround = false;
	private boolean fourGround = false;
	private boolean fiveGround = false;
	private boolean sixGround = false;
	private boolean speedImmune = false;
	private boolean noslowImmune =  false;
	private boolean flyimmune = false;
	private boolean lastNegative = false;
	private boolean beforeNegative = false;
	private boolean threeNegative = false;
	private boolean lastOnClimable = false;
	private boolean threeOnClimable = false;
	
	private int vioations = 0;
	
	private double lastDistY = 0;
	private double lastDist = 0;
	
	private boolean CP;
	
	private int task1 = 0;
	private int task2 = 0;
	private int task3 = 0;
	private int task4 = 0;
	private int task5 = 0;
	private int task6 = 0;
	
	public User(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}

	public boolean wasLastOnGround() {
		return lastOnGround;
	}

	public void setLastOnGround(boolean lastOnGround) {
		this.lastOnGround = lastOnGround;
	}

	public boolean isSpeedImmune() {
		return speedImmune;
	}

	public void setSpeedImmune(boolean speedImmune) {
		this.speedImmune = speedImmune;
	}

	public boolean isNoslowImmune() {
		return noslowImmune;
	}

	public void setNoslowImmune(boolean noslowImmune) {
		this.noslowImmune = noslowImmune;
	}

	public boolean isFlyimmune() {
		return flyimmune;
	}

	public void setFlyimmune(boolean flyimmune) {
		this.flyimmune = flyimmune;
	}

	public boolean isBeforeOnGround() {
		return beforeOnGround;
	}

	public void setBeforeOnGround(boolean beforeOnGround) {
		this.beforeOnGround = beforeOnGround;
	}

	public boolean isThreeGround() {
		return threeGround;
	}

	public void setThreeGround(boolean threeGround) {
		this.threeGround = threeGround;
	}

	public boolean isFourGround() {
		return fourGround;
	}

	public void setFourGround(boolean fourGround) {
		this.fourGround = fourGround;
	}

	public boolean isFiveGround() {
		return fiveGround;
	}

	public void setFiveGround(boolean fiveGround) {
		this.fiveGround = fiveGround;
	}

	public boolean isSixGround() {
		return sixGround;
	}

	public void setSixGround(boolean sixGround) {
		this.sixGround = sixGround;
	}

	public boolean isLastNegative() {
		return lastNegative;
	}

	public void setLastNegative(boolean lastNegative) {
		this.lastNegative = lastNegative;
	}

	public boolean isBeforeNegative() {
		return beforeNegative;
	}

	public void setBeforeNegative(boolean beforeNegative) {
		this.beforeNegative = beforeNegative;
	}

	public boolean isThreeNegative() {
		return threeNegative;
	}

	public void setThreeNegative(boolean threeNegative) {
		this.threeNegative = threeNegative;
	}

	public boolean isLastOnClimable() {
		return lastOnClimable;
	}

	public void setLastOnClimable(boolean lastOnClimable) {
		this.lastOnClimable = lastOnClimable;
	}

	public boolean isThreeOnClimable() {
		return threeOnClimable;
	}

	public void setThreeOnClimable(boolean threeOnClimable) {
		this.threeOnClimable = threeOnClimable;
	}

	public boolean isLastOnGround() {
		return lastOnGround;
	}

	public double getLastDistY() {
		return lastDistY;
	}

	public void setLastDistY(double lastDistY) {
		this.lastDistY = lastDistY;
	}

	public double getLastDist() {
		return lastDist;
	}

	public void setLastDist(double lastDist) {
		this.lastDist = lastDist;
	}

	public int getVioations() {
		return vioations;
	}

	public void addVioation() {
		vioations++;
	}
	
	public void resetViolations() {
		vioations = 0;
	}

	public boolean isCP() {
		return CP;
	}

	public void setCP(boolean cP) {
		CP = cP;
	}

	public int getTask1() {
		return task1;
	}

	public void setTask1(int task1) {
		this.task1 = task1;
	}

	public int getTask2() {
		return task2;
	}

	public void setTask2(int task2) {
		this.task2 = task2;
	}

	public int getTask3() {
		return task3;
	}

	public void setTask3(int task3) {
		this.task3 = task3;
	}

	public int getTask4() {
		return task4;
	}

	public void setTask4(int task4) {
		this.task4 = task4;
	}

	public int getTask5() {
		return task5;
	}

	public void setTask5(int task5) {
		this.task5 = task5;
	}

	public int getTask6() {
		return task6;
	}

	public void setTask6(int task6) {
		this.task6 = task6;
	}
	
	
}
