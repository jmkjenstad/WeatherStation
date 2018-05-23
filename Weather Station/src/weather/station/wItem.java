package weather.station;

/************************************************************************
    Class:  wItem
    Author: All
    Description: Class representing a "sample" object that contains 
	weather data at a particular time and date
 ************************************************************************/
public class wItem 
{
	/* date of sample */
    private String date;
	
	/* year of sample */
    private int year;
	
	/* month of sample */
    private int month;
	
	/*day of sample */
    private int day;
	
	/* time of sample */
    private String time;
	
	/* hour of sample */
	private int hour;
	
	/* minute of sample */
	private int minute;
	
	/* sample temperature */
    private float temperature;
	
	/* sample humidity */
    private float humidity;
	
	/* sample barometer reading */
    private float barometer;
	
	/* sample windspeed */
    private float windspeed;
	
	/* sample winddirection */
    private String winddirection;
	
	/* sample windgust */
    private float windgust;
	
	/* sample windchill */
    private float windchill;
	
	/* sample heatindex */
    private float heatindex;
	
	/* sample uvindex */
    private float uvindex;
	
	/* sample rainfall */
    private float rainfall;
	
	
     /**
     * **********************************************************************
     * Function: getDate()
	 * Author: All 
	 * Description: gets the date of the sample
     * Parameters: n/a
     * **********************************************************************
     */
    public String getDate() 
	{
        return date;
    }
	
	
	/**
     * **********************************************************************
     * Function: setDate()
	 * Author: All 
	 * Description: sets the date of the sample
     * Parameters: String date - date of sample
     * **********************************************************************
     */
    public void setDate(String date) {
        this.date = date;
        
        String temp = date.trim();
        String delims = "[/]";
        String[] token = temp.split(delims);
        
		
		this.month = Integer.valueOf(token[0]);
		this.day = Integer.valueOf(token[1]);
		this.year = Integer.valueOf(token[2]);
    }  

	/**
     * **********************************************************************
     * Function: getYear()
	 * Author: All 
	 * Description: gets the year of the sample
     * Parameters: n/a
     * **********************************************************************
     */
    public int getYear() {
        return year;
    }
	
	/**
     * **********************************************************************
     * Function: getMonth()
	 * Author: All 
	 * Description: gets the month of the sample
     * Parameters: n/a
     * **********************************************************************
     */
    public int getMonth() 
	{
        return month;
    }
	
	/**
     * **********************************************************************
     * Function: getDay()
	 * Author: All 
	 * Description: gets the date of the sample
     * Parameters: n/a
     * **********************************************************************
     */
    public int getDay() {
        return day;
    }


    /**
     * **********************************************************************
     * Function: getTime()
	 * Author: All 
	 * Description: gets the Time of the sample
     * Parameters: n/a
     * **********************************************************************
     */
    public String getTime() 
	{
        return time;
    }
	
	
	/**
     * **********************************************************************
     * Function: getHour()
	 * Author: All 
	 * Description: gets the hour of the sample
     * Parameters: n/a
     * **********************************************************************
     */
	public int getHour()
	{
		return this.hour;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMinute()
	 * Author: All 
	 * Description: gets the minute of the sample
     * Parameters: n/a
     * **********************************************************************
     */
	public int getMinute()
	{
		return this.minute;
	}
	
	/**
     * **********************************************************************
     * Function: setTime()
	 * Author: All 
	 * Description: sets the time of the sample
     * Parameters: String ttime - time of sample
     * **********************************************************************
     */
    public void setTime(String ttime) 
	{
        this.time = ttime;
		
		String temp = ttime.trim();
		String delims = "[:AP]";
        String[] token = temp.split(delims);
		
		
		this.hour = Integer.parseInt(token[0]);
		this.minute = Integer.parseInt(token[1]);
		
    }
	
	/**
     * **********************************************************************
     * Function: getTemperature()
	 * Author: All 
	 * Description: gets the sample temperature
     * Parameters: n/a
     * **********************************************************************
     */
    public float getTemperature() {
        return temperature;
    }
	
	/**
     * **********************************************************************
     * Function: setTemperature()
	 * Author: All 
	 * Description: sets the sample temperature
     * Parameters: float temperature - sample temperature
     * **********************************************************************
     */
    public void setTemperature(float temperature) 
	{
        this.temperature = temperature;
    }
	
	/**
     * **********************************************************************
     * Function: getHumidity()
	 * Author: All 
	 * Description: gets the sample humidity
     * Parameters: n/a
     * **********************************************************************
     */
    public float getHumidity() 
	{
        return humidity;
    }
	
	
	/**
     * **********************************************************************
     * Function: setHumidity()
	 * Author: All 
	 * Description: sets the sample humidity
     * Parameters: float humidity - sample humidity
     * **********************************************************************
     */
    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
	
	/**
     * **********************************************************************
     * Function: getBarometer()
	 * Author: All 
	 * Description: gets the sample barometer reading
     * Parameters: n/a
     * **********************************************************************
     */
    public float getBarometer() 
	{
        return barometer;
    }
	
	/**
     * **********************************************************************
     * Function: setBarometer()
	 * Author: All 
	 * Description: sets the sample barometer reading
     * Parameters: float barometer - sample barometer reading
     * **********************************************************************
     */
    public void setBarometer(float barometer) 
	{
        this.barometer = barometer;
    }
	
	
	/**
     * **********************************************************************
     * Function: getWindspeed()
	 * Author: All 
	 * Description: gets the sample wind speed
     * Parameters: n/a
     * **********************************************************************
     */
    public float getWindspeed() 
	{
        return windspeed;
    }
	
	/**
     * **********************************************************************
     * Function: setWindspeed()
	 * Author: All 
	 * Description: gets the sample wind speed
     * Parameters: float windspeed - sample wind speed
     * **********************************************************************
     */
    public void setWindspeed(float windspeed) 
	{
        this.windspeed = windspeed;
    }
	
	/**
     * **********************************************************************
     * Function: getWinddirection()
	 * Author: All 
	 * Description: gets the sample wind direction
     * Parameters: n/a
     * **********************************************************************
     */
    public String getWinddirection() 
	{
        return this.winddirection;
    }
	
	
	/**
     * **********************************************************************
     * Function: setWinddirection()
	 * Author: All 
	 * Description: sets the sample wind direction
     * Parameters: String winddirection - sample wind direction
     * **********************************************************************
     */
    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }
	
	/**
     * **********************************************************************
     * Function: getWindgust()
	 * Author: All 
	 * Description: gets the sample wind gust
     * Parameters: n/a
     * **********************************************************************
     */
    public float getWindgust() {
        return windgust;
    }
	
	
	/**
     * **********************************************************************
     * Function: setWindgust()
	 * Author: All 
	 * Description: sets the sample wind gust
     * Parameters: float windgust - sample windgust
     * **********************************************************************
     */
    public void setWindgust(float windgust) {
        this.windgust = windgust;
    }
	
	/**
     * **********************************************************************
     * Function: getWindchill()
	 * Author: All 
	 * Description: gets the sample wind chill
     * Parameters: n/a
     * **********************************************************************
     */
    public float getWindchill() {
        return windchill;
    }
	
	
	/**
     * **********************************************************************
     * Function: setWindchill()
	 * Author: All 
	 * Description: sets the sample wind chill 
     * Parameters: float windchill - sample wind chill
     * **********************************************************************
     */
    public void setWindchill(float windchill) {
        this.windchill = windchill;
    }
	
	/**
     * **********************************************************************
     * Function: getHeatindex()
	 * Author: All 
	 * Description: gets the sample heat index
     * Parameters: n/a
     * **********************************************************************
     */
    public float getHeatindex() {
        return heatindex;
    }
	
	/**
     * **********************************************************************
     * Function: setHeatindex()
	 * Author: All 
	 * Description: sets the sample heat index
     * Parameters: float heatindex - sample head index
     * **********************************************************************
     */
    public void setHeatindex(float heatindex) {
        this.heatindex = heatindex;
    }
	
	/**
     * **********************************************************************
     * Function: getUvindex()
	 * Author: All 
	 * Description: gets the sample UV index
     * Parameters: n/a
     * **********************************************************************
     */
    public float getUvindex() {
        return uvindex;
    }
	
	
	/**
     * **********************************************************************
     * Function: setUvindex()
	 * Author: All 
	 * Description: sets the sample UV index
     * Parameters: n/a
     * **********************************************************************
     */
    public void setUvindex(float uvindex) {
        this.uvindex = uvindex;
    }
	
	/**
     * **********************************************************************
     * Function: getRainfall()
	 * Author: All 
	 * Description: gets the sample precipitation
     * Parameters: n/a
     * **********************************************************************
     */
    public float getRainfall() {
        return rainfall;
    }
	
	
	/**
     * **********************************************************************
     * Function: setRainfall()
	 * Author: All 
	 * Description: sets the sample precipitation
     * Parameters: float rainfall - sample precipitation
     * **********************************************************************
     */
    public void setRainfall(float rainfall) {
        this.rainfall = rainfall;
    }
	
	
     /**
     * **********************************************************************
     * Function: toString()
	 * Author: All 
	 * Description: over ridden 'toString' method to print wItem data
     * Parameters: n/a
     * **********************************************************************
     */ 
    @Override
    public String toString() {
        return "wItem:: date="+this.date + " Time: " +this.time + "Temperature: " + this.temperature; //+" time=" + this.time + " temperature=" + this.temperature;
    }
     
}
