package com.dutch_computer_technology.UID;

import java.security.SecureRandom;
import java.util.HexFormat;

/**
 * Generate a new UID or from a String
 */
public class UID {
	
	private static final SecureRandom ran = new SecureRandom();
	
	private long time;
	private int version;
	private byte[] key;
	private String uid;
	
	/**
	 * Constructs a new UID using version {@code 1}
	 */
	public UID() {
		
		generate(1);
		
	};
	
	/**
	 * Constructs a new UID using the provided version
	 * 
	 * @param version Version used to generate
	 * @throws IllegalArgumentException Caused by an invalid version
	 */
	public UID(int version) {
		
		generate(version);
		
	};
	
	/**
	 * Constructs a new UID using the provided version
	 * 
	 * @param version Version used to generate
	 * @throws IllegalArgumentException Caused by an invalid version
	 */
	private void generate(int version) throws IllegalArgumentException {
		
		switch(version) {
			
			case 1:
				
				this.version = 1;
				this.time = System.currentTimeMillis();
				this.key = new byte[12];
				ran.nextBytes(this.key);
				
				HexFormat hex = HexFormat.of().withUpperCase();
				StringBuilder uid = new StringBuilder();
				uid.append(hex.toHexDigits(this.version, 2));
				uid.append("-");
				uid.append(hex.toHexDigits( (int)((this.time >> 32) & 0xFFFF) , 4));
				uid.append(hex.toHexDigits( (int)((this.time >> 16) & 0xFFFF) , 4));
				uid.append(hex.toHexDigits( (int)(this.time & 0xFFFF) , 4));
				uid.append("-");
				uid.append(hex.formatHex(this.key));
				
				this.uid = uid.toString();
				
				break;
				
			default:
				throw new IllegalArgumentException("Invalid UID version");
			
		};
		
	};
	
	/**
	 * Constructs a new UID using the provided String
	 * 
	 * @param uid String used to generate
	 * @throws IllegalArgumentException Caused by an invalid uid
	 */
	public UID(String uid) {
		
		if (uid == null || uid.isBlank()) throw new IllegalArgumentException("No UID String provided");
		this.uid = uid;
		
		try {
			
			this.version = HexFormat.fromHexDigits(uid.substring(0, 2));
			
		} catch(IllegalArgumentException e) {
			
			throw new IllegalArgumentException("Invalid UID version", e);
			
		};
		
		switch(this.version) {
			
			case 1:
				
				if (uid.length() != 40) throw new IllegalArgumentException("Invalid UID size");
				
				try {
					
					long high = HexFormat.fromHexDigits(uid.substring(3, 7));
					long mid = HexFormat.fromHexDigits(uid.substring(7, 11));
					long low = HexFormat.fromHexDigits(uid.substring(11, 15));
					this.time = (high << 32) | (mid << 16) | low;
					
				} catch(IllegalArgumentException e) {
					
					throw new IllegalArgumentException("Invalid UID time", e);
					
				};
				
				HexFormat hex = HexFormat.of().withUpperCase();
				
				try {
					
					this.key = hex.parseHex(uid.substring(16));
					
				} catch(IllegalArgumentException e) {
					
					throw new IllegalArgumentException("Invalid UID key", e);
					
				};
				
				break;
				
			default:
				throw new IllegalArgumentException("Invalid UID version");
			
		};
		
	};
	
	@Override
	public String toString() {
		
		return this.uid;
		
	};
	
	/**
	 * Get version of the UID
	 * 
	 * @return Returns the version
	 */
	public int getVersion() {
		
		return this.version;
		
	};
	
	/**
	 * Get time of the UID
	 * 
	 * @return Returns the time when generated
	 */
	public long getTime() {
		
		return this.time;
		
	};
	
	/**
	 * Get key of the UID
	 * 
	 * @return Returns the random key of the UID
	 */
	public byte[] getKey() {
		
		return this.key.clone();
		
	};
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) return false;
		return obj.toString().equals(this.toString());
		
	};
	
};