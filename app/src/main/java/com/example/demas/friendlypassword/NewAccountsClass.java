package com.example.demas.friendlypassword;

public class NewAccountsClass
{
    private String appSiteName;
    private String id;
    private String password;
    private String imageUrl;
//create getters and setters ready to be used
    public NewAccountsClass(){};
    public NewAccountsClass(String appSiteName, String id, String password,String imageUrl)
    {
        this.setAppSiteName(appSiteName);
        this.setId(id);
        this.setPassword(password);
        this.setImageUrl(imageUrl);

    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }
    public String getAppSiteName() {
        return appSiteName;
    }

    public void setAppSiteName(String appSiteName) {
        this.appSiteName = appSiteName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
