package weather.station;
import java.util.*;


/************************************************************************
    Class:  Year
    Author: All
    Description: Class representing a "year" object within the context of 
	weather data; contains a hash table of all months of weather data 
	associated with the year in question.
 ************************************************************************/
public class Year
{
	/* two digit integer representing the year */
	private int year;
	
	/* collection of all months of weather data in the year */
	private Hashtable<Integer, Month> monthlySamples;
	
	/* avg temp for the year */
	private float meanTemp;
	
	/* high temp for the year */
	private float highTemp;
	
	/* high temp date */ 
	private String hTempDate;
	
	/* high temp time */
	private String hTempTime;
	
	/* low temp for the year */
	private float lowTemp;
	
	/* low temp date */
	private String lTempDate;
	
	/* low temp time */
	private String lTempTime;
	
	/* avg wind speed for the year */
	private float meanWind;
	
	/* max wind gust for the year */
	private float maxWind;
	
	/* max wind gust date */
	private String mWindDate;
	
	/* max wind gust time */
	private String mWindTime;
	
	/* accumulated rainfall for the year */
	private float rainfall;
	
	/* prevailing wind direction for the year */
	private String prevailingWindDir;
	
	
	/**
     * **********************************************************************
     * Function: Year() 
	 * Author: All 
	 * Description: Constructor for year class
     * Parameters: n/a
     * **********************************************************************
     */
	public Year()
	{
		this.monthlySamples = new Hashtable<Integer, Month > ();
		this.year = -1;
	}
	
	
	/**
     * **********************************************************************
     * Function: Year() 
	 * Author: All 
	 * Description: Over-loaded constructor for year class
     * Parameters: Year tempYear - year object with state
     * **********************************************************************
     */
	public Year( Year tempYear )
	{
		this.monthlySamples = new Hashtable<Integer, Month>();
		
		this.year = tempYear.getYear();
		
		this.monthlySamples.putAll( tempYear.getAllMonthlySamples() );		
	}
	
	
	/**
     * **********************************************************************
     * Function: getPrevailingWindDir()
	 * Author: All 
	 * Description: gets the prevailing wind direction for the year
     * Parameters: n/a
     * **********************************************************************
     */
	public String getPrevailingWindDir()
	{
		return this.prevailingWindDir;
	}
	
	
	/**
     * **********************************************************************
     * Function: getSampleCount()
	 * Author: All 
	 * Description: gets the number of samples in the year 
     * Parameters: n/a
     * **********************************************************************
     */
	public int getSampleCount()
	{
		int count = 0;
		
		Hashtable<Integer, Month> muns = this.getAllMonthlySamples();
		
        // for each month in the year
		for( int i = 1; i <= muns.size(); i++ )
		{
			Month month = muns.get(i);
			
			if( month == null )
			{
				continue;
			}
			Hashtable<Integer, Day> daas = month.getAllDaySamples();
			
			 // for each day in the month
			for( int j = 1; j <=daas.size(); j++ )
			{
				Day day = daas.get(j);
				
				if(day == null )
				{
					continue;
				}
				count += day.getSampleCount();  // sum the number of samples in each day
			}			
		}
		
		return count;
	}
	
	
	/**
     * **********************************************************************
     * Function: calcStats()
	 * Author: All 
	 * Description: calculates avg temp, high/low temp, avg wind speed, 
	 * max wind gust, and accumulated rainfall for the year
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
		
		
		Hashtable<Integer, Month> months = this.getAllMonthlySamples(); // grab the months in the current year
		
		
		for( int i = 1; i <= months.size(); i++ )  // for each month in the year
		{
			Month mun = months.get(i);//mkey);  // grab a single month in the list of months
			
			if( mun == null )  // if the month doesn't exist, skip it
			{
				continue;
			}
			
			Hashtable<Integer, Day> days = mun.getAllDaySamples();  // get all daily samples in the month
		
			for( int j = 1; j <= days.size(); j++ )  // for each day 
			{
				Day daa = days.get( j );
			
				if( daa == null )  // if day doesn't exist, skip to next day lookup
				{
					continue;
				}
				
				ArrayList<wItem> samples = daa.getSamples();
			
				for( wItem item: samples )  //for each sample
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
			
					temp = item.getWindspeed();  // get running sum of wind speed for the month
			
					windSum += temp;
					
					temp = item.getWindgust();
			
					if( temp > maxWind )  // track max wind gust
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
			
			}
		}
		
		this.setHighTemp( maxTemp, maxTempDate, maxTempTime );  // set the max temp for year
			
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
	 * Description: gets the mean average temperature for the year
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
	 * Description: sets the mean temperature for the year
     * Parameters: float avg - avg temp for year
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
	 * Description: gets the high temperature for the year
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
	 * Description: sets the high temperature for the year, and its date/time
     * Parameters: float high - high temp for the year
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
	 * Description: gets the date of the high temp
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
	 * Description: gets the time of the high temp in the year
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
	 * Description: gets the low temp for the year
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
	 * Description: sets the low temp of the year, also its date/time 
     * Parameters: float low - low temp of the year
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
	 * Description: gets the date of the low temp in the year
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
	 * Description: gets the time of the low temp in the year
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
	 * Description: gets the average wind speed for the year
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
	 * Description: sets the mean average wind speed for the year
     * Parameters: float avg - average wind speed value
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
	 * Description: gets the max wind gust in the year
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
	 * Description: sets the max wind gust in the year, and its date/time
     * Parameters: float max - max wind gust value
	 *             String date - date of max wind gust
	 *             String time - time of max wind gust
     * **********************************************************************
     */
	public void setMaxWind( float max, String date, String time )
	{
		this.maxWind = max;
		this.mWindDate = date;
		this.mWindTime = time;
	}
	
	/**
     * **********************************************************************
     * Function: getMaxWindDate()
	 * Author: All 
	 * Description: gets the date of the max wind gust in the year
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
	 * Description: gets the time of the max wind gust in the year
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
	 * Description: gets the accumulated precipitation in the year
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
	 * Description: sets the precipitation in the year
     * Parameters: float rain - accumulated rain in the year
     * **********************************************************************
     */
	public void setRainfall( float rain )
	{
		this.rainfall = rain;
	}
	

	/**
     * **********************************************************************
     * Function: getYear()
	 * Author: All 
	 * Description: gets the year value for the year object
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
	 * Description: sets the year value for the year object
     * Parameters: int tempYear - integer value of the year object
     * **********************************************************************
     */	
	public void setYear( int tempYear )
	{
		this.year = tempYear;
	}
	
	
	/**
     * **********************************************************************
     * Function: setMonthlySamples()
	 * Author: All 
	 * Description: sets the monthly samples of the year object
     * Parameters: int monthKey - index to reference a month
	 *             Month monthOfSamples - month object full of data
     * **********************************************************************
     */	
	public void setMonthlySamples( int monthKey, Month monthOfSamples )
	{
		
		this.monthlySamples.put( monthKey, monthOfSamples );
	}
	
	/**
     * **********************************************************************
     * Function: getAllMonthlySamples()
	 * Author: All 
	 * Description: gets all monthly samples in the year object
     * Parameters: n/a
     * **********************************************************************
     */	
	public Hashtable<Integer, Month> getAllMonthlySamples()
	{
		return this.monthlySamples;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMonthlySamplesByMonth()
	 * Author: All 
	 * Description: gets a single month of samples in the year object
     * Parameters: int monthKey - index to reference a month
     * **********************************************************************
     */	
	public Month getMonthlySamplesByMonth( int monthKey )
	{		
		return this.monthlySamples.get(monthKey);
	}
	
	
	/**
     * **********************************************************************
     * Function: reset()
	 * Author: All 
	 * Description: resets the year object to a "fresh" state
     * Parameters: n/a
     * **********************************************************************
     */
	public void reset()
	{
		this.monthlySamples = new Hashtable<Integer, Month >();
		this.year = -1;
	}

}
