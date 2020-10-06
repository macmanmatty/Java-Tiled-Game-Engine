package com.jessematty.black.tower.Maps.Buildings.Shops;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jessematty.black.tower.GameBaseClasses.Loaders.GameAssets;
import com.jessematty.black.tower.Maps.Buildings.Building;

import java.util.ArrayList;

public class Tavern extends Building {
	int minStats;
	int maxStats;
	int maxStrength;
	int minStrength;
	int maxSpeed;
	int minSpeed;
	int minExperience;
	int maxExperience;
	ArrayList<String> fighterGroups= new ArrayList<String>();
	ArrayList<String> weaponGroups = new ArrayList<String>();
	ArrayList<String> armorGroups= new ArrayList<String>();
	ArrayList<String> secondaryItemGroups= new ArrayList<String>();
	ArrayList<String> packGroups= new ArrayList<String>();
	ArrayList<String> soliderIds= new ArrayList<String>();
	ArrayList<Vector2> tables= new ArrayList<Vector2>();
	boolean randomFighters;
	boolean fightersReappear;










	public int getMinStats() {
		return minStats;
	}

	public void setMinStats(int minStats) {
		this.minStats = minStats;
	}

	public int getMaxStats() {
		return maxStats;
	}

	public void setMaxStats(int maxStats) {
		this.maxStats = maxStats;
	}

	public int getMaxStrength() {
		return maxStrength;
	}

	public void setMaxStrength(int maxStrength) {
		this.maxStrength = maxStrength;
	}

	public int getMinStrength() {
		return minStrength;
	}

	public void setMinStrength(int minStrength) {
		this.minStrength = minStrength;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getMinSpeed() {
		return minSpeed;
	}

	public void setMinSpeed(int minSpeed) {
		this.minSpeed = minSpeed;
	}

	public int getMinExperience() {
		return minExperience;
	}

	public void setMinExperience(int minExperience) {
		this.minExperience = minExperience;
	}

	public int getMaxExperience() {
		return maxExperience;
	}

	public void setMaxExperience(int maxExperience) {
		this.maxExperience = maxExperience;
	}

	public ArrayList<String> getFighterGroups() {
		return fighterGroups;
	}

	public void setFighterGroups(ArrayList<String> fighterGroups) {
		this.fighterGroups = fighterGroups;
	}

	public ArrayList<String> getWeaponGroups() {
		return weaponGroups;
	}

	public void setWeaponGroups(ArrayList<String> weaponGroups) {
		this.weaponGroups = weaponGroups;
	}

	public ArrayList<String> getArmorGroups() {
		return armorGroups;
	}

	public void setArmorGroups(ArrayList<String> armorGroups) {
		this.armorGroups = armorGroups;
	}

	public ArrayList<String> getSecondaryItemGroups() {
		return secondaryItemGroups;
	}

	public void setSecondaryItemGroups(ArrayList<String> secondaryItemGroups) {
		this.secondaryItemGroups = secondaryItemGroups;
	}

	public ArrayList<String> getPackGroups() {
		return packGroups;
	}

	public void setPackGroups(ArrayList<String> packGroups) {
		this.packGroups = packGroups;
	}

	public ArrayList<String> getSoliderIds() {
		return soliderIds;
	}

	public void setSoliderIds(ArrayList<String> soliderIds) {
		this.soliderIds = soliderIds;
	}

	public ArrayList<Vector2> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Vector2> tables) {
		this.tables = tables;
	}

	public boolean isRandomFighters() {
		return randomFighters;
	}

	public void setRandomFighters(boolean randomFighters) {
		this.randomFighters = randomFighters;
	}

	public boolean isFightersReappear() {
		return fightersReappear;
	}

	public void setFightersReappear(boolean fightersReappear) {
		this.fightersReappear = fightersReappear;
	}
}

	
	
	



