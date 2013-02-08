package me.sablednah.legendquest.races;

import java.util.List;

public class Race {

	public String filename;
	public String name;
	public String plural;
	public Double size;
	public boolean defaultRace;
	public int statStr;
	public int statDex;
	public int statInt;
	public int statWis;
	public int statCon;
	public int statChr;
	public int baseHealth;
	
	public String perm;
	
	public List<Integer>	allowedTools;
	public List<Integer>	allowedArmour;
	public List<Integer>	allowedWeapons;

	public List<Integer>	dissallowedTools;
	public List<Integer>	dissallowedArmour;
	public List<Integer>	dissallowedWeapons;

	
	// frequency used for NPC chance.
	public int frequency;

	// list of race "groups"  such as good,evil,orcoid,humanoid.  
	// Used by classes as allowes races - e.g. Paladin could be allowed to all good races.  
	public List<String> groups;

}