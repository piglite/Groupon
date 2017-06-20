package com.tarena.groupon.bean;

import java.util.List;

public class TuanBean {
	
	String status;
	int count;
	List<Deal> deals;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Deal> getDeals() {
		return deals;
	}
	public void setDeals(List<Deal> deals) {
		this.deals = deals;
	}
	@Override
	public String toString() {
		return "TuanBean [status=" + status + ", count=" + count + ", deals=" + deals + "]";
	}
	public static class Deal{
		String deal_id;
		String title;
		String description;
		String city;
		double list_price;
		double current_price;
		List<String> regions;
		List<String> categories;
		int purchase_count;
		String purchase_deadline;
		String publish_date;
		String details;
		String image_url;
		String s_image_url;
		List<String> more_image_urls;
		List<String> more_s_image_urls;
		int is_popular;
		Restrictions restrictions;
		String notice;
		String deal_url;
		String deal_h5_url;
		int commission_ratio;
		List<Business> businesses;



		@Override
		public String toString() {
			return "Deal [deal_id=" + deal_id + ", title=" + title + ", description=" + description + ", city=" + city
					+ ", list_price=" + list_price + ", current_price=" + current_price + ", regions=" + regions
					+ ", categories=" + categories + ", purchase_count=" + purchase_count + ", purchase_deadline="
					+ purchase_deadline + ", publish_date=" + publish_date + ", details=" + details + ", image_url="
					+ image_url + ", s_image_url=" + s_image_url + ", more_image_urls=" + more_image_urls
					+ ", more_s_image_urls=" + more_s_image_urls + ", is_popular=" + is_popular + ", restrictions="
					+ restrictions + ", notice=" + notice + ", deal_url=" + deal_url + ", deal_h5_url=" + deal_h5_url
					+ ", commission_ratio=" + commission_ratio + ", businesses=" + businesses + "]";
		}
		public String getDeal_id() {
			return deal_id;
		}
		public void setDeal_id(String deal_id) {
			this.deal_id = deal_id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		
		public double getList_price() {
			return list_price;
		}
		public void setList_price(double list_price) {
			this.list_price = list_price;
		}
		public double getCurrent_price() {
			return current_price;
		}
		public void setCurrent_price(double current_price) {
			this.current_price = current_price;
		}
		public List<String> getRegions() {
			return regions;
		}
		public void setRegions(List<String> regions) {
			this.regions = regions;
		}
		public List<String> getCategories() {
			return categories;
		}
		public void setCategories(List<String> categories) {
			this.categories = categories;
		}
		public int getPurchase_count() {
			return purchase_count;
		}
		public void setPurchase_count(int purchase_count) {
			this.purchase_count = purchase_count;
		}
		public String getPurchase_deadline() {
			return purchase_deadline;
		}
		public void setPurchase_deadline(String purchase_deadline) {
			this.purchase_deadline = purchase_deadline;
		}
		public String getPublish_date() {
			return publish_date;
		}
		public void setPublish_date(String publish_date) {
			this.publish_date = publish_date;
		}
		public String getDetails() {
			return details;
		}
		public void setDetails(String details) {
			this.details = details;
		}
		public String getImage_url() {
			return image_url;
		}
		public void setImage_url(String image_url) {
			this.image_url = image_url;
		}
		public String getS_image_url() {
			return s_image_url;
		}
		public void setS_image_url(String s_image_url) {
			this.s_image_url = s_image_url;
		}
		public List<String> getMore_image_urls() {
			return more_image_urls;
		}
		public void setMore_image_urls(List<String> more_image_urls) {
			this.more_image_urls = more_image_urls;
		}
		public List<String> getMore_s_image_urls() {
			return more_s_image_urls;
		}
		public void setMore_s_image_urls(List<String> more_s_image_urls) {
			this.more_s_image_urls = more_s_image_urls;
		}
		public int getIs_popular() {
			return is_popular;
		}
		public void setIs_popular(int is_popular) {
			this.is_popular = is_popular;
		}
		public Restrictions getRestrictions() {
			return restrictions;
		}
		public void setRestrictions(Restrictions restrictions) {
			this.restrictions = restrictions;
		}
		public String getNotice() {
			return notice;
		}
		public void setNotice(String notice) {
			this.notice = notice;
		}
		public String getDeal_url() {
			return deal_url;
		}
		public void setDeal_url(String deal_url) {
			this.deal_url = deal_url;
		}
		public String getDeal_h5_url() {
			return deal_h5_url;
		}
		public void setDeal_h5_url(String deal_h5_url) {
			this.deal_h5_url = deal_h5_url;
		}
		public int getCommission_ratio() {
			return commission_ratio;
		}
		public void setCommission_ratio(int commission_ratio) {
			this.commission_ratio = commission_ratio;
		}
		public List<Business> getBusinesses() {
			return businesses;
		}
		public void setBusinesses(List<Business> businesses) {
			this.businesses = businesses;
		}
		public static class Restrictions{
			int is_reservation_required;
			int is_refundable;
			String special_tips;
			public int getIs_reservation_required() {
				return is_reservation_required;
			}
			public void setIs_reservation_required(int is_reservation_required) {
				this.is_reservation_required = is_reservation_required;
			}
			public int getIs_refundable() {
				return is_refundable;
			}
			public void setIs_refundable(int is_refundable) {
				this.is_refundable = is_refundable;
			}
			public String getSpecial_tips() {
				return special_tips;
			}
			public void setSpecial_tips(String special_tips) {
				this.special_tips = special_tips;
			}
			@Override
			public String toString() {
				return "Restrictions [is_reservation_required=" + is_reservation_required + ", is_refundable="
						+ is_refundable + ", special_tips=" + special_tips + "]";
			}

		}
		public static class Business{
			String name;
			long id;
			String city;
			String address;
			double latitude;
			double longitude;
			String url;
			String h5_url;
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public long getId() {
				return id;
			}
			public void setId(long id) {
				this.id = id;
			}
			public String getCity() {
				return city;
			}
			public void setCity(String city) {
				this.city = city;
			}
			public String getAddress() {
				return address;
			}
			public void setAddress(String address) {
				this.address = address;
			}
			public double getLatitude() {
				return latitude;
			}
			public void setLatitude(double latitude) {
				this.latitude = latitude;
			}
			public double getLongitude() {
				return longitude;
			}
			public void setLongtitude(double longitude) {
				this.longitude = longitude;
			}
			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}
			public String getH5_url() {
				return h5_url;
			}
			public void setH5_url(String h5_url) {
				this.h5_url = h5_url;
			}
			@Override
			public String toString() {
				return "Business [name=" + name + ", id=" + id + ", city=" + city + ", address=" + address
						+ ", latitude=" + latitude + ", longitude=" + longitude + ", url=" + url + ", h5_url="
						+ h5_url + "]";
			}

		}

	}
}
