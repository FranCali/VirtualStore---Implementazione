package it.unisa.beans;

public class ReviewBean {

	String user_email, title, description, review_date;
	int content_id, rating;
	
	public ReviewBean() {
		user_email = "";
		title = "";
		description = "";
		review_date = "";
		content_id = 0;
		rating = 0;
	}

	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
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

	public String getReview_date() {
		return review_date;
	}

	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "ReviewBean [user_email=" + user_email + ", title=" + title + ", description=" + description
				+ ", review_date=" + review_date + ", content_id=" + content_id + ", rating=" + rating + "]";
	}
	
}
