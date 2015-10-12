/**
 * 
 */
/**
 * @author xzh
 *
 */
package com.neighbor.bean;

/*<website>美团网</website>
<deal_more_img>...</deal_more_img>
<reservation>0</reservation>
<destination/>
<partner>0</partner>
<city_name>惠州</city_name>
<city_id>huizhou</city_id>
<city_url>http://huizhou.meituan.com</city_url>
<deal_id>27885848</deal_id>
<deal_title>全部5选2，精致美味，幸福滋味</deal_title>
<deal_rank>0</deal_rank>
<deal_url>http://huizhou.meituan.com/deal/27885848.html</deal_url>
<deal_wap_url>http://i.meituan.com/deal/27885848.html</deal_wap_url>
<deal_img>...</deal_img>
<deal_cate_id>226</deal_cate_id>
<deal_cate>美食</deal_cate>
<deal_subcate_id>10</deal_subcate_id>
<deal_subcate>小吃</deal_subcate>
<deal_desc>...</deal_desc>
<deal_score>5.3159285</deal_score>
<value>30</value>
<price>24</price>
<rebate>8</rebate>
<refund>1</refund>
<sales_min>1</sales_min>
<sales_num>22</sales_num>
<sold_out>no</sold_out>
<is_post>no</is_post>
<start_time>1422347649</start_time>
<end_time>1453788000</end_time>
<coupon_start_time>1422347649</coupon_start_time>
<coupon_end_time>1453823999</coupon_end_time>
<deal_tips>...</deal_tips>
<deal_wow>...</deal_wow>
<deal_range>麦地片区</deal_range>
<deal_range_id>5794</deal_range_id>
<deal_district_id>3659</deal_district_id>
<deal_district_name>惠城区</deal_district_name>
<deal_address>...</deal_address>
<deal_lng>114.404561</deal_lng>
<deal_lat>23.072299</deal_lat>
<deal_name>优客韩国小吃全部</deal_name>
<deal_prom>0</deal_prom>
<dist>0</dist>
<deal_seller>...</deal_seller>
<deal_phones>...</deal_phones>
<deal_roomtype/>
<deal_roomtime/>
<coupon>0</coupon>*/

public class DealBean{
	private String website;
	private String deal_more_img;
	private String reservation;
	private String partner;
	private String city_name;
	private String city_id;
	private String city_url;
	private String deal_id;
	private String deal_title;
	private String deal_rank;
	private String deal_url;
	private String deal_wap_url;
	private String deal_img;
	private String deal_cate_id;
	private String deal_cate;
	private String deal_subcate_id;
	private String deal_subcate;
	private String deal_desc;
	private String deal_score;
	private String value;
	private String price;
	private String rebate;
	private String refund;
	private String sales_min;
	private String sales_num;
	private String sold_out;
	private String is_post;
	private String start_time;
	private String end_time;
	private String coupon_start_time;
	private String coupon_end_time;
	private String deal_tips;
	private String deal_wow;
	private String deal_range;
	private String deal_range_id;
	private String deal_district_id;
	private String deal_district_name;
	private String deal_address;
	private String deal_lng;
	private String deal_lat;
	private String deal_name;
	private String deal_prom;
	private String dist;
	private String deal_seller;
	private String deal_phones;
	private String coupon;
	
	
	
	public DealBean() {
		super();
	}

	public DealBean(String website, String deal_more_img, String reservation,
			String partner, String city_name, String city_id, String city_url,
			String deal_id, String deal_title, String deal_rank,
			String deal_url, String deal_wap_url, String deal_img,
			String deal_cate_id, String deal_cate, String deal_subcate_id,
			String deal_subcate, String deal_desc, String deal_score,
			String value, String price, String rebate, String refund,
			String sales_min, String sales_num, String sold_out,
			String is_post, String start_time, String end_time,
			String coupon_start_time, String coupon_end_time, String deal_tips,
			String deal_wow, String deal_range, String deal_range_id,
			String deal_district_id, String deal_district_name,
			String deal_address, String deal_lng, String deal_lat,
			String deal_name, String deal_prom, String dist,
			String deal_seller, String deal_phones, String coupon) {
		super();
		this.website = website;
		this.deal_more_img = deal_more_img;
		this.reservation = reservation;
		this.partner = partner;
		this.city_name = city_name;
		this.city_id = city_id;
		this.city_url = city_url;
		this.deal_id = deal_id;
		this.deal_title = deal_title;
		this.deal_rank = deal_rank;
		this.deal_url = deal_url;
		this.deal_wap_url = deal_wap_url;
		this.deal_img = deal_img;
		this.deal_cate_id = deal_cate_id;
		this.deal_cate = deal_cate;
		this.deal_subcate_id = deal_subcate_id;
		this.deal_subcate = deal_subcate;
		this.deal_desc = deal_desc;
		this.deal_score = deal_score;
		this.value = value;
		this.price = price;
		this.rebate = rebate;
		this.refund = refund;
		this.sales_min = sales_min;
		this.sales_num = sales_num;
		this.sold_out = sold_out;
		this.is_post = is_post;
		this.start_time = start_time;
		this.end_time = end_time;
		this.coupon_start_time = coupon_start_time;
		this.coupon_end_time = coupon_end_time;
		this.deal_tips = deal_tips;
		this.deal_wow = deal_wow;
		this.deal_range = deal_range;
		this.deal_range_id = deal_range_id;
		this.deal_district_id = deal_district_id;
		this.deal_district_name = deal_district_name;
		this.deal_address = deal_address;
		this.deal_lng = deal_lng;
		this.deal_lat = deal_lat;
		this.deal_name = deal_name;
		this.deal_prom = deal_prom;
		this.dist = dist;
		this.deal_seller = deal_seller;
		this.deal_phones = deal_phones;
		this.coupon = coupon;
	}
	
	
	
	
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getDeal_more_img() {
		return deal_more_img;
	}
	public void setDeal_more_img(String deal_more_img) {
		this.deal_more_img = deal_more_img;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCity_url() {
		return city_url;
	}
	public void setCity_url(String city_url) {
		this.city_url = city_url;
	}
	public String getDeal_id() {
		return deal_id;
	}
	public void setDeal_id(String deal_id) {
		this.deal_id = deal_id;
	}
	public String getDeal_title() {
		return deal_title;
	}
	public void setDeal_title(String deal_title) {
		this.deal_title = deal_title;
	}
	public String getDeal_rank() {
		return deal_rank;
	}
	public void setDeal_rank(String deal_rank) {
		this.deal_rank = deal_rank;
	}
	public String getDeal_url() {
		return deal_url;
	}
	public void setDeal_url(String deal_url) {
		this.deal_url = deal_url;
	}
	public String getDeal_wap_url() {
		return deal_wap_url;
	}
	public void setDeal_wap_url(String deal_wap_url) {
		this.deal_wap_url = deal_wap_url;
	}
	public String getDeal_img() {
		return deal_img;
	}
	public void setDeal_img(String deal_img) {
		this.deal_img = deal_img;
	}
	public String getDeal_cate_id() {
		return deal_cate_id;
	}
	public void setDeal_cate_id(String deal_cate_id) {
		this.deal_cate_id = deal_cate_id;
	}
	public String getDeal_cate() {
		return deal_cate;
	}
	public void setDeal_cate(String deal_cate) {
		this.deal_cate = deal_cate;
	}
	public String getDeal_subcate_id() {
		return deal_subcate_id;
	}
	public void setDeal_subcate_id(String deal_subcate_id) {
		this.deal_subcate_id = deal_subcate_id;
	}
	public String getDeal_subcate() {
		return deal_subcate;
	}
	public void setDeal_subcate(String deal_subcate) {
		this.deal_subcate = deal_subcate;
	}
	public String getDeal_desc() {
		return deal_desc;
	}
	public void setDeal_desc(String deal_desc) {
		this.deal_desc = deal_desc;
	}
	public String getDeal_score() {
		return deal_score;
	}
	public void setDeal_score(String deal_score) {
		this.deal_score = deal_score;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRebate() {
		return rebate;
	}
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
	public String getSales_min() {
		return sales_min;
	}
	public void setSales_min(String sales_min) {
		this.sales_min = sales_min;
	}
	public String getSales_num() {
		return sales_num;
	}
	public void setSales_num(String sales_num) {
		this.sales_num = sales_num;
	}
	public String getSold_out() {
		return sold_out;
	}
	public void setSold_out(String sold_out) {
		this.sold_out = sold_out;
	}
	public String getIs_post() {
		return is_post;
	}
	public void setIs_post(String is_post) {
		this.is_post = is_post;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getCoupon_start_time() {
		return coupon_start_time;
	}
	public void setCoupon_start_time(String coupon_start_time) {
		this.coupon_start_time = coupon_start_time;
	}
	public String getCoupon_end_time() {
		return coupon_end_time;
	}
	public void setCoupon_end_time(String coupon_end_time) {
		this.coupon_end_time = coupon_end_time;
	}
	public String getDeal_tips() {
		return deal_tips;
	}
	public void setDeal_tips(String deal_tips) {
		this.deal_tips = deal_tips;
	}
	public String getDeal_wow() {
		return deal_wow;
	}
	public void setDeal_wow(String deal_wow) {
		this.deal_wow = deal_wow;
	}
	public String getDeal_range() {
		return deal_range;
	}
	public void setDeal_range(String deal_range) {
		this.deal_range = deal_range;
	}
	public String getDeal_range_id() {
		return deal_range_id;
	}
	public void setDeal_range_id(String deal_range_id) {
		this.deal_range_id = deal_range_id;
	}
	public String getDeal_district_id() {
		return deal_district_id;
	}
	public void setDeal_district_id(String deal_district_id) {
		this.deal_district_id = deal_district_id;
	}
	public String getDeal_district_name() {
		return deal_district_name;
	}
	public void setDeal_district_name(String deal_district_name) {
		this.deal_district_name = deal_district_name;
	}
	public String getDeal_address() {
		return deal_address;
	}
	public void setDeal_address(String deal_address) {
		this.deal_address = deal_address;
	}
	public String getDeal_lng() {
		return deal_lng;
	}
	public void setDeal_lng(String deal_lng) {
		this.deal_lng = deal_lng;
	}
	public String getDeal_lat() {
		return deal_lat;
	}
	public void setDeal_lat(String deal_lat) {
		this.deal_lat = deal_lat;
	}
	public String getDeal_name() {
		return deal_name;
	}
	public void setDeal_name(String deal_name) {
		this.deal_name = deal_name;
	}
	public String getDeal_prom() {
		return deal_prom;
	}
	public void setDeal_prom(String deal_prom) {
		this.deal_prom = deal_prom;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getDeal_seller() {
		return deal_seller;
	}
	public void setDeal_seller(String deal_seller) {
		this.deal_seller = deal_seller;
	}
	public String getDeal_phones() {
		return deal_phones;
	}
	public void setDeal_phones(String deal_phones) {
		this.deal_phones = deal_phones;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	@Override
	public String toString() {
		return "DealBean [website=" + website + ", deal_more_img="
				+ deal_more_img + ", reservation=" + reservation + ", partner="
				+ partner + ", city_name=" + city_name + ", city_id=" + city_id
				+ ", city_url=" + city_url + ", deal_id=" + deal_id
				+ ", deal_title=" + deal_title + ", deal_rank=" + deal_rank
				+ ", deal_url=" + deal_url + ", deal_wap_url=" + deal_wap_url
				+ ", deal_img=" + deal_img + ", deal_cate_id=" + deal_cate_id
				+ ", deal_cate=" + deal_cate + ", deal_subcate_id="
				+ deal_subcate_id + ", deal_subcate=" + deal_subcate
				+ ", deal_desc=" + deal_desc + ", deal_score=" + deal_score
				+ ", value=" + value + ", price=" + price + ", rebate="
				+ rebate + ", refund=" + refund + ", sales_min=" + sales_min
				+ ", sales_num=" + sales_num + ", sold_out=" + sold_out
				+ ", is_post=" + is_post + ", start_time=" + start_time
				+ ", end_time=" + end_time + ", coupon_start_time="
				+ coupon_start_time + ", coupon_end_time=" + coupon_end_time
				+ ", deal_tips=" + deal_tips + ", deal_wow=" + deal_wow
				+ ", deal_range=" + deal_range + ", deal_range_id="
				+ deal_range_id + ", deal_district_id=" + deal_district_id
				+ ", deal_district_name=" + deal_district_name
				+ ", deal_address=" + deal_address + ", deal_lng=" + deal_lng
				+ ", deal_lat=" + deal_lat + ", deal_name=" + deal_name
				+ ", deal_prom=" + deal_prom + ", dist=" + dist
				+ ", deal_seller=" + deal_seller + ", deal_phones="
				+ deal_phones + ", coupon=" + coupon + "]";
	}
	
	
}

