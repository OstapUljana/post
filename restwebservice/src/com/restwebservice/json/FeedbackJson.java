package com.restwebservice.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.restwebservice.entities.Feedback;

public class FeedbackJson {
	private int idfeedback;
	private String eMail;
	private String text;
	private String date;
	DateFormat df = new SimpleDateFormat("dd MMMM yyyy 'at'  HH:mm", Locale.UK);
	public FeedbackJson(Feedback feedback) {
		super();
		this.idfeedback = feedback.getIdfeedback();		
		this.date = (df.format(feedback.getDate()).toString());	
		this.text = feedback.getText();
		this.eMail = feedback.geteMail();
	}
	public int getIdfeedback() {
		return idfeedback;
	}
	public void setIdfeedback(int idfeedback) {
		this.idfeedback = idfeedback;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	@Override
	public String toString() {
		return "FeedbackJson [idfeedback=" + idfeedback + ", eMail=" + eMail
				+ ", text=" + text + ", date=" + date + ", df=" + df + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((df == null) ? 0 : df.hashCode());
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		result = prime * result + idfeedback;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeedbackJson other = (FeedbackJson) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (df == null) {
			if (other.df != null)
				return false;
		} else if (!df.equals(other.df))
			return false;
		if (eMail == null) {
			if (other.eMail != null)
				return false;
		} else if (!eMail.equals(other.eMail))
			return false;
		if (idfeedback != other.idfeedback)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	public DateFormat getDf() {
		return df;
	}
	public void setDf(DateFormat df) {
		this.df = df;
	}
	




}
