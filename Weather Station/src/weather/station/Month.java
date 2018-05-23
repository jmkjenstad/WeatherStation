package weather.station;

import java.util.*;

/************************************************************************
    Class:  Month
    Author: All
    Description: This class creates month data from the parsed xml data
    * that is read in to the program.
    Parameters: None
 ************************************************************************/
public class Month
{
        //private members and containers
	private int month;
	private int year;
	private Hashtable<Integer, Day> dailySamples;
	private Hashtable<Integer, ArrayList<Day> > weeklySamples;
	
	private String prevailingWindDir;	
	
	private Hashtable<Integer, WeekStats> weekStats;
	
	
	private Hashtable< Integer, String > weekPrevailingWindDir;
	
	private float meanTemp;
	private float highTemp;
	private String hTempDate;
	private String hTempTime;
	private float lowTemp;
	private String lTempDate;
	private String lTempTime;
	private float meanWind;
	private float maxWind;
	private String mWindDate;
	private String mWindTime;
	private float rainfall;
	
	/************************************************************************
            Function:  Month
            Author: All
            Description: This is the constructor that instantiates the containers
            Parameters: None
         ************************************************************************/
	public Month()
	{
		this.dailySamples = new Hashtable<Integer, Day>();
		this.weeklySamples = new Hashtable<Integer, ArrayList<Day> >();
		this.weekStats = new Hashtable< Integer, WeekStats>();
		this.month = -1;
		this.year = -1;
	}
	
        /************************************************************************
            Function:  Month
            Author: All
            Description: This is an overloaded constructor that instantiates 
            * the containers and certain data.
            Parameters: tempMonth - the month currently being handled
         ************************************************************************/
	public Month( Month tempMonth )
	{
		this.dailySamples = new Hashtable<Integer, Day>();
		this.weeklySamples = new Hashtable<Integer, ArrayList<Day> >();
		this.weekStats = new Hashtable< Integer, WeekStats>();
				
		this.year = tempMonth.getYear();
		this.month = tempMonth.getMonth();
		
		this.dailySamples.putAll( tempMonth.getAllDaySamples() );
		this.weeklySamples.putAll( tempMonth.getAllWeekSamples() ); 	

	}
	
        /************************************************************************
            Function:  getPrevailingWindDir
            Author: All
            Description: Getter to get the prevailing wind direction.
            Parameters: none
         ************************************************************************/
	public String getPrevailingWindDir()
	{
		return this.prevailingWindDir;
	}
	
        /************************************************************************
            Function:  getWeekPrevailingWindDir
            Author: All
            Description: Getter to get the prevailing wind direction for a week.
            Parameters: weekIndex - the index into the week data
         ************************************************************************/
	public String getWeekPrevailingWindDir( int weekIndex )
	{
		String dir = this.weekPrevailingWindDir.get( weekIndex );
		
		return dir;
		
	}
	
	/************************************************************************
            Function:  getSampleCount
            Author: All
            Description: Gets the number of samples in the dataset.
            Parameters: none
         ************************************************************************/
	public int getSampleCount()
	{
		int count = 0;
		Hashtable<Integer, Day> daas = this.getAllDaySamples();
		
		// for each day in the month
		for( int i = 1; i <= daas.size(); i++ )
		{
			Day day = daas.get(i);
			if( day == null )
			{
				continue;
			}
			
			count += day.getSampleCount();  // sum the number of samples in each day			
		}
		
		return count;
	}
	
	/************************************************************************
            Function:  getWeekStats
            Author: All
            Description: Getter to get the prevailing wind direction.
            Parameters: weekIndex - index to access week data
         ************************************************************************/
	public WeekStats getWeekStats( int weekIndex )
	{
		return this.weekStats.get(weekIndex);
	}
	
	
        /************************************************************************
            Function:  calcWeekStats
            Author: All
            Description: calculates all the needed statistics for a week's 
            * period of time.  Calculates min, max, and average temperature for 
            * the week. Calculates the average and high wind from the week.
            * Also calculates average wind wind direction. Finally calculates
            * total rainfall for the week.
            Parameters: none
         ************************************************************************/
	public void calcWeekStats()
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
		float windGust = 0.0f;
		
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
		
	    Hashtable<Integer, ArrayList<Day> > weeks = this.getAllWeekSamples();  
		
		for( int i = 1; i <= weeks.size(); i++ )  // for all weeks in the month
		{
			ArrayList<Day> week = weeks.get(i);
			
			WeekStats stats = new WeekStats();
			
			for( int j = 0; j < week.size(); j++ )  // for each day in the week
			{
				Day day = week.get(j);
				
				if( day == null )  // if day doesn't exist, skip it
				{
					continue;
				}
				
				ArrayList<wItem> samples = day.getSamples();
				
				for( wItem item : samples )
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
			
					rainSum += item.getRainfall();  // accumulate rainfall
					
					String windDir = item.getWinddirection();
			
					// calculate prevailing wind direction
					if( north.equals( windDir ) )
					{
						nCount += 1;
						
						if( nCount > maxCount )
						{
							maxCount = nCount;
							this.weekPrevailingWindDir.put( i, "N" );
						}
					}
					else if( east.equals( windDir ) )
					{
						eCount +=1;
						
						if( eCount > maxCount )
						{
							maxCount = eCount;
							this.weekPrevailingWindDir.put( i, "E" );
						}
					}
					else if( south.equals( windDir ) )
					{
						sCount += 1;
						
						if( sCount > maxCount )
						{
							maxCount = sCount;
							this.weekPrevailingWindDir.put( i, "S" );
						}
					}
					else if( west.equals( windDir ) )
					{
						wCount +=1;
						
						if( wCount > maxCount )
						{
							maxCount = wCount;
							this.weekPrevailingWindDir.put( i, "W" );
						}
					}
					else if( nEast.equals( windDir ) )
					{
						neCount += 1;
						
						if( neCount > maxCount )
						{
							maxCount = neCount;
							this.weekPrevailingWindDir.put( i, "NE" );
						}
					}
					else if( sEast.equals( windDir ) )
					{
						seCount += 1;
						
						if( seCount > maxCount )
						{
							maxCount = seCount;
							this.weekPrevailingWindDir.put( i, "SE" );
						}
					}
					else if( sWest.equals( windDir ) )
					{
						swCount += 1;
						
						if( swCount > maxCount )
						{
							maxCount = swCount;
							this.weekPrevailingWindDir.put( i, "SW" );
						}
					}
					else if( nWest.equals( windDir ) )
					{
						nwCount += 1;
						
						if( nwCount > maxCount )
						{
							maxCount = nwCount;
							this.weekPrevailingWindDir.put( i, "NW" );
						}
					}			
				}
				
				stats.setMaxTemp( maxTemp, maxTempDate, maxTempTime );
				stats.setMeanTemp( (float) tempSum / sampCount );
				stats.setLowTemp( minTemp, minTempDate, minTempTime );
				stats.setMeanWind( (float) windSum / sampCount );
				stats.setMaxWind( maxWind, windDate, windTime );
				stats.setRainfall( rainSum );								
				
			}
			
			this.weekStats.put( i, stats );
		}
		
		
	}
	
	/**
     * **********************************************************************
     * Function: calcStats()
	 * Author: All 
	 * Description: calculates avg temp, high/low temp, avg wind speed, 
	 * max wind gust, and accumulated rainfall for the month
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
		
		Hashtable<Integer, Day> days = this.getAllDaySamples();
		
		for( int i = 1; i <= days.size(); i++ )  // for each day in the month
		{
			Day daa = days.get( i );//dkey );
			
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
			
				if( temp > maxWind )
				{
					maxWind = temp;
					windDate = tempDate;
					windTime = tempTime;
				}
			
				rainSum += item.getRainfall();	

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
		
		this.setHighTemp( maxTemp, maxTempDate, maxTempTime );  // set the max temp for month
			
		this.setLowTemp( minTemp, minTempDate, minTempTime );  // set low temp
		
		this.setMaxWind( maxWind, windDate, windTime );  // set max wind speed
		
		this.setMeanWind( (float) windSum / sampCount );  // set avg wind speed
		
		this.setMeanTemp( (float) tempSum / sampCount ); // set avg temp speed 
		
		this.setRainfall( rainSum );  // set accumulated precipitation		
		
	}
	
	/************************************************************************
            Function:  getMeanTemp
            Author: All
            Description: Getter for mean temperature of a dataset
            Parameters: none
         ************************************************************************/
	public float getMeanTemp()
	{
		return this.meanTemp;
	}
	
        /************************************************************************
            Function:  setMeanTemp
            Author: All
            Description: Set the mean temperature for a dataset
            Parameters: avg - holds the mean data value
         ************************************************************************/
	public void setMeanTemp( float avg )
	{
		this.meanTemp = avg;
	}
	
        /************************************************************************
            Function:  getHighTemp
            Author: All
            Description: Getter for the high temperature of a dataset
            Parameters: none
         ************************************************************************/
	public float getHighTemp()
	{
		return this.highTemp;
	}
	
        /************************************************************************
            Function:  setHighTemp
            Author: All
            Description: Sets high temperature for a dataset
            Parameters: high - high temperature
            * date - date the high happened
            * time - time the high happened
         ************************************************************************/
	public void setHighTemp( float high, String date, String time )
	{
		this.highTemp = high;
		this.hTempDate = date;
		this.hTempTime = time;
	}
	
        /************************************************************************
            Function:  getHighTempDate
            Author: All
            Description: Gets date of the high temperature
            Parameters: none
         ************************************************************************/
	public String getHighTempDate()
	{
		return this.hTempDate;
	}
	
        /************************************************************************
            Function:  getHighTempTime
            Author: All
            Description: Gets time of the high temperature
            Parameters: none
         ************************************************************************/
	public String getHighTempTime()
	{
		return this.hTempTime;
	}
	
        /************************************************************************
            Function:  getLowTemp
            Author: All
            Description: Gets low temperature of a dataset
            Parameters: none
         ************************************************************************/
	public float getLowTemp()
	{
		return this.lowTemp;
	}
	
        /************************************************************************
            Function:  setLowTemp
            Author: All
            Description: Sets low temperature of a dataset
            Parameters: high - low temperature
            * date - date the low happened
            * time - time the low happened
         ************************************************************************/
	public void setLowTemp( float high, String date, String time )
	{
		this.lowTemp = high;
		this.lTempDate = date;
		this.lTempTime = time;
	}
	
        /************************************************************************
            Function:  getLowTempDate
            Author: All
            Description: Get the date of the low temp of a dataset
            Parameters: none
         ************************************************************************/
	public String getLowTempDate()
	{
		return this.lTempDate;
	}
	
        /************************************************************************
            Function:  getLowTempTime
            Author: All
            Description: Gets the time of the low temp of a dataset
            Parameters: none
         ************************************************************************/
	public String getLowTempTime()
	{
		return this.lTempTime;
	}
	
        /************************************************************************
            Function:  getMeanWind
            Author: All
            Description: Gets average wind speed of a dataset
            Parameters: none
         ************************************************************************/
	public float getMeanWind()
	{
		return this.meanWind;
	}
	
        /************************************************************************
            Function:  setMeanWind
            Author: All
            Description: set average wind speed for a dataset
            Parameters: avg - average wind speed
         ************************************************************************/
	public void setMeanWind( float avg )
	{
		this.meanWind = avg;
	}
	
        /************************************************************************
            Function:  getMaxWind
            Author: All
            Description: Gets maximum wind speed for a set of data
            Parameters: none
         ************************************************************************/
	public float getMaxWind()
	{
		return this.maxWind;
	}
	
        /************************************************************************
            Function:  setMaxWind
            Author: All
            Description: Sets maximum wind direction for a set of data
            Parameters:  max - maximum wind speed
            * date - date the maximum happened
            * time - time the maximum happened
         ************************************************************************/
	public void setMaxWind( float max, String date, String time )
	{
		this.maxWind = max;
		this.mWindDate = date;
		this.mWindTime = time;
	}
	
        /************************************************************************
            Function:  getMaxWindDate
            Author: All
            Description: Gets date the max wind happened
            Parameters: none
         ************************************************************************/
	public String getMaxWindDate()
	{
		return this.mWindDate;
	}
	
        /************************************************************************
            Function:  getMaxWindTime
            Author: All
            Description: Gets time the max wind happened
            Parameters: none
         ************************************************************************/
	public String getMaxWindTime()
	{
		return this.mWindTime;
	}
	
        /************************************************************************
            Function:  getRainfall
            Author: All
            Description: Gets total rainfall for a dataset
            Parameters: none
         ************************************************************************/
	public float getRainfall()
	{
		return this.rainfall;
	}
	
        /************************************************************************
            Function:  setRainfall
            Author: All
            Description: Sets rainfall total for a datset
            Parameters: none
         ************************************************************************/
	public void setRainfall( float rain )
	{
		this.rainfall = rain;
	}
	
	
	/************************************************************************
            Function:  getNumberOfDays
            Author: All
            Description: Gets number of days in sample set
            Parameters: none
         ************************************************************************/
	public int getNumberOfDays()
	{
		return this.dailySamples.size();
	}
	
	
	/************************************************************************
            Function:  setWeeklySamples
            Author: All
            Description: Sets the week long data sets
            Parameters: none
         ************************************************************************/
	public void setWeeklySamples()
	{
		
		int numOdays = this.dailySamples.size();  // number of days in the month
		
		ArrayList<Day> daze = new ArrayList<Day>();
		
		int weekIndex = 1;

		
		for( int i = 1; i <= numOdays; i++ )  // for each day in the month
		{
			Day daa = this.dailySamples.get(i);
			
			daze.add( daa );  // add the day to our week 
			
			boolean flag = false;  // flag tells us if there is a fifth week in the month
			
			if( i % 7 == 0 ) // if we have processed seven days, consider this a week
			{

				ArrayList<Day> temp = new ArrayList<Day>(daze);
				this.weeklySamples.put( weekIndex, temp );
				
				daze.clear(); 
				weekIndex += 1;
				flag = true;
			}
			
			if( i == numOdays && !flag ) // if we have processed the last day in the fifth week of a month
			{
				// add the last incomplete week to the week list
				this.weeklySamples.put( weekIndex, daze );
				
			}												
		}	

	}
	
	/************************************************************************
            Function:  getAllWeekSamples
            Author: All
            Description: gets all week long data samples
            Parameters: none
         ************************************************************************/
	public Hashtable<Integer, ArrayList<Day> > getAllWeekSamples()
	{
		return this.weeklySamples;
	}
	
        /************************************************************************
            Function:  getWeekOfSamples
            Author: All
            Description: Gets the week of samples from the given index
            Parameters: weekKey - week index of the data
         ************************************************************************/
	public ArrayList<Day> getWeekOfSamples( int weekKey )
	{
		return this.weeklySamples.get( weekKey );
	}
	
	/************************************************************************
            Function:  getMonth
            Author: All
            Description: Gets the month the data set is in
            Parameters: none
         ************************************************************************/
	public int getMonth()
	{
		return this.month;
	}
	
        /************************************************************************
            Function:  setMonth
            Author: All
            Description: Set data to given month
            Parameters:  tempMonth - month to change to
         ************************************************************************/
	public void setMonth( int tempMonth)
	{
		this.month = tempMonth;
	}
	
        /************************************************************************
            Function:  getYear
            Author: All
            Description: Gets year dataset is in
            Parameters: none
         ************************************************************************/
	public int getYear()
	{
		return this.year;
	}
	
        /************************************************************************
            Function:  setYear
            Author: All
            Description: Sets year of dataset
            Parameters: none
         ************************************************************************/
	public void setYear( int tempYear )
	{
		this.year = tempYear;
	}
	
        /************************************************************************
            Function:  setDailySamples
            Author: All
            Description: Sets data for day of sample
            Parameters: dayKey - day of the sample
            * dayOfSamples - data for a day of samples
         ************************************************************************/
	public void setDailySamples( int dayKey, Day dayOfSamples )
	{		
		this.dailySamples.put( dayKey, dayOfSamples );
	}
	
        /************************************************************************
            Function:  getAllDaySamples
            Author: All
            Description: Gets all samples from all days in dataset
            Parameters: none
         ************************************************************************/
	public Hashtable<Integer, Day> getAllDaySamples()
	{
		return this.dailySamples;
	}
	
        /************************************************************************
            Function:  getDayofSamples
            Author: All
            Description: Gets data from a single day of samples
            Parameters: none
         ************************************************************************/
	public Day getDayofSamples( int dayKey)
	{
		return this.dailySamples.get(dayKey);
	}
	
        /************************************************************************
            Function:  reset
            Author: All
            Description: sets all the data to null
            Parameters: none
         ************************************************************************/
	public void reset()
	{
		this.dailySamples = new Hashtable<Integer, Day>();
		this.month = -1;
		this.year = -1;
	}
	
	
}
