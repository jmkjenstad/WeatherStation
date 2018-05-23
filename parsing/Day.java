import java.util.*;


/************************************************************************
    Class:  Day
    Author: All
    Description: Class representing a "day" object within the context of 
	weather data; contains a list of 10-15 minute samples of weather 
	conditions and various data relevant to a day, like the month and 
	year it belongs to, as well as its numeric reference within the
	particular month. Also contains various statistical information
	regarding the weather conditions for that day. 
 ************************************************************************/
public class Day
{
	/* integer representation of day relative to the month it resides in*/
	private int day;
	
	/*integer representing the month the day resides in*/
	private int month;
	
	/* the year the day resides in */
	private int year;
	
	/* the list of samples gathered in the day */
	private ArrayList<wItem> samples;
	
	/* the mean average temp for the day */
	private float meanTemp;
	
	/* the high temp for the day */
	private float highTemp;
	
	/*date of the high temp */
	private String hTempDate;
	
	/*time of high temp */
	private String hTempTime;
	
	/*low temp for the day*/
	private float lowTemp;
	
	/*date of low temp*/
	private String lTempDate;
	
	/* time of low temp */
	private String lTempTime;
	
	/* avg wind speed for the day */
	private float meanWind;
	
	/* highest wind gust for the day */
	private float maxWind;
	
	/*date of max wind gust */
	private String mWindDate;
	
	/*time of max wind gust */
	private String mWindTime;
	
	/*total rainfall for the day */
	private float rainfall;
	
	/*prevailing wind direction for the day */
	private String prevailingWindDir;
	
	
	/**
     * **********************************************************************
     * Function: Day() 
	 * Author: All 
	 * Description: Constructor for day class
     * Parameters: n/a
     * **********************************************************************
     */
	public Day()
	{
		this.day = -1;
		this.month = -1;
		this.year = -1;
		this.samples = new ArrayList<wItem>();
	}
	
	
	/**
     * **********************************************************************
     * Function: getSampleCount()
	 * Author: All 
	 * Description: gets the number of samples in the day
     * Parameters: n/a
     * **********************************************************************
     */
	public int getSampleCount()  
	{
		return this.samples.size();
	}
	
	/**
     * **********************************************************************
     * Function: getPrevailingWindDir()
	 * Author: All 
	 * Description: gets the prevailing wind direction for the day
     * Parameters: n/a
     * **********************************************************************
     */
	public String getPrevailingWindDir()
	{
		return this.prevailingWindDir;
	}
	
	
	/**
     * **********************************************************************
     * Function: calcStats()
	 * Author: All 
	 * Description: calculates avg temp, high/low temp, avg wind speed, 
	 * max wind gust, and accumulated rainfall for the day
     * Parameters: n/a
     * **********************************************************************
     */
	public void calcStats()
	{
		float tempSum = 0.0f;
		float sampCount = 0.0f;
		float windSum = 0.0f;
		float maxTemp = -50.0f;
		float minTemp = 1000.0f;
		String maxTempDate = "";
		String maxTempTime = "";
		String minTempDate = "";
		String minTempTime = "";
		float maxWind = 0.0f;
		String windDate = "";
		String windTime = "";
		float rainSum = 0.0f;
		
		String north = "N";
		int nCount = 0;
		String east = "E";
		int eCount = 0;
		String south = "S";
		int sCount = 0;
		String west = "W";
		int wCount = 0;
		String nEast = "NE";
		int neCount = 0;
		String sEast = "SE";
		int seCount = 0;
		String sWest = "SW";
		int swCount = 0;
		String nWest = "NW";
		int nwCount = 0;
		
		int maxCount = -1;
		
		
		ArrayList<wItem> samples = this.getSamples();
		
		for( wItem item: samples )  // for each sample
		{
			sampCount += 1.0;  // count for upcoming mean calculation
			
			float temp = item.getTemperature();  // get relevant sample info to save later
			String tempDate = item.getDate();
			String tempTime = item.getTime();
			
			tempSum += temp;  // get running sum of temp for the day
			
			if( temp > maxTemp )  // find max temp for day
			{
				maxTemp = temp;
				maxTempDate = tempDate;
				maxTempTime = tempTime;
			}
			
			if( temp < minTemp )  // find min temp for day
			{
				minTemp = temp;
				minTempDate = tempDate;
				minTempTime = tempTime;
			}
			
			temp = item.getWindspeed();  // get running sum of wind speed for the day
			
			windSum += temp;
			
			temp = item.getWindgust();
			
			if( temp > maxWind )  // find the max wind gust
			{
				maxWind = temp;
				windDate = tempDate;
				windTime = tempTime;
			}
			
			rainSum += item.getRainfall();	// accumulate rainfall
			
		    String windDir = item.getWinddirection();
			
            // calculate prevailing wind direction
			if( north.equals( windDir ) )
			{
				nCount += 1;
				
				
				if( nCount > maxCount )
				{
					maxCount = nCount;
					this.prevailingWindDir = "N";
				}
			}
			else if( east.equals( windDir ) )
			{
				eCount +=1;
				
				if( eCount > maxCount )
				{
					maxCount = eCount;
					this.prevailingWindDir = "E";
				}
			}
			else if( south.equals( windDir ) )
			{
				sCount += 1;
				
				if( sCount > maxCount )
				{
					maxCount = sCount;
					this.prevailingWindDir = "S";
				}
			}
			else if( west.equals( windDir ) )
			{
				wCount +=1;
				
				if( wCount > maxCount )
				{
					maxCount = wCount;
					this.prevailingWindDir = "W";
				}
			}
			else if( nEast.equals( windDir ) )
			{
				neCount += 1;
				
				if( neCount > maxCount )
				{
					maxCount = neCount;
					this.prevailingWindDir = "NE";
				}
			}
			else if( sEast.equals( windDir ) )
			{
				seCount += 1;
				
				if( seCount > maxCount )
				{
					maxCount = seCount;
					this.prevailingWindDir = "SE";
				}
			}
			else if( sWest.equals( windDir ) )
			{
				swCount += 1;
				
				if( swCount > maxCount )
				{
					maxCount = swCount;
					this.prevailingWindDir = "SW";
				}
			}
			else if( nWest.equals( windDir ) )
			{
				nwCount += 1;
				
				if( nwCount > maxCount )
				{
					maxCount = nwCount;
					this.prevailingWindDir = "NW";
				}
			}			
			
		}
		
		this.setHighTemp( maxTemp, maxTempDate, maxTempTime );  // set the max temp for day
		
		this.setLowTemp( minTemp, minTempDate, minTempTime );  // set low temp
		
		this.setMaxWind( maxWind, windDate, windTime );  // set max wind speed
		
		this.setMeanWind( (float) windSum / sampCount );  // set avg wind speed
		
		this.setMeanTemp( (float) tempSum / sampCount ); // set avg temp speed 
		
		this.setRainfall( rainSum );  // set accumulated precipitation	
				
	}
	
	/**
     * **********************************************************************
     * Function: getMeanTemp()
	 * Author: All 
	 * Description: gets the mean average temp for the day
     * Parameters: n/a
     * **********************************************************************
     */
	public float getMeanTemp()
	{
		return this.meanTemp;
	}
	
	
	/**
     * **********************************************************************
     * Function: setMeanTemp()
	 * Author: All 
	 * Description: sets the mean average temp for the day
     * Parameters: float avg - contains the mean avg temp 
     * **********************************************************************
     */
	public void setMeanTemp( float avg )
	{
		this.meanTemp = avg;
	}
	
	
	/**
     * **********************************************************************
     * Function: getHighTemp()
	 * Author: All 
	 * Description: gets the high temp for the day
     * Parameters: n/a
     * **********************************************************************
     */
	public float getHighTemp()
	{
		return this.highTemp;
	}
	
	
	/**
     * **********************************************************************
     * Function: setHighTemp()
	 * Author: All 
	 * Description: sets the high temp for the day
     * Parameters: float high - high temp for the day
	 *             String date - date of high temp
	 *             String time - time of high temp
     * **********************************************************************
     */
	public void setHighTemp( float high, String date, String time )
	{
		this.highTemp = high;
		this.hTempDate = date;
		this.hTempTime = time;
	}
	
	
	/**
     * **********************************************************************
     * Function: getHighTempDate()
	 * Author: All 
	 * Description: gets the high temp date 
     * Parameters: n/a
     * **********************************************************************
     */
	public String getHighTempDate()
	{
		return this.hTempDate;
	}
	
	/**
     * **********************************************************************
     * Function: getHighTempTime()
	 * Author: All 
	 * Description: gets the high temp time 
     * Parameters: n/a
     * **********************************************************************
     */
	public String getHighTempTime()
	{
		return this.hTempTime;
	}
	
	
	/**
     * **********************************************************************
     * Function: getLowTemp()
	 * Author: All 
	 * Description: gets the low temp for the day
     * Parameters: n/a
     * **********************************************************************
     */
	public float getLowTemp()
	{
		return this.lowTemp;
	}
	
	
	/**
     * **********************************************************************
     * Function: setLowTemp()
	 * Author: All 
	 * Description: sets the low temp for the day
     * Parameters: float low - low temp for the day
	 *             String date - date of low temp
	 *             String time - time of low temp
     * **********************************************************************
     */
	public void setLowTemp( float low, String date, String time )
	{
		this.lowTemp = low;
		this.lTempDate = date;
		this.lTempTime = time;
	}
	
	
	/**
     * **********************************************************************
     * Function: getLowTempDate()
	 * Author: All 
	 * Description: gets the low temp date 
     * Parameters: n/a
     * **********************************************************************
     */
	public String getLowTempDate()
	{
		return this.lTempDate;
	}
	
	
	/**
     * **********************************************************************
     * Function: getLowTempTime()
	 * Author: All 
	 * Description: gets the high temp time 
     * Parameters: n/a
     * **********************************************************************
     */
	public String getLowTempTime()
	{
		return this.lTempTime;
	}
	
	/**
     * **********************************************************************
     * Function: getMeanWind()
	 * Author: All 
	 * Description: gets the mean average wind speed for the day 
     * Parameters: n/a
     * **********************************************************************
     */
	public float getMeanWind()
	{
		return this.meanWind;
	}
	
	
	/**
     * **********************************************************************
     * Function: setMeanWind()
	 * Author: All 
	 * Description: sets the mean average wind speed for the day 
     * Parameters: float avg - mean average wind speed
     * **********************************************************************
     */
	public void setMeanWind( float avg )
	{
		this.meanWind = avg;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMaxWind()
	 * Author: All 
	 * Description: gets the highest wind gust for the day
     * Parameters: n/a
     * **********************************************************************
     */
	public float getMaxWind()
	{
		return this.maxWind;
	}
	
	
	/**
     * **********************************************************************
     * Function: setMaxWind()
	 * Author: All 
	 * Description: sets the mean average wind speed for the day, also the time
	 * and date of occurence
     * Parameters: float max - max wind speed
	 *             String date = date of 
     * **********************************************************************
     */
	public void setMaxWind( float max, String date, String time)
	{
		this.maxWind = max;
		this.mWindDate = date;
		this.mWindTime = time;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMaxWindDate()
	 * Author: All 
	 * Description: gets the highest wind gust date
     * Parameters: n/a
     * **********************************************************************
     */
	public String getMaxWindDate()
	{
		return this.mWindDate;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMaxWindTime()
	 * Author: All 
	 * Description: gets the highest wind gust date
     * Parameters: n/a
     * **********************************************************************
     */
	public String getMaxWindTime()
	{
		return this.mWindTime;
	}
	
	
	/**
     * **********************************************************************
     * Function: getRainfall()
	 * Author: All 
	 * Description: gets the accumulated rainfall for the day
     * Parameters: n/a
     * **********************************************************************
     */
	public float getRainfall()
	{
		return this.rainfall;
	}
	
	
	/**
     * **********************************************************************
     * Function: setRainfall()
	 * Author: All 
	 * Description: sets the accumulated rainfall for the day
     * Parameters: float rain - accumulated rainfall for the day
     * **********************************************************************
     */
	public void setRainfall( float rain )
	{
		this.rainfall = rain;
	}
	
	/**
     * **********************************************************************
     * Function: getDay()
	 * Author: All 
	 * Description: gets the integer value of the day relative to it's positon
	 * in a month
     * Parameters: n/a
     * **********************************************************************
     */
	public int getDay()
	{
		return this.day;
	}
	
	
	/**
     * **********************************************************************
     * Function: setDay()
	 * Author: All 
	 * Description: sets the integer value of the day relative to it's positon
	 * in a month
     * Parameters: int tempDay - value of day in month
     * **********************************************************************
     */
	public void setDay( int tempDay )
	{
		this.day = tempDay;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMonth()
	 * Author: All 
	 * Description: gets the integer value of the month that the day is in
	 * in a month
     * Parameters: n/a
     * **********************************************************************
     */
	public int getMonth()
	{
		return this.month;
	}
	
	
	/**
     * **********************************************************************
     * Function: setMonth()
	 * Author: All 
	 * Description: sets the integer value of the month that the day is in
	 * in a month
     * Parameters: int tempMonth - value of the month
     * **********************************************************************
     */
	public void setMonth( int tempMonth)
	{
		this.month = tempMonth;
	}
	
	/**
     * **********************************************************************
     * Function: getYear()
	 * Author: All 
	 * Description: gets the year the day is in
     * Parameters: n/a
     * **********************************************************************
     */
	public int getYear()
	{	
		return this.year;
	}
	
	/**
     * **********************************************************************
     * Function: setYear()
	 * Author: All 
	 * Description: sets the year the day is in
     * Parameters: int tempYear - year the day is in
     * **********************************************************************
     */
	public void setYear( int tempYear )
	{
		this.year = tempYear;
	}
	
	
	/**
     * **********************************************************************
     * Function: setSamples()
	 * Author: All 
	 * Description: sets the daily samples
     * Parameters: List<wItem> tempSamples - contains sample data for the day
     * **********************************************************************
     */
	public void setSamples( List<wItem> tempSamples )
	{
		this.samples = new ArrayList( tempSamples );
		
		this.calcStats(); // set the stats for the day
	}
	
	
	/**
     * **********************************************************************
     * Function: getSamples()
	 * Author: All 
	 * Description: gets the daily samples
     * Parameters: n/a
     * **********************************************************************
     */
	public ArrayList<wItem> getSamples()
	{
		return this.samples;
	}
	
	
	/**
     * **********************************************************************
     * Function: reset()
	 * Author: All 
	 * Description: resets the day object to a fresh state
     * Parameters: n/a
     * **********************************************************************
     */
	public void reset()
	{
		this.day = -1;
		this.month = -1;
		this.year = -1;
		this.samples = new ArrayList<wItem>();
	}
}
