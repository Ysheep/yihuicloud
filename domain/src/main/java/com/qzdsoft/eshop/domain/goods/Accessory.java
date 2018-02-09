package com.qzdsoft.eshop.domain.goods;

import java.util.Date;
import javax.persistence.*;

public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accessory_id")
    private Integer accessoryId;

    private String url;

    private String name;

    private Date ctime;

    /**
     * @return accessory_id
     */
    public Integer getAccessoryId() {
        return accessoryId;
    }

    /**
     * @param accessoryId
     */
    public void setAccessoryId(Integer accessoryId) {
        this.accessoryId = accessoryId;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return ctime
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * @param ctime
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}