package com.jessematty.black.tower.Components.Other;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public  abstract  class StorageCompartmentPack implements  Component {

	java.util.Map<Integer, Entity> items = new HashMap<Integer, Entity>(); // the items in the pack.
	ArrayList<String> groupsAddable = new ArrayList<String>();
	int compartments;




	public StorageCompartmentPack() {
	}

	public StorageCompartmentPack(StorageCompartmentPack other) {


	}


	public Entity getItem(int number) {
		return items.get(number);
	}


	public java.util.Map<Integer, Entity> getAllItems() {
		return items;
	}

	public ArrayList<String> getGroupsAddable() {
		return groupsAddable;
	}


	public int getNumberOfItems() {
		return items.size();
	}

	public void emptyPack() {
		items.clear();
	}


	public int getCompartments() {
		return compartments;
	}

	public void setCompartments(int compartments) {
		this.compartments = compartments;
	}
}

