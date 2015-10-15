package com.neighbor.bean;

import java.io.Serializable;

/*<shop_name>优客韩国小吃</shop_name>
<shop_poiid>42919967</shop_poiid>
<shop_tel>13288172517</shop_tel>
<shop_addr>...</shop_addr>
<shop_area>...</shop_area>
<shop_long>114.404561</shop_long>
<shop_lat>23.072299</shop_lat>
<shop_city>惠州</shop_city>
*/
public class ShopBean implements Serializable {

	private String shop_name ;
	private String shop_poiid ;
	private String shop_tel ;
	private String shop_addr ;
	private String shop_area ;
	private String shop_long ;
	private String shop_lat ;
	private String shop_city ;
	
	public ShopBean() {
		super();
	}

	public ShopBean(String shop_name, String shop_poiid, String shop_tel,
			String shop_addr, String shop_area, String shop_long,
			String shop_lat, String shop_city) {
		super();
		this.shop_name = shop_name;
		this.shop_poiid = shop_poiid;
		this.shop_tel = shop_tel;
		this.shop_addr = shop_addr;
		this.shop_area = shop_area;
		this.shop_long = shop_long;
		this.shop_lat = shop_lat;
		this.shop_city = shop_city;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getShop_poiid() {
		return shop_poiid;
	}

	public void setShop_poiid(String shop_poiid) {
		this.shop_poiid = shop_poiid;
	}

	public String getShop_tel() {
		return shop_tel;
	}

	public void setShop_tel(String shop_tel) {
		this.shop_tel = shop_tel;
	}

	public String getShop_addr() {
		return shop_addr;
	}

	public void setShop_addr(String shop_addr) {
		this.shop_addr = shop_addr;
	}

	public String getShop_area() {
		return shop_area;
	}

	public void setShop_area(String shop_area) {
		this.shop_area = shop_area;
	}

	public String getShop_long() {
		return shop_long;
	}

	public void setShop_long(String shop_long) {
		this.shop_long = shop_long;
	}

	public String getShop_lat() {
		return shop_lat;
	}

	public void setShop_lat(String shop_lat) {
		this.shop_lat = shop_lat;
	}

	public String getShop_city() {
		return shop_city;
	}

	public void setShop_city(String shop_city) {
		this.shop_city = shop_city;
	}

	@Override
	public String toString() {
		return "ShopBean [shop_name=" + shop_name + ", shop_poiid="
				+ shop_poiid + ", shop_tel=" + shop_tel + ", shop_addr="
				+ shop_addr + ", shop_area=" + shop_area + ", shop_long="
				+ shop_long + ", shop_lat=" + shop_lat + ", shop_city="
				+ shop_city + "]";
	}
	
	
}
