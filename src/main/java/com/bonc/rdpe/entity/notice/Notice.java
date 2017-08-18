package com.bonc.rdpe.entity.notice;

import java.util.Date;

public class Notice {
    private String noticeId;

    private String noticeTitle;

    private String noticeType;

    private String noticeContent;

    private String state;

    private String fromSign;

    private Date pubdate;

    private String pubTime;
    
    private String pubUserId;
    
    private String recOrgId;

    private String pubPerson;
    
    public String getPubPerson() {
		return pubPerson;
	}

	public void setPubPerson(String pubPerson) {
		this.pubPerson = pubPerson;
	}

	public String getRecOrgId() {
		return recOrgId;
	}

	public void setRecOrgId(String recOrgId) {
		this.recOrgId = recOrgId;
	}

	public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId == null ? null : noticeId.trim();
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType == null ? null : noticeType.trim();
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getFromSign() {
        return fromSign;
    }

    public void setFromSign(String fromSign) {
        this.fromSign = fromSign == null ? null : fromSign.trim();
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

	public String getPubUserId() {
		return pubUserId;
	}

	public void setPubUserId(String pubUserId) {
		this.pubUserId = pubUserId;
	}

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}
}