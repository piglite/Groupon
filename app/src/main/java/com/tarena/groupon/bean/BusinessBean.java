package com.tarena.groupon.bean;

import java.io.Serializable;
import java.util.List;

public class BusinessBean implements Serializable{
	String status;
	int total_count;
	int count;
	List<Business> businesses;

	@Override
	public String toString() {
		return "BusinessBean [status=" + status + ", total_count=" + total_count + ", count=" + count + ", businesses="
				+ businesses + "]";
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getTotal_count() {
		return total_count;
	}



	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public List<Business> getBusinesses() {
		return businesses;
	}



	public void setBusinesses(List<Business> businesses) {
		this.businesses = businesses;
	}



	public static class Business implements Serializable{
		int business_id;
		String name;
		String branch_name;
		String address;
		String telephone;
		String city;
		List<String> regions;
		List<String> categories;
		double latitude;
		double longitude;
		double avg_rating;
		String rating_img_rul;
		String ragting_s_img_url;
		int product_grade;
		int decoration_grade;
		int service_grade;
		int product_score;
		int service_score;
		double avg_price;
		int review_count;
		String review_list_url;
		int distance;
		String business_url;
		String photo_url;
		String s_photo_url;
		int photo_count;
		String photo_list_url;
		int has_coupon;
		int coupon_id;
		String coupon_description;
		String coupon_url;
		int has_deal;
		int deal_count;
		List<Deal> deals;
		int has_online_reservation;
		String online_reservation_url;



		@Override
		public String toString() {
			return "Business [business_id=" + business_id + ", name=" + name + ", branch_name=" + branch_name
					+ ", address=" + address + ", telephone=" + telephone + ", city=" + city + ", regions=" + regions
					+ ", categories=" + categories + ", latitude=" + latitude + ", longitude=" + longitude
					+ ", avg_rating=" + avg_rating + ", rating_img_rul=" + rating_img_rul + ", ragting_s_img_url="
					+ ragting_s_img_url + ", product_grade=" + product_grade + ", decoration_grade=" + decoration_grade
					+ ", service_grade=" + service_grade + ", product_score=" + product_score + ", service_score="
					+ service_score + ", avg_price=" + avg_price + ", review_count=" + review_count
					+ ", review_list_url=" + review_list_url + ", distance=" + distance + ", business_url="
					+ business_url + ", photo_url=" + photo_url + ", s_photo_url=" + s_photo_url + ", photo_count="
					+ photo_count + ", photo_list_url=" + photo_list_url + ", has_coupon=" + has_coupon + ", coupon_id="
					+ coupon_id + ", coupon_description=" + coupon_description + ", coupon_url=" + coupon_url
					+ ", has_deal=" + has_deal + ", deal_count=" + deal_count + ", deals=" + deals
					+ ", has_online_reservation=" + has_online_reservation + ", online_reservation_url="
					+ online_reservation_url + "]";
		}



		public int getBusiness_id() {
			return business_id;
		}



		public void setBusiness_id(int business_id) {
			this.business_id = business_id;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}



		public String getBranch_name() {
			return branch_name;
		}



		public void setBranch_name(String branch_name) {
			this.branch_name = branch_name;
		}



		public String getAddress() {
			return address;
		}



		public void setAddress(String address) {
			this.address = address;
		}



		public String getTelephone() {
			return telephone;
		}



		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}



		public String getCity() {
			return city;
		}



		public void setCity(String city) {
			this.city = city;
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



		public double getLatitude() {
			return latitude;
		}



		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}



		public double getLongitude() {
			return longitude;
		}



		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}



		public double getAvg_rating() {
			return avg_rating;
		}



		public void setAvg_rating(double avg_rating) {
			this.avg_rating = avg_rating;
		}



		public String getRating_img_rul() {
			return rating_img_rul;
		}



		public void setRating_img_rul(String rating_img_rul) {
			this.rating_img_rul = rating_img_rul;
		}



		public String getRagting_s_img_url() {
			return ragting_s_img_url;
		}



		public void setRagting_s_img_url(String ragting_s_img_url) {
			this.ragting_s_img_url = ragting_s_img_url;
		}



		public int getProduct_grade() {
			return product_grade;
		}



		public void setProduct_grade(int product_grade) {
			this.product_grade = product_grade;
		}



		public int getDecoration_grade() {
			return decoration_grade;
		}



		public void setDecoration_grade(int decoration_grade) {
			this.decoration_grade = decoration_grade;
		}



		public int getService_grade() {
			return service_grade;
		}



		public void setService_grade(int service_grade) {
			this.service_grade = service_grade;
		}



		public int getProduct_score() {
			return product_score;
		}



		public void setProduct_score(int product_score) {
			this.product_score = product_score;
		}



		public int getService_score() {
			return service_score;
		}



		public void setService_score(int service_score) {
			this.service_score = service_score;
		}



		public double getAvg_price() {
			return avg_price;
		}



		public void setAvg_price(double avg_price) {
			this.avg_price = avg_price;
		}



		public int getReview_count() {
			return review_count;
		}



		public void setReview_count(int review_count) {
			this.review_count = review_count;
		}



		public String getReview_list_url() {
			return review_list_url;
		}



		public void setReview_list_url(String review_list_url) {
			this.review_list_url = review_list_url;
		}



		public int getDistance() {
			return distance;
		}



		public void setDistance(int distance) {
			this.distance = distance;
		}



		public String getBusiness_url() {
			return business_url;
		}



		public void setBusiness_url(String business_url) {
			this.business_url = business_url;
		}



		public String getPhoto_url() {
			return photo_url;
		}



		public void setPhoto_url(String photo_url) {
			this.photo_url = photo_url;
		}



		public String getS_photo_url() {
			return s_photo_url;
		}



		public void setS_photo_url(String s_photo_url) {
			this.s_photo_url = s_photo_url;
		}



		public int getPhoto_count() {
			return photo_count;
		}



		public void setPhoto_count(int photo_count) {
			this.photo_count = photo_count;
		}



		public String getPhoto_list_url() {
			return photo_list_url;
		}



		public void setPhoto_list_url(String photo_list_url) {
			this.photo_list_url = photo_list_url;
		}



		public int getHas_coupon() {
			return has_coupon;
		}



		public void setHas_coupon(int has_coupon) {
			this.has_coupon = has_coupon;
		}



		public int getCoupon_id() {
			return coupon_id;
		}



		public void setCoupon_id(int coupon_id) {
			this.coupon_id = coupon_id;
		}



		public String getCoupon_description() {
			return coupon_description;
		}



		public void setCoupon_description(String coupon_description) {
			this.coupon_description = coupon_description;
		}



		public String getCoupon_url() {
			return coupon_url;
		}



		public void setCoupon_url(String coupon_url) {
			this.coupon_url = coupon_url;
		}



		public int getHas_deal() {
			return has_deal;
		}



		public void setHas_deal(int has_deal) {
			this.has_deal = has_deal;
		}



		public int getDeal_count() {
			return deal_count;
		}



		public void setDeal_count(int deal_count) {
			this.deal_count = deal_count;
		}



		public List<Deal> getDeals() {
			return deals;
		}



		public void setDeals(List<Deal> deals) {
			this.deals = deals;
		}



		public int getHas_online_reservation() {
			return has_online_reservation;
		}



		public void setHas_online_reservation(int has_online_reservation) {
			this.has_online_reservation = has_online_reservation;
		}



		public String getOnline_reservation_url() {
			return online_reservation_url;
		}



		public void setOnline_reservation_url(String online_reservation_url) {
			this.online_reservation_url = online_reservation_url;
		}



		public static class Deal implements Serializable{
			String id;
			String description;
			String url;
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public String getDescription() {
				return description;
			}
			public void setDescription(String description) {
				this.description = description;
			}
			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}
			@Override
			public String toString() {
				return "Deal [id=" + id + ", description=" + description + ", url=" + url + "]";
			}


		}
	}
}
