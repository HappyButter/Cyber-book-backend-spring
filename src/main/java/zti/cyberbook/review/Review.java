package zti.cyberbook.review;


public class Review {
    private String userId;

    private Double ISBN;

    private Integer rate;

    private String review;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getISBN() {
        return ISBN;
    }

    public void setISBN(Double ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
