package com.mx.forty.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import jakarta.annotation.PostConstruct;

@Named("calendarView")
@ViewScoped
public class CalendarView implements Serializable {

	  /**
	 * 
	 */
	  private Date date;
	    private Date date1;
	    private Date dateTimeDe;
	    private Date dateTimeMillis;
	    private List<Date> multi;
	    private List<Date> range;
	    private List<Date> invalidDates;
	    private List<Date> validDates;
	    private List<Integer> invalidDays;
	    private Date minDate;
	    private Date maxDate;
	    private Date minTime;
	    private Date maxTime;
	    private Date minDateTime;
	    private Date maxDateTime;

	    @PostConstruct
	    public void init() {
	        Date today = new Date();
	        long oneDay = 24 * 60 * 60 * 1000;

	        Calendar tmp = Calendar.getInstance();
	        tmp.set(Calendar.HOUR_OF_DAY, 9);
	        tmp.set(Calendar.MINUTE, 0);
	        tmp.set(Calendar.SECOND, 0);
	        tmp.set(Calendar.MILLISECOND, 0);
	        minTime = tmp.getTime();

	        tmp = Calendar.getInstance();
	        tmp.set(Calendar.HOUR_OF_DAY, 17);
	        tmp.set(Calendar.MINUTE, 0);
	        tmp.set(Calendar.SECOND, 0);
	        tmp.set(Calendar.MILLISECOND, 0);
	        maxTime = tmp.getTime();

	        minDateTime = new Date(today.getTime() - (7 * oneDay));
	        maxDateTime = new Date(today.getTime() + (7 * oneDay));

	        dateTimeDe = GregorianCalendar.getInstance().getTime();
	        dateTimeMillis = GregorianCalendar.getInstance().getTime();
	    }

	    public void onDateSelect(SelectEvent<Date> event) {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	    }

	    public void click() {
	        PrimeFaces.current().ajax().update("form:display");
	        PrimeFaces.current().executeScript("PF('dlg').show()");
	    }

	    public Date getDate() {
	        return date;
	    }

	    public void setDate(Date date) {
	        this.date = date;
	    }

	    public Date getDate1() {
	        return date1;
	    }

	    public void setDate1(Date date1) {
	        this.date1 = date1;
	    }


	    public List<Date> getMulti() {
	        return multi;
	    }

	    public void setMulti(List<Date> multi) {
	        this.multi = multi;
	    }

	    public List<Date> getRange() {
	        return range;
	    }

	    public void setRange(List<Date> range) {
	        this.range = range;
	    }

	    public List<Date> getInvalidDates() {
	        return invalidDates;
	    }

	    public void setInvalidDates(List<Date> invalidDates) {
	        this.invalidDates = invalidDates;
	    }

	    public List<Date> getValidDates() {
	        return validDates;
	    }

	    public void setValidDates(List<Date> validDates) {
	        this.validDates = validDates;
	    }

	    public List<Integer> getInvalidDays() {
	        return invalidDays;
	    }

	    public void setInvalidDays(List<Integer> invalidDays) {
	        this.invalidDays = invalidDays;
	    }

	    public Date getMinDate() {
	        return minDate;
	    }

	    public void setMinDate(Date minDate) {
	        this.minDate = minDate;
	    }

	    public Date getMaxDate() {
	        return maxDate;
	    }

	    public void setMaxDate(Date maxDate) {
	        this.maxDate = maxDate;
	    }

	    public Date getDateTimeDe() {
	        return dateTimeDe;
	    }

	    public void setDateTimeDe(Date dateTimeDe) {
	        this.dateTimeDe = dateTimeDe;
	    }


	    public Date getDateTimeMillis() {
	        return dateTimeMillis;
	    }

	    public void setDateTimeMillis(Date dateTimeMillis) {
	        this.dateTimeMillis = dateTimeMillis;
	    }


	    public Date getMinTime() {
	        return minTime;
	    }

	    public void setMinTime(Date minTime) {
	        this.minTime = minTime;
	    }

	    public Date getMaxTime() {
	        return maxTime;
	    }

	    public void setMaxTime(Date maxTime) {
	        this.maxTime = maxTime;
	    }

	    public Date getMinDateTime() {
	        return minDateTime;
	    }

	    public void setMinDateTime(Date minDateTime) {
	        this.minDateTime = minDateTime;
	    }

	    public Date getMaxDateTime() {
	        return maxDateTime;
	    }

	    public void setMaxDateTime(Date maxDateTime) {
	        this.maxDateTime = maxDateTime;
	    }
}
