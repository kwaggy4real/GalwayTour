package com.example.galwaytour;

import java.io.*;
import java.lang.*;

/**
 * Created by Dave on 20/03/2015.
 */
public class Retrieved_Event {

    String event_name;
    String start_date;
    String end_date;
    String information;
    String location;
    String image_id;

    public Retrieved_Event(String name, String startdate, String enddate, String info, String loc, String imageid)
    {
        event_name = name;
        start_date = startdate;
        end_date = enddate;
        information = info;
        location = loc;
        image_id = imageid;

    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getInformation() {
        return information;
    }

    public String getLocation() {
        return location;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getImage_id() {
        return image_id;
    }
}
