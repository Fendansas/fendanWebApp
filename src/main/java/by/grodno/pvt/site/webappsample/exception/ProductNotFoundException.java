package by.grodno.pvt.site.webappsample.exception;

public class ProductNotFoundException extends RuntimeException {

    private String productId;

    public String getUserId() {
        return productId;
    }

    public void setUserId(String userId) {
        this.productId = userId;
    }
}
