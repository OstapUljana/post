package com.restwebservice.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id ;
import java.sql.Timestamp;

/**
* Created by SU on 08.12.2014.
*/
@Entity
public class Feedback {
private int idfeedback;
private String text;
private Timestamp date;
private String eMail;

@Id
@Column(name = "idfeedback", nullable = false, insertable = true, updatable = true)
public int getIdfeedback() {
return idfeedback;
}

public void setIdfeedback(int idfeedback) {
this.idfeedback = idfeedback;
}

@Basic
@Column(name = "text", nullable = true, insertable = true, updatable = true, length = 65535)
public String getText() {
return text;
}

public void setText(String text) {
this.text = text;
}

@Basic
@Column(name = "date", nullable = true, insertable = true, updatable = true)
public Timestamp getDate() {
return date;
}

public void setDate(Timestamp date) {
this.date = date;
}

@Basic
@Column(name = "eMail", nullable = true, insertable = true, updatable = true, length = 45)
public String geteMail() {
return eMail;
}

public void seteMail(String eMail) {
this.eMail = eMail;
}

@Override
public boolean equals(Object o) {
if (this == o) return true;
if (o == null || getClass() != o.getClass()) return false;

Feedback feedback = (Feedback) o;

if (idfeedback != feedback.idfeedback) return false;
if (date != null ? !date.equals(feedback.date) : feedback.date != null) return false;
if (eMail != null ? !eMail.equals(feedback.eMail) : feedback.eMail != null) return false;
if (text != null ? !text.equals(feedback.text) : feedback.text != null) return false;

return true;
}

@Override
public int hashCode() {
int result = idfeedback;
result = 31 * result + (text != null ? text.hashCode() : 0);
result = 31 * result + (date != null ? date.hashCode() : 0);
result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
return result;
}
}