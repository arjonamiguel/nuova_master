package com.nuova.dto;

import java.util.Date;

public class CalendarDTO {
    private Integer id;
    private String resourceId;
    private String start;
    private String end;
    private String title;
    
    public CalendarDTO(){	
    };
    
	public CalendarDTO(Integer id, String resourceId, String start, String end,
			String title) {
		super();
		this.id = id;
		this.resourceId = resourceId;
		this.start = start;
		this.end = end;
		this.title = title;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

    
}
